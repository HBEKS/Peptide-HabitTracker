# Final Project (UAS) - Advanced Native Mobile Programming (ANMP)

This project is a **Habit Tracker** Android application developed as the **Final Project (UAS)** for the **Advanced Native Mobile Programming (ANMP)** course. The application is built using **Kotlin** and follows the **MVVM (Model-View-ViewModel)** architecture while utilizing **Room Database** for local data persistence.

The application enables users to create and manage their daily habits, authenticate using a local Room database, and automatically maintain login sessions using SharedPreferences.

---

## 👥 Team Members

- **Jonathan Petra Fradana** (160423149)
- **Enrique Juan** (160423116)
- **Daniel Wuliutomo** (160423900)

---

## 📱 Features

- User Login Authentication
- Automatic User Registration
- Auto Login Session using SharedPreferences
- Create New Habit
- View Habit List
- Local Habit Storage using Room Database
- Persistent User Authentication
- Modern MVVM Architecture
- Responsive UI with Kotlin Coroutines
- View Binding Implementation
- Navigation Component with Safe Args

---

## 🏗️ Architecture

This project implements the **MVVM (Model-View-ViewModel)** architecture to separate the UI, business logic, and data layers.

```
UI (Fragment / Activity)
        │
        ▼
 ViewModel
        │
        ▼
 Room Database (DAO)
        │
        ▼
    SQLite
```

Main components include:

- **View**
  - Handles user interface and user interactions.
- **ViewModel**
  - Manages business logic and communicates between UI and data layer.
- **Model**
  - Represents Room entities and DAO interfaces.
- **Room Database**
  - Stores user accounts and habit data locally.
- **SharedPreferences**
  - Manages login sessions and Auto Login functionality.

---

## 📂 Project Structure

```text
com.ubayadev.peptideuts
├── model
│   ├── Habit.kt
│   ├── HabitDao.kt
│   ├── HabitDatabase.kt
│   ├── ListHabitAdapter.kt
│   ├── User.kt
│   └── UserDao.kt
│
├── util
│   ├── FileHelper.kt
│   ├── SharedPrefManager.kt
│   └── Util.kt
│
├── view
│   ├── CreateHabitFragment.kt
│   ├── DashboardFragment.kt
│   ├── HabitAdapter.kt
│   ├── LoginFragment.kt
│   └── MainActivity.kt
│
└── viewmodel
    ├── CreateHabitViewModel.kt
    ├── DashboardViewModel.kt
    └── LoginViewModel.kt
```

---

## 🛠️ Technologies Used

| Technology | Description |
|------------|-------------|
| Kotlin | Main programming language |
| Android Studio | IDE |
| MVVM | Application architecture |
| Room Database | Local database persistence |
| SQLite | Database engine |
| ViewModel | Business logic layer |
| LiveData | Observable UI data |
| Kotlin Coroutines | Background processing |
| RecyclerView | Display list of habits |
| Navigation Component | Fragment navigation |
| Safe Args | Type-safe navigation |
| SharedPreferences | Session management |
| View Binding | Safe view access |

---

## 📖 Module Description

### Model

Responsible for representing application data and database access.

- **Habit.kt**
  - Room Entity representing habit data.

- **HabitDao.kt**
  - Provides CRUD operations for Habit.

- **HabitDatabase.kt**
  - Main Room Database configuration.

- **User.kt**
  - Room Entity representing registered users.

- **UserDao.kt**
  - Handles user authentication and registration.

- **ListHabitAdapter.kt**
  - RecyclerView adapter for displaying habit items.

---

### View

Responsible for displaying UI and handling user interactions.

- **LoginFragment**
  - User authentication screen.

- **DashboardFragment**
  - Displays all stored habits.

- **CreateHabitFragment**
  - Allows users to create a new habit.

- **HabitAdapter**
  - Adapter for displaying habit cards.

- **MainActivity**
  - Hosts Navigation Component.

---

### ViewModel

Contains business logic and communicates with Room Database.

- **LoginViewModel**
  - Handles login, auto-registration, and session validation.

- **DashboardViewModel**
  - Retrieves habit data from Room Database.

- **CreateHabitViewModel**
  - Validates and stores new habits.

---

### Utility

Contains helper classes used throughout the application.

- **SharedPrefManager**
  - Handles Auto Login and user session.

- **FileHelper**
  - Utility for file management.

- **Util**
  - General helper and extension functions.

---

## 🚀 Main Features

### 🔐 User Authentication

- Login using username and password.
- Automatically registers new users if the account does not exist.
- Credentials are stored locally using Room Database.

---

### 👤 Auto Login Session

The application remembers the logged-in user using **SharedPreferences**.

When the application is reopened:

- Logged-in users are redirected directly to the Dashboard.
- Login is only required after logging out.

---

### 📝 Habit Management

Users can:

- Create new habits.
- View all saved habits.
- Store habits permanently using Room Database.

---

### ⚡ Background Processing

Database operations are executed using **Kotlin Coroutines** with `Dispatchers.IO` to keep the UI responsive.

---

### 📦 Local Database

The application uses **Room Database** for:

- User authentication
- Habit storage
- Persistent local data

---

## 📚 Learning Objectives

This project demonstrates the implementation of:

- MVVM Architecture
- Android Jetpack Components
- Room Database
- LiveData
- ViewModel
- Kotlin Coroutines
- RecyclerView
- Navigation Component
- Safe Args
- View Binding
- SharedPreferences Session Management
- Local Authentication System

---
