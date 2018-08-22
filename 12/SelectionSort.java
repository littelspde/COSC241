package week12;

/**
 *  A selection sort implementation which is able to be observed through
 *  its Sorter superclass.
 *
 * @author Iain Hewson
 */
public class SelectionSort extends Sorter {
    
    /**
     *  Create a new SelectionSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public SelectionSort(Integer[] nums) {
        super(nums);
    }

    /**
     *  Sort the integers (which are stored in the parent Sorter class)
     *  using selection sort.
     */
    public void sortNums() {
        int temp =0;
        int smallest;
        int target;

        for(int p = 0; p< nums.length; p++){
            smallest = nums[p];
            target = p;

            for (int j = p+1; j< nums.length; j++){
                if(nums[j] < smallest){
                    smallest = nums[j];
                    target = j;
                }
            }

            temp = nums[p];
            nums[p] = nums[target];
            nums[target] = temp;
            smallest = nums[p];
            update();
        }
        
    }

}
