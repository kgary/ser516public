# Example_2 — Java Unit Testing with JUnit 5

## Overview

This example demonstrates a basic CI pipeline using **Maven** and **JUnit 5** integrated with **Jenkins**.  
The goal is to understand how unit tests are written in Java and how Jenkins automatically runs them on every commit.

## Tools Used

| Tool    | Purpose                                         |
|---------|-------------------------------------------------|
| Maven   | Build automation and dependency management      |
| JUnit 5 | Unit testing framework for Java                 |
| Jenkins | CI server that runs the pipeline on each commit |

## Project Structure

```
Example_2/
├── Jenkinsfile                              # Jenkins pipeline configuration
├── pom.xml                                  # Maven project configuration
├── README.md                                # This file
└── src/
    ├── main/
    │   └── java/
    │       └── com/example/
    │           └── Greeter.java             # Main application code
    └── test/
        └── java/
            └── com/example/
                └── GreeterTest.java         # JUnit unit tests
```

---

> **Important — Create Your Own Public Repo:**  
> Do NOT commit directly to the class repo. You must create your own **public** GitHub repository and work from there. Jenkins needs to access your repo to trigger builds, so it must be **public**.

---

## Step 1 — Prerequisites

Make sure you have the following installed:
- Java 21 or higher (`java -version`)
- Maven 3.6 or higher (`mvn -version`)

## Step 2 — Set Up Your Own Repository

> **Important:** Do NOT commit directly to the class repo. Follow the steps below carefully.

### 2.1 — Create your own public GitHub repository
1. Go to [github.com](https://github.com) and create a new repository (e.g. `asurite-ser516-assign2`)
2. Make sure it is set to **Public** so Jenkins can access it
3. Clone your own repo to your local machine:
```bash
git clone <your-repo-url>
cd asurite-ser516-assign2
```

### 2.2 — Clone the class repo separately
Open a new terminal window and clone the class repo:
```bash
git clone https://github.com/kgary/ser516public
```
This will create a folder (e.g. `ser516public`) on your machine with all the example files for the class. Alternatively you could choose to Download the repo form GitHub in your browser, but then you can not do pulls later to get class update. 

### 2.3 — Copy Example_2 into your own repo
Make sure you are inside your own repo folder, then run:
```bash
# You should be inside asurite-ser516-assign2 when you run this
cp -r <local_path_to_cloned_class_repo>/ser516public/project-tools/jenkins-examples/Example_2 .
```
The `.` at the end means "copy into the current directory". 

For example, if you cloned the class repo at `Desktop/516/ser516public`, the command would be:
```bash
cp -r ~/Desktop/516/ser516public/project-tools/jenkins-examples/Example_2 .
```

After this, your repo should look like:
```
asurite-ser516-assign2/
└── Example_2/
    ├── Jenkinsfile
    ├── pom.xml
    ├── README.md
    └── src/
```
Note: When you copy over to your local repo folder, make sure you do not have any hidden .git directories brought over from the ser516public repo. Do
```
mac$ ls -la
dos> dir /A
```

If the folder is there, remove it. You can then add, commit and push the folder to your personal GitHub account (a new .git folder is created).

### 2.4 — Verify the Jenkinsfile
> **Important:** Open `Example_2/Jenkinsfile` and make sure the `dir()` block says `Example_2` or the directory to where Example_2 exists on your local, and NOT the full class repo path. It should look like this:
> ```groovy
> dir('Example_2') {
>     sh 'mvn clean compile'
> }
> ```
> If it says `dir('project-tools/jenkins-examples/Example_2')`, update it to `dir('Example_2')` or the directory to where Example_2 exists on your local before pushing. This is the most common mistake and will cause the pipeline to fail with `No POM in this directory`.

### 2.5 — Commit and push to your own repo
```bash
git add .
git commit -m "Add Example_2"
git push
```

## Step 3 — Build and Run the Tests Locally

Navigate into the Example_2 folder:
```bash
cd Example_2
```

First, compile the main source code:
```bash
mvn clean compile
```

You should see:
```
[INFO] BUILD SUCCESS
```

Then run the tests (this compiles the test code and runs them):
```bash
mvn test
```

You should see:
```
testGreet -> Expected: 'Hello, Alice!'  Got: 'Hello, Alice!'
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Step 4 — Understanding the Code

**Greeter.java** has three methods:
- `greet(name)` → returns `"Hello, <n>!"`
- `greetLoud(name)` → returns `"HELLO, <n>!"`
- `greetFormal(name)` → returns `"Good day, <n>."`

**GreeterTest.java** has one provided test for `greet()`. It prints the expected vs actual value so you can see what the test is doing. Open the file and read the comments for hints on adding your own test.

## Step 5 — Set Up the Jenkins Pipeline

> **Note:** Use `https://swent0linux.asu.edu/jenkins` for the class Jenkins server. If you have trouble accessing it, you can run Jenkins locally on your machine — refer to the course material for local Jenkins setup instructions.

1. Log in to Jenkins
2. Navigate to your group folder -> you would probably be inside your respective groups when you login(check left top corner, you will be able to see your group number).
3. Click **New Item** → name it `Example_2` → select **Pipeline** → click OK
4. Under **Pipeline**, set **Definition** to `Pipeline script from SCM`
5. Set **SCM** to `Git` and enter your own repository URL
6. Under **Branches to build**, enter the branch name of your own repository (e.g. `*/main` or `*/master`)
7. Set **Script Path** to `Example_2/Jenkinsfile`
8. Under **Triggers**, check **Poll SCM** and set the schedule to `* * * * *` (polls every minute)
9. Click **Save** then click **Build Now** to trigger the first build

> **Important — Maven Installation Check:**  
> Before pushing, go to **Manage Jenkins → Tools → Maven installations** and check the following:
> - **If no Maven installation exists** → Click **Add Maven**, give it a name (e.g. `Maven`), select a version, and click **Save**
> - **If a Maven installation already exists** → Note the exact name it is saved under
>
> Then open the `Jenkinsfile` and make sure the name matches exactly:
> ```groovy
> tools {
>     maven 'Maven'  // Replace 'Maven' with your exact installation name
> }
> ```
> If the name does not match, the pipeline will fail with `mvn: not found`.

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
testGreet -> Expected: 'Hello, Alice!'  Got: 'Hello, Alice!'
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

You can also click **Test Result** on the build page for a structured view of all tests run.

## Step 8 (Optional) — Add Your Own Test

Open `GreeterTest.java` and uncomment the optional section at the bottom.  
Try writing a test for `greetFormal()` or `greetLoud()` and push again to see the pipeline re-run with your new test.
