# PlantIntelligence 🌿🤖

An enterprise-grade, IoT-driven plant growth monitoring and automated control system built using **Spring Boot (MVC/REST)** and **MongoDB**.

## 🏗️ System Architecture
The application follows a strict architectural pattern adapted for Spring Boot:
- **Model (`/model`):** Contains MongoDB document entities mapped via Spring Data annotations (e.g., `@Document`).
- **View / UI (`/resources/templates` or Frontend):** Handles the enterprise dashboard presentation layer using Thymeleaf or a decoupled UI interface.
- **Controller (`/controller`):** REST and Web controllers managing request routing, handling user inputs, and returning responses.
- **Repository (`/repository`):** Data access layer extending Spring Data's `MongoRepository` for database CRUD operations.
- **IoT Layer (`/firmware`):** C++ code running on ESP32 microcontrollers to push telemetry JSON payloads over HTTP/MQTT.

## 🚀 Tech Stack
- **Backend Framework:** Java Spring Boot (Spring Web, Spring Data MongoDB)
- **Database:** MongoDB (NoSQL document store optimized for high-frequency time-series sensor logs)
- **Frontend:** HTML5, Tailwind CSS, Chart.js
- **IoT Hardware:** ESP32 NodeMCU, DHT22, Capacitive Soil Moisture Sensor v1.2, BH1750 Lux Sensor, 5V Relay Modules.

## 👥 Team & Workflow
Developed collaboratively by a team of 5 engineering undergraduates using parallel feature branches and structured pull request reviews managed by the team leader.

## 📂 Repository Structure
```text
PlantIntelligence/
├── src/main/java/com/plantintelligence/
│   ├── controller/    # Request handlers and routing logic
│   ├── model/         # MongoDB document data models
│   ├── repository/    # Spring Data MongoDB interfaces
│   └── service/       # Business logic layer
├── src/main/resources/
│   ├── static/        # CSS, client-side JS, images
│   ├── templates/     # View templates (HTML)
│   └── application.properties # MongoDB connection configs
└── firmware/          # ESP32 C++ source code for telemetry
