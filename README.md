# MandolaAppAPI
A REST API created using the Java Spark framework to receive the reports from the Mandolapp application and store them in a MySQL database




**Instructions to use:**

1. Download zip project file and extract to desired location or clone github project
1. Launch Intellij IDEA or Eclipse and open project from desired IDE
1. Create MySQL database named Mandola with username:root and password:1234
1. Create following tables in Mandola database:
    * **TwitterReports**

 ID | DESCRIPTION | CATEGORY | AUTHORITY | URL | DATE
------------ | ------------- | ------------ | ------------- | ------------ | ------------
int | STRING | set {Relegious, Gender, Sexual, Class, Politics, Ethnicity, Nationality, Other} | set {Authority 1, Authority 2, Authority 3} | STRING | DATETIME   
    
   
        
    
