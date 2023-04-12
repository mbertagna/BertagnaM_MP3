// a. Michael Bertagna
// b. 2353491
// c. bertagna@chapman.edu
// d. CPSC 231-01
// e. MP3: Modern War(fare)

import java.util.*;

public class Simulation{
  //member variables
  private int m_numTotalBattles;
  private int m_numTotalWars;
  private int m_numTotalDoubleWars;
  private int m_maxNumBattles;
  private int m_minNumBattles;
  private int m_maxNumWars;
  private int m_minNumWars;
  private double m_avgNumBattlesPerGame;
  private double m_avgNumWarsPerGame;
  private double m_avgNumDoubleWarsPerGame;

  //default constructor
  public Simulation(){
    m_numTotalBattles=0;
    m_numTotalWars=0;
    m_numTotalDoubleWars=0;
    m_maxNumBattles=0;
    m_maxNumWars=0;
    m_minNumBattles=0;
    m_minNumWars=0;
    m_avgNumBattlesPerGame=0;
    m_avgNumWarsPerGame=0;
    m_avgNumDoubleWarsPerGame=0;
  }

  //accessors
  public int getNumTotalBattles(){
    return m_numTotalBattles;
  }
  public int getNumTotalWars(){
    return m_numTotalWars;
  }
  public int getNumTotalDoubleWars(){
    return m_numTotalDoubleWars;
  }
  public int getMaxNumBattles(){
    return m_maxNumBattles;
  }
  public int getMinNumBattles(){
    return m_minNumBattles;
  }
  public int getMaxNumWars(){
    return m_maxNumWars;
  }
  public int getMinNumWars(){
    return m_minNumWars;
  }
  public double getAvgNumBattlesPerGame(){
    return m_avgNumBattlesPerGame;
  }
  public double getAvgNumWarsPerGame(){
    return m_avgNumWarsPerGame;
  }
  public double getAvgNumDoubleWarsPerGame(){
    return m_avgNumDoubleWarsPerGame;
  }

  //equals method
  public boolean equals(Simulation anotherSimulation){
    return(m_numTotalBattles==anotherSimulation.m_numTotalBattles&&
    m_numTotalWars==anotherSimulation.m_numTotalWars&&
    m_numTotalDoubleWars==anotherSimulation.m_numTotalDoubleWars&&
    m_maxNumBattles==anotherSimulation.m_maxNumBattles&&
    m_maxNumWars==anotherSimulation.m_maxNumWars&&
    m_minNumBattles==anotherSimulation.m_minNumBattles&&
    m_minNumWars==anotherSimulation.m_minNumWars&&
    m_avgNumBattlesPerGame==anotherSimulation.m_avgNumBattlesPerGame&&
    m_avgNumWarsPerGame==anotherSimulation.m_avgNumWarsPerGame&&
    m_avgNumDoubleWarsPerGame==anotherSimulation.m_avgNumDoubleWarsPerGame);
  }

  //method plays the number of specified games of war and calculates stats based on all games
  public void simulation(int numberOfGames){
    int firstGameCheck=0;
    //loop runs specified number of games
    for (int i = 0 ; i < numberOfGames ; ++i){
      ++firstGameCheck;
      Game trackedGame = new Game();
      Player playerWhoWonGame = new Player();
      playerWhoWonGame=trackedGame.play();
      m_numTotalBattles+=trackedGame.getNumBattles();
      m_numTotalWars+=trackedGame.getNumWars();
      m_numTotalDoubleWars+=trackedGame.getNumDoubleWars();
      //sets min battles and wars to first number of battles and wars
      if (firstGameCheck==1){
        m_minNumBattles=m_numTotalBattles;
        m_minNumWars=m_numTotalWars;
      }
      //changes number of min and max battles and wars after every game if necessary
      if (trackedGame.getNumBattles()>m_maxNumBattles){
        m_maxNumBattles=trackedGame.getNumBattles();
      }
      if (trackedGame.getNumBattles()<m_minNumBattles){
        m_minNumBattles=trackedGame.getNumBattles();
      }
      if (trackedGame.getNumWars()>m_maxNumWars){
        m_maxNumWars=trackedGame.getNumWars();
      }
      if (trackedGame.getNumWars()<m_minNumWars){
        m_minNumWars=trackedGame.getNumWars();
      }
      if (playerWhoWonGame.getNumber()==1){
        WarLogger.getInstance().logGameOutcome(firstGameCheck,WarLogger.P1);
      }
      if (playerWhoWonGame.getNumber()==2){
        WarLogger.getInstance().logGameOutcome(firstGameCheck,WarLogger.P2);
      }
    }
    //calculates stats based on all games after loop breaks
    this.calculate(numberOfGames);
  }

  //helper method for simulate method: calculates average number of battles, wars, and double wars per game
  private void calculate(int numberOfGames){
    m_avgNumBattlesPerGame=(double)m_numTotalBattles/(double)numberOfGames;
    m_avgNumWarsPerGame=(double)m_numTotalWars/(double)numberOfGames;
    m_avgNumDoubleWarsPerGame=(double)m_numTotalDoubleWars/(double)numberOfGames;
  }

  //method prints statistics of all games played
  public void report(){
    System.out.println("Average Number of Battles per Game: "+m_avgNumBattlesPerGame
    +"\nAverage Number of Wars per Game: "+m_avgNumWarsPerGame
    +"\nAverage Number of Double Wars per Game: "+m_avgNumDoubleWarsPerGame
    +"\nMaximum Number of Battles in a Game: "+m_maxNumBattles
    +"\nMinimum Number of Battles in a Game: "+m_minNumBattles
    +"\nMaximum Number of Wars in a Game: "+m_maxNumWars
    +"\nMinimum Number of Wars in a Game: "+m_minNumWars);
  }

  //main method used to drive Card, Deck, Player, Game, and Simulation classes
  //takes int as command line input and plays int number of games and prints stats
  public static void main(String[] args) {
    Simulation warSim=new Simulation();
    warSim.simulation(Integer.parseInt(args[0]));
    warSim.report();
    WarLogger.getInstance().release();
  }
}
