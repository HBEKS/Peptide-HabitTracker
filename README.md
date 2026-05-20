# Mid Term Project - Advanced Native Mobile Programming (ANMP)

Project ini merupakan aplikasi **Habit Tracker** yang dikembangkan sebagai syarat Project UTS mata kuliah *Advanced Native Mobile Programming*. Aplikasi ini dibangun menggunakan bahasa pemrograman Kotlin dengan menerapkan arsitektur modern Android.

## 👥 Anggota Kelompok
* **Jonathan Petra Fradana** (160423149)
* **Enrique Juan** (160423116)
* **Daniel Wuliutomo** (160423900)

## 📂 Struktur Folder
Proyek ini mengikuti struktur package yang rapi untuk memisahkan logika bisnis dan tampilan:
```text
com.ubayadev.peptideuts
├── view
│   ├── MainActivity.kt (Container)
│   ├── LoginFragment.kt (UI Login)
│   └── DashboardFragment.kt (UI Dashboard)
├── viewmodel
│   ├── LoginViewModel.kt (Logic Login)
└── model
    └── (Data Classes & Repository)
