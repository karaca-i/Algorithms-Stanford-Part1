/**
 * @author İbrahim Karaca
 * 
 * Time Complexity O(n)
 */

import java.util.*;
import java.io.*;

/*
 * The range of the input is roughly from -1*10^11 to 1*10^11, and there are
 * 1000000 numbers. Suppose it is distributed evenly, every bucket of range 
 * 200000 expect one number.
 * So for every a, we expect to find b in three buckets, which is index[-a],
 * index[-a]-1, index[-a]+1 such that a+b can be in the [-10000,10000] range
 * 
 * When we set the size of hashtable 500000 which is the half of the original
 * size, than the range is actually converted to 400000, you can do the math.
 * If you want to make it simple, than the hashtable's size should be 1000000.
 */

 /*
 * The range of input should be in the following format: [-1*10^11, 1*10^11)
 * max elements is not inclusive, otherwise we may get a runtime error.
 */

class HashTable{
    Bucket[] buckets;
    final int RANGE = 200000;

    public HashTable(int size){
        buckets = new Bucket[size];
        for (int i = 0; i<size; i++){
            buckets[i] = new Bucket();
        }
    }

    public void addLong(long l){
        int index = (int)((l / RANGE) + 500000);
        buckets[index].addLong(l);
    }
}

class Bucket{
    List<Long> table;

    public Bucket(){
        table = new ArrayList<Long>();
    }

    public void addLong(long l){
        if (!table.contains(l)) table.add(l);
    }
}
public class TwoSumAlternative {
    int count;

    public TwoSumAlternative() throws FileNotFoundException {
        Scanner in = new Scanner(new File("Input.txt"));

        long[] numbers = new long[1000000];
        HashTable table = new HashTable(1000000);

        for (int i = 0; i<numbers.length; i++){
            long l = in.nextLong();
            numbers[i] = l;
            table.addLong(l);
        }


        Set<Integer> sums = new HashSet<Integer>();

        for (long l : numbers){
            int invertedIndex = (int)((-l / table.RANGE) + 500000);
            for (int j = Math.max(0,invertedIndex-1); j<1000000 && j <= invertedIndex + 1; j++){
                for (long l2 : table.buckets[j].table){
                    int sum = (int)(l + l2);
                    if (sum <= 10000 && sum >= -10000 && !sums.contains(sum)) sums.add(sum); 
                }
            }
        }

        this.count = sums.size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        TwoSumAlternative twoSum = new TwoSumAlternative();
        System.out.println(twoSum.count);
    }
}