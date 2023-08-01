import java.util.*;
import java.io.*;

public class Median {
    
    int res = 0;

    public Median() throws FileNotFoundException {
        
        Scanner in = new Scanner(new File("Median.txt"));

        long medianSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int i = 0; i< 10000;i++){
            int num = in.nextInt();
            pq.add(num);
            
            int k = i+1;
            int time = k % 2 == 1 ? (k+1)/2 : k/2;

            int[] polled = new int[time];
            for (int j = 0; j<time; j++){
                polled[j] = pq.poll();
            }
            medianSum += polled[time-1];

            for (int a : polled){
                pq.add(a);
            }
        }

        res = (int)( medianSum % 10000);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Median median = new Median();
        System.out.println(median.res);
    }
}
