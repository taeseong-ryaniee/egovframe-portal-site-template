#!/usr/bin/env bash
set -euo pipefail

# Generate MySQL schema DDL from Oracle DDL and write to DATABASE/mysql/all_pst_schema_mysql.sql
# Usage: bash scripts/gen_mysql_schema.sh

SRC=DATABASE/oracle/all_pst_ddl_oracle.sql
OUT=DATABASE/mysql/all_pst_schema_mysql.sql

if [[ ! -f "$SRC" ]]; then
  echo "Source not found: $SRC" >&2
  exit 1
fi

TMP=$(mktemp)

sed -E \
  -e 's/\r$//' \
  -e 's/DROP TABLE ([A-Z0-9_]+) CASCADE CONSTRAINTS;/DROP TABLE IF EXISTS \1;/' \
  -e 's/varchar2\(/varchar(/Ig' \
  -e 's/number\(([0-9]+)\)/decimal(\1,0)/Ig' \
  -e 's/number\(([0-9]+),([0-9]+)\)/decimal(\1,\2)/Ig' \
  -e 's/ DATE([ ,\n])/ datetime\1/Ig' \
  -e 's/ CLOB/ longtext/Ig' \
  -e 's/ BLOB/ longblob/Ig' \
  -e 's/ DEFAULT SYSDATE/ DEFAULT CURRENT_TIMESTAMP/Ig' \
  "$SRC" > "$TMP"

# Remove DROP lines (safety) and make CREATE TABLE idempotent
sed -E -i '' '/^DROP TABLE IF EXISTS /Id' "$TMP" 2>/dev/null || sed -E -i '/^DROP TABLE IF EXISTS /Id' "$TMP"
sed -E -i '' 's/^CREATE TABLE ([A-Z0-9_]+)/CREATE TABLE IF NOT EXISTS \1/I' "$TMP" 2>/dev/null || sed -E -i 's/^CREATE TABLE ([A-Z0-9_]+)/CREATE TABLE IF NOT EXISTS \1/I' "$TMP"

# Ensure statements end with semicolons and wrap FK checks, set names
{
  echo "SET NAMES utf8mb4;";
  echo "SET FOREIGN_KEY_CHECKS=0;";
  cat "$TMP";
  echo "SET FOREIGN_KEY_CHECKS=1;";
} > "$OUT"

rm -f "$TMP"
echo "Generated: $OUT"

