# Kotlin Ktor REST API

A simple REST API built using **Kotlin** and **Ktor**.
This project demonstrates basic backend concepts such as routing, request handling, and in-memory data storage.

---

## 🚀 Features

* RESTful API using Ktor
* JSON request/response handling
* In-memory data storage (no database)
* Basic CRUD operations:

  * Get all users
  * Get user by ID
  * Create a new user
  * Delete a user

---

## 🛠 Tech Stack

* Kotlin
* Ktor (Server framework)
* Netty (Server engine)
* Gradle (Build tool)

---

## 📦 Dependencies

The project uses the following main dependencies:

```kotlin
implementation("io.ktor:ktor-server-core-jvm:2.3.12")
implementation("io.ktor:ktor-server-netty-jvm:2.3.12")
implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.12")
implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.12")
implementation("ch.qos.logback:logback-classic:1.5.6")
```

### Explanation

* **ktor-server-core** → Core Ktor functionality
* **ktor-server-netty** → Runs the server using Netty
* **content-negotiation** → Handles JSON conversion
* **kotlinx-serialization** → Converts Kotlin objects ↔ JSON
* **logback** → Logging for the application

---

## 📁 Project Structure

```text
app/src/main/kotlin/com/example/
├── Application.kt          # Entry point of the app
├── models/
│   └── User.kt             # Data model
├── plugins/
│   ├── Routing.kt          # Registers routes
│   └── Serialization.kt    # JSON configuration
├── repository/
│   └── UserRepository.kt   # In-memory data storage
└── routes/
    └── UserRoutes.kt       # API endpoints
```

---

## ▶️ How to Run

### Prerequisites

* JDK 21+
* Gradle (or use wrapper)

### Run the server

```bash
./gradlew :app:run
```

Server will start at:

```text
http://localhost:8080
```

---

## 🧪 API Endpoints

### Root

```http
GET /
```

Response:

```text
Kotlin REST API is running
```

---

### Get all users

```http
GET /users
```

---

### Get user by ID

```http
GET /users/{id}
```

Example:

```http
GET /users/1
```

---

### Create user

```http
POST /users
Content-Type: application/json
```

Body:

```json
{
  "id": 3,
  "name": "Charlie",
  "email": "charlie@example.com"
}
```

---

### Delete user

```http
DELETE /users/{id}
```

---

## 🧪 Testing the API

### Using curl

```bash
curl http://localhost:8080/users
```

### Using VS Code

* Install **Thunder Client** or **REST Client**
* Send requests to `http://localhost:8080`

---

## ⚠️ Notes

* This project uses **in-memory storage**, so data resets when the server restarts
* No authentication or database is included (for simplicity)

---

## 🔮 Future Improvements

* Add database (PostgreSQL / MySQL)
* Add authentication (JWT)
* Add validation and error handling
* Add service layer
* Deploy to cloud (AWS / Render)

---

## 👨‍💻 Author

Built as a beginner-friendly Kotlin backend project.
