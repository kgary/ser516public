# Unit Testing Examples

This directory contains Calculator applications in Java and Python, each with two variants — one demonstrating **good** test coverage and one demonstrating **bad** test coverage. All projects include Jenkins CI/CD pipelines.

## Projects

| Directory                 | Language  | Coverage  | Purpose                          |
|---------------------------|-----------|-----------|----------------------------------|
| `java-good-coverage/`     | Java      | High      | Comprehensive unit and BDD tests |
| `java-bad-coverage/`      | Java      | Low       | Intentionally insufficient tests |
| `python-good-coverage/`   | Python    | High      | Comprehensive unit and BDD tests |
| `python-bad-coverage/`    | Python    | Low       | Intentionally insufficient tests |

## Tools Used

| Purpose           | Java      | Python       |
|-------------------|-----------|--------------|
| Build / Deps      | Maven     | Poetry       |
| Unit Testing      | JUnit 5   | pytest       |
| BDD Testing       | Cucumber  | Behave       |
| Code Coverage     | JaCoCo    | Coverage.py  |
| Static Analysis   | SpotBugs  | Pylint       |
| CI/CD             | Jenkins   | Jenkins      |

## Getting Started

Each subdirectory has its own README with detailed setup and usage instructions.
