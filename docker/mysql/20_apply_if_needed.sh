#!/bin/bash
set -euo pipefail

echo "[DB-INIT] Checking and applying schema/data if needed..."

MYSQL_HOST=${MYSQL_HOST:-mysql}
MYSQL_DATABASE=${MYSQL_DATABASE:-pst}
MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD:-rootpw}

SRC_DDL="/init/src/all_pst_ddl_oracle.sql"
SRC_DATA="/init/src/all_pst_data_mysql.sql"
OUT_DDL="/tmp/01_schema_mysql.sql"
OUT_DATA="/tmp/02_data_mysql.sql"

until mysqladmin ping -h"${MYSQL_HOST}" -uroot -p"${MYSQL_ROOT_PASSWORD}" --silent; do
  echo "[DB-INIT] Waiting for MySQL at ${MYSQL_HOST}..."
  sleep 2
done

EXISTS=$(mysql -N -s -h"${MYSQL_HOST}" -uroot -p"${MYSQL_ROOT_PASSWORD}" -e "SHOW TABLES LIKE 'LETTNBBS';" "${MYSQL_DATABASE}" || true)
if [[ -n "${EXISTS}" ]]; then
  echo "[DB-INIT] Table LETTNBBS already exists. Skipping init."
  exit 0
fi

if [[ ! -f "${SRC_DDL}" ]] || [[ ! -f "${SRC_DATA}" ]]; then
  echo "[DB-INIT] Missing source files: ${SRC_DDL} or ${SRC_DATA}" >&2
  exit 1
fi

echo "[DB-INIT] Transforming Oracle DDL to MySQL compatible SQL..."
sed -E \
  -e 's/\r$//' \
  -e 's/DROP TABLE ([A-Z0-9_]+) CASCADE CONSTRAINTS;/DROP TABLE IF EXISTS \1;/' \
  -e 's/varchar2\(/varchar(/Ig' \
  -e 's/number\(([0-9]+),([0-9]+)\)/decimal(\1,\2)/Ig' \
  -e 's/ DATE([ ,\n])/ datetime\1/Ig' \
  -e 's/ CLOB/ longtext/Ig' \
  -e 's/ BLOB/ longblob/Ig' \
  -e 's/ DEFAULT SYSDATE/ DEFAULT CURRENT_TIMESTAMP/Ig' \
  -e 's/CREATE OR REPLACE VIEW/CREATE VIEW IF NOT EXISTS/Ig' \
  "${SRC_DDL}" > "${OUT_DDL}"

cp "${SRC_DATA}" "${OUT_DATA}"

echo "[DB-INIT] Loading schema into ${MYSQL_DATABASE}..."
mysql -h"${MYSQL_HOST}" -uroot -p"${MYSQL_ROOT_PASSWORD}" "${MYSQL_DATABASE}" < "${OUT_DDL}"

echo "[DB-INIT] Loading seed data into ${MYSQL_DATABASE}..."
mysql -h"${MYSQL_HOST}" -uroot -p"${MYSQL_ROOT_PASSWORD}" "${MYSQL_DATABASE}" < "${OUT_DATA}"

echo "[DB-INIT] Done."

