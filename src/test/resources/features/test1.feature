Feature: List the cheapest flights 
	The user should be able to find the cheapest flight

Scenario: The cheapest value is dispalyed in case of "1 stop" search 
	Given the user is navigated to the following URL: 'http://www.liligo.co.uk/' 
	When the user initiates a search with the following parameters: 
		| flightFrom     | Berlin         |
		| flightTo       | Marseille      |
		| departureDate  | 6 January 2018 |
		| numberOfAdults | 2              |
		| airClass       | Business       |
		| flightType     | One-way        |
	Then the cheapest value is displayed in the All results column of the search result table for '1 stop' 
