# 🔗 URL Shortener Backend

A URL shortener backend built with **Kotlin + Ktor**, supporting:
- Creating short links with **1-hour expiration**
- **Click tracking** during the validity period

---

## 🧱 Technologies Used

- [Kotlin](https://kotlinlang.org/) + [Ktor](https://ktor.io/) — backend server
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) — build tool
- [PostgreSQL](https://www.postgresql.org/) — database
- [Exposed ORM](https://github.com/JetBrains/Exposed) — Kotlin ORM
- [Docker](https://www.docker.com/) + [Docker Compose](https://docs.docker.com/compose/) — local container orchestration
- [Traefik](https://traefik.io/) — reverse proxy
- [Postman](https://www.postman.com/) — API testing

---
---

## 📁 Project Structure

@@TODO

---

## 🚀 Getting Started Locally

### Prerequisites
- Docker installed ✅
- Postman account ✅
- macOS system (setup adapted accordingly)

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/url-shortener.git
cd url-shortener
2. Run with Docker Compose
bash
Copy
Edit
docker-compose up --build
The backend will be accessible at:
📍 http://localhost (proxied through Traefik) (@@TODO - After deploy put real link)

📬 API Endpoints (in development)
Method	Route	Description
POST	/shorten	Create a short link with 1-hour expiration
GET	/r/{shortId}	Redirect if the link is still valid
GET	/stats/{shortId}	Get the number of clicks within 1 hour

🧪 Testing
Endpoints can be tested using Postman

Automated tests will be written using JUnit + Ktor Test

✍️ Author
Developed by Guilherme Leocadio as a personal project to learn backend development with Kotlin.

📌 Notes
This project is being built from scratch with a full step-by-step approach

Focused on real, hands-on learning of backend, DevOps, and best practices.