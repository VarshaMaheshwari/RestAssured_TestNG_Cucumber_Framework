Feature: Testing Students app CRUD operations

  Scenario: Testing Get ALL Students API call
    Given I have all required data for API call
    When I call get all students api
    Then I get response status as 200 with valid response body

  Scenario Outline: Adding multiple students using Create Student API
    Given I have "<firstName>" "<lastName>" "<email>" "<programme>" "<courses>" data API payload
    When I call create students api
    Then I get response status as 201 with response body contains success message
    Examples:
      | firstName | lastName  | email                  | programme        | courses            |
      | Test901     | TestUser1 | testuser903434@gtest.com | Computer science | DSA, Java, Python  |
     # | Test2     | TestUser2 | testuser902@autotest.com | Computer science | DSA, Java, C sharp |

#    Scenario: Update student details



