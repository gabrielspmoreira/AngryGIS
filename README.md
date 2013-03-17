Introduction
===========

*AngryGIS** is an useless game for Android where player places in an interactive map "angry birds" and green pigs, that are attacked by the birds. 
It consists of just an initial Proof-Of-Concepts using ArcGIS Runtime for Android, but you can find some samples of:
* How to interact with Single-Tap and Long-Tap in the map
* How to animate graphics in the map using a thread, like a tracking application
* How to move a graphic toward other graphic, using some trigonometry
* How to play with PictureMarkerSymbol

Screenshots
-----------

![AngryGIS screenshots](https://raw.github.com/gabrielspmoreira/AngryGIS/master/resources/Screenshot.png) 

How to play
-----------
- Single Tap to position random birds (each type with specific speeds)
- Long Tap to position a pig, that will be aimed by all the birds

Environment
-----------
This is the current development environment we are using:

* ADT Bundle for Windows (Eclipse (Juno) + ADT plugin) - http://developer.android.com/sdk/index.html
* Oracle Java Development Kit (JDK) 6 - http://www.oracle.com/technetwork/java/javase/downloads/index.html
* ArcGIS Runtime SDK for Android 10.1.1 - http://resources.arcgis.com/en/communities/runtime-android/
                    
P.s. In order to make ESRI Runtime SDK work on Android Emulator, follow the instructions in http://blogs.esri.com/esri/arcgis/2012/05/02/arcgis-runtime-sdk-for-android-v1-1-supports-android-emulator/

Dependencies
------------
* The app requires Android 2.3.3 as minimun platform (also minimun required by ArcGIS Runtime for Android), so the app is compatible with more than 95% of current Android devices.
* The game map is a widget of ArcGIS Runtime SDK for Android.
* The World basemap is provided by ESRI ArcGIS Online plaform, so the device must be online in order to see the basemap.

Resources
---------
*	[Android Developers official site](http://developer.android.com/) is a great resource
*	[ArcGIS Runtime SDK for Android](http://resources.arcgis.com/en/communities/runtime-android/) is the main resource for this ESRI API
