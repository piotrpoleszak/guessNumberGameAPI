package com.poleszak.GuessGame.acceptance.testStepDefinitions;

import com.poleszak.GuessGame.acceptance.common.AcceptanceTestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreatingGameStepDefinitions extends AcceptanceTestBase {

    private  String url;
    private String result;

    @Given("I Set POST game service api endpoint")
    public void set_post_endpoint() {
        url = PATH + "/start";
    }

    @When("I create new game")
    public void create_new_game() throws Exception {
        result = mvc.perform(post(url))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Then("New game saved in database and returned id")
    public void save_game_in_db_and_return_id() {
        assertThat(result).isEqualTo("1");
    }
}
