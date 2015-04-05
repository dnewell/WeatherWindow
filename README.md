![Imgur](http://i.imgur.com/F9Cn09l.png)

## Synopsis
<br>
Weather Window is an end user appliacation that provides current, short-term, and long-term weather forcasts for a desired location.  All of aforementioned weather information is displayed to the user in its beautiful, world-class graphic-user-interface (GUI).
	
All of Weather Window's weather information is sourced from openweathermap.org's free [API](http://openweathermap.org/), and then gathered using the [JSON library](http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm) of functions.  To generate sky condition icons for the current, short, and long-term forcasts, the [ow-font](http://websygen.github.io/owfont/) pack was used.  [Ow-font](http://websygen.github.io/owfont/) is a custom font pack designed to match it's font icon images to all weather ids returned by OpenWeatherMap.  Font packs [FontAwesome](http://fortawesome.github.io/Font-Awesome/) and 
[Weather-Icons](http://erikflowers.github.io/weather-icons/) were also used.
</br>

## Install
1. From the Repository home page click the 'Download ZIP' button
2. Find the ZIP file where files are typically saved after downloading
3. Uncompress the file.  Right click extract for windows, or open with 'Archive Utility' for mac
4. Find the extracted file, and click and open the 'WeatherApp' folder.  From there you can double click the jar file named '22-WeatherApp.jar' and the program should run.  Or from a terminal window type the command (sans quotes): "java -jar 22-WeatherApp.jar".  Make sure for the terminal method that you are in the folder with the jar.

## Build
1.  Follow steps 1-3 from the 'Install' instructions above to download the needed files
2.  Have the latest version of maven installed on your computer (refer to maven download and install instructions if not)
3.  Open a terminal, make sure you are in within the 'WeatherApp' folder, and type (sans quotes) "mvn package"
4.  Any dependecies to run the application should download (if not don't worry about), after packaging a executable jar (22-WeatherApp.jar) should be found in the newly made 'target' folder

## Usage Example
Here is a demostration video of Weather Window in action: https://www.youtube.com/watch?v=CYdVTKYqQSM

## Documentation
1.  Follow steps 1-3 from the 'Install' instructions above to download the needed files
2.  Open the 'doc' folder within the 'WeatherApp' folder
3.  In the folder contains all Java doc files detailing the construction of the application
4.  These files are viewable in your browser of choice
