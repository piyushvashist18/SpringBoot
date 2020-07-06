### Lab01

* __Pair Up__ and implement this exercise, if you want. 

##### Application 01
* Create a Spring boot application running on **8080**
* Accessing **http://localhost:8080** will give you a text box where you enter a word as input 
* On hitting the submit button your application will talk to **http://localhost:8081/{word}**  
* Display the result in the **same page**


##### Application 02

* The **8081** application is another Spring boot application that accepts a word and computes if the given word is a palindrome or not


##### RestTemplate
* Talk to other services using **RestTemplate** class provided by Spring
* Create an instance of **RestTemplate** and use **getForXXX** or **postForXXX** methods