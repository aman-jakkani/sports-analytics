# Sports Analytics
Given the increasing amount of data gathered in sporting events, new statistical methods have been increasingly used to analyze sports data. These methods can be used to prepare players and teams for competitions or even a better computation of betting odds for sports betting. Sports Analytics is an integrated visualization tool for sports data analysis. Its goal is to consolidate many solutions for individual sports into a single, unified tool.

## Dependencies
Sports Analytics uses the Java Runtime Environment SE 8 to access its Oracle DB database. Java Runtime Environment SE 8 is linked below. 

[Java Runtime Environment SE 8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

If you have java installed on your system, you can check its version using the following command:

```
java -version
```

It should prompt "java version "1.8.0_131"", but SE 7 should work too. Make sure to shutdown other webservers running (like e.g. XAMPP)

## Getting started
Clone the repository onto your local machine using

```
git clone https://github.com/sports-analytics/sports-analytics
```

Then start the application by double clicking the sportanalytics.jar in the project's root directory. If everything is fine, the server should prompt "finished initialization" after a few seconds. When prompted, insert the password into the login screen. You should now be able to access the website by typing "localhost:8081" in your browser.

The webserver makes every file in the /web folder accessible
The website can be accessed at "localhost:8081/index.html"
