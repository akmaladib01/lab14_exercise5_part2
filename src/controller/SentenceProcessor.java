package controller;

public class SentenceProcessor {

    private int size = 65535;

    private String sentence;

    public SentenceProcessor(byte[] byteData) {
        this.sentence = new String(byteData);
    }

    public String getSentence() {
        return sentence;
    }

    /**
     * This method converts a value into an array of bytes
     *
     * @param value the value to convert
     * @return the byte array representation of the value
     */
    public byte[] convertToByteArray(int value) {
        byte[] outData;
        String stringValue = Integer.toString(value);
        outData = stringValue.getBytes();
        return outData;
    }

    /**
     * This method counts the number of characters in a sentence
     *
     * @return the number of characters
     */
    public int countCharacters() {
        return sentence.length();
    }

    /**
     * This method counts the number of vowels in a sentence
     *
     * @return the number of vowels
     */
    public int countVowel() {
    	
        int vowels = 0;
        
        for (int i = 0; i < sentence.length(); i++) {
        	
            char currentChar = Character.toLowerCase(sentence.charAt(i));
            
            if (isVowel(currentChar)) {
            	
                vowels++;
            }
        }
        return vowels;
    }

    /**
     * This method counts the number of consonants in a sentence
     *
     * @return the number of consonants
     */
    public int countConsonant() {
    	
        int consonants = 0;
        
        for (int i = 0; i < sentence.length(); i++) {
        	
            char currentChar = Character.toLowerCase(sentence.charAt(i));
            
            if (Character.isLetter(currentChar) && !isVowel(currentChar)) {
            	
                consonants++;
            }
        }
        return consonants;
    }

    /**
     * This method counts the number of punctuation marks in a sentence
     *
     * @return the number of punctuation marks
     */
    public int countPunctuation() {
    	
        int punctuations = 0;
        
        for (int i = 0; i < sentence.length(); i++) {
        	
            char currentChar = sentence.charAt(i);
            
            if (isPunctuation(currentChar)) {
            	
                punctuations++;
            }
        }
        return punctuations;
    }

    /**
     * This method checks if a character is a vowel
     *
     * @param check the character to check
     * @return true if the character is a vowel, false otherwise
     */
    private boolean isVowel(char check) {
    	
    	check = Character.toLowerCase(check);
        return check == 'a' || check == 'e' || check == 'i' || check == 'o' || check == 'u';
    }

    /**
     * This method checks if a character is a punctuation mark
     *
     * @param check the character to check
     * @return true if the character is a punctuation mark, false otherwise
     */
    private boolean isPunctuation(char check) {
    	
        return check == ',' || check == '.' || check == '?' || check == '!' || check == '-' || check == '"';
    }
}