#!/usr/bin/env sh
# =============================================================================
# SanityCheck.sh
# Validates the running Calculator API container by sending a POST request
# and checking the response contains the expected result.
#
# With Traefik, the calc-api container is NOT published on a port.
# Instead, Traefik routes requests by path prefix on the jenkins-docker_jenkins
# network. We call http://traefik/group0/calculator/api/calculator/calculate.
#
# Usage (called from the Jenkins pipeline):
#   bash SanityCheck.sh
# =============================================================================

# Call via Traefik — reachable by Docker DNS name on jenkins-docker_jenkins network
API_URL="http://traefik/group0/calculator/api/calculator/calculate"
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
      -d "$PAYLOAD" 2>&1) && CURL_EXIT=0 || CURL_EXIT=$?

    if [ $CURL_EXIT -eq 0 ] && [ -n "$RESPONSE" ]; then
        break
    fi

    echo "Attempt $i/$MAX_RETRIES failed (curl exit $CURL_EXIT). Retrying in ${SLEEP_SEC}s..."
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
