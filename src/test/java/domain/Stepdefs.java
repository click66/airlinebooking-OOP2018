package domain;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs
{
    @Given("^an airport exists with the name (.*)$")
    public void an_airport_exists_with_the_name(String name)
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I create an airport with the name (.*)$")
    public void i_create_an_airport_with_the_name(String name)
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^an airport will exist with the name (.*)$")
    public void an_airport_will_exist_with_the_name(String name)
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^it should fail to create the airport$")
    public void it_should_fail_to_create_the_airport()
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
