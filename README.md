# Products API

This repository contains a RESTful API for managing products and their producers. The application is built using Java and the Spring Boot framework, with a PostgreSQL database for persistence. It is fully containerized using Docker for easy setup and deployment.

## Features

-   Full CRUD (Create, Read, Update, Delete) operations for Products.
-   Full CRUD operations for Producers.
-   Dynamic filtering of products based on JSONB attributes.
-   Database schema is managed via Liquibase migrations.
-   Containerized setup using Docker and Docker Compose.
-   Centralized exception handling.

## Technology Stack

-   **Backend:** Java 21, Spring Boot 3
-   **Database:** PostgreSQL
-   **ORM:** Spring Data JPA / Hibernate
-   **Database Migration:** Liquibase
-   **Build Tool:** Maven
-   **Containerization:** Docker

## Prerequisites

-   Java 21
-   Maven
-   Docker
-   Docker Compose

## Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/nirrax/products_api.git
cd products_api
```

### 2. Running with Docker Compose (Recommended)
This is the simplest way to get the application and its database running.

1.  Make sure Docker is running on your machine.
2.  From the root directory of the project, run:
    ```bash
    docker-compose up --build
    ```
This command will build the Spring Boot application image, pull the PostgreSQL image, and start both services. The API will be available at `http://localhost:8080/api`.

### 3. Running Locally (Manual Setup)
If you prefer not to use Docker, you can run the application locally.

1.  **Start a PostgreSQL Instance:**
    Ensure you have a PostgreSQL server running. You can use any method you prefer (local installation, Docker container, etc.).

2.  **Configure the Database:**
    The application is configured to connect to a database named `mydb` on `localhost:5432` with the username `postgres` and password `pass`. You can change these settings in the `src/main/resources/application.yml` file.

3.  **Run the Application:**
    Use the Maven wrapper to start the application. Liquibase will automatically create the necessary tables on startup.
    ```bash
    ./mvnw spring-boot:run
    ```
The API will be available at `http://localhost:8080/api`.

## API Endpoints

The base URL for all endpoints is `/api`.

### Producers

Endpoints for managing producers.

| Method | Endpoint             | Description                       |
|--------|----------------------|-----------------------------------|
| `GET`    | `/producers`         | Retrieve a list of all producers. |
| `GET`    | `/producers/{id}`    | Retrieve a single producer by ID. |
| `POST`   | `/producers`         | Create a new producer.            |
| `PATCH`  | `/producers/{id}`    | Update an existing producer.      |
| `DELETE` | `/producers/{id}`    | Delete a producer by ID.          |

**POST /producers** - Create Producer
```json
{
  "name": "Apple"
}
```

**PATCH /producers/{id}** - Update Producer
```json
{
  "name": "Apple Inc."
}
```

### Products

Endpoints for managing products.

| Method | Endpoint          | Description                                    |
|--------|-------------------|------------------------------------------------|
| `GET`    | `/products`       | Retrieve a list of all products.               |
| `GET`    | `/products?key=val` | Filter products by their attributes.         |
| `GET`    | `/products/{id}`  | Retrieve a single product by ID.               |
| `POST`   | `/products`       | Create a new product.                          |
| `PATCH`  | `/products/{id}`  | Update an existing product.                    |
| `DELETE` | `/products/{id}`  | Delete a product by ID.                        |

---

**Filtering Products**

You can filter products by providing query parameters that match their attributes.

*Example:* To find all products where the `color` attribute is "red" and the `storage` attribute is "256GB":
`GET /api/products?color=red&storage=256GB`

**POST /products** - Create Product
```json
{
  "name": "iPhone 15 Pro",
  "producerId": 1,
  "attributes": {
    "color": "Natural Titanium",
    "storage": "256GB",
    "screen_size": 6.1
  }
}
```

**PATCH /products/{id}** - Update Product

You can update a product's name and/or its attributes.
- To update an attribute, provide the new value.
- To add a new attribute, include it in the `attributes` object.
- To remove an attribute, set its value to `null`.

```json
{
  "name": "iPhone 15 Pro Max",
  "attributes": {
    "storage": "512GB",
    "color": null
  }
}
```
*In this example, the product's name is updated, the `storage` attribute is changed to "512GB", and the `color` attribute is removed.*

## Database Schema

The database schema is managed by Liquibase. The migration scripts can be found in `src/main/resources/db/changelog/`.

- **PRODUCERS**
  - `id` (PK, BIGINT)
  - `name` (VARCHAR, UNIQUE)

- **PRODUCTS**
  - `id` (PK, BIGINT)
  - `name` (VARCHAR)
  - `producer_id` (FK to `PRODUCERS.id`)
  - `attributes` (JSONB): A flexible field to store product-specific attributes. A GIN index is applied to this column for efficient querying.
