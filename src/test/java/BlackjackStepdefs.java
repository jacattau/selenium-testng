import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Random;

public class BlackjackStepdefs {
    String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    String[] dealtCards;

    @Given("^I am playing a game of blackjack$")
    public void iAmPlayingAGameOfBlackjack() {
        System.out.println("You are playing a game of blackjack!");
    }

    @When("I am dealt {int} cards")
    public void iAmDealtCards(int arg0) {
        dealtCards = new String[arg0];
        Random random = new Random();
        dealtCards[0] = cards[random.nextInt(cards.length)];
        dealtCards[1] = cards[random.nextInt(cards.length)];
        System.out.println("You were dealt a " + dealtCards[0] + " and a " + dealtCards[1]);
    }

    @Then("I must decide which action to take next")
    public void iMustDecideWhichActionToTakeNext() {

        if (dealtCards[0].equals(dealtCards[1])){
            System.out.println("Split the cards");
        }else {
            for (int i = 0; i < dealtCards.length; i++){
                if (dealtCards[i].equals("A")){
                    dealtCards[i] = "11";
                }
                if (dealtCards[i].equals("J") || dealtCards[i].equals("Q") || dealtCards[i].equals("K")){
                    dealtCards[i] = "10";
                }
            }

            int card1Value = Integer.parseInt(dealtCards[0]);
            int card2Value = Integer.parseInt(dealtCards[1]);

            if ((card1Value + card2Value) > 21){
                if (card1Value == 11){
                    card1Value = 1;
                }
                if (card2Value == 11 && card1Value != 1){
                    card2Value = 1;
                }
            }

            int sum = card1Value + card2Value;

            System.out.println("The current value of your cards is " + sum);

            if (sum > 21){
                System.out.println("Sorry, you bust!");
            }else if (sum == 21) {
                System.out.println("BLACKJACK!");
            }else {
                System.out.println("Hit!");
            }
        }
    }
}
