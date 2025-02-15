# Excel Automation with Apache POI, Cucumber BDD, and Selenium 🚀

This project demonstrates how to read and write Excel files (`.xlsx`) using **Apache POI**, integrated with **Cucumber BDD** and **Selenium** for automated testing. It allows you to read values from an Excel file, perform actions based on the data, and write back the results (Pass/Fail) into the Excel file.

---

## 🛠️ Technologies Used
- **Apache POI**: For reading and writing Excel files.
- **Cucumber BDD**: For behavior-driven development and test automation.
- **Selenium**: For browser automation and UI testing.
- **Maven**: For dependency management and project build.

---

## 📋 Prerequisites
- Java JDK 8 or higher
- Maven installed
- ChromeDriver or any other WebDriver executable
- An Excel file (`.xlsx`) for test data

---

## ⚙️ Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
2. **Install Dependencies**:
```xml
   <dependencies>
        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.28.1</version>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.20.1</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.20.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.4.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.4.0</version>
        </dependency>
   <dependencies>
```
### ⚠️ Before Running Tests
- Make sure Test Case Number at the beginning of the Scenario.(Not space sensitive)

## 📝 Example Feature File
```gherkin
Feature: As an admin i should be able to control excel

  @excel
  Scenario: US01-AC01-TC01 I need to read/write values
    When i read username and password "US01-AC01-TC01"
    Then test needs to pass
```

## 🚀 Running the Tests
- Run Cucumber Tests:
- Use the following command to execute the Cucumber tests:

```bash
mvn test
```
Check Results:
After the tests run, the results (Pass/Fail) will be written back to the Excel file in the specified column.

## 📜 License
This project is licensed under the MIT License. See the LICENSE file for details.

## 🙌 Contributing
Feel free to open issues or submit pull requests for improvements. Contributions are welcome!

