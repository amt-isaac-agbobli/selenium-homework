# Homework Project

This project is about automating Google Search using Selenium WebDriver. The project is written in Java and uses Maven for dependency management.

## Main Task

The main task of this project is to:

1. Open the Google main page (https://www.google.com/)
2. Verify that the search input field is available
3. Enter "selenium" in the search input
4. Submit the search

The code for this task is implemented in the `GoogleHomePage` class and the `GoogleSearchHistoryTest` class.

## Optional Homework

The optional homework for this project is to:

1. Open the Google main page (https://www.google.com/)
2. Perform a number of searches ("selenium", "webdriver", "xpath")
3. Refresh the search page
4. Invoke the search history (click in the empty input)
5. Get a list of search history entries
6. Verify that these entries include the "selenium" search query

The code for this task is implemented in the `GoogleSearchHistoryTest` class.

## Running the Tests

To run the tests, you can use the following command in your terminal:

```bash
mvn test
```

Please ensure that you have Maven installed and correctly set up in your PATH.

## Dependencies

This project uses the following dependencies:

- Selenium WebDriver
- WebDriverManager
- TestNG

These dependencies are managed by Maven and are defined in the `pom.xml` file.

## Note

Please ensure that you have the correct version of Chrome installed on your machine as the tests use ChromeDriver to automate the browser. The version of ChromeDriver is managed by WebDriverManager and should match the version of Chrome installed on your machine.