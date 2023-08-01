package Assignment2;

public class RandomizedSelection {
    public static void main(String[] args) {
        int[] arr = {1,6,4,3,8,13,78,97,5}; // 3.el : 4
        int res = rselect(arr, 0, arr.length-1, 3);
        System.out.println(res);
    }

    // RANDOMIZED SELECTION O(n)
    static int rselect(int[] arr, int start, int end, int ithElement){
        if (start < end){
            int split = partititon(arr, start, end);
            if (ithElement == split + 1) return arr[split];
            if (split + 1 < ithElement) return rselect(arr, start+1, end, ithElement);
            if (split + 1 > ithElement) return rselect(arr, start, split-1, ithElement);
        }
        return -1; // not found
    }

    static int partititon(int[] arr, int start, int end){
        int pivot = arr[end];
        int j = start;

        for (int i = start; i<end; i++){
            if (arr[i] < pivot){
                swap(arr, i, j);;
                j++;
            }
        }
        swap(arr,j,end);
        return j;
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
