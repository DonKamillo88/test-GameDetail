# test-GameDetail
List - detail app, different layout for tablets and phones, localisation date and currency format, cache data

Retrofit, Glide, Gson, mockwebserver
Unit, Instrumentation and UI tests

Task description:
Implement a simple 2 view application. The app should parse the JSON files provided with the test and display the info as described below.

Model:
The application should pull the latest version of the JSON from the url provided. The data retrieved should then be cached someway with an expiry date of 1 hour (the cache requirement is just for the gameData.json). The application will then use that data to populate the views.

View 1:
It should display a list of items using the value of data.name as the label. On clicking an item it should take you to View 2 which will display the details of the game.

View 2:
It should display the name, jackpot and date of the game, using best practices for locale formatting. Use currency provided in JSON to format jackpot. Both the views must have a header showing an avatar image, player name, balance and last login date which are retrieved by requesting the playerInfo.json. In View 2, last login date must be hidden.

JSON game data file location: https://dl.dropboxusercontent.com/s/2ewt6r22zo4qwgx/gameData.json
JSON header info file location: https://dl.dropboxusercontent.com/s/5zz3hibrxpspoe5/playerInfo.json

Requirements:
• All dates and numbers must have correct localisation support and should be displayed using the device’s locale.
• Code must be compatible with Android 4+
• Use of third party libraries is encouraged
• Views must be adaptive to device screens (phone + tablet)
• Unit tests and Android tests must be provided
