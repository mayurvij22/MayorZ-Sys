# Employee Management System - Backend

A comprehensive Spring Boot backend API for managing employees, payroll, timesheets, leave requests, feedback, and theme preferences. Built with a modular architecture using MySQL and follows best practices for validation, error handling, and clean RESTful design.

## 📌 Features

- Employee Management (CRUD)
- Paybook Management (CTC, Salary, Company info)
- Timesheet Tracking (Day-wise entries, summary reports)
- Leave Management (Apply, update status, fetch by empId)
- Feedback Module (Store employee feedback)
- Theme Preferences (Color preferences: red, blue, green, etc.)
- Role-based endpoints (Admin/User support)
- JWT Authentication-ready structure
- RESTful APIs with Validation & Exception Handling

---

## 📦 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Spring Validation
- Maven
- Lombok (for cleaner code)
- Swagger (optional for API docs)

---

## ⚙️ Project Setup

### Prerequisites

- Java 17+
- Maven
- MySQL
- IDE (IntelliJ, VS Code)

### 1. Clone the repository

```bash
git clone https://github.com/your-username/employee-management-backend.git
cd employee-management-backend
2. Configure application.properties
Create src/main/resources/application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
3. Build and Run
bash
Copy
Edit
mvn clean install
mvn spring-boot:run
Server runs at: http://localhost:8080

📁 Project Structure
css
Copy
Edit
src/
├── main/
│   ├── java/employee/
│   │   ├── controllers/
│   │   ├── services/
│   │   ├── servicesImp/
│   │   ├── dtos/
│   │   ├── models/
│   │   ├── repos/
│   │   └── exceptions/
│   └── resources/
│       └── application.properties
📮 API Endpoints (Examples)
🧑 Employees
GET /api/employees – Get all employees

POST /api/employees – Add new employee

PUT /api/employees/{id} – Update employee

DELETE /api/employees/{id} – Delete employee

💸 Paybook
GET /api/paybook/by-empid/{empId}

POST /api/paybook – Add pay details

⏱️ Timesheet
POST /api/timesheet – Add day entry

GET /api/timesheet/by-empid/{empId}

GET /api/timesheet/summary/{empId}/{month} – Monthly summary

🌴 Leave Management
POST /api/leaves – Apply leave

GET /api/leaves/by-empid/{empId}

PUT /api/leaves/{id}/status/{status} – Approve/Reject

DELETE /api/leaves/{id} – Delete request

💬 Feedback
POST /api/feedback – Submit feedback

GET /api/feedback/all – Admin: Get all feedback

🎨 Theme Preferences
GET /api/theme/{empId}

POST /api/theme – Save/update theme color

🧪 Postman Collection
👉 Download Postman Collection (upload link to your .json file or include in repo)

✅ To-Do (Enhancements)
 JWT Authentication with Role-based access

 Swagger UI Integration

 Email notifications for leave status

 Timesheet analytics and charts

📜 License
This project is open-source and available under the MIT License.

