# PlantIntelligence 🌿🤖

An enterprise-grade, IoT-driven plant growth monitoring and automated control system built using **Spring Boot (MVC/REST)** and **Supabase (PostgreSQL)**.

## 🏗️ System Architecture
The application follows a strict architectural pattern adapted for Spring Boot:
- **Model (`/model`):** Contains relational database entities mapped via Spring Data JPA / Hibernate annotations (e.g., `@Entity`).
- **View / UI (`/resources/templates` or Frontend):** Handles the enterprise dashboard presentation layer using Thymeleaf or a decoupled UI interface.
- **Controller (`/controller`):** REST and Web controllers managing request routing, handling user inputs, and returning responses.
- **Repository (`/repository`):** Data access layer extending Spring Data's `JpaRepository` for relational database operations.
- **IoT Layer (`/firmware`):** C++ code running on ESP32 microcontrollers to push telemetry JSON payloads over HTTP/MQTT.

## 🚀 Tech Stack
- **Backend Framework:** Java Spring Boot (Spring Web, Spring Data JPA)
- **Database:** Supabase (Hosted PostgreSQL Relational Database) [Free Tier]
- **Frontend:** HTML5, Tailwind CSS, Chart.js
- **IoT Hardware:** ESP32 NodeMCU, DHT22, Capacitive Soil Moisture Sensor v1.2, BH1750 Lux Sensor, 5V Relay Modules.

## 👥 Team & Workflow
Developed collaboratively by a team of 5 engineering undergraduates using parallel feature branches and structured pull request reviews managed by the team leader.

## 📂 Repository Structure
```text
PlantIntelligence/
├── src/main/java/com/plantintelligence/
│   ├── controller/    # Request handlers and routing logic
│   ├── model/         # Relational database entity models (JPA)
│   ├── repository/    # Spring Data JPA interfaces
│   └── service/       # Business logic layer
├── src/main/resources/
│   ├── static/        # CSS, client-side JS, images
│   ├── templates/     # View templates (HTML)
│   └── application.properties # Supabase PostgreSQL connection configs
└── firmware/          # ESP32 C++ source code for telemetry

[View Hardware Wiring and Component Pinout Guide](./docx/hardware_wiring.md)

---

## 🗄️ Users Database

### Table: `users`

| Column | Type | Constraints |
|---|---|---|
| `id` | `BIGINT` | Primary Key, auto-generated (`BIGSERIAL`) |
| `username` | `VARCHAR(50)` | NOT NULL, UNIQUE |
| `email` | `VARCHAR(255)` | NOT NULL, UNIQUE |
| `password_hash` | `VARCHAR(255)` | NOT NULL — stores a **hashed** password, never plain-text |
| `role` | `VARCHAR(30)` | NOT NULL — stored as the enum name string (e.g. `USER`, `ADMIN`) |

### Schema Migration

Managed by **Flyway** (replaces `spring.jpa.hibernate.ddl-auto=update`).

- Migration file: [`backend/src/main/resources/db/migration/V1__create_users_table.sql`](./backend/src/main/resources/db/migration/V1__create_users_table.sql)
- Flyway creates/migrates the schema; Hibernate is set to `validate` mode only.
- To evolve the schema, add a new versioned migration (e.g. `V2__...`). **Never edit existing migration files that have been applied.**

### Java Model

- Entity: [`backend/src/main/java/com/plantintelligence/backend/model/User.java`](./backend/src/main/java/com/plantintelligence/backend/model/User.java)
- Role enum: [`backend/src/main/java/com/plantintelligence/backend/model/UserRole.java`](./backend/src/main/java/com/plantintelligence/backend/model/UserRole.java)
- Repository: [`backend/src/main/java/com/plantintelligence/backend/repository/UserRepository.java`](./backend/src/main/java/com/plantintelligence/backend/repository/UserRepository.java)

**Current roles:** `USER` (standard user), `ADMIN` (elevated privileges).
To add a new role, update the `UserRole` enum and document it here.

### Database Connection Configuration

Production datasource properties are read from environment variables — **no credentials are stored in source code**:

```properties
DB_URL=jdbc:postgresql://host:5432/dbname
DB_USERNAME=your-db-username
DB_PASSWORD=your-db-password
```

Set these in your deployment environment, IDE run configuration, or a local `.env` file (never commit `.env`).

### Running Tests Locally (No Supabase Required)

Tests use an **H2 in-memory database** (PostgreSQL compatibility mode) via the `test` Spring profile. Flyway runs the production migration against H2, proving the SQL is valid before it reaches Supabase.

```bash
# Windows
cd backend
mvnw.cmd test

# macOS / Linux
cd backend
./mvnw test
```

The test profile is configured in [`backend/src/test/resources/application-test.properties`](./backend/src/test/resources/application-test.properties).
