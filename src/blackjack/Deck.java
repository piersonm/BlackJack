package blackjack;

import java.util.*;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck(){
        //Generate Cards
        for(Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()){
                //Add a new card to deck
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
    }

    //Shuffle cards
    public void shuffle(){
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        //Use Random
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for(int i = 0; i < originalSize; i++){
            //Generate random index
            randomCardIndex = random.nextInt((this.cards.size() - 1) + 1);
            tempDeck.add(this.cards.get(randomCardIndex));
            //Remove from original deck
            this.cards.remove(randomCardIndex);
        }

        this.cards = tempDeck;
    }

    public String toString() {
        String cardListOutput = "";
        int i = 0;

        for (Card aCard : this.cards) {
            cardListOutput += "\n" + aCard.toString();
            i++;
        }
        return cardListOutput;
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    //Draws from deck
    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int deckSize(){
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();

        //put cards into moveTo deck
        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }

    //Return value of cards in deck
    public int cardsValue(){
        int totalValue= 0;
        int aces = 0;

        for(Card aCard : this.cards){
            switch(aCard.getValue()) {
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;
                case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                    totalValue += 10;
                    break;
                case JACK:
                    totalValue += 10;
                    break;
                case QUEEN:
                    totalValue += 10;
                    break;
                case KING:
                    totalValue += 10;
                    break;
                case ACE:
                    totalValue += 1;
                    break;
            }
        }

        //Aces value
        for(int i = 0; i < aces; i++){

            if (totalValue > 10){
                totalValue += 1;
            }
            else{
                totalValue += 11;
            }
        }
        return totalValue;
    }

}
