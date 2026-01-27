# 1. Use a lightweight Python runtime as a base image
# This follows the principle of "minimal resource requirements" [4].
FROM python:3.9-slim

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the requirements file and install dependencies
# This uses the container's "Package Manager" to prepare the environment [2].
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# 4. Copy the application code into the container
# This bundles "the code" into the image [2].
COPY app.py .

# 5. Define the command to run the application
# This makes the package "executable" [4].
ENTRYPOINT ["python", "app.py"]