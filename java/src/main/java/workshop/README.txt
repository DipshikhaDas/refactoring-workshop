1. In FizzBuzz.java :
- removed all the comments to avoid comment smell.
- changed the name of the method to make it understandable without comment.
- changed the logic to make it simpler.
- removed the temporary variable "strReturn" because we can return the string directly without making that temp variable.

2. In PlaintextToHtmlConverter :
- has removed the temporary variable used in toHtml() method
- has changed the switch statement in basicHtml() and changed the logic here to make it more understandable
- changed the pushACharacterToTheOutput() a little bit and used it inside the if else statement in basicHtmlCode()
- removed the method stashNextCharacterAndAdvanceThePointer() because of the change in logic in basicHtmlCode()

3. In TrivialGame
- has created several new methods addPlayers(int index), setPlaces(), isPlayerNumberGreaterThanEleven(int playerNumber), isOdd(int number), addPlacesAndRoll(int roll), checkPopCategory(), checkScienceCategory(), checkSportsCategory(), increasePurseCount(), checkPlayerNumberAndSize(), makeCurrentPlayerEqualsZero(), incrementCurrentPlayerCount() to increase readibilty and to avoid duplicate code. 

