# Anime Viewer App

A feature-rich Android application that allows users to browse popular or top-rated anime and view detailed information, including trailers or posters, using the Jikan API.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Requirements](#requirements)
3. [Project Features](#project-features)
4. [Assumptions Made](#assumptions-made)
5. [Features Implemented](#features-implemented)
6. [Known Limitations](#known-limitations)
7. [Technologies Used](#technologies-used)
8. [Project File Structure](#project-file-structure)
9. [How to Run the Project](#how-to-run-the-project)
10. [Future Enhancements](#future-enhancements)
11. [Contributing](#contributing)
12. [License](#license)

---

## Project Overview

The Anime Viewer App allows users to:
- Browse a list of popular or top-rated anime fetched using the Jikan API.
- View detailed information about an anime, including its trailer or poster, genres, main cast, number of episodes, rating, and synopsis.

---

## Requirements

### 1. Anime List Page
- Fetch a list of popular or top-rated anime using the Jikan API.
  - **API Endpoint**: [https://api.jikan.moe/v4/top/anime](https://api.jikan.moe/v4/top/anime)
- Display the following information on the home screen:
  - **Title**
  - **Number of Episodes**
  - **Rating** (e.g., MyAnimeList score)
  - **Poster Image**

### 2. Anime Detail Page
- When an anime is clicked, open a detailed page displaying:
  - **Trailer Video** (if available) or **Poster Image** as a fallback.
  - **Title**
  - **Plot/Synopsis**
  - **Genres**
  - **Main Cast**
  - **Number of Episodes**
  - **Rating**
  - **API Endpoint**: [https://api.jikan.moe/v4/anime/{anime_id}](https://api.jikan.moe/v4/anime/{anime_id})

---

## Project Features

- **Anime List Page**:
  - Displays a scrollable list of anime with key details (title, episodes, rating, and poster).
  - Fetches data dynamically using the Jikan API.

- **Anime Detail Page**:
  - Plays the trailer using ExoPlayer if available.
  - Displays the poster image as a fallback for missing trailers.
  - Provides detailed information about the anime, including genres, main cast, and synopsis.

- **Dynamic Content**:
  - Automatically adapts to the availability of trailers or posters.

---

## Assumptions Made

1. **Anime Data**:
   - The app assumes the API returns complete and valid data, including trailer URLs and poster images.
2. **Video Playback**:
   - Trailer URLs provided are playable and compatible with ExoPlayer.
3. **Internet Connection**:
   - A stable internet connection is required to fetch anime data and play trailers.

---

## Features Implemented

- **Anime List Page**:
  - Fetches and displays anime data from the Jikan API.
  - Shows titles, episodes, ratings, and poster images.

- **Anime Detail Page**:
  - Trailer playback using ExoPlayer.
  - Poster fallback when the trailer is unavailable.
  - Detailed anime information (title, synopsis, genres, main cast, episodes, and rating).

### Known Limitations

- **Offline Support**:
  - The app does not support offline browsing or playback.
- **Error Handling**:
  - Limited handling for API failures or invalid data.
- **Main Cast**:
  - Only retrieves the main cast from the API but does not show character details.

---

## Technologies Used

- **Kotlin**: Programming language for Android development.
- **ExoPlayer**: Video playback library for trailers.
- **Picasso**: Image loading library for posters.
- **Jikan API**: API for anime data.
- **Android SDK**: Core tools for building Android applications.
- **Gradle**: Dependency management and build automation.

---

## Project File Structure
```
AnimeViewerApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/animeviewer/
│   │   │   │   ├── MainActivity.kt          # Home screen activity for anime list
│   │   │   │   ├── DetailActivity.kt        # Detail screen activity for anime details
│   │   │   ├── res/
│   │   │   │   ├── drawable/                # Images and icons
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml    # Layout for anime list
│   │   │   │   │   ├── activity_detail.xml  # Layout for anime details
│   │   │   │   ├── mipmap/                  # App icons
│   │   │   │   ├── values/                  # Strings, colors, styles
│   │   │   └── AndroidManifest.xml          # App manifest file
│   ├── build.gradle                         # Module-level Gradle file
│   └── proguard-rules.pro                   # ProGuard configuration
├── build.gradle                             # Project-level Gradle file
└── README.md                                # Project documentation
```

---
## How to Run the Project
1. Clone the repository:
   ```
   git clone https://github.com/yourusername/AnimeViewerApp.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle and ensure all dependencies are installed.
4. Build and run the app on an emulator or a physical device.

---
## Future Enhancements
1. Search Feature:
Add a search bar to allow users to find specific anime.

2. Pagination:
Implement pagination for the anime list to improve performance.

3. Offline Mode:
Cache trailers and posters for offline viewing.

4. Error Handling:

5. Add comprehensive error handling for API failures and invalid data.
Favorites:

6. Allow users to mark anime as favorites for quick access.

---
## Contributing
1. Contributions are welcome! Please follow these steps:

2. Fork the repository.
3. Create a feature branch.
4. Commit your changes.
5. Submit a pull request with a detailed explanation.

---
## License
This project is licensed under the MIT License.
