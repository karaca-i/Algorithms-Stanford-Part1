/**
 * @author İbrahimKaraca
 * 
 * Find the number of inversions in an array.
 * (two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j)
 * 
 * Input format: 
 * the file contains all of the 100,000 integers between 1 and 100,000 
 * (inclusive) in some order, with no integer repeated.
 * 
 * Output format:
 * An integer indicates the number of inversions in the input array.
 * 
 * Time complexity: O(n log n)
 */

 package Assignment1;
import java.io.*;

// INVERSIONS, MERGE SORT
public class CountInversions {
    static long res = 0;
    public static void main(String[] args) throws IOException {
        
        getList();
        System.out.println(res);
    }

    static void getList() throws IOException{
        File fil = new File("IntegerArray.txt");
        FileReader inputFil = new FileReader(fil);
        BufferedReader in = new BufferedReader(inputFil);

        int[] arr = new int[100000];
        String s =in.readLine();
        int i = 0;
        while(s!=null)
        {
            int num = Integer.parseInt(s);
            arr[i++] = num;
            s = in.readLine();
        }
        System.out.println(i);
        in.close();

        count(arr);
    }

    static void count(int[] arr){
        if (arr.length>1){
            int[] left = new int[arr.length/2];
            int[] right = new int[arr.length-left.length];

            int i = 0;
            while (i<left.length) left[i] = arr[i++];
            for (int j = 0; j<right.length; j++)right[j] = arr[i++];

            count(left);
            count(right);
            mergeCount(left,right,arr);
        }
    }

    static void mergeCount(int[] left, int[] right, int[] arr){
        int i = 0, j = 0;
        while (i<left.length && j < right.length){
            if (left[i] < right[j]) arr[i+j] = left[i++];
            else{
                arr[i+j] = right[j++];
                res += left.length - i;
            }
        }

        while (i<left.length) arr[i+j] = left[i++];
        while (j<right.length) arr[i+j] = right[j++];
    }
    
}