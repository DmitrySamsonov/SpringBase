If you start Spring Boot and:

org.h2.jdbc.JdbcSQLNonTransientConnectionException: 

Database may be already in use: null. Possible solutions: close all other connection(s); use the server mode

Caused by: java.lang.IllegalStateException: The file is locked: nio:C:/git/SpringBase/dataH2storage/h2db.mv.db 


#Solution
You should kill a proccess for previous running app

write in cmd:

netstat -ano | findstr :yourPortNumber

taskkill /pid yourid /f


Example:

netstat -ano | findstr :8080

taskkill /pid 9824 /f




#Also you can:
Change in application.proprties

From

spring.datasource.url=jdbc:h2:file:./dataH2storage/h2db

To

spring.datasource.url=jdbc:h2:file:./dataH2storage/h2db;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE

