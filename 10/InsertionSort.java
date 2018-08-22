package week10;

/**
 *  An insertion sort implementation which is able to be observed through its
 *  Sorter superclass.
 *
 * @author Josh King
 */
public class InsertionSort extends Sorter {

    /**
     *  Create a new InsertionSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public InsertionSort(Integer[] nums) {
        super(nums); // pass nums[] to the superclass Sort
    }

    /**
     * Sort the integers (which are stored in the parent Sorter class). 
     */
    public void sortNums() {
        // int i, j, comparisons, and nums[] are all protected datafields in
        // the superclass Sort so we can use them without declaring them
        comparisons = 0;
        int temp;

        for (i = 1; i < nums.length; i++){

            int key = nums[i];
            int j = i-1;

            while (j >= 0 && nums[j] > key){
                nums[j+1] = nums[j];
                j = j-1;
                comparisons++;
                update();
            }
            nums[j+1] = key;
        }

    }
}
