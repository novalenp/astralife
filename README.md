# astralife



Importing the Project
----------------------
Open Eclipse

File > Import > Choose Maven >Existing Maven Project > Click Next

Select Root Directory by clicking Browse > select 'astrarest' folder > Click OK > Click Finish


Importing The Database
----------------------
You can find the sql file under src > ddl

Import the sql file into your database client


Modify Properties
-----------------
Find application.properties under src/main/resources

Open the file then set the following value according to your database connection :
    spring.datasource.url= ***
    spring.datasource.username= **
    spring.datasource.password= **

Save The file


Run The Project
---------------
Run > Click Run Configurations > Double click Maven Build 

Click Workspace > Select base directory > then Click OK

Inside the Goals field fill it with following command(without quotes) :  "clean spring-boot:run"

(Optional: Rename "New_configuration" inside the Name field)

Click Apply > Then Finally Click Run
