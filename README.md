<div align="center">

# 👥 Employee Management System (Spring Boot)

**A modular Spring Boot REST API for HR operations: employees, payroll, timesheets, leave, feedback and UI theme preferences.**

![Java](https://img.shields.io/badge/Java%2017-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot%203.5-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger%20UI-85EA2D?logo=swagger&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

</div>

---

## ✨ Modules

| Module | Base path | What it does |
|---|---|---|
| 👤 Employees | `/api/employee` | Create, read, update and delete employees |
| 💰 Paybook | `/api/paybook` | CTC, salary and company details per employee |
| ⏱️ Timesheets | `/api/timesheet` | Day-wise entries and a **monthly summary** (`/summary/{empId}/{year}/{month}`) |
| 🌴 Leave | `/api/leaves` | Apply, update, approve or reject (`/updateStatus/{id}/status/{status}`) and list by employee |
| 💬 Feedback | `/api/feedback` | Submit, update and list employee feedback |
| 🎨 Theme | `/api/theme` | Save and fetch each employee's UI colour preference |

## 🧱 Design

- **Layered architecture**: Controller → Service interface → Service implementation → Repository → Entity
- **DTOs with Bean Validation** for every request
- **Global exception handling** (`@RestControllerAdvice`) with custom exceptions:
  - `ResourceNotFoundException`
  - `DuplicateResourceException`
  - `InvalidInputException`
- A consistent **`ApiResponse`** wrapper for responses
- **Swagger UI** (springdoc-openapi) for interactive API docs
- Lombok to keep entities and DTOs concise

```text
employee/
├── controllers/   # REST endpoints
├── dtos/          # Request DTOs and ApiResponse
├── exceptions/    # Custom exceptions and GlobalExceptionHandler
├── models/        # JPA entities
├── repos/         # Spring Data JPA repositories
├── services/      # Service interfaces
└── servicesImp/   # Implementations
```

## 🚀 Run locally

**Prerequisites:** JDK 17 and MySQL 8.

```sql
CREATE DATABASE Altimetrik;
```

Set your MySQL username and password in `src/main/resources/application.properties`
(`spring.datasource.url=jdbc:mysql://localhost:3306/Altimetrik`). Tables are created automatically (`ddl-auto=update`).

```bash
git clone https://github.com/mayurvij22/MayorZ-Sys.git
cd MayorZ-Sys
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

## 🛠️ Tech stack

Java 17 · Spring Boot 3.5 (Web, Data JPA, Validation) · MySQL · Lombok · springdoc-openapi · Maven

---

<div align="center">

Made by **[Mayur Patil](https://github.com/mayurvij22)**

</div>
