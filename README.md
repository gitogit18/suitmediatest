An Android application developed as part of. Mobile Developer Technical Test.

The application consists of three screens and demonstrates palindrome validation, screen-to-screen data passing, user data fetching from the ReqRes API, pagination, pull-to-refresh, empty state handling, and user selection.

---

## Features

### First Screen
- Input user's name
- Input sentence for palindrome validation
- Palindrome validation
- Dialog displaying the validation result
- Navigation to the second screen
- Passes the entered name to the second screen

### Second Screen
- Displays a static welcome message
- Displays the user's name from the first screen
- Displays the currently selected user
- Navigation to the user selection screen

### Third Screen
- Fetches users from the ReqRes API
- Displays user avatar, name, and email
- Loading indicator
- Pull-to-refresh
- Pagination / load next page when reaching the bottom
- Empty state
- Select a user and return the selected user to the second screen

---

## Screenshots


<img width="500" alt="UI" src="https://github.com/user-attachments/assets/a3c63721-3672-427b-b904-a5c70768439e" />

---

## Tech Stack

- **Kotlin**
- **Android SDK**
- **XML Layout**
- **ViewBinding**
- **MVVM Architecture**
- **Retrofit**
- **Gson Converter**
- **OkHttp**
- **RecyclerView**
- **Coil**
- **SwipeRefreshLayout**
- **Kotlin Coroutines**
- **StateFlow**

---

## Architecture

The application follows the **MVVM (Model-View-ViewModel)** architecture with a feature-based UI structure.

```text
UI / View
│
├── FirstActivity
├── SecondActivity
└── ThirdActivity
        │
        ▼
    UserViewModel
        │
        ▼
   UserRepository
        │
        ▼
   UserApiService
        │
        ▼
    ReqRes API
```

  ##  How to Run
```
1. Clone this repository.
2. git clone <YOUR_REPOSITORY_URL>
3. Open the project in Android Studio.
4. Add your ReqRes API key to local.properties.
5. REQRES_API_KEY=YOUR_API_KEY
6. Sync the Gradle project.
7. Connect an Android device or start an Android Emulator.
8. Run the application.
```
