package week11;

/**
 *  A selection sort implementation which is able to be observed through its
 *  Sorter superclass.
 *
 * @author Josh King
 */
public class SelectionSort extends Sorter {

    /**
     *  Create a new SelectionSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public SelectionSort(Integer[] nums) {
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
        int smallest = 0;
        int target = 0;
        int count = 0;

        for(i = 0; i < nums.length; i++){
            smallest = nums[i];
            target = i;
            for(j = i+1; j < nums.length; j++){
                if (nums[j] <= smallest){
                    smallest = nums[j];
                    target = j;
                }
                comparisons++;
            

            }
            temp = nums[i];
            nums[i] = nums[target];
            nums[target] = temp;
            smallest = nums[i];
            update();
        }
    }
}
