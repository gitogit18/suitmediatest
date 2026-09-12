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

### First Screen

![First Screen](screenshots/first_screen.png)

### Second Screen

![Second Screen](screenshots/second_screen.png)

### Third Screen

![Third Screen](screenshots/third_screen.png)

### User Selection

![Selected User](screenshots/selected_user.png)

> Place the UI screenshots inside the `screenshots/` directory using the filenames shown above.

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
