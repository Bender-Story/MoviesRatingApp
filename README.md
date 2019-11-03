**# MoviesRatingApp**

**Recent Movie details**

This App has been created to show the latest Movies list with few details of the Movie.
```
Screen 1 : Has the list of the Movies sorted as per their ratings
Screen 2 : The Movie details of the movie that user has selected from the list and it also shows a list of related movies at the bottom with a horizontal scroll.
```

This code has been written in Kotlin - Android using Androidx components in MVVM Pattern.

This code has the unittests written using junit and Instrumentation test cases written using espresso.

This app also consists of a local database, so whenever there is no service available it would ask the user if he/she wants to fetch from the local database.

The libraries or languages that are being used for this project are.
```
1. Kotlin
2. Retrofit - service calls
3. junit - Testing
4. espresso - ui Testing
5. Mockito - Testing
6. Gson
7. Rxjava
8. RecyclerViews
9. cardViews
10. androidx components
11. coroutines
12. anko
13. Room - For Local Data Base.
```
***URL used ***

https://api.themoviedb.org/3/movie/now_playing?api_key=81b19ffd8a7ac360cce26f4f071e72ee&language=en-US&page=1
