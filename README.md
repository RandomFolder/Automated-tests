# Task1
## Description
The task consists of 4 tests mentioned below.

### 1. LoginTest
Checks whether transitions between cards work properly in case everything is entered correctly.
### 2. AcceptCookiesTest
Checks whether huge red banner disappers after pressing "Not really, no".
### 3. HideHelpFormTest
Checks whether help form in the bottom right corner disappears after pressing "Send to bottom".
### 4. TimerTest
Checks whether timer always starts at 00:00:00.

## Running all tests one after another
1. Download java (https://www.oracle.com/cis/java/technologies/downloads/)
2. Download maven (https://maven.apache.org/download.cgi)
3. Clone this repo
4. Go to the directory of cloned repo (*/demo)
5. run this:
```bash
mvn test
```

## Running one test
1. Download java (https://www.oracle.com/cis/java/technologies/downloads/)
2. Download maven (https://maven.apache.org/download.cgi)
3. Clone this repo
4. Go to the directory of cloned repo (*/demo)
5. run this:
```bash
mvn -Dtest=<ClassName> test
```
