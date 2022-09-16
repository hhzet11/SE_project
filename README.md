# Software Engineering project "Closet"

***
## Objective
Do you exactly remember what clothes are in your closet?
Don’t you spend too much time deciding what to wear in every morning?
With closet
* You can check what clothes you have  
* You can find best dress code anytime

***
## Requirement - functional
1. Login
* Login must be carried out first
* Login and logout are possible when user wants to 

2. Load picture from smart phone
* Click add button in the closet 
* Choose to take a picture or recall from the album

3. Save picture to firebase
* Remove unnecessary background from photo
* By using grab cut algorithm

4. Remove background
* Receive data from the user about cloth 
* It is classified according to data and stored in the database

5. Dress up avatar
* User can compose a styling by dressing an avatar with user’s face
* User can save completed styling list
- Select clothes that fit the category I want.
- When selected, the avatar is automatically dressed with clothes.

***
## System modeling 
### Scenario
①. First, join to our system with ID, Password and your extra information. User data send to our database, save safely. Only developers and manager have a qualification to access data.

②. If you are already member of our system, then you can join by entering your ID and password. Our system check your ID and password whether they are valid or not.

③. Then, you can save your clothes in your albums into closet or use camera to take pictures of your clothes. You can access camera in our app. 

④. When register your clothes, you may input information of your clothes. You should input category, color, style of your clothes. Style means style of your clothes which style is casual style, formal style, athletics style.

⑤. In your closet, we show your registered clothes as a list by categories based on information about clothes. Also you can delete your clothes in closet.

⑥. If you register your clothes into closet, Grabcut algorithm is implementing to remove background of your picture. Only the clothes pictures are saving in closet. Also the data of clothes pictures send to database.

⑦. Using the clothes in your closet, you can make your own coordinates. You can check your clothes whether they are harmonious or not. Also, if you like matching of clothes, you can save that coordinate to your favorites. You can see your favorite stylists.

### Activity diagram
- Define System boundaries 
Inside the system, there has many functions such as login, sign up, closet, make cody list, favorite, load and save photo, etc. But some functions must be accessed to outside of the system to perform the desired function. For example, when we want to load photo, we have to access camera and album to perform, and we have to set database and save data because of capacity of app. So, the outside the system, there has database, camera, album.

![image](https://user-images.githubusercontent.com/57340671/114292297-8fb3e400-9ac8-11eb-8e54-90d43995e1ca.png)
![image](https://user-images.githubusercontent.com/57340671/114292320-b07c3980-9ac8-11eb-8563-0e235b82f2c1.png)

### Usecase diagram

![image](https://user-images.githubusercontent.com/57340671/114292334-c2f67300-9ac8-11eb-8504-76d976261d82.png)
![image](https://user-images.githubusercontent.com/57340671/114292348-d1dd2580-9ac8-11eb-926d-574a575457db.png)
![image](https://user-images.githubusercontent.com/57340671/114292356-e1f50500-9ac8-11eb-89fd-ab853f211a1c.png)

### Sequence diagram

![image](https://user-images.githubusercontent.com/57340671/114292361-ee795d80-9ac8-11eb-94fb-1a3a41c7c4b2.png)
![image](https://user-images.githubusercontent.com/57340671/114292364-f33e1180-9ac8-11eb-8e43-b010f45c1d92.png)

### Class diagram

![image](https://user-images.githubusercontent.com/57340671/114292368-fcc77980-9ac8-11eb-9ccf-45052867ce09.png)
![image](https://user-images.githubusercontent.com/57340671/114292371-0355f100-9ac9-11eb-87f3-f7946adb62a0.png)
![image](https://user-images.githubusercontent.com/57340671/114292374-0bae2c00-9ac9-11eb-939d-376ce04764f0.png)

### Data-driven modeling

![image](https://user-images.githubusercontent.com/57340671/114292380-1ff22900-9ac9-11eb-90c3-433086ce2cb9.png)
![image](https://user-images.githubusercontent.com/57340671/114292381-24b6dd00-9ac9-11eb-9040-3080521d2acb.png)

### Event-driven modeling
- State Diagram

![image](https://user-images.githubusercontent.com/57340671/114292383-2680a080-9ac9-11eb-9e19-a380fbf47a37.png)
![image](https://user-images.githubusercontent.com/57340671/114292386-2c768180-9ac9-11eb-9338-18a4b59d911d.png)
![image](https://user-images.githubusercontent.com/57340671/114292387-313b3580-9ac9-11eb-9191-47bc193adbe7.png)

## Analysis object model

![image](https://user-images.githubusercontent.com/57340671/114292413-5def4d00-9ac9-11eb-882b-67617d4ff0d8.png)
![image](https://user-images.githubusercontent.com/57340671/114292414-634c9780-9ac9-11eb-9992-93aabcc765bb.png)

***
## Architecture design
### Architecture and system characteristics
- Performance 
We can respond quickly to our users’ inputs or events and show changes or outputs in real-time by using a real-time database. Our database system is implemented by Firebase, Firebase can use and adopt in android environment, and no need to buy extra domain or server. Also no need to learn Linux commands. Firebase support amount of free storage, so we can save our money to develop system. So, input data is saving very fast to our database server and subsequent output are visualized without delay.
User can select and use the desired services by touching clear UI of our system. We use Android GUI. This is an applicable system in a mobile environment. Android GUI is the graphical and usually touch-sensitive display on a mobile device, such as a smartphone or tablet which allows the user to interact with the device’s apps, features, content and functions. 
In conclusion, our system is composed of uncomplicated structure, so unnecessary communication processes are omitted. Our system can communicate between database and other functional objects in Android in real time. Because of minimal communication of objects, response time will be faster than other web systems. This is an advantage of using ‘Firebase’. The process of loading clothes from the database system or storing clothes in the database system will take only 1, 2 seconds. These means our system have better response time for users.
- Security
To increase security, our system use a layered architecture with critical assets in the inner layers.  Database system has important and private data so database system is located on the bottom layer. Authorized system managers only access to database.
Also our database system support authorized system. System check the authority in log-in process of firebase. Also our database support console that can manage server system. 
In conclusion, we manage and save data in our database and check validity to use or access to our system. People who have not a permission cannot access to our database. Other user cannot access other user’s data like clothes or closet, system managers only access to database. It help to prevent hacking user’s data.
- Safety
In order not to cause physical and economic damage despite system failures, our database system and service system are managed separately from each other. We are limited to data operations such as store, modify, delete, and access data only within the database in order to increase safety. 
Also if there is any error or data interference or system problem, our system itself determines the error. The debug code will continue to operate and alert to system manage. The system itself keeps monitoring errors, and our systems built in Firebase and Android studios are debugging and coding safety methods. You can check the error on Logcat through Log.
- Availability
1) Firebase 
The administrator periodically backs up the user's information and manages the database before the firebase fails. If the firebase fails, the manager will be notified to fix the database as soon as possible. It also periodically checks the capacity of how many users can register their personal information and how many images of their clothes can be registered in the Firebase.
2) Grabcut
Before the Grabcut algorithm fails, the manager debugs it frequently to minimize errors. If the Grabcut algorithm fails, the manager fixes it as soon as possible. It also periodically tests Grabcut algorithms to improve performance.
In addition, we want to use always objects such as Login, Sign-up, connect with program and camera app, connect with program and album, we create redundant components.
- Maintainability
1) Corrective maintenance
Create an object that receives feedback from users. We find problems and solve errors.
2) Adaptive maintenance
We will quickly apply environmental changes such as new hardware or the next operating system to our programs.
3) Perfective maintenance
We will quickly accept new or changed user requirements. In addition, to improve the performance of each object and function, we plan to go through a lot of testing.
We will quickly accept new or changed user requirements. In addition, to improve the performance of each object and function, we plan to go through a lot of testing.

4) Preventive maintenance
We will make great efforts to increase the maintenance of the system in various ways, such as updating documents or adding explanations or Improving the module configuration of the system.
We will do maintenance based on the four methods described earlier. We are planning to create various replace components to deal with problems in the program.

### Architecture views
- Logical view

![image](https://user-images.githubusercontent.com/57340671/114292470-ad357d80-9ac9-11eb-9405-665a8c5b1b56.png)

- Development view

![image](https://user-images.githubusercontent.com/57340671/114292475-b292c800-9ac9-11eb-9f2c-92b4cc272406.png)

- Physical view

![image](https://user-images.githubusercontent.com/57340671/114292478-b4f52200-9ac9-11eb-917d-e0399038938e.png)
![image](https://user-images.githubusercontent.com/57340671/114292479-b6bee580-9ac9-11eb-9eae-edf95b1fd7e5.png)

- Usecase diagram

![image](https://user-images.githubusercontent.com/57340671/114292481-b9213f80-9ac9-11eb-862d-43480c5ffe6f.png)

### Architecture patterns
- Layered architecture pattern

![image](https://user-images.githubusercontent.com/57340671/114292484-bde5f380-9ac9-11eb-9d36-35843a7f2cd6.png)

- Repository architecture pattern

![image](https://user-images.githubusercontent.com/57340671/114292489-c0484d80-9ac9-11eb-96fb-0b3c9c87816b.png)

- Client-Server architecture pattern

![image](https://user-images.githubusercontent.com/57340671/114292491-c2aaa780-9ac9-11eb-8159-d16461ece9de.png)

### Application architecture
- Transaction processing system

![image](https://user-images.githubusercontent.com/57340671/114292493-c8a08880-9ac9-11eb-99ce-2324c1a743d3.png)


***
## Design and implementation

![image](https://user-images.githubusercontent.com/57340671/114292578-64ca8f80-9aca-11eb-9332-bd5c62bd0051.png)

Splash : It is the first loading screen that appears when you start an application.
Main login : It is the main screen with ID entry, password entry, login, membership registration, ID finding, and password finding functions.
Main register : It is a membership function and requires user to enter user’s name, ID, password, password confirmation, and email.
Check for duplicate IDs	: It indicates duplication of ID when sign up.

![image](https://user-images.githubusercontent.com/57340671/114292582-698f4380-9aca-11eb-9af6-7b2c20378985.png)

Check for duplicate IDs : It checks the password again when signing up as a member.
Find ID : It is a screen that helps you find your ID. You must enter a name and email.
Find password : It is a screen that helps you find your Password. You must enter a name, ID and email.
Main home : It is the main screen that decides whether to register or see outfit registered in the closet.

![image](https://user-images.githubusercontent.com/57340671/114292613-a3604a00-9aca-11eb-99c4-3c2ee4b93f7d.png)

Main closet : It is the main wardrobe and can be classified by category such as top, bottom, outer, and shoes.
Search for top of criteria : It is once again classified as design, color, style in top.
Searched clothes output : It is the output that came out after searching sweet shirts, black and daily styles among top.
Clothes information : It shows the details of the clothes

![image](https://user-images.githubusercontent.com/57340671/114292615-a5c2a400-9aca-11eb-997d-a6e53f210c3a.png)

Main Register clothes : It is the screen for registering clothes. It provides photo addition, photo cutting function and can be categorized by selecting clothing type, detail, color, and style.
Register clothes : It is a screen where user can decide whether to take a picture and register user’s clothes or the clothes that already exist in the album.
Selected clothes : This is the screen where I chose the picture. You can register your name and cut the background.
Remove background : It is the screen after the background has been deleted.

![image](https://user-images.githubusercontent.com/57340671/114292618-a78c6780-9aca-11eb-87f9-b175ecf5bb99.png)

Register after entering information : It is a screen for registering an outer and can be classified in various ways.
Search for outer of criteria : This screen allows you to search for an outer according to its classification.
Selected clothes : This is the screen where the search result came out.
Main cody : This is the main body screen and can determine the type of clothing, details, color and style.

![image](https://user-images.githubusercontent.com/57340671/114292619-a9562b00-9aca-11eb-8fbc-16fd6f6e12b2.png)

Recall previous cody : This is a screen that calls up the previously made stylist. It supports face change, return, and save.
Search for top of criteria : This screen allows you to search for a top according to its classification.
Selected clothes output	: This is the screen where the search result came out.
Coordinate on avatar : It is a screen where you put the code you made on your avatar. It also supports face change, return, and save.

***
## Software Testing
### Documentation
Use Javadoc to help understand our source’s classes or codes. So it will make a documentation with the comment at the Java file. 

![image](https://user-images.githubusercontent.com/57340671/114292658-f0dcb700-9aca-11eb-8a2e-dd667b7374f3.png)
![image](https://user-images.githubusercontent.com/57340671/114292659-f1754d80-9aca-11eb-8916-9d67af6cd5c8.png)

### Unit test
Use Junit to test which individual units of source code are tested to determine if they are fit for use.

![image](https://user-images.githubusercontent.com/57340671/114292663-fa661f00-9aca-11eb-8caa-127d92bda602.png)

### Test case generator
Use Espresso Recorder to make test case automatically. By running the app through emulator, click some widgets to test, or enter the necessary data, and then click Add Assertion to add step about testing. So you register the course and build a test case based on them.

![image](https://user-images.githubusercontent.com/57340671/114292669-0c47c200-9acb-11eb-8284-034a90fa6006.png)

### UI Testing
Test the UI using Espresso. When you start to test, it will show whether the test was successful or failed by executing an automatically generated test case by Espresso Recorder. The picture below is a capture of a successful screen.

![image](https://user-images.githubusercontent.com/57340671/114292670-0e118580-9acb-11eb-880c-b32dc075a265.png)

### Performance Analysis
Use Jmeter to load testing client / server applications and perform to determine how a system performs in terms of responsiveness and stability under a particular workload.
- Memory testing

![image](https://user-images.githubusercontent.com/57340671/114292684-31d4cb80-9acb-11eb-9d60-d9ff29b9e9df.png)

- CPU testing

![image](https://user-images.githubusercontent.com/57340671/114292685-3305f880-9acb-11eb-8301-8ae7c5f606d3.png)

- Network testing

![image](https://user-images.githubusercontent.com/57340671/114292686-34cfbc00-9acb-11eb-8a21-7c5e75406ad2.png)

- Energy testing

![image](https://user-images.githubusercontent.com/57340671/114292687-3600e900-9acb-11eb-9caa-b372ed42ad9f.png)
