/**Solution to Cipher of the White Lotus
 * PWSH 2016 invitational
 * @author Ari Cohn
 */

import java.util.*;
import java.io.*;

public class Lotus {
    public static void main(String[] args) throws IOException
    {
        Scanner scn = new Scanner(new File("lotus.in")); // Scanner to read in data file
        
        int cases = scn.nextInt(); // get number of test cases
        
        for (int i=0; i < cases; i++)
        {
            int m = scn.nextInt(); // get m value (multiplicative constant)
            int k = scn.nextInt(); // get k value (additive constant)
            
            scn.nextLine(); // flush the buffer
            
            String operation = scn.next(); // get the operation to perform
            String text = scn.nextLine(); // get the text to modify
            
            text = text.trim(); // trim whitespace off the front
            
            if (operation.equals("ENCRYPT")) 
                System.out.println(encrypt(text, m, k)); // display encrypted message
            else
                System.out.println(decrypt(text, m, k)); // display decrypted message
        }
    }
    
    /**
     * Encrypt a message
     * @param txt the plaintext to encrypt
     * @param m the multiplicative constant
     * @param k the additive constant
     * @return the encrypted message
     */
    public static String encrypt(String txt, int m, int k)
    {
        String cipherText = ""; // ciphertext to return
        
        char[] letters = txt.toUpperCase().toCharArray(); // make an array of each upper-cased character to traverse
        
        for (char letter : letters)
        {
            if (letter == 32) // spaces aren't encrypted
            {
                cipherText += " ";
                continue;
            }
            
            int newLetter = letter - 65; // convert character to 0-indexed alphabet
            
            newLetter *= m; // multiply by multiplicative constant
            newLetter += k; // add additive constant
            newLetter %= 26; // mod 26 to get alphabet values
            
            cipherText += (char) (newLetter+65); // add new letter to ciphertext string
        }
        
        return cipherText; // return encrypted message
    }
    
    /**
     * Decrypt a message
     * @param txt the ciphertext to decrypt
     * @param m the multiplicative constant
     * @param k the additive constant
     * @return the decrypted message
     */
    public static String decrypt(String txt, int m, int k)
    {
        String plainText = ""; // plaintext to return
        
        char[] letters = txt.toUpperCase().toCharArray(); // make an array of each upper case letter
        
        int inverse = modInv(m); // get the multiplicative inverse of m mod 26
        
        for (char letter : letters)
        {
            if (letter == 32)
            {
                plainText += " "; // spaces aren't encrypted, so they don't need to be decrypted
                continue;
            }
            
            int newLetter = letter - 65; // convert character to 0-indexed alphabet
            
            newLetter -= k; // subtract additive constant
            newLetter *= inverse; // multiply by the multiplicative inverse
            newLetter %= 26; // mod 26 to get alphabet values
            
            if (newLetter < 0)
                newLetter += 26; // convert negatives to positives
            
            plainText += (char) (newLetter+65); // add new letter to plaintext string
        }
        
        return plainText; // return decrypted message
    }
    
    /**
     * Get the multiplicative inverse of a number mod 26
     * @param n the number to find the inverse of
     * @return the inverse (or -1 if it doesn't exist, which shouldn't happen)
     */
    public static int modInv(int n)
    {   
        for (int i=0; i < 26; i++)
        {
            if ((n * i) % 26 == 1) 
                return i; // if i is the multiplicative inverse of n, return i
        }
        
        return -1; // inverse doesn't exist (shouldn't ever happen)
    }
}
