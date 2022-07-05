# README
## Instructions
- How to build application:
  - Checkout the source code from github
  - cd to project folder
  - Execute: mvn clean install - to run build and run unit test
  - Execute: mvn spring-boot:run - to run the project as spring boot application

- How to trigger api call:
  - Send POST request to:
    - Endpoint: /api/calculateTax
    - Request param: city - (String) configuration city file
    - Request body: Json data
      - vehicle - (String) vehicle type
      - dates - (Array) list of entries
    - Request headers:
      - Content-Type: application/json
      - Accept: application/json

    - Response: Json data
      - vehicle - (String) vehicle type
      - taxAmount - (int) amount of calculated tax

  - Sample below

    HTTP Method = POST
    Request URI = /api/calculateTax
    Parameters = {name=[Gothenburg]}
    Headers = [Content-Type:"application/json;charset=UTF-8", Accept:"application/json", Content-Length:"71"]
    Body = {"vehicle":"Car","dates":["2013-01-14 06:01:01","2013-01-14 06:58:01"]}


## Bugs found
  - IsTollFreeDate() method has a bug in finding toll-free dates
    - missing days: 20 May, 31 Oct, 30 Dec
    - logical issue check at year == 2013 because Date.getYear is returning the difference
    between current year and 1900, thus the expected value is 113
  - GetTollFee() method has the bug in finding toll fee
    - condition is wrong for the slot from 08:30-14:59, 15:30-16:59

- Solution:
  - tollFreeVehicles is loaded as the configuration from properties file    
  - IsTollFreeDate() method is refactored to segregate the logic of public holiday is toll free, 
    the previous date before holiday is toll free, the holiday fall in Saturday or Sunday
  - GetTollFee() method is refactored to support the external configuration for the hours and amounts
    for congestion tax rules
  - Change the deprecated Date api implementation with Calendar api 
  
  ## Assumption:
  - Based on the given scribbled dates in front of the assignment, the program will take the series of input date for which the congestion tax had to be calculated
 
  - Extension / Bonus points:
    - City tax config is designed as external standard properties file. This will allow the external editor to change the config file during runtime and the new rule is applied into the system.
    - Further more, the introduction of new city tax config in the system is also straight forward, only need to add the new properties file, the existing rule should be auto supported for new city.

# Data Structures used
- Config object CityTaxConfiguration is designed with below attributes :
   - private String cityName;
   - private int maxAmountPerDay;
   - private int singleChargeInMinutes;
   - private Set<String> tollFreeVehicles = new HashSet<>();
   - private Map<String, Integer> tollAmount = new HashMap<>();
   - private Set<String> publicHolidays = new HashSet<>();
   - private Set<Integer> freeMonths = new HashSet<>();
- These fields would allow us to store the data in the appropriate format and also eliminate all the duplicated data

## Performance Checks
- Performance test is noted above:
  - Test1:
2022-07-02 17:52:35.714  INFO 68762 --- [           main] m.c.controller.CongestionTaxController   : Processing time 13 ms

MockHttpServletRequest:
HTTP Method = POST
Request URI = /api/calculateTax
Parameters = {name=[Gothenburg]}
Headers = [Content-Type:"application/json;charset=UTF-8", Accept:"application/json", Content-Length:"49"]
Body = {"vehicle":"Car","dates":["2013-01-14 21:00:00"]}
Session Attrs = {}
    
  - Test2:
2022-07-02 17:52:35.789  INFO 68762 --- [           main] m.c.controller.CongestionTaxController   : Processing time 3 ms

MockHttpServletRequest:
HTTP Method = POST
Request URI = /api/calculateTax
Parameters = {name=[Gothenburg]}
Headers = [Content-Type:"application/json;charset=UTF-8", Accept:"application/json", Content-Length:"49"]
Body = {"vehicle":"Car","dates":["2013-01-14 09:00:00"]}
Session Attrs = {}

  - Test3:
2022-07-02 19:09:36.141  INFO 68916 --- [           main] m.c.controller.CongestionTaxController   : Processing time 40 ms

MockHttpServletRequest:
HTTP Method = POST
Request URI = /api/calculateTax
Parameters = {name=[Gothenburg]}
Headers = [Content-Type:"application/json;charset=UTF-8", Accept:"application/json", Content-Length:"181"]
Body = {"vehicle":"Car","dates":["2013-01-14 06:01:01","2013-01-14 06:58:01","2013-01-14 07:58:01","2013-01-14 08:05:01","2013-01-14 15:05:01","2013-01-14 15:58:01","2013-01-14 17:58:01"]}
Session Attrs = {}

  - Test4:
2022-07-02 19:09:36.431  INFO 68916 --- [           main] m.c.controller.CongestionTaxController   : Processing time 6 ms

MockHttpServletRequest:
HTTP Method = POST
Request URI = /api/calculateTax
Parameters = {name=[Gothenburg]}
Headers = [Content-Type:"application/json;charset=UTF-8", Accept:"application/json", Content-Length:"71"]
Body = {"vehicle":"Car","dates":["2013-01-14 06:01:01","2013-01-14 06:58:01"]}
Session Attrs = {}
  
  - Average response time: 15ms
