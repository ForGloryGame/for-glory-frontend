#!/usr/bin/env bash
curl -fsSL https://bun.sh/install | bash
BUN_INSTALL="/home/runner/.bun"
echo "$BUN_INSTALL/bin" >>$GITHUB_PATH
