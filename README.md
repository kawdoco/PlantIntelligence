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
