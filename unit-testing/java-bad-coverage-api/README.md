# java-bad-coverage-api

A **Spring Boot RESTful Calculator API** derived from `java-bad-coverage`.  
Intentionally retains code smells and minimal test coverage for **SER-516** teaching purposes.

---

## API Reference

**Endpoint:** `POST /api/calculator/calculate`

**Request body (JSON):**
```json
{
  "a": 10,
  "b": 2,
  "operation": "/"
}
```

| Field | Type | Values |
|---|---|---|
| `a` | number | any double |
| `b` | number | any double |
| `operation` | string symbol | `"+"` `"-"` `"*"` `"/"` |

**Success response (HTTP 200):**
```json
{ "result": 5.0, "operation": "/", "error": null }
```

**Error response (HTTP 400)** — division by zero or unknown symbol:
```json
{ "result": 0.0, "operation": "/", "error": "Cannot divide by zero" }
```

---

## Quick Start (local)

```bash
# Build & run via Maven + Docker
mvn package -DskipTests
docker build -t java-bad-coverage-api:local .
docker run -d --name calc-api -p 8080:8080 java-bad-coverage-api:local

# Test it
curl -X POST http://localhost:8080/api/calculator/calculate \
  -H 'Content-Type: application/json' \
  -d '{"a":6,"b":7,"operation":"*"}'
# → {"result":42.0,"operation":"*","error":null}
```

---

## Stopping the Container

The Jenkins pipeline leaves the container running after the Sanity Check.  
To stop and remove it manually:

### Option A — From inside `jenkins-server` (DOCKER_HOST already points to DinD)
```bash
docker stop calc-api && docker rm calc-api
```

### Option B — From the Ubuntu host via `jenkins-docker`
```bash
docker exec -it jenkins-docker docker stop calc-api
docker exec -it jenkins-docker docker rm calc-api
```

---

## DinD Architecture

See [`DIND_ARCHITECTURE.md`](../java-bad-coverage/DIND_ARCHITECTURE.md) in the sibling project for the full two-container DinD setup documentation.

**No changes are required to `jenkins-docker` (the DinD sidecar).**  
If `curl` is missing from `jenkins-server`, add this to its Dockerfile:
```dockerfile
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
```

---

## Jenkins Pipeline Stages

| Stage | What It Does |
|---|---|
| Checkout | Clones the repo |
| Build | `mvn clean compile` |
| Test | `mvn verify` (JUnit 5 + Cucumber + JaCoCo) |
| SpotBugs Analysis | Static analysis |
| Package | `mvn package -DskipTests` |
| Docker Build | Builds multi-stage image, tags as `latest` |
| Sanity Check | Starts `calc-api` container, calls `SanityCheck.sh`, **container stays running** |
| Cleanup | Removes the Docker *image* only (container persists) |

---

## Running Tests Locally

```bash
mvn verify        # compile + unit tests + Cucumber + JaCoCo coverage check
mvn spotbugs:spotbugs  # static analysis
```

Coverage reports: `target/site/jacoco/index.html`  
Cucumber reports: `target/cucumber-reports/cucumber.html`
