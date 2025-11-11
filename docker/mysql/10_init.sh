#!/bin/bash
set -euo pipefail

echo "[INIT] Preparing schema from MySQL DDL..."

SRC_DDL="/docker-entrypoint-initdb.d/src/all_pst_ddl_mysql.sql"
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

# Use provided MySQL DDL/DATA as-is (normalize CRLF -> LF)
sed -E 's/\r$//' "$SRC_DDL" > "$OUT_DDL"
sed -E 's/\r$//' "$SRC_DATA" > "$OUT_DATA"

echo "[INIT] Loading schema into database: $MYSQL_DATABASE"
mysql -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < "$OUT_DDL"

echo "[INIT] Loading seed data..."
mysql -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < "$OUT_DATA"

echo "[INIT] Done."
