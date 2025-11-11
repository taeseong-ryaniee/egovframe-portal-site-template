#!/bin/bash
set -euo pipefail

echo "[INIT] Preparing schema from Oracle DDL -> MySQL..."

SRC_DDL="/docker-entrypoint-initdb.d/src/all_pst_ddl_oracle.sql"
SRC_DATA="/docker-entrypoint-initdb.d/src/all_pst_data_mysql.sql"
OUT_DDL="/docker-entrypoint-initdb.d/01_schema_mysql.sql"
OUT_DATA="/docker-entrypoint-initdb.d/02_data_mysql.sql"

if [[ ! -f "$SRC_DDL" ]]; then
  echo "[INIT] Missing source DDL: $SRC_DDL" >&2
  exit 1
fi

if [[ ! -f "$SRC_DATA" ]]; then
  echo "[INIT] Missing source DATA: $SRC_DATA" >&2
  exit 1
fi

# Basic transform of Oracle-style DDL to MySQL-compatible DDL
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
  -e 's/CREATE OR REPLACE VIEW/CREATE VIEW IF NOT EXISTS/Ig' \
  "$SRC_DDL" > "$OUT_DDL"

# Ensure statements end with semicolons where needed (best-effort)
awk 'BEGIN{ORS=""} {print $0 "\n"} END{print "\n"}' "$OUT_DDL" > "$OUT_DDL.tmp" && mv "$OUT_DDL.tmp" "$OUT_DDL"

# Copy data script (contains MySQL-specific functions like NOW())
cp "$SRC_DATA" "$OUT_DATA"

echo "[INIT] Loading schema into database: $MYSQL_DATABASE"
mysql -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < "$OUT_DDL"

echo "[INIT] Loading seed data..."
mysql -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < "$OUT_DATA"

echo "[INIT] Done."
