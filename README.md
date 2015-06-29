# todolist
A simple todolist manager made with AngularJS and Spring Boot

## JDK
The todolist run with JDK8+

## Mysql Database
The todolist is made to run with a mysql database, so make sure you have a mysql database with the following configuration in **localhost**:
```
database name: todo
mysql user: root
mysql password: root
```

If the configuration above is not possible, you need to run the application from the sources.

You can modify the database configuration in **_src/main/resources/application.properties_**.

## Run from sources
To run the project from the source files you need to have maven 3+ installed and clone this [repository](https://github.com/robsonperassoli/todolist.git).

To run the todolist execute the following command inside the repository:
```
mvn clean install spring-boot:run
```

## Run from dist jar 
If you want to run the todolist from the distribution jar download the file at (https://github.com/robsonperassoli/todolist/releases/download/0.0.1/todo-0.0.1.jar) and run the file with the command:
```bash
java -jar todo-0.0.1.jar
```

## Enjoy!
If everything went fine you should be capable of access the application in (http://localhost:8080)