# Liquibase MongoDB

[![Java CI with Maven](https://github.com/hendisantika/liquibase-mongodb/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/liquibase-mongodb/actions/workflows/maven.yml)

Sample Spring Boot application that manages MongoDB schema and seed data with
[Liquibase](https://www.liquibase.com/) using the
[liquibase-mongodb](https://github.com/liquibase/liquibase-mongodb) extension.

On startup, the application runs the Liquibase changelogs against MongoDB (creating the
`customers` collection and seeding it), then exposes a REST endpoint to read the customers.

## Tech Stack

- Java 25
- Spring Boot 3.5.16 (Web, Data MongoDB, Docker Compose support)
- Liquibase Core & Liquibase MongoDB extension 4.24.0
- MongoDB 8.0.4
- Lombok
- Maven (wrapper included)

## Project Structure

```
src/main/java/id/my/hendisantika/liquibasemongodb
├── LiquibaseMongodbApplication.java
├── config
│   ├── LiquibaseMongoDBConfig.java            # Runs Liquibase update via CommandScope API (on startup)
│   └── LiquibaseOldVersionMongoDBConfig.java  # Legacy Liquibase API example (lazy bean)
└── customer
    ├── Customer.java
    ├── CustomerController.java
    ├── CustomerRepository.java
    └── CustomerService.java

src/main/resources
├── application.properties
└── db-json
    ├── master.json                 # Master changelog
    ├── 00_initial-structure.json   # Drop & create `customers` collection
    └── 01_initial-data.json        # Seed customer documents
```

## Prerequisites

- JDK 25
- Docker & Docker Compose

## Getting Started

1. Clone the repository

   ```bash
   git clone https://github.com/hendisantika/liquibase-mongodb.git
   cd liquibase-mongodb
   ```

2. Start MongoDB

   ```bash
   docker compose up -d
   ```

   > Spring Boot Docker Compose support also starts `compose.yaml` automatically when you run the app.

3. Run the application

   ```bash
   ./mvnw spring-boot:run
   ```

4. Get all customers

   ```bash
   curl http://localhost:8080/api/customers
   ```

## Configuration

Default settings in `src/main/resources/application.properties`:

| Property                  | Value                                                                      |
|---------------------------|----------------------------------------------------------------------------|
| `spring.data.mongodb.uri` | `mongodb://yu71:53cret@localhost:27017/customers?authSource=admin`         |
| `changeLogFile`           | `db-json/master.json`                                                      |

The MongoDB credentials match those defined in `compose.yaml`. Change both if you use different credentials.

## API

| Method | Endpoint         | Description       |
|--------|------------------|-------------------|
| GET    | `/api/customers` | List all customers |

## Liquibase Changelogs

| ChangeSet           | Description                                  |
|---------------------|----------------------------------------------|
| `drop-collection`   | Drop the `customers` collection if it exists |
| `create-collection` | Create the `customers` collection            |
| `insert-customers`  | Insert base customers                        |
| `insert-owner`      | Insert customer `John Doe`                   |
| `insert-test`       | Insert customer `Jane Doe` (tag `0.10.0`)    |

Liquibase tracks applied changesets in the `DATABASECHANGELOG` collection, so each changeset only runs once.

## Running Tests

Tests need a running MongoDB instance:

```bash
docker compose up -d
./mvnw test
```

## CI/CD

- **GitHub Actions** — builds and tests on every push and pull request to `main`, and daily at 05:00 Asia/Jakarta.
- **Dependabot** — checks Maven, GitHub Actions, and Docker Compose dependencies daily at 05:00 Asia/Jakarta.

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
