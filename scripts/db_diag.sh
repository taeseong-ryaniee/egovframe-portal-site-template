#!/usr/bin/env bash
set -euo pipefail

# Simple DB sanity check runner.
# Usage: bash scripts/db_diag.sh [DB_HOST] [DB_NAME] [ROOT_PASSWORD]

DB_HOST=${1:-mysql}
DB_NAME=${2:-pst}
DB_ROOT_PW=${3:-rootpw}

echo "[DB-DIAG] Host=${DB_HOST} DB=${DB_NAME}"

mysql -h"${DB_HOST}" -uroot -p"${DB_ROOT_PW}" "${DB_NAME}" < DATABASE/diagnostics/diag.sql

echo "[DB-DIAG] Done."

