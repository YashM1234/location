# Location Management API

This API provides endpoints for managing user locations, including CRUD operations and fetching location details.

## Endpoints

- **GET /api/v1/locations/users/{userId}**
  - Retrieves all locations for a specific user.
  - **Response**: List of LocationModel.
  - **HTTP Status Codes**: 200 OK

- **GET /api/v1/locations/{locationId}/users/{userId}**
  - Retrieves detailed information about a specific location for a user.
  - **Response**: LocationDetailModel.
  - **HTTP Status Codes**: 200 OK, 404 Not Found if location not found.

- **POST /api/v1/locations/users**
  - Creates a new location for a user.
  - **Request Body**: LocationModel.
  - **Response**: "Location Created!" if successful, "Location Not Found!" if unsuccessful.
  - **HTTP Status Codes**: 201 Created, 500 Internal Server Error if creation fails.

- **PUT /api/v1/locations/{locationId}/users/{userId}**
  - Updates an existing location for a user.
  - **Request Body**: LocationModel.
  - **Response**: Updated LocationModel.
  - **HTTP Status Codes**: 200 OK, 404 Not Found if location not found.

- **DELETE /api/v1/locations/{locationId}/users/{userId}**
  - Deletes a location for a user.
  - **Response**: "Location Deleted Successfully!" if successful, "Location Not Found!" if location not found.
  - **HTTP Status Codes**: 204 No Content, 404 Not Found if location not found.

## Technologies Used

- **Spring Boot**: Framework for building the microservice.
- **PostgreSQL**: Database for storing user information.
- **Logback**: Logging framework for capturing application logs.
- **Swagger**: API documentation tool for easy API exploration.
- **Geocode API**: External service used for geocoding functionality.
- **Caching**: Implemented for improving response times.

## Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL database
- IDE (e.g., IntelliJ IDEA, Eclipse) for development

## Getting Started

1. **Clone the Repository**
    ```bash
    git clone https://github.com/your-username/location.git
    cd location-management-api
    ```

2. **Set Up PostgreSQL**
    - Create a PostgreSQL database named `location`.
    - Update `application.properties` with your PostgreSQL credentials:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/location
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      ```

3. **Build and Run the Application**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Explore the API**
    - Once running, access the Swagger UI to explore and test the API endpoints:
      [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Logging Configuration

The application uses SLF4J with Logback for logging, configured in `src/main/resources/logback-spring.xml`.
