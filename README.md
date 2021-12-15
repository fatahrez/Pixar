<p align="left">
  <a href="#"><img alt="Android OS" src="https://img.shields.io/badge/OS-Android-3DDC84?style=flat-square&logo=android"></a>
  <a href="#"><img alt="Languages-Kotlin" src="https://flat.badgen.net/badge/Language/Kotlin?icon=https://raw.githubusercontent.com/binaryshrey/Awesome-Android-Open-Source-Projects/master/assets/Kotlin_Logo_icon_white.svg&color=f18e33"/></a>
  <a href="#"><img alt="PRs" src="https://img.shields.io/badge/PRs-Welcome-3DDC84?style=flat-square"></a>
</p>

# Pixar
This is App shows you Images from the **Pixabay API**

## Table of Contents

- [Functionalities](#functionalities)
- [Approach](#approach)
- [Screenshots](#screenshots)
- [How To Setup](#how-to-setup)
- [Libraries Used](#libraries-used)
- [Author Info](#author-info)


## Functionalities

- **Top Images** - A user is able to See Top Images
- **Image Details** - A user is able to see Image details when they click on an Image
- **Search Images** - A user can search for Images
- **Profile Screen** - A user get's to see a profile screen

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

## How to Setup

Clone this repo

    $ git clone git@github.com:fatahrez/Pixar.git

or HTTPS

    $ git clone https://github.com/fatahrez/Pixar.git

Open the project with Android Studio and build using gradle. Feel free to run the App on your phone.

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

# Author Info 

Twitter - [@Abdullfertah](https://twitter.com/Abdullfertah)

LinkedIn - [Abdulfatah](https://www.linkedin.com/in/abdulfatah-mohamed-83709a177/)


[Back To The Top](#pixar)
