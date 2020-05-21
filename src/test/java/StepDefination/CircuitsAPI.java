package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CircuitsAPI {
    private static String BASE_URL;
    private static Response response;
    Log log = LogFactory.getLog(getClass());

    @Given("the API Endpoint is {string}")
    public void the_API_Endpoint_is(String strURL) {
        // Geting the URI of the API
        log.info("Base URL is set");
        BASE_URL = strURL;
    }

    @Given("I want to know the number of Formula One races in {int}")
    public void i_want_to_know_the_number_of_Formula_One_races_in(int intSeason) {
        // Set the Season value to be fetched
        log.info("Set season value in URL");
        BASE_URL = BASE_URL + "/" + intSeason;

    }

    @When("I retrieve the circuit list for that season")
    public void i_retrieve_the_circuit_list_for_that_season() {
        // Doing the get call and verifying the 200 status
        log.info("Doing the HTTP Call and verify the status");
        response =  given().
                when().
                get(BASE_URL).then().statusCode(200).extract().response();
    }

    @Then("there should be {int} circuits in the list returned Examples:")
    public void there_should_be_circuits_in_the_list_returned_Examples(int intCount) {
        // Verify the XML response for season count
        XmlPath xmlPath = new XmlPath(response.asString());
        String totalValue = xmlPath.get("MRData.@total");
        int intValue = Integer.parseInt(totalValue);
        log.info("Verify the values expected");
        assertEquals(xmlPath.getInt("MRData.RaceTable.Race.Circuit.size()"),intCount);
        assertEquals(intValue,intCount);
    }


}
