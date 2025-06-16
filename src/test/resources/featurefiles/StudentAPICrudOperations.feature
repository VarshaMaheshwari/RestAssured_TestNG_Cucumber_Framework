Feature: Testing Students app CRUD operations

  Scenario: Testing Get ALL Students API call
    Given I have all required data for API call
    When I call get all students api
    Then I get response status as 200 with valid response body

    Scenario:
