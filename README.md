# Junit-Testing

A small Java project demonstrating unit testing with **JUnit 5**, built around a simple in-memory `CustomerService`. It covers common patterns like CRUD operations, custom exceptions, and test lifecycle hooks (`@BeforeEach` / `@AfterEach`).

## Features

- In-memory customer management (add, retrieve, delete, list, total balance)
- Custom unchecked exceptions for domain-specific error handling
- Builder pattern for object creation via Lombok (`@Builder`, `@Data`)
- A full JUnit 5 test suite covering both success and failure scenarios

## Tech Stack

- **Java 25**
- **Maven** for build and dependency management
- **JUnit 5 (Jupiter)** for testing
- **Lombok** to reduce boilerplate (getters/setters, constructors, builder)

## Project Structure

```
Junit-Testing/
├── pom.xml
└── src/
    ├── main/java/
    │   ├── App.java
    │   ├── model/
    │   │   └── Customer.java
    │   ├── service/
    │   │   └── CustomerService.java
    │   └── exception/
    │       ├── CustomerExistsException.java
    │       └── CustomerNotFoundException.java
    └── test/java/org/example/
        └── AppTest.java
```

## Prerequisites

- JDK 25 (or update the `<source>` / `<target>` values in `pom.xml` to match your installed JDK)
- Maven 3.6+

## Getting Started

Clone the repository:

```bash
git clone https://github.com/kota64453/Junit-Testing.git
cd Junit-Testing
```

Build the project:

```bash
mvn clean install
```

## Running the Tests

```bash
mvn test
```

This runs the full `AppTest` suite, which verifies:

- Adding a new customer succeeds
- Adding a duplicate customer throws `CustomerExistsException`
- Retrieving a customer by a valid ID returns the correct customer
- Retrieving a customer by an invalid ID throws `CustomerNotFoundException`
- Deleting a customer by a valid ID removes them from the list
- Deleting a non-existent customer throws `CustomerNotFoundException`
- Listing all customers returns the expected count
- Calculating the total balance sums correctly across all customers

## Core Classes

**`Customer`** — a simple data model (id, name, email, balance) built with Lombok annotations.

**`CustomerService`** — holds an in-memory list of customers and exposes:
- `addCustomer(Customer)`
- `getAllCustomers()`
- `getCustomerById(int)`
- `deleteCustomer(int)`
- `getTotalBalance()`

**Exceptions** — `CustomerExistsException` and `CustomerNotFoundException` are thrown for invalid operations (duplicate IDs, missing customers).

## License

This project currently has no license specified. Feel free to add one (e.g., MIT) if you intend others to reuse this code.
