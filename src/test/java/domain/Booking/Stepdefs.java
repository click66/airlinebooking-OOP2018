package domain.Booking;

import application.Repository.AirportRepository;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Airport.Airport;
import domain.Airport.Name;
import domain.Airport.Repository;
import org.junit.Assert;

import java.util.HashMap;
import java.util.UUID;

public class Stepdefs
{
    @Before
    public void before()
    {
    }

    @Given("^a flight has a (\\d+)x(\\d+) Economy class section$")
    public void a_flight_has_a_x_Economy_class_section(int arg1, int arg2) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^seat (\\d+)A in Economy will be available$")
    public void seat_A_in_Economy_will_be_available(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a flight has a (\\d+)x(\\d+) Business class section$")
    public void a_flight_has_a_x_Business_class_section(int arg1, int arg2) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I book seat (\\d+)A in Business$")
    public void i_book_seat_A_in_Business(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^seat (\\d+)A in Economy will be booked$")
    public void seat_A_in_Economy_will_be_booked(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I book seat (\\d+)A in Economy$")
    public void i_book_seat_A_in_Economy(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I book seat (\\d+)B in Economy$")
    public void i_book_seat_B_in_Economy(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^seat (\\d+)B in Economy will be booked$")
    public void seat_B_in_Economy_will_be_booked(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a flight has a (\\d+)x(\\d+) Business class ection$")
    public void a_flight_has_a_x_Business_class_ection(int arg1, int arg2) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^seat (\\d+)A in Business will be booked$")
    public void seat_A_in_Business_will_be_booked(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^seat (\\d+)A in Business will be available$")
    public void seat_A_in_Business_will_be_available(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I try and book seat (\\d+)A in Economy$")
    public void i_try_and_book_seat_A_in_Economy(int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the booking should fail$")
    public void the_booking_should_fail() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
