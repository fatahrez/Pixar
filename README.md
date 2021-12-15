# Pixar
Search image app [Adanian Labs Interview], Android developer role

## Approach ##
### Architecture 
This app has an architecture in place to allow the App to **Scale**, improve Quality and Robustness and Allow the App to Scale. This also makes the App to Scale

<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/CleanArchitecture.jpeg" width="500"/>
This App uses Clean Architecture to ensure:

- Separation of **concern**
- Drive UI from Data Model
- Make functionality easily changeable or droppable 
- Make Code easier to read
- Make use of good practices and Jetpack libraries(Architecture components)

#### Layers
<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/googleclen.png" width="500"/>

**Domain Layer**

- Sits between the UI and Data layer
- Used in this project to encapsulate business logic
- Enables use-cases to be reused in multiple view model
- Defines the repository interface that drives the main functionality

**Data Layer**

- Contains the implementation of business logic(Repository Implementation)
- Gets data from the remote data source
- Cache's remote data to Local Room Database

**Presentation/UI layer**

- This layer is the layer that displays data to the user screen
- Contains view models that are lifecycle friendly and takes code away from our Activity/UI components
- Defines our architecture which is MVVM (Model View View-Model)
- Contains our states that handle logic like loading

**Dependency Injection**

- Used Hilt Dagger library for dependency injection
- Allows classes to define their dependencies without constructing them
- Also brings all the layers of the App together
- Helps UI layer to be driven from data layer

## Screenshots
<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/Screenshot_20211215_121124.png" width="300"/>.<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/Screenshot_20211215_121147.png" width="300"/>.<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/Screenshot_20211215_121200.png" width="300"/>.<img src="https://raw.githubusercontent.com/fatahrez/Pixar/development/screenshots/Screenshot_20211215_121208.png" width="300"/>

## Libraries used

- **Retrofit** - Android Network Client, Used to consume API from Pixabay API
- **Room** - SQLite ORM - used to save data to the phone's database for caching
- **Hilt Dagger** - Used for dependency Injection
- **Coroutines** - Used to execute code asynchronously
- **Jetpack Compose** - Used to write the declarative UI of the App
- **Material Design** - Give the App a theme and generally improve UI of the App
- **Coil Image** - Image Loading library
- **Lifecycle library** - Majorly to define the ViewModels of the app
- **Navigation** - To navigate to different screens of the App
