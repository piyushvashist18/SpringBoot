* Start from scratch

#### WeatherService
* Create one service __weather-service__ running in __8081__
* It has one endpoint __/weather/{city}__ which returns the current temperature of the city. You can generate a random number as a temperature
* The output will be __Temperature of Delhi is 11.5__

#### Eureka
* Configure an Eureka Server __training-discovery-server__ running in __8761__
* Register __weather-service__ with Eureka

#### Config
* Create a Config server __training-config-server__ running in 6000
* Configure __weather-service__ and it's port name here

#### Zuul
* Create a Gateway server __training-gateway-server__ running in 4000
* Configure the route for __/weather/{city}__


#### Swagger
* Configure __swagger__ for __weather-service__ so that you can view the documentation 

#### UI
* Create one service __UI__ running in __8080__
* It has a page with textbox and button which talks to __weather-service__
* Implement __circuit breaker__ for calling of __weather-service__

#### Hystrix and Turbine

* Create a service __training-dashboard__ in 9000
* Add Turbine, Hystrix, actuator and Hystrix Dashboard to the pom.xml
* Configure UI, and weather-service 
* Get help for this configuration from the instructor







