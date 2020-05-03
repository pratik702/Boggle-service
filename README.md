# Boggle-service

**Steps to setup:**

1. Download wildfly from https://wildfly.org/downloads/
2. Clone this project.
3. Open command prompt and traverse to project directory.
4. Run the following command:
        mvn clean install -DskipTests
5. Copy the war file from boggle-client/target directory.
6. Paste it in <wildfly_directory>/standalone/deployments
7. Run <wildfly_directory>/bin/standalone.bat

**Provided APIs:**

1. Get list of random alphabets:
    **http://localhost:8080/boggle-service/alphabetWordList/4**
    Note: Here <n> is the size of the board. You may use 1,2,3,4,... sizes as per need.
    
2. Validate a word:
   **http://localhost:8080/boggle-service/validateWord/apple**