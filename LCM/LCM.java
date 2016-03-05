import java.io.*;
import java.util.*;

public class LCM {
  public static void main(String[] args)throws IOException{
    System.out.println(LCM(8,28));
  }

  public static int LCM(int i, int j){
    return i / gcd(i,j) * j;
  }
  public static int gcd(int a, int b) {
      if (a == 0)
                return b;

      while (true){

        if(a < b)
        {
            int temp = a;
            a = b - a;
            b = temp;
        }
        int remainder = a%b;
        if(remainder == 0) return b;


        a = b;
        b = remainder;
      }
   }
}
