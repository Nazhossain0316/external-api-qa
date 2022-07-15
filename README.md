How to Run the automated tests:


- Run a test cases for a single endpoint:
  - 
       ./gradlew test --tests *nameOfTestMethod -Denv=[test, dev]

- Run all the test cases listed in the testng.xml file:
  - 
       ./gradlew test -Denv=[test, dev]