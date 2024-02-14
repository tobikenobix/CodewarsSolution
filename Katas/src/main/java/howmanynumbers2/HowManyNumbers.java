package howmanynumbers2;

import java.util.LinkedList;
import java.util.List;

/**
 * Kata of codewars
 */
class HowManyNumbers {
private static final List<Long> resultList = new LinkedList<>();
//targeted sum of digits has to be static since i am checking this from a methode instead of in findAll
private static int targetSum;

private static void calcNumbers(int index, int sum, char[] number){

    // end recursion if you have reached the end of the char array that fulfils the targetSum
    if(index == number.length){
        if(sum == targetSum){
            //add to resultList
            resultList.add(Long.parseLong(new String(number)));
        }
        return;
    }
    // here is where the magic happens
    // determine the start digit, if it's 0 then start with 1, otherwise take the value behind it - the - '0' is there to cast the char value back to int
    int startDig = (index == 0)?1 : number[index -1] -'0';

    // loop from your start digit up to 9
    for(int i = startDig; i<=9; i++){
        if(sum > targetSum) break;
        // set the value at the index to i and cast it to char
        number[index] = (char) (i + '0');

        //do the same for the next index in numbers and increase the sum by what you just added to the number
        calcNumbers(index+1, sum+i, number);
    }


    }

    public static List<Long> findAll(final int sumDigits, final int numDigits) {
        resultList.clear();
        targetSum = sumDigits;
        calcNumbers(0, 0, new char[numDigits]);
        if(resultList.isEmpty()) return resultList;

        List<Long> finalResults = new LinkedList<>();
        finalResults.add((long) resultList.size());
        finalResults.add(resultList.getFirst());
        finalResults.add(resultList.getLast());
        return finalResults;
    }



}
