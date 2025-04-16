# 💳 Online Banking System

A full-stack **online banking system** built with **React**, **Spring Boot**, and **PostgreSQL**, allowing users to create secure accounts with auto-generated 12-digit account numbers.

---

## 🚀 Features

- ✅ Account creation with email & password
- 🔒 Securely stores user data in a PostgreSQL database
- 🔁 Unique 12-digit account number generated on signup
- 🔧 Modular Java backend using Spring Boot
- 🌐 React frontend powered by Vite
- 🔗 API communication via REST (JSON)
- 🛡️ CORS configured for secure frontend-backend communication

---

## 🧠 Tech Stack

### Frontend
- [React](https://reactjs.org/)
- [Vite](https://vitejs.dev/)
- TypeScript

### Backend
- Java 17
- [Spring Boot](https://spring.io/projects/spring-boot)
- PostgreSQL
- Maven

---

## 📁 Folder Structure

```
onlinebankingsystem/
├── backend/
│   ├── src/
│   │   └── main/java/com/banking/
│   │       ├── api/            # REST controllers
│   │       ├── config/         # CORS / WebConfig
│   │       ├── database/       # Database interaction logic
│   │       ├── exceptions/     # Custom exceptions
│   │       ├── models/         # Domain models
│   │       ├── utils/          # DB utils
│   │       └── OnlineBankingApplication.java
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   └── App.tsx             # React UI for account creation
│   └── package.json
```

---

## 🛠️ Getting Started

### Prerequisites
- [Node.js](https://nodejs.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Maven](https://maven.apache.org/)

---

### 1. 🚧 Backend Setup

```bash
cd backend
# Update PostgreSQL credentials in DBUtil.java if needed
mvn clean spring-boot:run
```

✅ Make sure your PostgreSQL `accounts` table is created with:

```sql
CREATE TABLE accounts (
  account_number VARCHAR(12) PRIMARY KEY,
  holder_name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  balance DOUBLE PRECISION,
  CONSTRAINT unique_account_number UNIQUE (account_number)
);
```

---

### 2. 🎨 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Navigate to `http://localhost:5173` to test the UI.

---

## 📦 API Endpoint

**POST** `/api/accounts/create`  
**Payload**:
```json
{
  "email": "example@email.com",
  "password": "yourPassword123"
}
```

**Response**:
```json
{
  "accountNumber": "123456789012",
  "message": "Account created successfully."
}
```

---

## 📌 TODO / Future Enhancements

- [ ] Password hashing (BCrypt)
- [ ] User login & authentication
- [ ] View account balance
- [ ] Transaction history & logging
- [ ] Improved UI with Tailwind/Figma design
- [ ] Dockerization & deployment

---

## 📸 UI Preview

*To be added once styling is complete.*

---

## 🧑‍💻 Author

**Rishi Gandhi**  
📫 [LinkedIn](https://www.linkedin.com/in/iamrishigandhi)  
💻 [GitHub](https://github.com/iamrishigandhi)

---

## 📜 License

This project is licensed under the MIT License.
```