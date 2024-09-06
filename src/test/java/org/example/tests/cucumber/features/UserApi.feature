@ApiTest
Feature: Fetch user posts from JSONPlaceholder API

  As a tester
  I want to fetch users from the JSONPlaceholder API
  So that I can validate the response structure and status code

  Scenario: Get all user posts
    Given the API is available
    When I send a GET request to the posts endpoint
    Then the response should contain a list of users
    And each user should have all the fields
    And the first user should have correct data as:
      | userId | id | title                                                                      | body                                                                                                                                                              |
      | 1      | 1  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |

  Scenario Outline: Get a specific user by ID
    Given the API is available
    When I send GET request to the <id> endpoint
    Then the response should contain a user with id <id>
    And the response should contain a user with title <title>
    And the response should contain a user with body <body>
    Examples:
      | userId | id | title                                                         | body                                                                                                                                                                      |
      | 1      | 5  | "nesciunt quas odio"                                          | "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"                  |
      | 1      | 3  | "ea molestias quasi exercitationem repellat qui ipsa sit aut" | "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut" |

  Scenario: Create a user
    Given the API is available
    When I send POST request with user details:
      | id  | title           | body           |
      | 101 | "testing title" | "testing body" |
    Then the response should contain a created user with id 101




