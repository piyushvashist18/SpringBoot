* Build a Spring Boot Web application that takes the name of the movie and year of release as input

* The information is pushed to a Queue [X]

* A backend service will continuously poll for new entries in the Queue [X] and pass them on to __http://www.omdbapi.com/?i=tt3896198&apikey=52d1c7f__ (Read the documentation for JSON response you get) and fetch the star cast and directors information

* The star cast and directors data is stored in another Queue [Y]

* The Spring Boot Web application has a page(/details) that displays all the movie details you have requested so far. It polls for the output from the Queue [Y] every 10 seconds, and displays all the details

* Design your own Queues