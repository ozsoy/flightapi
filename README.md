# flightapi

I merged two different JSON object from gived URLs.
When the application start, you will see a System.out.
I added a Controller with the service layer.

There are 4 different URL to handle sorted by {field}

http://localhost:8080/flights/departure -> Sorted by Departure name
http://localhost:8080/flights/arrival   -> Sorted by Arrival name
http://localhost:8080/flights/departureTime -> Sorted by Departure Time
http://localhost:8080/flights/arrivalTime -> Sorted by Arrival Time

There are one URL to search departure name, arrival name or both of them.

http://localhost:8080/flights/search/{type}/{location}   --> type [departure, arrival, both]; location [istanbul, ankara, ..]
for example: http://localhost:8080/flights/search/arrival/antalya


Also you can try your request on swagger too. -> http://localhost:8080/swagger-ui.html

There are 2 Test files, FlightControllerTest.java and FlightApiApplicationTest.java
