from flask import Flask, jsonify
import requests

app = Flask(__name__)

@app.route('/')
def home():
    try:
        # We use the service name 'java-api' defined in docker-compose.yaml
        # because containers on the same network can resolve each other by name.
        response = requests.get("http://java-api:8080/api/data")
        data = response.json()
        return f"<h1>Flask Frontend</h1><p>Data from Java API: {data['message']}</p>"
    except Exception as e:
        return f"<h1>Error</h1><p>Could not connect to Java API: {str(e)}</p>"

if __name__ == "__main__":
    # Host 0.0.0.0 allows the container to accept external connections
    app.run(host="0.0.0.0", port=5000)