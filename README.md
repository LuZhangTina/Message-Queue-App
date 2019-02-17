### MessageQueueApp
This project aims to test and use [MessageQueue](https://github.com/LuZhangTina/MessageQueue) jar package.

### Project Configuration
1. Download [MessageQueue jar package](https://github.com/LuZhangTina/MessageQueue/blob/master/target/messagequeue-1.0-SNAPSHOT.jar)
2. Install jar packet to local repo
```
mvn install:install-file -Dfile=messagequeue-1.0-SNAPSHOT.jar  -DgroupId=com.tina -DartifactId=messagequeue -Dversion=1.0 -Dpackaging=jar
```
3. Configure pom
```
        <dependency>
            <groupId>com.tina</groupId>
            <artifactId>messagequeue</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

### Description of Implementation
1. Add local.properties file to configure the queueServiceType
2. Create two producers and two consumers. Producers push data into queue. Consumers pull from queue and delete the data.
3. Create a Thread pool with 4 threads. Execute each thread to implement multiple threads operate one queue.