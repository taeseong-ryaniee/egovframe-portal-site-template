#!/usr/bin/env bash
set -euo pipefail

# Toggle admin menus visibility for anonymous user.
# Usage:
#   bash scripts/admin_menu_switch.sh enable  # expose admin menus to anonymous
#   bash scripts/admin_menu_switch.sh disable # hide admin menus from anonymous
# Optionally: host db user/pass
#   bash scripts/admin_menu_switch.sh enable mysql pst rootpw

ACTION=${1:-}
DB_HOST=${2:-mysql}
DB_NAME=${3:-pst}
DB_ROOT_PW=${4:-rootpw}

if [[ -z "${ACTION}" ]]; then
  echo "Usage: $0 <enable|disable> [DB_HOST] [DB_NAME] [ROOT_PW]" >&2
  exit 1
fi

case "${ACTION}" in
  enable)
    SQL=DATABASE/mysql/tools/anon_admin_menus_enable.sql;;
  disable)
    SQL=DATABASE/mysql/tools/anon_admin_menus_disable.sql;;
  *)
    echo "Unknown action: ${ACTION}. Use enable or disable" >&2
    exit 1;;
esac

echo "[ADMIN-MENU] Applying ${ACTION} using ${SQL} on ${DB_HOST}/${DB_NAME}"
mysql -h"${DB_HOST}" -uroot -p"${DB_ROOT_PW}" "${DB_NAME}" < "${SQL}"
echo "[ADMIN-MENU] Done."

