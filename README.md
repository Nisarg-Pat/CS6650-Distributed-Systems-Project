# CS6650-Distributed-Systems-Project - BookKeeper
BookKeeper is a simple e-commerce distributed application. It is a platform that allows user to keep track of the books that they own all from a single application. Using BookKeeper, user can store the details of the books they have in their bookshelf, sell them or buy new books all from the same place. User needs to create an account to interact with the application.
This project is focused mainly on making a distributed application, thus making the functionality of the application simple but sufficient to be completed in the required time.

## Running requirements
1) This project requires node to run the client application. Install the latest version of node from: https://nodejs.org
2) I used maven for java 19. Please install both maven if you want to build the project and java 19 to run the server application.

## How to Run

### Cooridinator
1) To run the coordinator, open the server folder in command prompt:
   > cd server
2) Coordinator always runs at port 8080. Run the coordinator app by typing
   > java -jar target/server-0.0.1-SNAPSHOT.jar --ds-coordinator

### Server
1) To run the server instances, open the server folder in command prompt:
   > cd server
2) Run the server app by typing the command, and changing the ports:
   > java -jar target/server-0.0.1-SNAPSHOT.jar localhost --server.port=8011

   > java -jar target/server-0.0.1-SNAPSHOT.jar localhost --server.port=8022

   > java -jar target/server-0.0.1-SNAPSHOT.jar localhost --server.port=8033

   > java -jar target/server-0.0.1-SNAPSHOT.jar localhost --server.port=8044

   > java -jar target/server-0.0.1-SNAPSHOT.jar localhost --server.port=8055

### Client
1) To run the client, open the client folder in command prompt:
   > cd client
2) Run the client app by typing:
   > npm start
3) Client runs on port 3000. Open a web browser and type:
   > http://localhost:3000