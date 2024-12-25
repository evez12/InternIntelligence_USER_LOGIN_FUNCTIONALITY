# Register and Login System with Spring Security

This repository contains a Spring Boot application that implements a secure user registration and login system using Spring Security. It is designed to showcase best practices for securing web applications, managing user authentication, and handling registration workflows.

---

## Features

- **User Registration**:
  - Securely stores user credentials in the database.
  - Validates user input during the registration process.
- **User Login**:
  - Supports authentication with Spring Security.
  - Implements session-based security.
- **Password Security**:
  - Hashes passwords using BCrypt before saving them to the database.
- **Role Management**:
  - Allows assigning roles to users (e.g., `ROLE_USER`, `ROLE_ADMIN`).
- **Frontend Integration**:
  - Ready to be integrated with modern frontend frameworks.
- **Error Handling**:
  - Provides user-friendly error messages for authentication and registration failures.

---

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Spring Security**: Provides authentication and authorization mechanisms.
- **Thymeleaf**: Template engine for dynamic web pages (optional for views).
- **H2 Database**: Embedded database for development and testing.
- **Maven**: Dependency management and build tool.

---

## Getting Started

### Prerequisites

1. **Java**: Ensure you have JDK 11 or higher installed.
2. **Maven**: Make sure Maven is installed on your system.
3. **Database**: The application uses H2 by default. For production, configure another database (e.g., MySQL, PostgreSQL).

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/evez12/Register-login-spring-security.git
   cd Register-login-spring-security
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**:
   - Open your browser and navigate to `http://localhost:8080`.

---

## Configuration

### Database

To switch from H2 to a production-grade database, update the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Security

Modify roles, permissions, and other security configurations in the `SecurityConfig` class.

---

## Folder Structure

- **src/main/java**: Contains the Java source code.
  - `controller`: Manages HTTP requests and responses.
  - `service`: Contains business logic.
  - `repository`: Interfaces for database operations.
  - `model`: Defines entity classes.
  - `config`: Spring Security configurations.
- **src/main/resources**: Contains application properties and templates.
  - `application.properties`: Configuration file for the application.
  - `templates/`: Thymeleaf templates (if applicable).

---

## Future Enhancements

- Add OAuth2 login support.
- Integrate email verification for user registration.
- Implement password recovery functionality.
- Enhance UI with modern frontend frameworks like React or Angular.

---

## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contact

For any questions or feedback, reach out to **[Əvəz](https://github.com/evez12)** on GitHub.

---

## Acknowledgments

Special thanks to the open-source community and Spring Security documentation for inspiration and guidance.

