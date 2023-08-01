/**
 * @author İbrahimKaraca
 * 
 * Examining the time complexity differences by choosing differenct pivots
 */

package Assignment2;
import java.io.*;

// DIFFERENT PIVOTS, QUICK SORT
public class PivotChoosing {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        // get array
        int[] arr = getArray();
        quickSort(arr, 0, arr.length-1);
        System.out.println(count);
    }
    static int[] getArray() throws IOException{
        File fil = new File("QuickSort.txt");
        FileReader inputFil = new FileReader(fil);
        BufferedReader in = new BufferedReader(inputFil);

        int[] arr = new int[10000];
        String s =in.readLine();
        int i = 0;
        while(s!=null)
        {
            int num = Integer.parseInt(s); //this is line 19
            arr[i++] = num;
            s = in.readLine();
        }
        System.out.println(i);
        in.close();

        return arr;
    }

    static void quickSort(int[] arr, int start, int end){
        if (start<end){

            int split = partition3(arr,start,end);
            count += end-start;
            quickSort(arr, start, split-1);
            quickSort(arr, split+1, end);
        }
    }

    // pivot start
    static int partition1(int[] arr, int start, int end){
        int pivot = arr[start];

        int j = start +1;
        for (int i = start+1; i<=end; i++){
            if (arr[i] < pivot){
                swap(arr,i,j);
                j++;
            }
        }
        swap(arr,j-1,start);
        return j-1;
    }

    // pivot end
    static int partition2(int[] arr, int start, int end){
        swap(arr, start, end);
        int pivot = arr[start];

        int j = start +1;
        for (int i = start+1; i<=end; i++){
            if (arr[i] < pivot){
                swap(arr,i,j);
                j++;
            }
        }
        swap(arr,j-1,start);
        return j-1;
    }
    
    // median of three pivot
    static int partition3(int[] arr, int start, int end){
        int medianIndex = middleOfThreeIndex(arr, start, (start + end)/2, end);
        swap(arr, medianIndex, start);
        int pivot = arr[start];

        int j = start +1;
        for (int i = start+1; i<=end; i++){
            if (arr[i] < pivot){
                swap(arr,i,j);
                j++;
            }
        }
        swap(arr,j-1,start);
        return j-1;
    }
    static int middleOfThreeIndex(int[] arr, int i1, int i2, int i3)
    {  
        int a = arr[i1], b = arr[i2], c = arr[i3];
        // Checking for b
        if ((a < b && b < c) || (c < b && b < a))
            return i2;
 
        // Checking for a
        else if ((b < a && a < c) || (c < a && a < b))
            return i1;
 
        else
            return i3;
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void printArr(int[] arr){
        for (int i:arr) System.out.print(i + " ");
    }
}
