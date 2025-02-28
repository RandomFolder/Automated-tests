# Reporting Portal API
## Description
The task includes only one test (ReportingPortalTest) which checks whether different actions (project creation, test uploading etc.) work properly.

## Files, required for the test
Add user_data.json (demo\src\test\resources\user_data.json)

user_data.json has to look this way:
```json
{
    "login": "<your login>",
    "password": "<your password>"
}
```

## Running test
1. Download java (https://www.oracle.com/cis/java/technologies/downloads/)
2. Download maven (https://maven.apache.org/download.cgi)
3. Clone this repo
4. Go to the directory of cloned repo (*/demo)
5. run this:
```bash
mvn test
```