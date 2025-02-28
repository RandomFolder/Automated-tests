# Databases
## Description
The task consists of 2 tests described below.

### 1. AddToDBTest
Adds data about tests to test table in database.
### 2. UpdateDBTest.java
Adds copies of randomly selected tests to test table, updates them and then removes.

## Additional files
To make tests work you'll alse need to add db_connection_data.json by the path: demo\src\test\resources\db_connection_data.json

The content of the file looks the following way:
```json
{
    "url": "<path to database>",
    "user": "<username>",
    "password": "<password>"
}
```

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
