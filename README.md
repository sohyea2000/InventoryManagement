# InventoryManagement
Inventory Management App is an android app which helps to manage the inventory of a company effectively.

The app uses google firebase authentication and realtime firebase database as its backend.

The zxing library has been used to scan the QR Code (https://github.com/journeyapps/zxing-android-embedded).

# Prerequisites
     * Android Studio - 3.1.6
     * Minimum SDK Version = 27
     * google-services.json file generated for your application.
 P.S - A stable internet connection is required for first build
 # Dependencies used
  
 App Level Gradle file
 
      * Firebase Authentication - implementation 'com.google.firebase:firebase-auth:19.3.0'
      * Firebase Database -  implementation 'com.google.firebase:firebase-database:19.3.0'
      * zxing Library -  implementation 'com.journeyapps:zxing-android-embedded:4.1.0'
          
 Project Level Gradle file
      
      *classpath 'com.google.gms:google-services:4.3.3'
# Scope Of The Project
Inventory Management is an important sector for every industry. This project is an attempt to make the task of inventory management easier and more effective 
by using this app.

The project mainly has 2 segments - The Admin and The Worker

Admin Segment - The admin can add an employee, add product, update details of the product by scanning the QR Code. This way the admin can keep a track regarding 
the employees , the products and also date and time of service of the machines.

Worker Segment - The worker can login using their id cards and they can scan the QR Code of the machine to get the details of the machine. Thus making the maintanance 
of the machine easier.

The Inventory Management app can therefore be used to make Inventory Management easier and more effective for both the company admin and also for their workers.
