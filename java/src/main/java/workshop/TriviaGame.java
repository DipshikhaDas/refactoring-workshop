package workshop;

import java.util.ArrayList;
import java.util.LinkedList;

public class TriviaGame {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {

        players.add(playerName);
        addPlayers(howManyPlayers());
        inPenaltyBox[howManyPlayers()] = false;
        announce(playerName + " was added");
        announce("They are player number " + howManyPlayers());
        return true;
    }

    private void addPlayers(int index){
        places[index] = 0;
        purses [index] = 0;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        announce(players.get(currentPlayer) + " is the current player");
        announce("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (isOdd(roll)) {
                isGettingOutOfPenaltyBox = true;

                announce(players.get(currentPlayer) + " is getting out of the penalty box");

                addPlacesAndRoll(roll);

                if (isPlayerNumberGreaterThanEleven(places[currentPlayer]))
                    setPlaces();

                announce(players.get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
                announce("The category is " + currentCategory());
                askQuestion();
            } else {
                announce(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            addPlacesAndRoll(roll);
            if (isPlayerNumberGreaterThanEleven(places[currentPlayer])) setPlaces();

            announce(players.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            announce("The category is " + currentCategory());
            askQuestion();
        }

    }
    private void setPlaces(){
        places[currentPlayer] = places[currentPlayer] - 12;
    }
    private boolean isPlayerNumberGreaterThanEleven(int playerNumber){
        if(playerNumber > 11) return true;
        else return false;
    }
    private boolean isOdd(int number){
        if(number % 2 == 0)
            return true;
        else return false;
    }
    private void addPlacesAndRoll(int roll){
        places[currentPlayer] = places[currentPlayer] + roll;
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            announce(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            announce(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            announce(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            announce(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (checkPopCategory()) return "Pop";
        else if (checkScienceCategory()) return "Science";
        else if (checkSportsCategory()) return "Sports";
         else return "Rock";
    }

    private boolean checkPopCategory(){
        if(places[currentPlayer]%4 == 0)
            return true;
        else return false;
    }

    private boolean checkScienceCategory(){
        if(places[currentPlayer]%4 == 1)
            return true;
        else return false;
    }

    private boolean checkSportsCategory(){
        if(places[currentPlayer]%4 == 2)
            return true;
        else return false;
    }


    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                announce("Answer was correct!!!!");
                increasePurseCount();
                announce(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                incrementCurrentPlayerCount();
                if(checkPlayerNumberAndSize()) 
                    makeCurrentPlayerEqualsZero();
                return winner;
            } else {
                incrementCurrentPlayerCount();
                if (checkPlayerNumberAndSize()) 
                    makeCurrentPlayerEqualsZero();
                return true;
            }


        } else {

            announce("Answer was correct!!!!");
            increasePurseCount();
            announce(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            incrementCurrentPlayerCount();
            if (checkPlayerNumberAndSize()) makeCurrentPlayerEqualsZero();

            return winner;
        }
    }

    private void increasePurseCount() {
        purses[currentPlayer]++;
    }

    private boolean checkPlayerNumberAndSize(){
        if (currentPlayer == howManyPlayers())
            return true;
        else return false;
    }
    
    public void makeCurrentPlayerEqualsZero(){
        currentPlayer = 0;
    }
    
    public void incrementCurrentPlayerCount(){
        currentPlayer++;
    }

    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        incrementCurrentPlayerCount();
        if (checkPlayerNumberAndSize()) makeCurrentPlayerEqualsZero();
        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}
