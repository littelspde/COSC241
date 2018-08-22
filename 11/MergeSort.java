package week11;

/**
 *  A merge sort implementation which is able to be observed through its
 *  Sorter superclass.
 *
 * @author Josh King
 */
public class MergeSort extends Sorter {

    /**
     *  Create a new MergeSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public MergeSort(Integer[] nums) {
        super(nums); // pass nums[] to the superclass Sort
    }

    /**
     * Sort the integers (which are stored in the parent Sorter class). 
     */
    public void sortNums() {
        // int i, j, comparisons, and nums[] are all protected datafields in
        // the superclass Sort so we can use them without declaring them
        comparisons = 0;
        mergeSort(0, nums.length - 1);
    }

    /** Recursive method to split array into subarrays until they are sorted
        and then merge them together.
        @param left The starting index of subarray to be sorted
        @param right The ending index of subarray to be sorted
    */
    private void mergeSort(int left, int right){

        int mid = 0;
        if (++comparisons > 0 && left < right){
            mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid + 1, right);
        }
    }

    /** Merges two adjacent sorted subarrays.
        @param left The starting index of the section of subarray to merge
        @param mid The mid point between two subarrays to be merged
        @param right The ending index of the subarrays to be merged
    */
    private void merge(int left, int mid, int right){

        Integer[] temp = new Integer[nums.length];
        int count = 0;

        for(Integer number : nums){
            temp[count] = new Integer(number);
            count++;
        }

        int i = left;
        int j = left;
        int k = mid;

        while (i < mid && k <= right){
            if (++comparisons > 0 && temp[i] < temp[k]){
                nums[j++] = temp [i++];
            } else{
                nums[j++] = temp[k++];
            }
            update();
        }
        while (i < mid){
            nums[j++] = temp [i++];
            update();
        }
        while (j <= right){
            nums[j++] = temp[k++];
            update();
        }
    }
        
}
