# Project to practice messaging with kafka 

### Reference Documentation
For further reference, please consider the following sections:

https://kafka.apache.org/quickstart

To test the application, start kafka server:
1. download the latest kafka and extract it
2. Start the Kafka Server
 Run the following commands in order to start all services in the correct order:
### Start the ZooKeeper service
$ bin/zookeeper-server-start.sh config/zookeeper.properties
Open another terminal session and run:

### Start the Kafka broker service
$ bin/kafka-server-start.sh config/server.properties
Once all services have successfully launched, you will have a basic Kafka environment running and ready to use.

The reading and writing message configured by SpringBoot Kafka, for the first version it is done for String.


### Guides
https://www.youtube.com/watch?v=KQDTtvZMS9c&t=939s


