#!/usr/bin/env sh
# =============================================================================
# SanityCheck.sh
# Validates the running Calculator API container by sending a POST request
# and checking the response contains the expected result.
#
# Usage (run from within the Jenkins pipeline or DinD shell):
#   bash SanityCheck.sh
#
# The container must already be running on port 8080 before this script runs.
# =============================================================================

set -e

API_URL="http://localhost:8080/api/calculator/calculate"
PAYLOAD='{"a":2,"b":3,"operation":"+"}'
EXPECTED='"result":5.0'

echo "=== Calculator API Sanity Check ==="
echo "Calling: POST $API_URL"
echo "Payload: $PAYLOAD"

RESPONSE=$(curl -sf \
  -X POST "$API_URL" \
  -H 'Content-Type: application/json' \
  -d "$PAYLOAD")

echo "Response: $RESPONSE"

if echo "$RESPONSE" | grep -q "$EXPECTED"; then
  echo "✔ Sanity check PASSED — API returned expected result 5.0 for 2 + 3"
  exit 0
else
  echo "✘ Sanity check FAILED — expected '$EXPECTED' in response"
  exit 1
fi
