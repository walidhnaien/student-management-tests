# Student Management UI Tests

Shareable Cucumber/Selenium test project for the Student Management System.
This project is maintained separately from both `student-qa-agent` and the
application repository.

## Stack

- Java 17
- Maven
- Cucumber / Gherkin
- Selenium WebDriver
- JUnit Platform
- Page Object Model

## Prerequisites

1. Start the application and its required services using the instructions in
   the [application repository](https://github.com/walidhnaien/student-management-system).
2. Confirm the frontend is available at `http://localhost:4200`.
3. Install Java 17, Maven 3.9+, and Google Chrome.

Selenium Manager resolves the browser driver. The test uses real UI behavior;
it does not install an API mock.

## Run

From this repository root:

```powershell
mvn clean test
```

Headless Chrome:

```powershell
mvn clean test -Dheadless=true
```

Override the frontend URL:

```powershell
mvn clean test -DbaseUrl=http://localhost:4200
```

The Cucumber reports are written under `target/`.

## Structure

```text
src/test/java/com/training/student/
├── hooks/
├── pages/
├── runners/
├── steps/
└── support/

src/test/resources/
├── features/
└── junit-platform.properties
```

The first scenario automates the student-creation acceptance criterion for
Jira Story STUA1-1. Test data uses a unique synthetic email address.

## Per-Story Git workflow

Create a Jira-key branch and run the tests before committing:

```powershell
git checkout -b test/STUA1-1
mvn clean test
git status --short
git diff --check
git add .
git diff --cached --check
git diff --cached
git commit -m "STUA1-1 Add automated test for student creation"
```

Review `git status` before `git add .` and inspect the staged diff before
committing. Only commit intended test-project changes. Build output under
`target/` is ignored. Push the branch only as a separate, explicitly intended
step.
