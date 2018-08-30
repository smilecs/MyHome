# MyHome Article Review app

## Installation Requirements
This app uses min sdk 16 and target sdk 28
* Download and install android studio 3.1+
* Ensure your Android studio uses android gradle plugin 3.1.4
* Ensure you sync your files with gradle so it can pull dependencies for the project

## Dependencies Overview
This app was built using kotlin, below are some of the libraries used and brief on their importance
* Google support libraries for design components and widgets
* Retrofit 2.3.0 for networking/api interactions
* Moshi 2.3.0 for serialising json into native objects
* Gson 2.8.5 for in-app parsing of lists into string to support addition into Room ORM
* Room persistence library: local caching of articles for review and state restoration
* Architecture components(LiveData, ViewModel): Enable an observable UI flow with delivering data when view is in a recievable state
* Ketro(My personal open source library): Used for enabling error handling and easily wrapping retrofit requests as LiveData streams
 for more info on ketro check https://github.com/smilecs/ketro

* Anko: extensions library for shorter method calls like creating async tasks in a single line `do async{}`

## Structure

Project uses an MVVM pattern based off android architectural components, files are separated by concerns and features.
