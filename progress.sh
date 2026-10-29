#!/usr/bin/env bash
# Prints study progress from CHECKLIST.md and topic branches with unmerged work.
set -euo pipefail
cd "$(dirname "$0")"

bar() {  # bar <done> <total>
  local done=$1 total=$2 width=30 filled=0 i out="["
  if (( total > 0 )); then filled=$(( done * width / total )); fi
  for (( i = 0; i < width; i++ )); do
    if (( i < filled )); then out+="#"; else out+="-"; fi
  done
  printf '%s]' "$out"
}

echo "== Phases (CHECKLIST.md) =="
awk '
  /^## Phase /        { if (name) print name "|" d "|" t; name=substr($0,4); d=0; t=0; next }
  /^- \[[ x]\] \*\*[0-9]/ { t++; if ($0 ~ /^- \[x\]/) d++ }
  END                 { if (name) print name "|" d "|" t }
' CHECKLIST.md | while IFS='|' read -r name d t; do
  printf '%-44s %s %3d/%-3d\n' "$name" "$(bar "$d" "$t")" "$d" "$t"
done

echo
echo "== Handbooks =="
for m in $(ls -d [0-9][0-9]-*/ | tr -d /); do
  t=$(grep -cE "^- \[[ x]\] \*\*[0-9]+.*topic/$m/" CHECKLIST.md || true)
  d=$(grep -cE "^- \[x\] \*\*[0-9]+.*topic/$m/" CHECKLIST.md || true)
  printf '%-44s %s %3d/%-3d\n' "$m" "$(bar "$d" "$t")" "$d" "$t"
done

total=$(grep -cE '^- \[[ x]\] \*\*[0-9]' CHECKLIST.md || true)
done_=$(grep -cE '^- \[x\] \*\*[0-9]' CHECKLIST.md || true)
echo
printf 'TOTAL %s %d/%d (%d%%)\n' "$(bar "$done_" "$total")" "$done_" "$total" $(( total ? done_ * 100 / total : 0 ))

echo
echo "== In progress (commits not merged into master) =="
git branch --no-merged master --list 'topic/*' --format='  %(refname:short)' || true

echo
echo "== Next step =="
grep -m1 -E '^- \[ \] \*\*[0-9]' CHECKLIST.md | sed -E 's/^- \[ \] /  /' || echo "  All chapters done 🎉"
