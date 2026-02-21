# Example_3 — Python Unit Testing with pytest

## Overview

This example demonstrates a basic CI pipeline using **Poetry** and **pytest** integrated with **Jenkins**.  
The goal is to understand how unit tests are written in Python and how Jenkins automatically runs them on every commit.

## Tools Used

| Tool    | Purpose                                          |
|---------|--------------------------------------------------|
| Poetry  | Dependency management and virtual environments   |
| pytest  | Unit testing framework for Python                |
| Jenkins | CI server that runs the pipeline on each commit  |

## Project Structure

```
Example_3/
├── Jenkinsfile                  # Jenkins pipeline configuration
├── pyproject.toml               # Poetry project configuration
├── README.md                    # This file
├── src/
│   └── greeter/
│       ├── __init__.py
│       └── greeter.py           # Main application code
└── tests/
    ├── __init__.py
    └── test_greeter.py          # pytest unit tests
```

---

> **Important — Create Your Own Public Repo:**  
> Do NOT commit directly to the class repo. You must create your own **public** GitHub repository and work from there. Jenkins needs to access your repo to trigger builds, so it must be **public**.

---

## Step 1 — Prerequisites

Make sure you have the following installed:
- Python 3.8 or higher (`python3 --version`)
- Poetry (`poetry --version`) — install via `pip3 install poetry` if missing

> **Note (Mac Terminal only):** After installing Poetry via pip, it may not be on your PATH. If `poetry --version` returns command not found, open your Mac Terminal and run:
> ```bash
> export PATH="/Users/<your-username>/Library/Python/3.9/bin:$PATH"
> ```
> To make this permanent (so you do not have to run it every time you open a new terminal):
> ```bash
> echo 'export PATH="/Users/<your-username>/Library/Python/3.9/bin:$PATH"' >> ~/.bash_profile
> source ~/.bash_profile
> ```

## Step 2 — Set Up Your Own Repository

> **Important:** Do NOT commit directly to the class repo. Follow the steps below carefully.

### 2.1 — Create your own public GitHub repository
1. Go to [github.com](https://github.com) and create a new repository (e.g. `my-ser516-ica`)
2. Make sure it is set to **Public** so Jenkins can access it
3. Clone your own repo to your local machine:
```bash
git clone <your-repo-url>
cd my-ser516-ica
```

### 2.2 — Clone the class repo separately
Open a new terminal window and clone the class repo:
```bash
git clone -b jenkins-examples <class-repo-url>
```
This will create a folder (e.g. `ser516public`) on your machine with all the example files.

### 2.3 — Copy Example_3 into your own repo
Make sure you are inside your own repo folder, then run:
```bash
# You should be inside my-ser516-ica when you run this
cp -r <local_path_to_cloned_class_repo>/ser516public/project-tools/jenkins-examples/Example_3 .
```
The `.` at the end means "copy into the current directory". 

For example, if you cloned the class repo at `Desktop/516/ser516public`, the command would be:
```bash
cp -r ~/Desktop/516/ser516public/project-tools/jenkins-examples/Example_3 .
```

After this, your repo should look like:
```
my-ser516-ica/
└── Example_3/
    ├── Jenkinsfile
    ├── pyproject.toml
    ├── README.md
    └── src/
```
### 2.4 — Verify the Jenkinsfile
> **Important:** Open `Example_2/Jenkinsfile` and make sure the `dir()` block says `Example_3` or the directory to where Example_2 exists on your local, and NOT the full class repo path. It should look like this:
> ```groovy
> dir('Example_3') {
>     sh 'mvn clean compile'
> }
> ```
> If it says `dir('project-tools/jenkins-examples/Example_2')`, update it to `dir('Example_3')` or the directory to where Example_2 exists on your local before pushing. This is the most common mistake and will cause the pipeline to fail with `No POM in this directory`.

### 2.5 — Commit and push to your own repo
```bash
git add .
git commit -m "Add Example_3"
git push
```

## Step 3 — Install Dependencies and Run Tests Locally

Navigate into the Example_3 folder:
```bash
cd Example_3
```

Install dependencies:
```bash
poetry install
```

You should see:
```
Installing the current project: greeter (0.1.0)
```

Run the tests:
```bash
poetry run pytest tests/ -v -s
```

You should see:
```
tests/test_greeter.py::TestGreeter::test_greet test_greet -> Expected: 'Hello, Alice!'  Got: 'Hello, Alice!'
PASSED
1 passed in 0.00s
```

## Step 4 — Understanding the Code

**greeter.py** has three methods:
- `greet(name)` → returns `"Hello, <n>!"`
- `greet_loud(name)` → returns `"HELLO, <n>!"`
- `greet_formal(name)` → returns `"Good day, <n>."`

**test_greeter.py** has one provided test for `greet()`. It prints the expected vs actual value so you can see what the test is doing. Open the file and read the comments for hints on adding your own test.

## Step 5 — Set Up the Jenkins Pipeline

> **Note:** Use `https://swent0linux.asu.edu/jenkins` for the class Jenkins server. If you have trouble accessing it, you can run Jenkins locally on your machine — refer to the course material for local Jenkins setup instructions.

1. Log in to Jenkins
2. Navigate to your group folder -> you would probably be inside your respective groups when you login(check left top corner, you will be able to see your group number).
3. Click **New Item** → name it `Example_3` → select **Pipeline** → click OK
4. Under **Pipeline**, set **Definition** to `Pipeline script from SCM`
5. Set **SCM** to `Git` and enter your own repository URL
6. Under **Branches to build**, enter the branch name of your own repository (e.g. `*/main` or `*/master`)
7. Set **Script Path** to `Example_3/Jenkinsfile`
8. Under **Triggers**, check **Poll SCM** and set the schedule to `* * * * *` (polls every minute)
9. Click **Save** then click **Build Now** to trigger the first build

> **Important — Docker Agent:**  
> The Jenkinsfile uses a Docker agent (`python:3`) to run the pipeline inside a Python container. This means Jenkins must have Docker available and running. If the build fails with a Docker-related error, check with your instructor.  
> On the first run, Jenkins will pull the `python:3` image from Docker Hub which may take a minute or two. Subsequent builds will be faster.

## Step 6 — Trigger the Pipeline with a Commit

Make a small change (e.g., add a comment), commit, and push:

```bash
git add .
git commit -m "Trigger pipeline"
git push
```

Within a minute, Jenkins will detect the change and run the pipeline automatically.

## Step 7 — View Test Results

In Jenkins, navigate to your build and click **Console Output** to see the full output including:
```
test_greet -> Expected: 'Hello, Alice!'  Got: 'Hello, Alice!'
PASSED
1 passed in 0.00s
```

## Step 8 (Optional) — Add Your Own Test

Open `test_greeter.py` and uncomment the optional section at the bottom.  
Try writing a test for `greet_formal()` or `greet_loud()` and push again to see the pipeline re-run with your new test.
