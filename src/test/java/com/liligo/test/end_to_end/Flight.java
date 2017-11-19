package com.liligo.test.end_to_end;

public class Flight {
    private String flightFrom;
    private String flightTo;
    private FlightDate departureDate;
    private FlightDate returnDate;
    private String numberOfAdults;
    private String airClass;
    private String flightType;

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public FlightDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(FlightDate departureDate) {
        this.departureDate = departureDate;
    }

    public FlightDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(FlightDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(String numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public String getAirClass() {
        return airClass;
    }

    public void setAirClass(String airClass) {
        this.airClass = airClass;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }
}
