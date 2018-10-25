# DepositBookkeeperApp

The application is designed to manage the deposits flow, retrieve data per user and account certain data parameters.

In order to launch this App you would need the following technologies stack installed and running on your PC:

- Apache ZooKeeper https://zookeeper.apache.org/
- Apache Kafka https://kafka.apache.org/
- Maven plugins https://maven.apache.org/
- Java 1.8 or later 

You could start working with my application by cloning git repository to your computer, opening it in IDE and running from there or you could download compiled .jar file and execute it in Console.

- In both cases you would need to start Zookeeper and Kafka beforehand. Here is a good tutorial on that : https://dzone.com/articles/live-dashboard-using-apache-kafka-and-spring-webso
- After that is done, you have the apps running on localhost: 9092 and localhost: 5656, from that point you can launch DepositBookkeeperApp, in IDE or Console (Please be aware that the ports can be adjusted/configured in application.properties)

I have to make a note that this app is using in-memory H2 database, which stores data to the same folder as the .jar file, or in the root directory of the project, when App is opened in IDE, so don't be surprised when you'll see file with .mv.db extension on your desktop.


Thank you for your attention to my work! 