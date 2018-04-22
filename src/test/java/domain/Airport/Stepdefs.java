package domain.Airport;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Airport.Service.UniqueNameCheck;
import org.junit.Assert;

import java.util.UUID;

public class Stepdefs
{
    private Repository airportRepository;

    private UniqueNameCheck uniqueNameCheckService;

    private UUID uuid;

    @Before
    public void before()
    {
        airportRepository = new Repository();
        uniqueNameCheckService = new UniqueNameCheck(airportRepository);
        uuid = UUID.randomUUID();
    }

    @Given("^an airport exists with the name (.*)$")
    public void an_airport_exists_with_the_name(String value)
    {
        airportRepository.store(
            new Airport(UUID.randomUUID(), new Name(value))
        );
    }

    @When("^I create an airport with the name (.*)$")
    public void i_create_an_airport_with_the_name(String value)
    {
        try {
            Name name = new Name(value);
            if (!uniqueNameCheckService.alreadyExistsByName(name)) {
                airportRepository.store(
                    new Airport(uuid, name)
                );
            }
        } catch (Exception exception) {
            // Ignore exception
        }
    }

    @Then("^an airport called (.*) will exist$")
    public void an_airport_called_will_exist(String value)
    {
        Name name = new Name(value);

        Airport fetched = airportRepository.fetchByName(name);

        Assert.assertNotNull(fetched);
        Assert.assertEquals(name, fetched.getName());
    }

    @Then("^an airport called (.*) will not exist$")
    public void an_airport_called_will_not_exist(String value)
    {
        Name name = new Name(value);

        Airport fetched = airportRepository.fetchByName(name);

        Assert.assertNull(fetched);
    }

    @Then("^it should fail to create the airport$")
    public void it_should_fail_to_create_the_airport()
    {
        Airport fetched = airportRepository.fetch(uuid);

        Assert.assertNull(fetched);
    }
}
