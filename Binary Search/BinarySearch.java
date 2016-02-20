
import java.util.Arrays;


public class BinarySearch {
    
    /**
     * Conducts a binary search
     * @param numbers the numbers to search through (must be in order)
     * @param key the number to search for
     * @return the index of the key or -1 if not found
     */
    public static int binarySearch(int[] numbers, int key)
    {
        int low = 0;
        int high = numbers.length - 1;
        
        while (low <= high)
        {
            int middle = (low + high) / 2; // Integer division
            
            if (numbers[middle] == key)
                return middle;
            else if (key < numbers[middle])
                high = middle - 1;
            else
                low = middle + 1;
        }
        
        return -1;
    }
    
    public static void main(String[] args)
    {
        int[] nums = { 1, 2, 3, 4, 5 };
        System.out.println(binarySearch(nums, 4));
        
        // Alternatively...
        System.out.println(Arrays.binarySearch(nums, 4));
    }
    
}
