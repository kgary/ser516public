#!/usr/bin/env sh
# =============================================================================
# SanityCheck.sh
# Validates the running Calculator API container by sending a POST request
# and checking the response contains the expected result.
#
# Traefik runs INSIDE DinD on port 80, exposed as port 9080 on DinD's network.
# This script runs inside the Jenkins container (on the `jenkins` docker network).
# Jenkins can reach DinD via its alias `docker` on that network.
# So we call http://docker:9080/... (DinD's port 9080 → Traefik port 80 → calc-api).
#
# Usage (called from the Jenkins pipeline):
#   bash SanityCheck.sh
# =============================================================================

# Call via Traefik: Jenkins container → DinD (docker:9080) → Traefik → calc-api
API_URL="http://docker:9080/group0/calculator/api/calculator/calculate"
PAYLOAD='{"a":2,"b":3,"operation":"+"}'
EXPECTED='"result":5.0'

echo "=== Calculator API Sanity Check ==="
echo "Calling: POST $API_URL"
echo "Payload: $PAYLOAD"

# Retry loop — wait up to 60 s for Spring Boot to be ready
MAX_RETRIES=12
SLEEP_SEC=5
RESPONSE=""

for i in $(seq 1 $MAX_RETRIES); do
    RESPONSE=$(curl -s \
      -X POST "$API_URL" \
      -H 'Content-Type: application/json' \
      -d "$PAYLOAD" 2>&1)
    CURL_EXIT=$?

    if echo "$RESPONSE" | grep -q "$EXPECTED"; then
        break
    fi

    echo "Attempt $i/$MAX_RETRIES — got: '$RESPONSE'. Retrying in ${SLEEP_SEC}s..."
    sleep $SLEEP_SEC
done

echo "Response: $RESPONSE"

if echo "$RESPONSE" | grep -q "$EXPECTED"; then
    echo "✔ Sanity check PASSED — API returned expected result 5.0 for 2 + 3"
    exit 0
else
    echo "✘ Sanity check FAILED — expected '$EXPECTED' in response"
    exit 1
fi
