## Asynchronous communication

* Let's implement two Spring Boot applications that communicate with each other asynchronously using Queues
* One application sends out a stock symbol say GOOG, INFY, FB
* The other application receives it and displays the current market value
* But these two applications don't talk to each other directly
* For the sake of simplicity, let's just generate a random number as stock value instead of going to Google Finance or Yahoo Finance

### Setting up ActiveMQ

* Go to http://activemq.apache.org/activemq-5154-release.html
* Download the zip(for Windows) or tar(for Mac) file
* Extract the zip file
* In Terminal or Command-Prompt navigate to the bin folder 
* Enter **./activemq start**
* Go to the browser and enter *http://localhost:8161/admin/*
* If it prompts for a password enter *admin*

### Two Spring Boot Applications
* Create **Sender** project
* Include Web, JMS(ActiveMQ) libraries 
* Make sender to run in port 9090

* Create **Receiver** project with just JMS(ActiveMQ) library added

#### Sender
* Create a **StockController** class
* Write a method with URL mapping **/stock/{symbol}**
* The method accepts a stock symbol like GOOG, APPL, or INTEL as argument
* Have a default response **You'll receive the stock value soon**
* Autowire a **JmsTemplate** in **StockController**

* Create a *queue* named **StockQueue** in ActiveMQ

* Go to **application.properties** and copy the following

```
server.port = 9090
spring.activemq.user=admin
spring.activemq.password=admin
spring.activemq.broker-url=tcp://localhost:61616

```

* Finally in the method in **StockController** add the following code to publish the stock symbol to StockQueue

```
jmsTemplate.convertAndSend("StockQueue",symbol);
```


#### Receiver

* Go to **application.properties** and copy the following

```
spring.activemq.user=admin
spring.activemq.password=admin
spring.activemq.broker-url=tcp://localhost:61616
```

* Create a class **StockQuoteFinder**
* This class will listen for messages in **StockQueue** and whenever it receives a message it's going to print the stock value of that symbol. For the sake of simplicity, let's just generate a random number as a stock value and display it.

```java
@Component
public class StockQuoteFinder {

	@JmsListener(destination="StockQueue",containerFactory="stockFactory")
	public void receiveMessage(String symbol) {
		double price = Math.random() * 1000;
		System.out.println("Price of " + symbol + ": " + price);
	}
```

* In the **ReceiverApplication** class add *@EnableJms* annotation
* Add the following method to the class
* This configuration is actually optional. You can ignore it if you want

```java
@Bean
  public JmsListenerContainerFactory<?> stockFactory(
      ConnectionFactory connectionFactory,
      DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

    configurer.configure(factory, connectionFactory);
    return factory;
  }
```


