# pipelinepigging
DB Project for Pipeline Pigging

The development of the complete project is contained in the file Project BDD.pdf.

There are 2 .sql files to import the database (MySQL), one file contains only the tables, relationships and triggers, while the other file also contains enough data (INSERT) to interact with the DB.

In the /src/ folder is a Java project to test the operations on the DB. The Database.java class contains all the public methods (called by the Main class) for queries on the DB. The other classes are for support.

The database was populated with special functions located in the Database.java class.

The JDBC connector contained in this folder was used to connect to the DB.
