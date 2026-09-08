# Spring Boot Practice — Student Management API

> 📚 Part of a **Spring Boot practice series**, building toward a Kotlin + Spring Boot project. See also: [SPRINGBOOT5 — Banking Domain Model](https://github.com/devrimsavas/SPRINGBOOT5).

A complete REST API built with Spring Boot to practice controllers, service-layer architecture, and dependency injection — a Java/Spring Boot parallel to projects like [DevHouse4](https://github.com/devrimsavas/DevHouse4) (built in ASP.NET Core).

## 🚀 Features

- **Full CRUD** on student records: create, read, update, delete
- **Layered architecture**: Controller → Service (`@Service`) → Model, wired via constructor-based dependency injection
- **JSON data loading**: students are loaded from a bundled `students.json` file at startup (`@PostConstruct`) via Jackson's `ObjectMapper`
- **Business logic**: find the oldest and youngest student using Java Streams
- **Proper HTTP status handling**: 404 (not found), 400 (bad request), 500 (server error) returned appropriately per failure case
- **CORS enabled** for cross-origin frontend access
- **Simple HTML/CSS frontend** included, consuming the API directly

## 🛠 Tech Stack

- Java, Spring Boot (Spring Web)
- Jackson (JSON parsing)
- Maven

## 📂 Project Structure

```
demo/
├── src/main/java/com/example/demo/
│   ├── HelloController.java     — warm-up endpoints (hello world, add numbers, etc.)
│   ├── StudentController.java   — REST API: /students/*
│   ├── ClassRoom.java            — @Service: business logic, in-memory student store
│   ├── JSONREADER.java           — @Service: reads students.json via Jackson
│   ├── Student.java              — domain model
│   ├── Course.java / Multiple.java / ShowArray.java — smaller practice classes
│   └── DemoApplication.java      — Spring Boot entry point
├── src/main/resources/
│   ├── application.properties
│   └── students.json             — seed data
└── frontend/
    ├── home.html                  — simple UI for listing/adding students
    └── common.css
```

## 📖 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Hello world |
| GET | `/students/allstudents` | List all students |
| POST | `/students/addstudent` | Add a new student |
| PUT | `/students/updatestudent/{id}` | Update a student by ID |
| DELETE | `/students/deletestudent/{id}` | Delete a student by ID |
| GET | `/students/getoldest` | Get the oldest student(s) |
| GET | `/students/getyoungest` | Get the youngest student(s) |
| GET | `/students/test` | Raw JSON read test |

## ▶️ Getting Started

```bash
cd demo
./mvnw spring-boot:run
```
The API runs on the default Spring Boot port (`8080`). Open `frontend/home.html` in a browser to use the simple UI, or call the endpoints directly.

## 📝 Notes

This is a learning project focused on Spring Boot fundamentals: REST controllers, service-layer separation, dependency injection, and JSON handling. It's part of a broader effort to build Kotlin/Spring Boot skills alongside existing .NET and Node.js experience — see the [roadmap](https://github.com/devrimsavas/SPRINGBOOT5#-roadmap) for what comes next.
