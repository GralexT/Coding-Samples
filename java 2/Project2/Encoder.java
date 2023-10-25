/**
 * @author Arran Stewart (Lecturer), Grant Alexander Taylor (Student) 21947126, Sadiya Afreen (Student) 22313318.
 *
 */

public class Encoder {

    public static final int ALPHABET_SIZE = 26;
    private final Deck deck;
    
    /** Create an Encoder using the default ordering
     * of a deck of cards.
     * 
     */
    public Encoder() {
        deck = new Deck();
    }

    /** Create an Encoder using a particular deck
     * of cards to generate the key.
     * 
     */
    public Encoder(Deck d) {
        deck = d;
    }
    
    /** Remove all non-alphabetic characters from a string,
     * and convert all alphabetic characters to upper-case.
     * 
     * @param s Input string
     * @return Sanitized string
     */
    public static String sanitize(String s) {
        return s.replaceAll("[^A-Za-z ]","").toUpperCase();
    }
    
    /** Return the position in the alphabet of an uppercase
     * character, starting from 1 (i.e., charToInt('A') returns 1,
     * charToInt('B') returns 2, and so on).
     * 
     * @param c Character to convert to an int
     * @return Result of conversion
     */
    public static int charToInt(char c) {
        return (int)c-64;
    }
    
    /** Given a position in the alphabet (starting from 1),
     * return the character at that position. 
     * (i.e. intToChar(1) returns 'A', intToChar(2) returns 'B',
     * and so on). If a number larger than 26 is passed in,
     * subtract 26 from it before applying this conversion.
     * 
     * @param c int to convert to a character
     * @return Result of conversion
     */
    public static char intToChar(int i) {
        if(i>26){i-=26;}
        return (char)(i+64);
    }
    
    /** Encode a character (inputChar) using a character from the keystream
     * (keyChar).
     * 
     * To do this, firstly convert both characters into integers,
     * as described in charToInt.
     * 
     * Then add the numbers together. If the result is greater than 26,
     * subtract 26 from it; then convert that result back to a character.
     * 
     * @param inputChar Character from message
     * @param keyChar Character from keystream
     * @return Encoded character
     */
    public static char encodeChar(char inputChar, char keyChar) {
        int sumchar = charToInt(inputChar)+charToInt(keyChar);
        if(sumchar>26){sumchar-=26;}
        return intToChar(sumchar);
    }


    /** Decode a character (inputChar) from an encrypted message using a character
     * from the keystream (keyChar).
     * 
     * Convert both characters to integers, as described for
     * charToInt. If inputChar is less than or equal to keyChar,
     * add 26 to it. Then subtract keyChar from inputChar,
     * and convert the result to a character.
     * 
     * @param inputChar Character from an encrypted message
     * @param keyChar Character from keystream
     * @return Decoded character
     */
    public static char decodeChar(char inputChar, char keyChar) {
        int ichar = charToInt(inputChar);
        int kchar = charToInt(keyChar);
        if(ichar<=kchar){ichar+=26;}
        return intToChar(ichar-kchar);
    }
    
    /** Encode the string inputText using the keystream characters
     * in keyChars, by repeatedly calling encodeChar.
     * 
     * @param inputText Message text to encode
     * @param keyChars Characters from keystream
     * @return Encoded message
     */
    public static String encodeString(String inputText, String keyChars) {
        char[] itext = inputText.toCharArray();
        char[] kchar = keyChars.toCharArray();
        char[] encodedtext = new char[inputText.length()];
        for(int i=0; i<inputText.length(); i++){encodedtext[i] = encodeChar(itext[i],kchar[i]);}
        return new String(encodedtext);
    }
    
    /** Decode the string inputText using the keystream characters
     * in keyChars, by repeatedly calling decodeChar.
     * 
     * @param inputText Encoded text which needs to be decoded
     * @param keyChars Characters from keystream
     * @return Decoded message
     */
    public static String decodeString(String inputText, String keyChars) {
        char[] itext = inputText.toCharArray();
        char[] kchar = keyChars.toCharArray();
        char[] decodedtext = new char[inputText.length()];
        for(int i=0; i<inputText.length(); i++){decodedtext[i]=decodeChar(itext[i],kchar[i]);}
        return new String(decodedtext);
    }
    
    /** Apply the Pontoon algorithm to generate the
     * next character in the *keystream*. The character
     * returned will depend on the state of the "deck"
     * instance variable when the method is called.
     * 
     * @return A keystream character
     */
    public char nextKeyStreamChar() {
        //Implement Pontoon algorithm, steps divided by spaces:
        deck.shiftDownOne(deck.JOKER1);
            
        deck.shiftDownOne(deck.JOKER2);
        deck.shiftDownOne(deck.JOKER2);

        deck.tripleCut(deck.findCard(deck.JOKER1),deck.findCard(deck.JOKER2));

        int botcard = deck.getCard(deck.size()-1);
        if(botcard==54){botcard-=1;}
        deck.countCut(botcard);

        int topcard = deck.getCard(0);
        if(topcard==54){topcard-=1;}
        int outputcard = deck.getCard(topcard);
            
        if(outputcard==54 || outputcard==53){return nextKeyStreamChar();}
        return intToChar(outputcard);
    }
    
    /** Encrypt a string, using the deck to generate
     * *keystream* characters which can be passed
     * to encodeChar.
     * 
     * @param inputString The string to encrypt
     * @return The result of encryption
     */
    public String encrypt(String inputString) {
        char[] inputarray = inputString.toCharArray();
        char[] keyarray = new char[inputarray.length];
        char[] encodearray = new char[inputarray.length];
        for(int i=0; i<inputarray.length; i++){
            keyarray[i] = nextKeyStreamChar();
            encodearray[i] = encodeChar(inputarray[i],keyarray[i]);
        }
        return new String(encodearray);
    }
    
    /** Decrypt a string, using the deck to generate
     * *keystream* characters which can be passed
     * to decodeChar.
     * 
     * @param inputString The string to decrypt
     * @return The result of decryption
     */
    public String decrypt(String inputString) {
        char[] inputarray = inputString.toCharArray();
        char[] keyarray = new char[inputString.length()];
        char[] decodearray = new char[inputString.length()];
        for(int i=0; i<inputString.length(); i++){
            keyarray[i] = nextKeyStreamChar();
            decodearray[i] = decodeChar(inputarray[i],keyarray[i]);
        }
        return new String(decodearray);
    }
}