# 💳 Online Banking System

A simple Java-based console application that simulates core functionalities of an online banking system. Designed with a focus on modularity and extensibility, this project demonstrates object-oriented programming principles, data handling, and Maven-based project structuring.

---

## 🚀 Features

- ✅ Create a bank account with customer details
- ✅ Deposit and withdraw money
- ✅ Display all account details
- ✅ Simulated transactions with logging
- ✅ Clean code structure using Java classes and packages
- ✅ Built using Maven with proper dependency management

---

## 🛠️ Technologies Used

- **Java 17** – Core programming language
- **Maven** – Build automation and dependency management
- **VS Code** – Development environment
- **Git & GitHub** – Version control and hosting

---

## 📁 Project Structure

```
onlinebankingsystem/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── banking/
│                   ├── Main.java
│                   ├── Account.java
│                   └── [Other supporting classes]
│
├── pom.xml
└── README.md
```

---

## ▶️ How to Run

> Prerequisites: Java 17+ and Maven installed

1. Clone the repository:
   ```bash
   git clone https://github.com/iamrishigandhi/onlinebankingsystem.git
   cd onlinebankingsystem
   ```

2. Compile the code:
   ```bash
   mvn compile
   ```

3. Run the project:
   ```bash
   mvn exec:java "-Dexec.mainClass=com.banking.Main"
   ```

---

## 🧪 Sample Output

```
Account created successfully.
Account created successfully.
Account{accountNumber='1002', accountHolderName='Bob Johnson', email='bob@example.com', balance=700.0}
Account{accountNumber='1001', accountHolderName='Alice Smith', email='alice@example.com', balance=700.0}
It works!
```

---

## 📌 Notes

- You can extend this project by adding persistence (e.g., file-based or database).
- Feel free to fork, star, and contribute!

---

## 👨‍💼 Author

**Rishi Gandhi**  
📧 [LinkedIn](https://www.linkedin.com/in/iamrishigandhi) | 🌐 [GitHub](https://github.com/iamrishigandhi)

---

## ⚖️ License

This project is open source and available under the [MIT License](LICENSE).
