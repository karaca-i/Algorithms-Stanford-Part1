/**
 * @author İbrahim Karaca
 * 
 * A very slow implementation of TwoSum, but extremely basic idea.
 */

import java.util.*;
import java.io.*;


public class TwoSumSlow {
    
    int count;
    public TwoSumSlow() throws FileNotFoundException {
        long[] numbers = new long[1000000];
        Set<Long> nums = new HashSet<Long>();
        Scanner in = new Scanner(new File("Input.txt"));
        for (int i = 0;i<numbers.length; i++){
            long l = in.nextLong();
            numbers[i] = l;
            nums.add(l);
        }
        count = 0;
        for (int target = -10000; target<= 10000; target++){
            for (long l : nums){
                if (nums.contains(target -  l) && target != l*2){
                    count++;
                    break;
                }
            }
            System.out.println(target);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        TwoSumSlow t = new TwoSumSlow();
        System.out.println("Count: " + t.count);
    }
}
