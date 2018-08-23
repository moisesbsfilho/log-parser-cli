# Parser Command Line


This project is part of test from Wallet Hub for Java programmer role. Powered by Java 8.

  - Command Line to interpret the follow commands:
    - Load data from database;
    - Filter records by threshold and date;

### Stacks Used

  - Java 8
  - Apache Maven


Important Information:
  - All the SQL instructions is under configuration folder
  - Access log is not present into resources
 
### Build project

Install the dependencies and generate jar file
```sh
$ cd parser
$ mvn clean install
```

### Usage example

Copy jar file into target folder and execute the following command:
```sh
$ java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
```

You can see all command allowed running the follow command:
```sh
$ java -cp "parser.jar" com.ef.Parser --help
```  
