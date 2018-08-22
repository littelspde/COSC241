package week11;

/**
 *  A heap sort implementation which is able to be observed through its
 *  Sorter superclass.
 *
 * @author Josh King
 */
public class HeapSort extends Sorter {

    /**
     *  Create a new HeapSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public HeapSort(Integer[] nums) {
        super(nums); // pass nums[] to the superclass Sort
    }

    /**
     * Sort the integers (which are stored in the parent Sorter class). 
     */
    public void sortNums() {
        // int i, j, comparisons, and nums[] are all protected datafields in
        // the superclass Sort so we can use them without declaring them
        comparisons = 0;

        int len = nums.length;

        for(int i = len/2 - 1; i >= 0; i--){
            heapify(nums, len, i);
            update();
        }

        for(int i = len-1; i>=0; i--){

            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            heapify(nums, i, 0);
            update();
        }
    }

    /** Heapify method with built in SiftDown. Recursiely structures subarrys
        into a parent/child relationship, building a heap.
        @param arr Array to be sorted into a heap structure
        @param n Size of subarray to be sorted
        @param i Root of subarray to be sorted
    */
    private void heapify(Integer[] arr, int n, int i){

        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(++comparisons > 0 && left < n && arr[left] > arr[largest]){
            largest = left;
        }

        if(++comparisons > 0 && right < n && arr[right] > arr[largest]){
            largest = right;
        }

        if (++comparisons > 0 && largest != i){
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
            update();
        }
    }
}
