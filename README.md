# SDET JUnit 5 Foundations

A Java test suite demonstrating core JUnit 5 skills for SDET roles.

## What this covers
- JUnit 5 annotations: @Test, @BeforeEach, @AfterEach, @DisplayName
- Parameterized tests with @ParameterizedTest and @ValueSource
- Nested test classes with @Nested
- Exception testing with assertThrows
- Happy path, negative, and boundary condition test cases

## Tech stack
- Java 17
- JUnit 5.10.0
- Maven

## How to run
```bash
mvn clean test
```

## Project structure
```
src/
├── main/java/com/banking/
│   └── BankAccount.java        # Class under test
└── test/java/com/banking/
    └── BankAccountTest.java    # Full test suite
```