# Medicine Data App

## Overview
This project is an Android application that manages medicine data, including various disease and drug-related information. It leverages modern Android development practices such as MVVM architecture, Room for local database storage, Dagger Hilt for dependency injection, Kotlin Coroutines for background tasks, and Mockito for unit testing.

## Features
- **Room Database**: Persist medicine data locally.
- **MVVM Architecture**: Separation of concerns with a ViewModel, UseCase, and Repository pattern.
- **Dagger Hilt**: Dependency injection for ViewModel, Repository, and other components.
- **Kotlin Coroutines**: Background operations (e.g., database access) are performed using coroutines.
- **Unit Testing**: Mockito and JUnit for unit tests.

---

## Project Structure

```bash
app/
 ├── data/
 │   ├── database/     # Room Database DAO and Entity classes
 │   └── model/        # Data model classes for diseases and drugs
 ├── domain/           # Business logic (UseCases)
 ├── presentation/     # ViewModels and UI-related code
 ├── di/               # Dagger Hilt module definitions
 └── utils/            # Utility classes
