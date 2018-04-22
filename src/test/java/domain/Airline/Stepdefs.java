package domain.Airline;

import application.Airlines;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Airline.Repository.HashMapRepository;
import domain.Airline.Repository.Repository;
import org.junit.Assert;

import java.util.UUID;

public class Stepdefs
{
    private Airlines airlines;

    private Repository airlineRepository;

    private UUID uuid;

    @Before
    public void before()
    {
        airlineRepository = new HashMapRepository();
        airlines          = new Airlines(airlineRepository);

        uuid = UUID.randomUUID();
    }

    @Given("^an airline exists with the name (.*)$")
    public void an_airline_exists_with_the_name(String value)
    {
        airlineRepository.store(
            new Airline(UUID.randomUUID(), new Name(value), new Designation("SW"))
        );
    }

    @When("^I create an airline with the name (.*)$")
    public void i_create_an_airline_with_the_name(String value)
    {
        try {
           airlines.registerNewAirline(new Name(value), new Designation("SW"));
        } catch (Exception exception) {
//             Ignore exception
        }
    }

    @Then("^an airline called (.*) will exist$")
    public void an_airline_called_will_exist(String value)
    {
        Name name = new Name(value);

        Airline fetched = airlineRepository.fetchByName(name);

        Assert.assertNotNull(fetched);
        Assert.assertEquals(name, fetched.getName());
    }

    @Then("^an airline called (.*) will not exist$")
    public void an_airline_called_will_not_exist(String value)
    {
        try {
            Name name = new Name(value);

            Airline fetched = airlineRepository.fetchByName(name);

            Assert.assertNull(fetched);
        } catch (Exception exception) {
            // Ignore exception
        }
    }

    @Then("^it should fail to create the airline$")
    public void it_should_fail_to_create_the_airline()
    {
        Airline fetched = airlineRepository.fetch(uuid);

        Assert.assertNull(fetched);
    }
}
