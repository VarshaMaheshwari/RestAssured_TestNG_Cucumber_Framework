Feature: User API CRUD operation validations
  As a tester
  I want to validate the CRUD operations for Users API from reqres.in
  so that I can ensure API behaves as expected


  Scenario Outline: Verify Get all users API works as expected
    Given I have API request payload
    When I invoke API request with <page> and <per_page>
    Then I receive response code as 200
    And Response body has total as <total> and total_pages as <total_pages>
    And Response body contains data as per expected values

    Examples:
      | page | per_page |total|total_pages|
      | 1    | 4        | 12    |     3      |
      | 2    | 10       |  12   |     2      |
      |      | 4        |12     |3           |
      | 3    |          |       |            |

