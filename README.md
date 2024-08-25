# Project to practice messaging with kafka 
Kafka is a message broker, it is responsible for facilitating communication and data exchange between different
applications or systems. Its primary function is to decouple producers from the consumers. The Message broker acts as a mediator and ensures that messages are delivered efficiently. 
Producers and consumers do not need to know about each other, allowing async communication.

To test the application, start kafka server:
1. download the latest kafka and extract it(To download and install Kafka, please refer to the official guide [here](https://kafka.apache.org/quickstart).)
2. Start the Kafka Server
 Run the following commands in order to start all services in the correct order:
#### Start the ZooKeeper service
```
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
Open another terminal session and run:
#### Start the Kafka broker service
```
$ bin/kafka-server-start.sh config/server.properties
```
Once all services have successfully launched, you will have a basic Kafka environment running and ready to use.

before you can write your first events, you must create a topic. Open another terminal session and run:
```
$ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
```
quickstart-events- is the name of your topic

For further reference, please consider the following sections:
https://kafka.apache.org/quickstart

### Configuring Topics

Previously, we ran command-line tools to create topics in Kafka, but with the introduction of AdminClient in Kafka,
we can now create topics programmatically.

We need to add the KafkaAdmin Spring bean, which will automatically add topics for all beans of type NewTopic:
```
@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic topic1() {
         return new NewTopic("baeldung", 1, (short) 1);
    }
}
```
In [tutorial](https://www.youtube.com/watch?v=KQDTtvZMS9c&t=939s) the KafkaAdmin was not configured,NewTopic uses the bootstrapAddress be default( why?) 
### Producer Configuration.
To create messages, we first need to configure a ProducerFactory. This sets the strategy for creating Kafka Producer instances.
It can be done in application properties, or programmatically in Config class 
```
@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          bootstrapAddress);
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
     @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
```
Then we can send messages using the KafkaTemplate class.

### Consumer Configuration
For consuming messages, we need to configure a ConsumerFactory and a KafkaListenerContainerFactory. 
Once these beans are available in the Spring bean factory, POJO-based consumers can be configured 
using @KafkaListener annotation.

It can be done using either application property file or config class.

The reading and writing message configured by SpringBoot Kafka, 
for the first version it is done for String.


### Guides
* https://www.youtube.com/watch?v=KQDTtvZMS9c&t=939s
* https://www.baeldung.com/spring-kafka



