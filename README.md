# Paging 3 Compose

This a sample project for implementing Paging library by using Github search user API. It's a Kotlin base project with most of the new Android technology for now (2023). 

The architecture is [MVVM](https://developer.android.com/topic/architecture)   



# Acceptance criteria

- Implement filtering for GitHub users
    - Only send the actual request after the second keystroke
    - Add 1 second throttling before you would start the request
    - Use GitHub REST API for your solution ([GitHub REST API documentation - GitHub Docs](https://docs.github.com/en/rest?apiVersion=2022-11-28))
- UX
    - Prepare the app for general error handling (4xx and 5xx errors, offline)
    - Implement loading states for async operations
- Testing
    - Cover your solution with unit tests, no need to test the UI

    
Prerequisites
=============
    - Compile SDK 34
    - Gradle version 8.0
    - Koltin 1.8.10 

## Author
[Ali Shatergholi](https://github.com/crypto-sh)
