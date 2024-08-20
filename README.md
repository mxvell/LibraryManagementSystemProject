# Library Management System

This project is an upgraded version of a Library Management System, rewritten using Hibernate and Spring Data JPA. It provides functionality for managing books and library patrons.

## Features

1. CRUD operations for Books and Patrons
2. Pagination for book listings
3. Sorting books by year
4. Book search functionality
5. Automatic overdue book detection

## Technologies Used

- Java
- Spring Boot
- Hibernate
- Spring Data JPA
- Thymeleaf
- PostgreSQL

## Setup

1. Clone the repository
2. Configure your PostgreSQL database in `application.properties`
3. Run the application using your IDE or with `mvn spring-boot:run`

## API Endpoints

### Books

- GET `/books` - List all books
  - Supports pagination with `page` and `books_per_page` parameters
  - Supports sorting with `sort_by_year=true` parameter
- GET `/books/search` - Search for books

### Patrons

- GET `/people/{id}` - View patron details and borrowed books

## Key Features Implementation

### Pagination

The `/books` endpoint supports pagination using `page` and `books_per_page` parameters.

Example: `/books?page=1&books_per_page=3`

### Sorting

Books can be sorted by year using the `sort_by_year=true` parameter.

Example: `/books?sort_by_year=true`

### Book Search

The `/books/search` endpoint allows searching for books by the initial letters of the book title.

### Overdue Book Detection

The system automatically checks for overdue books (books borrowed for more than 10 days) and highlights them on the patron's page.

## Notes for Developers

- Ensure all database interactions are done through services, not DAOs.
- Use `@Transient` for non-persistent fields if needed.
- Handle lazy loading carefully to avoid `LazyInitializationException`.
- Use `Hibernate.initialize()` within transactions to eagerly load related entities when necessary.

## Troubleshooting

If you encounter startup errors, ensure your Spring dependencies are up to date in `pom.xml`. Run `mvn clean` after updating dependencies.

For any `LazyInitializationException`, review your use of `Hibernate.initialize()` and ensure it's called within the transaction boundary.
