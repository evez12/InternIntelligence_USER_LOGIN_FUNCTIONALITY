# Register and Login System with Spring Security
This repository contains a Spring Boot application that demonstrates a secure user registration and login system using Spring Security. The project implements authentication and authorisation in web applications.

## Problem Description

Implementing a secure user authentication and authorization system is crucial for web applications to protect sensitive data and ensure that users have appropriate access levels. The challenge involves:

- **User Registration**: Allowing new users to create accounts securely.
- **User Authentication**: Verifying user credentials during login.
- **Session Management**: Maintaining user sessions after successful login.
- **Authorization**: Restricting access to resources based on user roles.

## Solution Approaches

Several methods can be employed to handle authentication and authorization:

1. **Session-Based Authentication**:
   - **Description**: Stores user session information on the server after successful login.
   - **Pros**: Simple to implement; suitable for server-side rendered applications.
   - **Cons**: Scalability issues in distributed environments; server memory usage increases with the number of users.

2. **Token-Based Authentication (e.g., JWT)**:
   - **Description**: Issues a token (such as a JSON Web Token) to the client upon successful authentication, which is then included in subsequent requests.
   - **Pros**: Stateless; scalable; suitable for SPAs and mobile applications.
   - **Cons**: Token management can be complex; requires secure storage on the client side.

3. **OAuth2 Authentication**:
   - **Description**: Allows third-party services to exchange user information securely without exposing user credentials.
   - **Pros**: Enables integration with external services; enhances security.
   - **Cons**: More complex to implement; requires understanding of OAuth2 flows.

## Chosen Solution: JWT (JSON Web Token)
> **Note**:  
> My future plans include integrating OAuth2 to enhance security and flexibility.

This project implements **Token-Based Authentication** using **JWT** due to its stateless nature and scalability benefits. JWTs are self-contained tokens that can securely transmit information between parties. They are particularly well-suited for modern web applications, including single-page applications (SPAs) and mobile apps.

**Advantages of Using JWT**:

- **Stateless**: No need to store session information on the server, making it easier to scale the application horizontally.
- **Decentralized Authentication**: Clients can authenticate themselves without continuous server-side checks.
- **Flexibility**: Can be used across different domains and services.
## Features

- **User Registration**: Secure functionality for new users to register
- **Login System**: Authenticate users securely
- **Password Encryption**: Uses **BCrypt** to hash passwords
- **Role-Based Access Control**: Manage permissions for different user roles
- **Database Integration**: Connects seamlessly with relational databases using Spring Data JPA

## How to Use This Project
[![Video Title](https://img.youtube.com/vi/qamSMQrAufQ/0.jpg)](https://www.youtube.com/watch?v=qamSMQrAufQ)

### Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher.
- **Maven**: For building the project.
- **PostgreSQL Database**: Ensure a PostgreSQL instance is running and accessible.

### Setup Instructions

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/evez12/InternIntelligence_USER_LOGIN_FUNCTIONALITY.git
   cd InternIntelligence_USER_LOGIN_FUNCTIONALITY
   ```

2. **Configure the Database**:

   - Create a new MySQL database (e.g., `spring_security_db`).
   - Update the `application.properties` file with your database credentials:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_db
     spring.datasource.username=your_db_username
     spring.datasource.password=your_db_password
     ```

3. **Build the Project**:

   ```bash
   mvn clean install
   ```

4. **Run the Application**:

   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**:

   - Open your browser and navigate to `http://localhost:8080`.
   - Use the registration page to create a new account.
   - After registration, log in with your credentials.

### Project Structure

- **`src/main/java`**: Contains the Java source code.
  - **`controller`**: Handles HTTP requests and responses.
  - **`service`**: Contains business logic, including user registration and authentication.
  - **`repository`**: Interfaces for database operations.
  - **`model`**: Entity classes representing database tables.
  - **`security`**: Configuration classes for Spring Security and JWT handling.

- **`src/main/resources`**: Contains configuration files and templates.
  - **`application.properties`**: Main configuration file for the application.
  - **`templates`**: Thymeleaf templates for views (e.g., login and registration pages).

### Key Endpoints

- **`/signup`**: Endpoint for user registration.
- **`/signin`**: Endpoint for user login; returns a JWT upon successful authentication.
- **`/api/**`**: Secured endpoints requiring authentication; include the JWT in the `Authorization` header as a Bearer token.

### Security Configuration

The security configuration is handled in the `SecurityConfig` class, which extends `WebSecurityConfigurerAdapter`. Key configurations include:

- **Password Encoding**: Uses BCryptPasswordEncoder for hashing passwords.
- **Authentication Manager**: Configured to use a custom `UserDetailsService` for loading user-specific data.
- **JWT Authentication Filter**: Intercepts requests to validate the JWT token.
- **Authorization Rules**: Specifies public and secured endpoints.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code follows the existing coding standards and includes appropriate tests.

