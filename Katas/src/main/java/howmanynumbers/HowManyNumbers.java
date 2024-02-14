package howmanynumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

/**
 * Kata of codewars
 */
class HowManyNumbers {

    public static Long sumOfDigits(Long number, int numDigits){
        long sum = 0L;
        for(int i =0; i<numDigits; i++){
            sum += number % 10;
            number/=10;
        }

        return sum;
    }

    private static boolean isNonDescending(long number) {
        long prevDigit = 9; // Start with a digit greater than 9
        while (number > 0) {
            long digit = number % 10;
            if (digit > prevDigit) {
                return false;
            }
            prevDigit = digit;
            number /= 10;
        }
        return true;
    }

    /**
     * Function to generate all numbers of numDigits digits with a special sum of the digits
     * @param sumDigits the requested sum of digits
     * @param numDigits the number of digits
     * @return a List with the total number of values, the lowest value, the highest value
     */
    public static List<Long> findAll(final int sumDigits, final int numDigits) {

        long maxNumber = (long) (Math.pow(10, numDigits));
        long startNumber = (long) (Math.pow(10, numDigits -1));
        List<Long> resultList = new ArrayList<>();
        LongStream stream = LongStream.range(startNumber, maxNumber);
        long[] result = stream.filter(x -> sumOfDigits(x, numDigits) == sumDigits).filter(HowManyNumbers::isNonDescending).parallel()
                .toArray();

        if(result.length != 0) {
            resultList.add((long) result.length);
            resultList.add(result[0]);
            resultList.add(result[result.length - 1]);
        }



        return resultList;
    }

    public static void main(String[] args){
        System.out.println(findAll(20, 10));
    }

}
