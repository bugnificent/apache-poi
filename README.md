# Excel Read/Write with Selenium & Apache POI

## Overview
This repository demonstrates how to read and write values in an Excel (`.xlsx`) sheet using **Apache POI** in a Selenium automation framework. The project follows the **Cucumber BDD** approach.

## Technologies Used
- **Java**
- **Selenium WebDriver**
- **Apache POI** (for Excel operations)
- **Cucumber BDD**
- **Maven**

## Setup
### Prerequisites
Ensure you have the following installed:
- **Java (JDK 8 or higher)**
- **Maven**
- **Selenium WebDriver**

### Dependencies (pom.xml)
Include the following dependencies in your `pom.xml`:

```xml
<!-- Apache POI for Excel Read/Write -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.4.0</version>
</dependency>

<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.4.0</version>
</dependency>
```

## Feature File (Cucumber BDD)
Below is the `.feature` file used for reading and writing Excel data in a Selenium test:

```gherkin
Feature: As an admin, I should be able to control Excel

  @excel
  Scenario: I need to read/write values
    When I read username and password "US01-AC01-TC03"
    Then test needs to pass "US01-AC02-TC01"
```

## How to Run
1. Clone the repository:
   ```sh
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```sh
   cd <project-folder>
   ```
3. Execute tests using Maven:
   ```sh
   mvn test
   ```

## Contribution
Feel free to fork the repository and submit pull requests for improvements.

## License
This project is licensed under the **MIT License**.


