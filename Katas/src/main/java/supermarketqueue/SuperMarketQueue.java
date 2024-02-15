package supermarketqueue;

import java.util.Arrays;

/**
 * It would be possible to shorten this using ArrayList, but this would increase runtime
 */
public class SuperMarketQueue {

    private static void heapify_min(int[] keys, int heapSize, int pos){
        int largestElemPos = pos;
        if(((2*pos)<= heapSize)&&((keys[2*pos])<keys[largestElemPos])){
            largestElemPos = 2*pos;
        }
        if(((2*pos+1)<=heapSize)&&((keys[2*pos+1])<keys[largestElemPos])){
            largestElemPos = 2*pos+1;
        }
        if(largestElemPos != pos){
            int tmp = keys[pos];
            keys[pos] = keys[largestElemPos];
            keys[largestElemPos] = tmp;
            heapify_min(keys, heapSize, largestElemPos);
        }
    }
    private static void buildHeap_min(int[] keys, int heapSize){
        for(int i = heapSize/2; i>=1; i--){
            heapify_min(keys,heapSize,i);
        }

    }



    public static int solveSuperMarketQueue(int[] customers, int n){
        // Idea: queue as heap map with first element being the lowest, add customer always to that
        int[] queue = new int[n+1];
        // edge-case customers is empy
        if(customers.length == 0) return 0;
        // edge-case n > customers
        if(n > customers.length){
            Arrays.parallelSort(customers);
            return customers[customers.length-1];
        }
        // first n customer are start heap
        if (n >= 0) System.arraycopy(customers, 0, queue, 1, n);

        buildHeap_min(queue, n);

        // first elem in heap is always smallest, add to this and then rebuild heap
        for(int i = n; i< customers.length; i++){
            queue[1]+=customers[i];
            heapify_min(queue,n,1);
        }
        return Arrays.stream(queue).max().getAsInt();

    }


    public static void main(String[] args){
        int[] test = {5,6};
        System.out.println(solveSuperMarketQueue(test, 3));
    }

}
