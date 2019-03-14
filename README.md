API wiki: https://developer.valvesoftware.com/wiki/Steam_Web_API 
Sample user key: 76561197960435530
API Key: 36CA30F19E38A1E627CED21BA4CD3342

Demo Due: Monday, March 18th
Code Due Date(??): 5PM, Friday, March 22nd

Team Eval: https://docs.google.com/document/d/1nAeLEjkrwo9iwRfNvDHyR6uLj6S9Pe4kHike4veE5pk/edit
 
Features:
Vertical list of saved users
Add and remove users by their Steam user key (
Button to manually refresh the info
Light and dark color theme
Use notifications when the active status of a saved user changes????
Tap to view more info about a user 

From API:
Username
Online status
Avatar
Link to online profile

Main page: Onclick opens up more details -> separate page
2-3 element including:
Avatar
Name
Online status

Details page: 
5-6 elements including:
Recently played games
Link to online profile
Avatar
Name
Online status
Steam ID

Settings page:
2 - 3 elements including
Light/dark theme
Text size
User sorting option




Requirement
Plan
Complete?
It should have multiple activities the user can navigate between.

Main page and settings page




It should use at least one implicit intent to launch another app.


Connect to Steam app through button




It should communicate via HTTP(s) with a third-party API to provide data for the app and optionally to send data back to the API.

Steam API
https://developer.valvesoftware.com/wiki/Steam_Web_API



It must implement activity lifecycle methods to ensure that activity-related data is handled elegantly through lifecycle events.


Scroll events viewing friends does not refresh on lifecycle changes, but stays on currently viewed friend



It should either store user preferences (via SharedPreferences) or store data in device storage (using SQLite). You may do both of these things if you want.


Settings page
Light/Dark theme
Sort by preference
Text size



It should have a polished, well-styled user interface.






Clear sample

{"response":{"players":[{
"steamid":"76561197960435530",
"communityvisibilitystate":3,
"profilestate":1,
"personaname":"Robin",
"lastlogoff":1552462427,
"profileurl":"https://steamcommunity.com/id/robinwalker/",
"avatar":"https://steamcdna.akamaihd.net/steamcommunity/public/images/avatars/f1/f1dd60a188883caf82d0cbfccfe6aba0af1732d4.jpg",
"avatarmedium":"https://steamcdna.akamaihd.net/steamcommunity/public/images/avatars/f1/f1dd60a188883caf82d0cbfccfe6aba0af1732d4_medium.jpg",
"avatarfull":"https://steamcdna.akamaihd.net/steamcommunity/public/images/avatars/f1/f1dd60a188883caf82d0cbfccfe6aba0af1732d4_full.jpg",
"personastate":0,
"realname":"Robin Walker",
"primaryclanid":"103582791429521412",
"timecreated":1063407589,
"personastateflags":0,
"loccountrycode":"US",
"locstatecode":"WA",
"loccityid":3961}]}}
