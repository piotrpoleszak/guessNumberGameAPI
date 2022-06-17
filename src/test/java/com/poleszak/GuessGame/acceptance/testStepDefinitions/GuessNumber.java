package com.poleszak.GuessGame.acceptance.testStepDefinitions;

import com.poleszak.GuessGame.acceptance.common.AcceptanceTestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GuessNumber extends AcceptanceTestBase {

    private  String url;
    private String result;

    @Given("I Set PUT game service api endpoint")
    public void set_post_endpoint() {
        url = PATH + "/guess";
    }

    @When("I guess a number")
    public void create_new_game() throws Exception {
        result = mvc.perform(put(url))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Then("Message is returned {string}")
    public void save_game_in_db_and_return_id(String message) {
        assertThat(result).isEqualTo("1");
    }
}
