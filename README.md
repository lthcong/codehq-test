***** HOW BUILD AND RUN APPLICATION *****

1. Use MySQL as your database.

2. Create a simple database and call it "codehq_test". 

3. Use port of "3306" and an account with username is "root" and password is "123456789"

4. Clone the project from:
   https://github.com/lthcong/codehq-test

5. Switch your branch to "develop".

6. At this point you can run your application.


***** EXPLANATION *****
As describe in the document, I decided to build the app with 2 entities. First one is device data with store specific data such as
device id, latitude, longitude. Second one is device detail which store generic data, and linked to the device data by device id.

The Device info class is just a general class which collect the input data from the user.


***** BREAK DOWN *****
Preparation: 1 hour.
Coding: 4 hours.
Document: 30 minutes.
Build and test: 30 minutes.
Grand total: 6 hours.