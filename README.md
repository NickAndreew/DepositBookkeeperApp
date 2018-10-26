# DepositBookkeeperApp



The application is designed to manage the deposits flow, retrieve data per user and account certain data parameters.


In order to launch this App you would need the following technologies stack installed and running on your PC:

- Apache ZooKeeper https://zookeeper.apache.org/
- Apache Kafka https://kafka.apache.org/
- Maven plugins https://maven.apache.org/
- Java 1.8 or later 


You could start working with my application by cloning git repository to your computer(recommended), opening it in IDE and running from there or you could download compiled .jar file (PlayerDepositApp.jar in the root directory) and execute it in Console.

- In both cases you would need to start Zookeeper and Kafka beforehand. Here is a good tutorial on that : https://dzone.com/articles/running-apache-kafka-on-windows-os
- After that is done, normally, you should have 'Zookeeper' server running on 'localhost:2181' and 'Apache Kafka' running on 'localhost: 9092', from that point you can launch DepositBookkeeperApp, in IDE or Console. Default port for the app is set to 'localhost:5656', can be reconfigured in './src/main/resources/application.properties'.


I have to make a note that this app is using embedded-memory H2 database, which stores data to the file, usually in the same folder with the .jar file, or in the root directory of the project, when App is opened in IDE, so don't be surprised when you'll see file with .mv.db extension on your desktop.


While the App is running, feel free to open it on http://localhost:5656/home. Also, I have to mention that the App does not have any UI for removing/deleting deposits from the tables, so if you would need to remove some data from the DB, you would have to open http://localhost:5656/h2-console/ , connect with whatever credentials there are by default and manage the data manually.


Hope you will enjoy testing it. Thank you for your attention to my work! 
