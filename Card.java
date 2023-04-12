// a. Michael Bertagna
// b. 2353491
// c. bertagna@chapman.edu
// d. CPSC 231-01
// e. MP3: Modern War(fare)

public class Card{
  //member variables
  private String m_value;
  private String m_suit;

  //default constructor
  public Card(){
    m_value=null;
    m_suit=null;
  }

  //overloaded constructor
  public Card(String value, String suit){
    m_value=value;
    m_suit=suit;
  }

  //copy constructor
  public Card(Card anotherCard){
    m_value=anotherCard.m_value;
    m_suit=anotherCard.m_suit;
  }

  //accessors
  public String getValue(){
    return m_value;
  }
  public String getSuit(){
    return m_suit;
  }

  //mutators
  public void setValue(String value){
    m_value=value;
  }
  public void setSuit(String suit){
    m_suit=suit;
  }

  //toString method
  public String toString(){
    return (m_value+" of "+m_suit);
  }

  //equals method
  public boolean equals(Card anotherCard){
    return (m_value.equals(anotherCard.m_value)&&
    m_suit.equals(anotherCard.m_suit));
  }

  //method associates number with card based on card hierarchy (mainly used to associate ints to
  // J, Q, K, and A) and returns number as an int
  public int numValue(){
    int value = 0;
    switch (this.getValue()){
      case "2":
        value=2;
        break;
      case "3":
        value=3;
        break;
      case "4":
        value=4;
        break;
      case "5":
        value=5;
        break;
      case "6":
        value=6;
        break;
      case "7":
        value=7;
        break;
      case "8":
        value=8;
        break;
      case "9":
        value=9;
        break;
      case "10":
        value=10;
        break;
      case "J":
        value=11;
        break;
      case "Q":
        value=12;
        break;
      case "K":
        value=13;
        break;
      case "A":
        value=14;
        break;
    }
    return value;
  }
}
