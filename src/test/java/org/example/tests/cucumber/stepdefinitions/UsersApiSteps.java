package org.example.tests.cucumber.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.backend.User;
import org.example.backend.UsersApiClient;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersApiSteps {
    @Autowired
    private UsersApiClient usersApiClient;
    private List<User> users;
    private User actualUserById;
    private User createdUser;

    @Given("the API is available")
    public void the_api_is_available() {
        // No need to explicitly call API here as it's invoked in the @When step.
    }

    @When("I send a GET request to the posts endpoint")
    public void i_send_a_get_request_to_the_posts_endpoint() {
        users = usersApiClient.getAllUsers();
    }

    @Then("the response should contain a list of users")
    public void the_response_should_contain_a_list_of_users() {
        Assertions.assertNotNull(users, "User list is null");
        Assertions.assertFalse(users.isEmpty(), "The response should contain a list of posts");
    }

    @Then("each user should have all the fields")
    public void each_user_should_have_all_the_fields() {
        for (User user : users) {
            assertUserValuesNotNull(user);
        }
    }

    @Then("the first user should have correct data as:")
    public void the_first_user_should_have_correct_data(DataTable dataTable) {
        User firstUser = users.get(0);
        List<Map<String, String>> expectedUsers = dataTable.asMaps();
        for (Map<String, String> expectedUser : expectedUsers) {
            Assertions.assertEquals(Integer.parseInt(expectedUser.get("userId")), firstUser.getUserId());
            Assertions.assertEquals(Integer.parseInt(expectedUser.get("id")), firstUser.getId());
            Assertions.assertEquals(expectedUser.get("title"), firstUser.getTitle());
            Assertions.assertEquals(expectedUser.get("body"), firstUser.getBody());
        }
    }

    @When("I send GET request to the {int} endpoint")
    public void iSendGETRequestToTheEndpoint(int endpoint) {
        actualUserById = usersApiClient.getUserById(endpoint);
        assertUserValuesNotNull(actualUserById);
    }

    @Then("the response should contain a user with id {int}")
    public void theResponseShouldContainAUserWithIdId(int expectedID) {
        Assertions.assertEquals(expectedID, actualUserById.getId());
    }

    private void assertUserValuesNotNull(User user) {
        assertNotNull(user.getUserId(), "userId should not be null");
        assertNotNull(user.getId(), "id should not be null");
        assertNotNull(user.getTitle(), "title should not be null");
        assertNotNull(user.getBody(), "body should not be null");
    }

    @And("the response should contain a user with title {string}")
    public void theResponseShouldContainAUserWithTitleTitle(String expectedTitle) {
        Assertions.assertEquals(expectedTitle.trim(), actualUserById.getTitle().trim());
    }

    @And("the response should contain a user with body {string}")
    public void theResponseShouldContainAUserWithBody(String expectedBody) {
        Assertions.assertEquals(expectedBody.trim(), actualUserById.getBody().trim());
    }

    @When("I send POST request with user details:")
    public void iSendPOSTRequestWithUserDetails(DataTable dataTable) {
        List<Map<String, String>> expectedUsers = dataTable.asMaps();
        Assertions.assertEquals(1, expectedUsers.size());
        for (Map<String, String> userRawData : expectedUsers) {
            User userToCreate = new User(111, Integer.parseInt(userRawData.get("id")), userRawData.get("title"), userRawData.get("body"));
            createdUser = usersApiClient.createUser(userToCreate);
            assertUserValuesNotNull(createdUser);
        }
    }

    @Then("the response should contain a created user with id {int}")
    public void theResponseShouldContainACreatedUserWithId(int expectedID) {
        Assertions.assertEquals(expectedID, createdUser.getId());
    }
}

