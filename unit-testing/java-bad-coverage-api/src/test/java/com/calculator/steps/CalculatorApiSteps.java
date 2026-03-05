package com.calculator.steps;

import com.calculator.CalculatorApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Cucumber step definitions for Calculator API feature tests.
 * Uses Spring Boot's MockMvc to call the real REST endpoint.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = CalculatorApplication.class)
@AutoConfigureMockMvc
public class CalculatorApiSteps {

    @Autowired
    private MockMvc mockMvc;

    private MvcResult mvcResult;
    private double expectedResult;

    @Given("I have the calculator API")
    public void i_have_the_calculator_api() {
        // Spring context already launched – nothing to do here
    }

    @When("I send a POST request with a={double}, b={double}, and operation={string}")
    public void i_send_a_post_request(Double a, Double b, String operation) throws Exception {
        String body = String.format("{\"a\":%s,\"b\":%s,\"operation\":\"%s\"}", a, b, operation);
        mvcResult = mockMvc.perform(
                post("/api/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk()).andReturn();
    }

    @Then("the response result should be {double}")
    public void the_response_result_should_be(Double expected) throws Exception {
        String responseBody = mvcResult.getResponse().getContentAsString();
        String expectedStr = String.valueOf(expected);
        assertTrue(responseBody.contains("\"result\":" + expectedStr),
                "Expected result " + expectedStr + " in response: " + responseBody);
    }
}
