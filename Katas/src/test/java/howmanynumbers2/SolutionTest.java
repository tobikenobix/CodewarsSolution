package howmanynumbers2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import java.util.*;


public class SolutionTest {
    @Test
    public void exampleTests() {
        assertEquals(Arrays.asList(8L, 118L, 334L),         HowManyNumbers.findAll(10, 3));
        assertEquals(Arrays.asList(1L, 999L, 999L),         HowManyNumbers.findAll(27, 3));
        assertEquals(new ArrayList<Long>(),                 HowManyNumbers.findAll(84, 4));
        assertEquals(Arrays.asList(123L, 116999L, 566666L), HowManyNumbers.findAll(35, 6));
    }

    private Random rand = new Random();
    private int[][] params = new int[][] {new int[] {100, 20, 21, 2, 7},
            new int[] {8,   40, 21, 7, 5},
            new int[] {1,   60, 6,  12, 2},
            new int[] {1,   60, 6,  14, 2}};
    private Iterator<String> nameTestIter = Arrays.asList("Not too large random tests",
            "    Large random tests    ",
            "     Huge random tests    ").iterator();

    @Test
    public void randomTests() {
        int count = 0;
        for (int[] p: params) {
            int nTests  = p[0],
                    sdLow   = p[1],
                    sdHigh  = p[2],
                    ndDelta = p[3],
                    ndHigh  = p[4];
            if (nameTestIter.hasNext())
                System.out.println( String.format("*\n*\n***********************************\n"+
                        "    %s\n"+
                        "***********************************\n*", nameTestIter.next()));
            for (int x  = 0 ; x < nTests ; x++) {
                int sd = sdLow + rand.nextInt(sdHigh),
                        nd = ndDelta + rand.nextInt(ndHigh);
                List<Long> actual   = HowManyNumbers.findAll(sd, nd),
                        expected = findTheseNumbers(sd, nd);
                System.out.println( String.format("-------\nTest #%d\nTest for sum_digits = %d and amount of digits = %d\nActual: %s\nExpected: %s", count, sd, nd, actual, expected));
                assertEquals(expected, actual);
                count++;
            }
        }
    }

    private static List<Long> findTheseNumbers(final int sd, final int nd) {

        if (sd > nd*9 || sd < nd) return new ArrayList<Long>();

        int d = sd / nd, r = sd % nd;

        int[] arrMax = new int[nd];
        for (int x = 0 ; x < nd ; x++)
            arrMax[nd-1-x] = x < r ? d+1 : d;

        Set<Long> s = new HashSet<Long>(Arrays.asList(getNumFromArr(arrMax)));
        s = generateNumbers(arrMax, s, 0);

        return Arrays.asList((long) s.size(), Collections.min(s), getNumFromArr(arrMax));
    }

    private static Set<Long> generateNumbers(int[] arr, Set<Long> s, int n) {
        for (int i = n ; i < arr.length-1 ; i++) {
            if (arr[i] > 1 && (i == 0 || arr[i] > arr[i-1])) {
                for (int j = i+1 ; j < arr.length ; j++) {
                    if (arr[j] < 9 && (j == arr.length-1 || arr[j] < arr[j+1])) {
                        int[] newArr = Arrays.copyOf(arr, arr.length);
                        newArr[i] -= 1;
                        newArr[j] += 1;
                        long newNum = getNumFromArr(newArr);
                        if (!s.contains(newNum)) {
                            s.add(newNum);
                            s.addAll( generateNumbers(newArr, s, i) );
                        }
                    }
                }
            }
        }
        return s;
    }

    private static long getNumFromArr(final int[] arr) {
        long tot = 0L;
        for (int x = 0 ; x < arr.length ; x++)
            tot += arr[arr.length-1-x] * (long) Math.pow(10.0, (double) x);
        return tot;
    }
}
