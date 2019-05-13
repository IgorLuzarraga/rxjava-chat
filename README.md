REACTIVE CHAT WITH RXJAVA2
==============

Spring Boot application that implements a reactive chat using RxJava2.
The GUI is made using the framework Vaadin.

Modules:
========
- Spring Boot
- Vaadin - Java web framework - https://vaadin.com
- RxJava2

Build the jar:
-------------------------
./gradlew build

Run the jar:
-------------------------
java -jar build/libs/rxjava-chat-0.0.1-SNAPSHOT.jar

How to use the application:
-------------------------
Connect to the server via http://localhost:8080 and start 
sending messages.
At least two chat users are needed.

You can see that the application is reactive, when any time one 
user sends a message the other users are automatically updated.




