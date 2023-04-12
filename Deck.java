// a. Michael Bertagna
// b. 2353491
// c. bertagna@chapman.edu
// d. CPSC 231-01
// e. MP3: Modern War(fare)

import java.util.*;

public class Deck{
  //member variable
  private LinkedList<Card> m_deck;

  //default constructor
  //creates classic 52 card deck
  public Deck(){
    m_deck = new LinkedList<Card>();

      String suit = "Clubs";
      m_deck.add(new Card("2",suit));
      m_deck.add(new Card("3",suit));
      m_deck.add(new Card("4",suit));
      m_deck.add(new Card("5",suit));
      m_deck.add(new Card("6",suit));
      m_deck.add(new Card("7",suit));
      m_deck.add(new Card("8",suit));
      m_deck.add(new Card("9",suit));
      m_deck.add(new Card("10",suit));
      m_deck.add(new Card("J",suit));
      m_deck.add(new Card("Q",suit));
      m_deck.add(new Card("K",suit));
      m_deck.add(new Card("A",suit));


      suit = "Spades";
      m_deck.add(new Card("2",suit));
      m_deck.add(new Card("3",suit));
      m_deck.add(new Card("4",suit));
      m_deck.add(new Card("5",suit));
      m_deck.add(new Card("6",suit));
      m_deck.add(new Card("7",suit));
      m_deck.add(new Card("8",suit));
      m_deck.add(new Card("9",suit));
      m_deck.add(new Card("10",suit));
      m_deck.add(new Card("J",suit));
      m_deck.add(new Card("Q",suit));
      m_deck.add(new Card("K",suit));
      m_deck.add(new Card("A",suit));


      suit = "Hearts";
      m_deck.add(new Card("2",suit));
      m_deck.add(new Card("3",suit));
      m_deck.add(new Card("4",suit));
      m_deck.add(new Card("5",suit));
      m_deck.add(new Card("6",suit));
      m_deck.add(new Card("7",suit));
      m_deck.add(new Card("8",suit));
      m_deck.add(new Card("9",suit));
      m_deck.add(new Card("10",suit));
      m_deck.add(new Card("J",suit));
      m_deck.add(new Card("Q",suit));
      m_deck.add(new Card("K",suit));
      m_deck.add(new Card("A",suit));


      suit = "Diamonds";
      m_deck.add(new Card("2",suit));
      m_deck.add(new Card("3",suit));
      m_deck.add(new Card("4",suit));
      m_deck.add(new Card("5",suit));
      m_deck.add(new Card("6",suit));
      m_deck.add(new Card("7",suit));
      m_deck.add(new Card("8",suit));
      m_deck.add(new Card("9",suit));
      m_deck.add(new Card("10",suit));
      m_deck.add(new Card("J",suit));
      m_deck.add(new Card("Q",suit));
      m_deck.add(new Card("K",suit));
      m_deck.add(new Card("A",suit));
  }

  //overloaded constructor
  public Deck(LinkedList<Card> cardCollection){
    m_deck=cardCollection;
  }

  //copy constructor
  public Deck(Deck otherDeck){
    m_deck = otherDeck.m_deck;
  }

  //accessor
  public LinkedList getDeck(){
    return m_deck;
  }

  // mutator
  public void setDeck(LinkedList<Card> deck){
    m_deck=deck;
  }

  //toString method
  public String toString(){
    return (""+this.getDeck());
  }

  //equals method
  public boolean equals(Deck otherDeck){
    return(m_deck.equals(otherDeck.m_deck));
  }

  //method returns random card in deck
  public Card deal(){
    Random rndm = new Random();
    int randNum = rndm.nextInt(m_deck.size());
    Card dealedCard = new Card((Card)(this.getDeck()).get(randNum));
    (this.getDeck()).remove(randNum);
    return dealedCard;
  }
}
