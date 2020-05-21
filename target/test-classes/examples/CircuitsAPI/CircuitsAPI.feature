Feature: CircuitsApi
  In order to impress my friends
  As a Formula 1 fan
  I want to know the number of races for a given Formula 1 season

  Background:
    * url 'http://ergast.com/api/f1/'

  Scenario Outline: Check the number of races in a season
    Given path <season>+'.json'
    When method get
    * def total = response.MRData.total
    * def sizeOfCircuits = response.MRData.RaceTable.Races.length
    Then assert <numberOfCircuits> == total
    And assert <numberOfCircuits> == sizeOfCircuits
    Examples:
      | season | numberOfCircuits |
      | 2017   | 20               |
      | 2016   | 21               |
      | 1966   | 9                |
      | 1950   | 8                |