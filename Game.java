// a. Michael Bertagna
// b. 2353491
// c. bertagna@chapman.edu
// d. CPSC 231-01
// e. MP3: Modern War(fare)

import java.util.*;

public class Game{
  //member variables
  private Player m_player1;
  private Player m_player2;
  private LinkedList<Card> m_player1FlippedCards = new LinkedList<Card>();
  private LinkedList<Card> m_player2FlippedCards = new LinkedList<Card>();
  private LinkedList<Card> m_cardsInThePot = new LinkedList<Card>();
  private int m_numBattles;
  private int m_numWars;
  private int m_numDoubleWars;

  //default constructor
  public Game(){
    m_player1 = new Player(1);
    m_player2 = new Player(2);
    m_numBattles = 0;
    m_numWars = 0;
    m_numDoubleWars = 0;
    m_player1FlippedCards = new LinkedList<Card>();
    m_player2FlippedCards = new LinkedList<Card>();
    m_cardsInThePot = new LinkedList<Card>();
  }

  //copy constructor (deep copy)
  public Game(Game anotherGame){
    m_player1 = new Player(anotherGame.m_player1);
    m_player2 = new Player(anotherGame.m_player2);
    m_numBattles = anotherGame.m_numBattles;
    m_numWars = anotherGame.m_numWars;
    m_numDoubleWars = anotherGame.m_numDoubleWars;
    m_player1FlippedCards = new LinkedList<Card>(anotherGame.m_player1FlippedCards);
    m_player2FlippedCards = new LinkedList<Card>(anotherGame.m_player2FlippedCards);
    m_cardsInThePot = new LinkedList<Card>(anotherGame.m_cardsInThePot);
  }

  //accessors
  public Player getPlayer1(){
    return m_player1;
  }
  public Player getPlayer2(){
    return m_player2;
  }
  public LinkedList<Card> getplayer1FlippedCards(){
    return m_player1FlippedCards;
  }
  public LinkedList<Card> getplayer2FlippedCards(){
    return m_player2FlippedCards;
  }
  public LinkedList<Card> getCardsInThePot(){
    return m_cardsInThePot;
  }
  public int getNumBattles(){
    return m_numBattles;
  }
  public int getNumWars(){
    return m_numWars;
  }
  public int getNumDoubleWars(){
    return m_numDoubleWars;
  }

  //mutators
  public void setPlayer1(Player player1){
    m_player1=player1;
  }
  public void setPlayer2(Player player2){
    m_player2=player2;
  }
  public void setplayer1FlippedCards(LinkedList<Card> player1FlippedCards){
    m_player1FlippedCards=player1FlippedCards;
  }
  public void setplayer2FlippedCards(LinkedList<Card> player2FlippedCards){
    m_player2FlippedCards=player2FlippedCards;
  }
  public void setCardsInThePot(LinkedList<Card> cardsInThePot){
    m_cardsInThePot=cardsInThePot;
  }
  public void setNumBattles(int numBattles){
    m_numBattles=numBattles;
  }
  public void setNumWars(int numWars){
    m_numWars=numWars;
  }
  public void setNumDoubleWars(int numDoubleWars){
    m_numDoubleWars=numDoubleWars;
  }

  //toString method
  public String toString(){
    return("Player 1: "+m_player1+"\nPlayer 2: "+m_player2+"\nPlayer 1 Flipped Cards: "+
    m_player1FlippedCards+"\nPlayer 2 Flipped Cards: "+m_player2FlippedCards+
    "\nNumber of Battles: "+m_numBattles+"\nNumber of Wars: "+m_numWars+
    "\nNumber of Double Wars: "+m_numDoubleWars);
  }

  //equals method
  public boolean equals(Game anotherGame){
    return(m_player1.equals(anotherGame.m_player1)&&m_player2.equals(anotherGame.m_player2)&&
    m_numBattles==anotherGame.m_numBattles&&m_numWars==anotherGame.m_numWars&&m_numDoubleWars==anotherGame.m_numDoubleWars&&
    m_player1FlippedCards.equals(anotherGame.m_player1FlippedCards)&&m_player2FlippedCards.equals(anotherGame.m_player2FlippedCards)&&
    m_cardsInThePot.equals(anotherGame.m_cardsInThePot));
  }

  //method carries out war game between two players until a specific player wins and returns the winning Player
  public Player play(){
    //loop checks if player won each time before round starts and breaks if player has won
    while (!(m_player1.hasWon() || m_player2.hasWon())){
      //clear pot and flipped cards each round
      m_cardsInThePot.clear();
      m_player1FlippedCards.clear();
      m_player2FlippedCards.clear();
      //players flip first three cards, or all cards if they have less than three
      m_player1FlippedCards=m_player1.flip();
      m_player2FlippedCards=m_player2.flip();
      //cards added to pot (now up for grabs by either player,
      //whoever wins round takes all cards in pot)
      m_cardsInThePot.addAll(m_player1FlippedCards);
      m_cardsInThePot.addAll(m_player2FlippedCards);
      //starts battle
      this.battle();
    }
    //checks to see which player won
    Player winningPlayer = new Player();
    if (m_player1.hasWon()){
      winningPlayer=m_player1;
    }
    if (m_player2.hasWon()){
      winningPlayer=m_player2;
    }
    //returns player who won
    return winningPlayer;
  }


  //helper method for play method: carries out a battle
  //between two Players and adds the cards in the pot to the winning Player's deck
  private void battle(){
    //add one to number of battles
    ++m_numBattles;

    //log battle start
    WarLogger.getInstance().logBattle(m_numBattles,WarLogger.P1,m_player1FlippedCards.toArray(new Card[m_player1FlippedCards.size()]));
    WarLogger.getInstance().logBattle(m_numBattles,WarLogger.P2,m_player2FlippedCards.toArray(new Card[m_player2FlippedCards.size()]));

    int player1Hand=0;
    int player2Hand=0;

    //determines which player wins battle or if there is a tie
    if (m_player1FlippedCards.size()==3){
      player1Hand=calculateMedianCardValue(m_player1FlippedCards);
    }
    else if (m_player1FlippedCards.size()==2){
      player1Hand=calculateMaxCardValue(m_player1FlippedCards);
    }
    else if (m_player1FlippedCards.size()==1){
      player1Hand=returnCardValue(m_player1FlippedCards);
    }

    if (m_player2FlippedCards.size()==3){
      player2Hand=calculateMedianCardValue(m_player2FlippedCards);
    }
    else if (m_player2FlippedCards.size()==2){
      player2Hand=calculateMaxCardValue(m_player2FlippedCards);
    }
    else if (m_player2FlippedCards.size()==1){
      player2Hand=returnCardValue(m_player2FlippedCards);
    }
    //if someone wins, adds all cards in pot to winning player
    if (player1Hand>player2Hand){
      m_player1.collect(m_cardsInThePot);

      //logs battle outcome if player 1 wins
      WarLogger.getInstance().logBattleOutcome(m_numBattles,WarLogger.P1);

    }
    else if (player2Hand>player1Hand){
      m_player2.collect(m_cardsInThePot);

      //logs battle outcome if player 2 wins
      WarLogger.getInstance().logBattleOutcome(m_numBattles,WarLogger.P2);

    }
    //executes if war necessary
    else if (player2Hand==player1Hand){

      //logs battle outcome if war
      WarLogger.getInstance().logBattleOutcome(m_numBattles,WarLogger.WAR);

      boolean warOverStatus=false;
      //loop runs until someone wins a war
      while(warOverStatus==false){
        warOverStatus=this.war();
      }
    }
  }

  //helper method for battle method: carries out a war between two
  //returns true if a Player wins the war, returns false if the war is a tie
  private boolean war(){
    boolean warOver=true;
    //add one to warcount
    ++m_numWars;
    int player1Hand;
    int player2Hand;
    //clears battle hands becuase now war hands decide winner of battle
    m_player1FlippedCards.clear();
    m_player2FlippedCards.clear();
    //if statements account for players running out of cards during contigous wars
    if (m_player1.hasNoCards()){
      m_player2.collect(m_cardsInThePot);
      return warOver;
    }
    if (m_player2.hasNoCards()){
      m_player1.collect(m_cardsInThePot);
      return warOver;
    }
    //pulls top card from each players deck and adds to pot
    m_player1FlippedCards=m_player1.war();
    m_player2FlippedCards=m_player2.war();
    m_cardsInThePot.addAll(m_player1FlippedCards);
    m_cardsInThePot.addAll(m_player2FlippedCards);
    player1Hand=returnCardValue(m_player1FlippedCards);
    player2Hand=returnCardValue(m_player2FlippedCards);
    //if one player wins, adds cards to the players deck and ends war/battle
    if (player1Hand>player2Hand){
      m_player1.collect(m_cardsInThePot);
      warOver=true;

      //logs war if player 1 wins
      WarLogger.getInstance().logWarOutcome(m_numWars,WarLogger.P1);

    }
    else if (player2Hand>player1Hand){
      m_player2.collect(m_cardsInThePot);
      warOver=true;

      //logs war if player 2 wins
      WarLogger.getInstance().logWarOutcome(m_numWars,WarLogger.P2);

    }
    //accounts for double war
    else if (player2Hand==player1Hand){
      //add one to double war count
      ++m_numDoubleWars;
      //sets war over to false to continue running loop of wars
      warOver=false;

      //logs war if double war
      WarLogger.getInstance().logWarOutcome(m_numWars,WarLogger.WAR);

    }
    return warOver;
  }

  //helper method for battle method: calculates median card number value based on collection of 3 flipped Cards
  private int calculateMedianCardValue(LinkedList<Card> cardCollection){
    int card1Value = (cardCollection.get(0)).numValue();
    int card2Value = (cardCollection.get(1)).numValue();
    int card3Value = (cardCollection.get(2)).numValue();
    int[] cardValueArr = {card1Value, card2Value, card3Value};
    Arrays.sort(cardValueArr);
    return cardValueArr[1];
  }

  //helper method for battle method: calculates max card number value based on collection of 2 flipped Cards
  private int calculateMaxCardValue(LinkedList<Card> cardCollection){
    int card1Value = (cardCollection.get(0)).numValue();
    int card2Value = (cardCollection.get(1)).numValue();
    int[] cardValueArr = {card1Value, card2Value};
    Arrays.sort(cardValueArr);
    return cardValueArr[1];
  }

  //helper method for battle and war methods: returns number value of card in cardCollection
  private int returnCardValue(LinkedList<Card> cardCollection){
    return (cardCollection.get(0)).numValue();
  }
}
