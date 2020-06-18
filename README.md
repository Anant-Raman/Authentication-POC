# Authentication-POC
A POC og Authentication feature using firebase to demonstrate clean and loosely coupled MVVM architecture. 


How to setup the project?

For security purpose I have removed my firebase API key from the project. To add Firebase to your project follow the steps given below :
1 - Create your Firebase project.
2 - Add Android app to your project. In package name add - com.example.authenticationpoc
3 - Once your app is added you need not to make any changes in the gradle, just add google-services.json file to your app directory.
4 - Once you have added the google-services.json file, go to authentication, then to sign in method and enable signin using email/password.
5 - Go to the database, select cloud firestore under test mode, then go to Rules and change the rule to 

    allow read, write: if true;
    
and then click on publish.
You are ready to run the project.   
