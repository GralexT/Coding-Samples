/** Represents a deck of cards, and operations that can be performed
 * on it.
 * 
 * @author Arran Stewart (Lecturer), Grant Alexander Taylor (Student) 21947126, Sadiya Afreen (Student) 22313318.
 *
 */

import java.util.Random;

public class Deck {
    /** The size of a deck of cards. Four suits of thirteen cards,
     * plus two jokers.
     */
    public final static int DEFAULT_DECK_SIZE = 13 * 4 + 2;
    
    /** The value given to the first joker
     **/
    public final static int JOKER1 = 13 * 4 + 1;
    
    /** The value given to the second joker
     **/
    public final static int JOKER2 = 13 * 4 + 2;
    
    // Array holding ints representing the cards. 
    // Card values should start from 1, and each int
    // should be unique within the array.
    private int[] cards;
    
    /** Create a deck of cards in the default order.
     */
    public Deck() {
        cards = new int[DEFAULT_DECK_SIZE];
        for (int i = 0; i < DEFAULT_DECK_SIZE; i++) {
            cards[i] = i + 1;
        }
    }
    
    /** Returns true when all values of the array arr are
     * different to each other; returns false otherwise.
     * 
     * @param arr An array of int values to be checked
     * @return Whether all values in the array are different
     */
    public static boolean allDifferent(int[] arr) {
        int[] sortedarr = new int[arr.length];
        for(int i=0; i<arr.length; i++){sortedarr[i] = arr[i];}
        for(int i=0; i<sortedarr.length-1; i++){if(sortedarr[i]==sortedarr[i+1]){return false;}}
        return true;
    }

    /** Construct a deck of cards from a String of comma-separated values.
     * 
     * @param s A string, consisting of comma-separated integers.
     */
    public Deck(String inputString) {
        if (inputString.equals("")) {
            cards = new int[0];
            return;
        }
        
        String[] strings = inputString.split(",");
        
        cards = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            cards[i] = Integer.parseInt( strings[i] );
        }
        validateCards();
    }
    
    /** This method should check whether all the
     * values in the "cards" instance variable
     * are different.
     *    If they are not, it should throw an
     * IllegalArgumentException exception.
     * 
     */
    private void validateCards() {
       if(!allDifferent(cards)){throw new IllegalArgumentException("Duplicate Cards Exist.");};
    }
    
    public int size() {
        return cards.length;
    }
    
    public int getCard(int idx) {
        return cards[idx];
    }
    
    /** Shuffles elements of an array into a random permutation.
     * 
     * @param arr Array to be shuffled.
     */
    public static void shuffleArray(int[] arr) {
        String s = "";
        for(int i=0; i<arr.length-1; i++){s = s+arr[i]+',';}
        s = s + arr[arr.length-1];
        Deck d = new Deck(s);
        Random rand = new Random();
        //int r = rand.nextInt(1000)+1;   //For a thorough Random shuffle. Slows test time massively!!
        //for(int i=0; i<r; i++){         //For a thorough Random shuffle. Slows test time massively!!
            d.tripleCut(rand.nextInt(arr.length-1)+1,rand.nextInt(arr.length-1)+1);
            d.countCut(rand.nextInt(arr.length-1)+1);
            d.shiftDownOne(rand.nextInt(arr.length-1)+1);
        //}                               //For a thorough Random shuffle. Slows test time massively!!
        for(int i=0; i<d.cards.length; i++){arr[i] = d.cards[i];}
    }
    
    public void shuffle() {
        shuffleArray(cards);
    }
    
    /** Find the position in the deck of the card
     * with value cardVal.
     * 
     * @param cardVal The card to find
     * @return The position of the card to find, or -1
     *     if it wasn't in the deck. 
     */
    public int findCard(int cardVal) {
        for(int i=0; i<cards.length; i++){if(cards[i]==cardVal){return i;}}
        return -1;
    }
    
    /** Shift a particular card down the deck by one place,
     * and if this would take you past the end of the deck,
     * treat the end of the deck as joining onto the beginning.
     * 
     * In other words: for any card except the last card, 
     * the card is swapped with the card immediately 
     * after it. For the last card: it gets inserted after the
     * first card, and the second card and all subsequent cards
     * are "moved down one".  
     * 
     * If the argument passed is not found in the deck,
     * this method should throw an IllegalArgumentException 
     * exception.
     * 
     * @param cardVal The value of the card to be shifted.
     */
    public void shiftDownOne(int cardVal) {
        int pos = findCard(cardVal);
        if(pos==-1){throw new IllegalArgumentException();}
        if(pos==cards.length - 1){
            for(int x=cards.length-1; x>1; x--) {cards[x] = cards[x-1];}
            cards[1] = cardVal;
        }
        else{
            cards[pos] = cards[pos+1]; 
            cards[pos+1] = cardVal;
        }
    }
    
    /** Perform a "triple cut": Given the positions of 2 cards in the deck,
     * divide the deck into three "chunks": 
     *  chunk "A", the "top" - cards before either position
     *  chunk "B", the "middle" - cards lying between the 2 positions
     *  chunk "C", the "bottom" - cards after either position.
     *  
     *  Reorder the deck by swapping the top and bottom chunks
     *  (chunks "A" and "C").
     *  
     * @param pos1 Position of a "fixed" card, counting from 0.
     * @param pos2 Position of another "fixed" card, counting from 0.
     */
    public void tripleCut(int pos1, int pos2) {
        //Make Pos1 the smaller position and pos2 the larger position.
        if(pos1>pos2){int temp=pos1;pos1=pos2;pos2=temp;}
        
        int[] newcards = new int[cards.length];
        int lastindex = 0;
        for(int i=pos2+1; i<cards.length; i++,lastindex++){newcards[lastindex]=cards[i];}
        for(int i=pos1; i<pos2+1; i++,lastindex++){newcards[lastindex]=cards[i];}
        for(int i=0; i<pos1; i++,lastindex++){newcards[lastindex]=cards[i];}
        cards = newcards;
    }
    
    /** Perform a "count cut". Let n be a number of cards.
     * Remove n cards from the top of the deck, and place them
     * just above the last card.
     * 
     * @param numCards
     */
    public void countCut(int numCards) {
        int[] tempcards = new int[cards.length];
        int tempi = 0;
        for(int i=numCards; i<cards.length-1; i++,tempi++){tempcards[tempi]=cards[i];}
        for(int i=0; tempi<cards.length-1; i++,tempi++){tempcards[tempi]=cards[i];}
        tempcards[cards.length-1] = cards[cards.length-1];
        cards = tempcards;
    }

}