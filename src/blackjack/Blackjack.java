package blackjack;

import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args){

        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        //Create deck for player
        Deck playerDeck = new Deck();

        //Create deck for dealer
        Deck dealerDeck = new Deck();

        System.out.println("How much money would you like to start with? ");
        double playerMoney = userInput.nextDouble();



        //Game Loop
        while(playerMoney > 0){
            //Take bet
            System.out.println("You have $"+ playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney){
                System.out.println("Sorry, you do not have that much. Try a different amount.");
                System.out.println("You have $"+ playerMoney + ", how much would you like to bet?");
                playerBet = userInput.nextDouble();
            }

            boolean endRound = false;

            //Start Dealing
            //Player gets two cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Dealer gets two cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true){
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Value: " + playerDeck.cardsValue());

                //Display dealer hand
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [HIDDEN]");

                System.out.println("Would you like to (1) Hit or (2) Stand?");
                int response = userInput.nextInt();

                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You drew a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    //Bust if > 21
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("BUST! " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                    if(playerDeck.cardsValue() == 21){
                        System.out.println("Auto Stand");
                        break;
                    }
                }

                if(response == 2){
                    break;
                }
            }
            //Reveal Dealer Cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());

            if(dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false){
                System.out.println("Dealer wins");
                playerMoney -= playerBet;
                endRound = true;
            }

            while(dealerDeck.cardsValue() < 17 && endRound == false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }

            //Display total value for dealer
            System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardsValue());
            if((dealerDeck.cardsValue() > 21) && endRound == false){
                System.out.println("Dealer Busts! You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            //Push
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
                System.out.println("Push");
                endRound = true;
            }
            //Player wins
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
                System.out.println("You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if(endRound == false){
                System.out.println ("You lose");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of hand.");

        }
        System.out.println("Game Over");

    }
}
