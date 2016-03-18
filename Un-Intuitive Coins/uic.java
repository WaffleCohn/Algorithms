import java.util.*;
import java.io.*;

public class uic{
  public static void main(String[] args) throws IOException{
    System.out.println(uic(new int[]{1, 3, 6, 10}, 12));
    System.out.println(uic(new int[]{1, 7, 9, 15}, 44));
  }
  //n is needed value
  //coins is the coins
  public static int uic(int[] coins, int n){
    int[] mins = new int[n+1];
    Arrays.fill(mins, -1);
    mins[0] = 0;
    for(int i = 1; i < n+1; i++){
      for(int c : coins){
        if(i-c >= 0){
          if(mins[i-c] != -1){
            mins[i] = Math.min(mins[i-c]+1, (mins[i]==-1?Integer.MAX_VALUE:mins[i]));
          }
        }
      }
    }

    return mins[n];
  }
}
