package u6pp;
import java.util.Scanner;

public class Blackjack{
    private Card[] userHand = new Card[10];
    private Card[] dealerHand = new Card[10];
    private int num = 0;
    private int hi = 0;
    Scanner input = new Scanner(System.in);
    Deck userDeck = new Deck();
    
    
    public Blackjack(){
        
    }
    public void play(){
        
        boolean again = true;
        
        
        while(again){
            
            boolean yo = true;
            boolean check = true;
            String hit = "";
            String response = "";
            hi = 0;
            
            System.out.print("Welcome to Blackjack! What is your name?");
            
            
            num = 0;
            String name = input.nextLine();
            
            for(int l = 0; l < userHand.length;l++){
                userHand[l] = null;
            }
            
            System.out.println("Hello " + name + "! I am Gambletron 5000! Let's play some cards.");
            
            userDeck.shuffle();
            userHand[num] = userDeck.deal();
            dealerHand[num] = userDeck.deal();
            hi = 2;
            userHand[num + 1] = userDeck.deal();
            dealerHand[num + 1] = userDeck.deal();
            
            
            System.out.println("Your Hand: " + userHand[num] + " " + userHand[num + 1]);
            System.out.println("Dealer's Hand: " + dealerHand[num] + " " + dealerHand[num + 1]);
            
            if((Blackjack.isBlackjack(userHand) == true) || (Blackjack.isBlackjack(dealerHand) == true)){
                System.out.print("Result: " + Blackjack.determineResult(userHand, dealerHand));
            }
            else{
                num = 2;
                while(check){
                    
                    System.out.print("Would you like to (H)it or (S)tay: ");
                    hit = input.nextLine();
                    
                    if(hit.equals("H") || hit.equals("h")){
                        userHand[num] = userDeck.deal();
                        num++;
                        hi++;
                        
                        if(Blackjack.isBust(userHand) == true){
                            System.out.println(name + " I'm so sorry you busted!");
                            System.out.println("Result: " + Blackjack.determineResult(userHand, dealerHand));
                            check = false;
                        }
                        
                    }
                    else if(hit.equals("S") || hit.equals("s")){
                        num = 2;
                        while(yo){
                            if(dealerKeepHitting(dealerHand) == true){
                                dealerHand[num] = userDeck.deal();
                                if(Blackjack.isBust(dealerHand) == true){
                                    System.out.print("Result: " + Blackjack.determineResult(userHand, dealerHand));
                                    check = false;
                                    yo = false;
                                    break;
                                }
                            }
                            else{
                                System.out.println("Result: " + Blackjack.determineResult(userHand, dealerHand));
                                yo = false;
                                check = false;
                            }
                        }
                    }
                    else{
                        System.out.println("Invalid input, try again");
                    }
                }
            } 
            
            System.out.print("Would you like to play again? (Y)es/(N)o: ");
            response = input.nextLine();
            while(true){
                if(response.equals("Y") || response.equals("y")){
                    again = true;
                    break;
                }
                else if(response.equals("N") || response.equals("n")){
                    again = false;
                    break;
                }
                else{
                    System.out.println("Invalid input, try again");
                    System.out.println("Would you like to play again? (Y)es/(N)o: ");
                    response = input.nextLine();
                }
            }
            
        }
    }
    
    public static int calcPoints(Card[] hand){
        int points = 0;
        for(int i = 0; i < hand.length; i++){
            if(hand[i] == null){
                points+= 0;
            }
            else if((hand[i].getValue().equals("Jack")) || (hand[i].getValue().equals("Queen")) || (hand[i].getValue().equals("King"))){
                points += 10;
            }
            else if(hand[i].getValue().equals("Ace")){
                points+=11;
            }
            else{
                points = points + Integer.parseInt(hand[i].getValue());
            }
        }
        return points;
    }
    
    public static String determineResult(Card[] userHand, Card[] dealerHand){
        if(Blackjack.isBust(userHand) == true){
            return "User Loses";
        }
        else if(Blackjack.isBust(dealerHand) == true){
            return "User Wins";
        }
        else if(Blackjack.calcPoints(userHand) > Blackjack.calcPoints(dealerHand)){
            return "User Wins";
        }
        else if(Blackjack.calcPoints(userHand) < Blackjack.calcPoints(dealerHand)){
            return "User Loses";
        }
        else{
            return "User Pushes";
        }
    }
    
    public static boolean isBust(Card[] hand){
        if(Blackjack.calcPoints(hand) > 21){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean isBlackjack(Card[] hand){
        int hummingbird = 0;
        boolean something = false;
        for(int v = 0; v < hand.length; v++){
            if(hand[v] != null){
                hummingbird++;
            }
        }
        if(hummingbird == 2){
            if(Blackjack.calcPoints(hand) != 21){
                something =false;
            }
            else{
                something = true;
            }
        }
        return something;
    }
    
    
    public static boolean dealerKeepHitting(Card[] hand){
        if(Blackjack.calcPoints(hand) < 17){
            return true;
        }
        else{
            return false;
        }
    }
    
}
