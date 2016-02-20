
import java.util.*;

public class Permutations {
    
    /**
     * Finds all permutations of a string
     * @param str the string to permute
     */
    public static void permute(String str)
    {
        permute("", str);
    }
    
    public static void permute(String prefix, String str)
    {
        if (str.length() == 0)
            System.out.println(prefix); // print out or add to arraylist
        else
        {
            for (int i=0; i < str.length(); i++)
            {
                String newPrefix = prefix + str.charAt(i);
                String newStr = str.substring(0, i) + str.substring(i+1);
                
                permute(newPrefix, newStr);
            }
        }
    }
    
    /**
     * Finds all of the permutations of an array
     * @param strs the array of strings to permute
     */
    public static void permuteArr(String[] strs)
    {
        permuteArr(new String[0], strs);
    }
    
    public static void permuteArr(String[] prefixes, String[] strs)
    {
        if (strs.length == 0)
            System.out.println(Arrays.toString(prefixes));
        else
        {
            for (int i=0; i < strs.length; i++)
            {
                String[] newPrefixes = new String[prefixes.length+1];
                String[] newStrs = new String[strs.length-1];
                
                for (int j=0; j < prefixes.length; j++) // or  System.arraycopy(prefixes, 0, newPrefixes, 0, prefixes.length);
                    newPrefixes[j] = prefixes[j];
                newPrefixes[prefixes.length] = strs[i];
                
                for (int j=0; j < i; j++) // or System.arraycopy(strs, 0, newStrs, 0, i);
                    newStrs[j] = strs[j];
                for (int j=i+1; j < strs.length; j++)
                    newStrs[j-1] = strs[j];
                
                permuteArr(newPrefixes, newStrs);
            }
        }
    }
    
    public static void main(String[] args)
    {
        permute("abc");
        System.out.println();
        permuteArr(new String[] {"a", "b", "c"});
    }
    
}
