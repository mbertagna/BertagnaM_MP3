// a. Michael Bertagna
// b. 2353491
// c. bertagna@chapman.edu
// d. CPSC 231-01
// e. MP3: Modern War(fare)

import java.util.*;

public class Player{
  //member variable
  private int m_number;
  private LinkedList<Card> m_ownedCards;
  //static variable not initialized here becuase new deck must be created every time player 1
  //created in order to avoid empty deck error
  private static Deck m_gameDeck;

  //default constructor
  public Player(){
    m_number=0;
    m_ownedCards = new LinkedList<Card>();
  }

  //overloaded constructor
  public Player(int number){
    //as discussed during creation of memeber variables, new gamedeck created with classic 52 cards
    //everytime player 1 created
    if (number==1){
      m_gameDeck = new Deck();
    }
    m_number = number;
    m_ownedCards = new LinkedList<Card>();
    //26 random cards dealt to player
    for (int i = 0 ; i < 26 ; i++){
      m_ownedCards.add(m_gameDeck.deal());
    }
  }

  //copy constructor
  public Player(Player anotherPlayer){
    m_number = anotherPlayer.m_number;
    m_ownedCards = anotherPlayer.m_ownedCards;
  }

  //accessors
  public int getNumber(){
    return m_number;
  }
  public LinkedList<Card> getOwnedCards(){
    return m_ownedCards;
  }

  public Deck getGameDeck(){
    return m_gameDeck;
  }

  //mutators
  public void setNumber(int number){
    m_number=number;
  }
  public void setOwnedCards(LinkedList<Card> ownedCards){
    m_ownedCards=ownedCards;
  }
  public void setNumber(Deck gameDeck){
    m_gameDeck=gameDeck;
  }


  //toString method
  public String toString(){
    return ("Player Number: "+m_number+", Owned Cards: "+m_ownedCards.toString());
  }

  //equals method
  public boolean equals(Player anotherPlayer){
    return(m_number==anotherPlayer.m_number&&m_ownedCards.equals(anotherPlayer.m_ownedCards));
  }

  //method returns top/first three cards in owned cards, if only two or three cards owned,
  //returns those cards
  public LinkedList<Card> flip(){
    LinkedList<Card> frontCardList = new LinkedList<Card>();
    if (m_ownedCards.size()==1){
      frontCardList.add(m_ownedCards.get(0));
      m_ownedCards.remove(0);
    }
    else if (m_ownedCards.size()==2){
      frontCardList.add(m_ownedCards.get(0));
      frontCardList.add(m_ownedCards.get(1));
      m_ownedCards.remove(0);
      m_ownedCards.remove(0);
    }
    else{
      frontCardList.add(m_ownedCards.get(0));
      frontCardList.add(m_ownedCards.get(1));
      frontCardList.add(m_ownedCards.get(2));
      m_ownedCards.remove(0);
      m_ownedCards.remove(0);
      m_ownedCards.remove(0);
    }
    return frontCardList;
  }

  //method takes a collection of cards and adds it to the designated player's owned cards
  public void collect(LinkedList<Card> cardCollection){
    Collections.shuffle(cardCollection);
    m_ownedCards.addAll(cardCollection);
  }

  //method returns true if a player has all 52 card and has therefore won, else returns false
  public boolean hasWon(){
    return(m_ownedCards.size()==52);
  }

  //method returns true is player has no cards, else false
  public boolean hasNoCards(){
    return(m_ownedCards.size()==0);
  }

  //method returns collection containing top/first card in a players deck (for use in a war)
  public LinkedList<Card> war(){
    LinkedList<Card> frontCardList = new LinkedList<Card>();
    frontCardList.add(m_ownedCards.get(0));
    m_ownedCards.remove(0);
    return frontCardList;
  }
}
