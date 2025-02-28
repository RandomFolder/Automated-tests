# Yandex Disk API
## Description
The task includes only one test (YandexDiskTest) which checks whether different actions (file uploading, renaming, deletion) work properly.

## Files, required for the test
Add token.txt (demo\src\test\resources\token.txt) and user_data.json (demo\src\test\resources\user_data.json)

token.txt must contain the OAuth token.
user_data.json has to look this way:
```json
{
    "email": "<your email>",
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
