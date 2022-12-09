# SeedBank - Personal Gardening Help System
![Slice 1 (2)](https://user-images.githubusercontent.com/81552207/206712277-7139cb80-b480-419a-98c1-86ecbff64f23.png)


## Project Description
This project is a Personal Gardening Help System that acts as an inventory management system for seeds. It was created using Java, JavaFX, and SceneBuilder. The program connects to a MySQL database (hosted using XAMPP) to save and retrieve data. The SeedBank Personal Gardening Help System helps users manage their seed inventory, and automate their planting and harvest schedules. The system includes a functional graphical user interface (GUI), user authentication, and various features to help users manage their gardening activities.

![Hello 2022-12-09 02-59-01](https://user-images.githubusercontent.com/81552207/206688621-23ed53a2-cb89-4259-a5e8-c1f4a3c55727.gif)

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Reason for Project](#reason-for-project)
- [Installation Instructions](#installation-instructions)
- [Interfaces](#interfaces)
- [Future Development](#future-development)

## Features
* Functional GUI
* User authentication with sign-up and login
* Seeds inventory with ability to add, update, and mark seeds as harvested or purchased
* Search function for seeds by name, category, planting time, and expiry date
* Images of fruit/vegetables in search results
* Automatic check for what vegetables should be planted in the current month
* Pop-up window on first day of each month with list of vegetables to be planted
* Automatic check for seed quantity (minimum: < 50g) and expiry date (minimum: < 1 year)
* Pop-up window alerts for low seed quantity and expiring seeds
* Marking of "planted" and "wasted" seeds and removal from inventory

## Reason for Project
I created this project as a fourth year university student to showcase my skills in Java, JavaFX, and database management. It serves as a useful tool for managing a personal gardening inventory and keeping track of important information such as expiry dates and quantities.

## Installation Instructions
1. Install [Java](https://www.java.com/download/ie_manual.jsp) on your system.
2. Install [XAMPP](https://www.apachefriends.org/download.html) and start the MySQL server.
3. Install [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)( and create a new MySQL connection.
4. In MySQL Workbench, click Server > Data Import > Import from Self-Contained File > and select the 'seedbank_db.sql' file uploaded with this project, and click Start Import.
5. Clone this repository and open the project in an IDE (such as [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?fromIDE=)).
6. To load the project in IntelliJ:
    * Extract the 'V24_Project.zip' file
    * Open the project folder in IntelliJ and add any Maven Build scripts if prompted by the IDE
    * Right click the .jar file in the file structure (V21_Project\V2_Project\demo\lib\mysql-connecter-java.8.0.28.jar) and click "Add as Library..."
    * In the IntelliJ taskbar, click Edit > Find > Replace in files > and replace "jdbc:mysql://localhost:3306/demo", "root", ""); with your MySQL root username and password.
7. Run SeedBank.main();
8. (*OPTIONAL*) To modify the layout or change elements of the UI, install [JavaFX](https://gluonhq.com/products/javafx/) and [SceneBuilder](https://gluonhq.com/products/scene-builder/), and use SceneBuilder to open and modify the .FXML files in the project folder.

## Interfaces
After logging in, the user is presented with the main screen of the program. From here, the user can access the Seeds Inventory to add, update, and mark seeds as harvested or purchased, or navigate to other interfaces to add new seed entries, search existing entries, review their planting schedule, update their user settings, and create reports.

### Register
Upon launching the program for the first time, users are instructed to register for an account. The registration form requests for the user to enter a username, password, and preferred alias (optional). The provided login credentials are then stored in the MySQL database, and the login credentials are used for future logins.

![image](https://user-images.githubusercontent.com/81552207/206692959-20d6a865-a6bb-4697-a00a-1de27f4cf618.png)

### Login
Once users have registered, they are able to login from the login portal. 

![image](https://user-images.githubusercontent.com/81552207/206693229-d688d151-e03f-47a7-9518-0eb5db564aee.png)

### Dashboard
The user can search for seeds using the search function. Results will include images of the fruit/vegetable, as well as other relevant information. Users can also view their alerts from this screen.

![image](https://user-images.githubusercontent.com/81552207/206693378-5870bca1-db92-44da-8aa5-35ca3ddb2064.png)

### Seed Entry
The user can create new seed entries by navigating to the Seed Entry page, selecting a Seed Type, and completing the form to provide information regarding method obtained (harvested or purchased), quantity (in grams), and expiry date. This page also displays an image of the seed type to make selection of the correct seed type easier for the user. 

![image](https://user-images.githubusercontent.com/81552207/206690454-2b626035-4e3a-4bf3-a64a-300b20c94e45.png)

### Search results
The program will automatically check for what vegetables should be planted in the current month and display a pop-up window on the first day of the month with a list of the vegetables to be planted.

![image](https://user-images.githubusercontent.com/81552207/206690548-1e48f1ff-ceec-487f-a499-427e7660843c.png)

### Planting Schedule
This page allows users to view seeds that are available to plant in the current year given a specific month. Currently, the planting schedule references the "optimal planting month" for each seed type, and only returns matching seed entries that are not "Wasted".

![image](https://user-images.githubusercontent.com/81552207/206691758-fd7a6d38-4fe4-496e-a063-1901b7caa301.png)

### User Settings
This page allows users to modify their user settings, including preferred alias and current password. In the future, a "dark mode" option may be made available to users.

![image](https://user-images.githubusercontent.com/81552207/206691878-eb574afe-ab5e-4d52-9809-f4b1da40ab23.png)

### Reports
This page allows users to automatically generate reports based on their seed entry and seed inventory activity. These reports include: Top 5 Harvested Seeds, Top 5 Purchased Seeds, Top 5 Wasted Seeds, and Next Year's Gardening Plan. Currently the reports are generated and stored in the database, though in the future, we may offer more detailed reports to be exported to a PDF or CSV file.

![image](https://user-images.githubusercontent.com/81552207/206692124-5f654c66-dabd-40f3-a8cc-ec43b3fd52f4.png)

### Pop-up window
The program will also automatically check the quantity and expiry date of seeds in the inventory and display pop-up alerts if necessary. Seeds that have expired can be marked as "wasted" and removed from the inventory.

![image](https://user-images.githubusercontent.com/81552207/206692465-608da2c7-904b-4f67-9017-5bc2ef7bf338.png)

## Future Development
In the future, I plan to add more features to the Personal Gardening Help System, including:
* Complete "Planting Schedule" interface to provide more functionality and detailed scheduling, including the option to search through multiple years (currently only searches the current year)
* Add a forgot password function to the login screen
* Add functionality to allow users to modify an existing seed entry
* Fully functional report generation (Top 5 Harvested Seeds, Top 5 Purchased Seeds, Top 5 Wasted Seeds) 
* A calendar view to track when seeds have been planted and harvested
* A shopping list feature to keep track of seeds to be purchased
* Improved user interface and visual design
* Integration with external gardening resources and tools!
