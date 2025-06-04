# Example OAuth2 With Roles

This is a Spring Boot backend application demonstrating user authentication and role-based access control using OAuth2 (Google/GitHub) and form login.

## Features

- User registration and login
- Role-based authorization (ADMIN, CLIENT, VIP_CLIENT, MANAGER)
- OAuth2 login via Google and GitHub
- H2 in-memory database for testing
- Secure REST endpoints based on user roles

## Getting Started

### H2 Console

Access the H2 database console at:  
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### OAuth2 Authentication

After logging in via Google or GitHub, access role-specific endpoints:  

> ⚠️ **Important:** The user's email in the database must match the email from Google or GitHub.

- `/api/v1/home` — accessible to all authenticated users
- `/api/v1/admin` — for users with `ADMIN` role
- `/api/v1/client` — for users with `CLIENT` role
- `/api/v1/vip` — for users with `VIP_CLIENT` role
- `/api/v1/manager` — for users with `MANAGER` role

### Endpoints

- `/login` — login page (supports form login and OAuth2)
- `/logout` — logout (redirects to login page)
- `/api/v1/register` — endpoint for user registration (defaults to `CLIENT` role)

## Registration Example (Postman)

**POST** `http://localhost:8080/api/v1/register`  
Headers:  
`Content-Type: application/json`  

**Request Body:**
```json
{
  "email": "testuser@example.com",
  "name": "Test User",
  "password": "123",
  "confirmPassword": "123"
}
```

**Response:**
```json
User registered successfully
```

## Login with Email/Password (Postman)

**POST** `http://localhost:8080/api/v1/auth/login`  
Headers:  
`Content-Type: application/x-www-form-urlencoded`  

**Body:**
```
username=testuser@example.com
password=123
```

**Response:**
```json
Login successful
```

**Cookies:**
```
JSESSIONID=472E04935B7E7A496FE4B098471960CF
```

## Tech Stack

- Java 21
- Spring Boot 3.5.0
- Spring Security (OAuth2 client)
- Spring Data JPA
- H2 Database
- Liquibase
- Lombok
- Maven

## License

MIT License. See [LICENSE](https://opensource.org/licenses/MIT) for more information.

## Author

**Roman Manzhula**  
📧 roman1985al@ukr.net  
🔗 [GitHub Repository](https://github.com/RoMANzhula/example_oauth2_with_roles.git)