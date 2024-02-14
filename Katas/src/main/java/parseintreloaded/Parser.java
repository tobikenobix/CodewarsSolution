package parseintreloaded;



public class Parser {
    private static final String[] words = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety",
            "hundred", "thousand", "million"
    };
    private static final int[] values = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 30, 40, 50, 60, 70, 80, 90,
            100, 1000, 1000000
    };

    public static int parseInt(String numStr) {
        numStr = numStr.replaceAll("-", " ").replaceAll(" and ", " ");
        String[] parts = numStr.split(" ");
        int result = 0;
        int temp = 0;
        for (String part : parts) {
            for (int i = 0; i < words.length; i++) {
                if (part.equals(words[i])) {
                    if (values[i] < 100) {
                        temp += values[i];
                    } else {
                        temp *= values[i];
                    }
                    break;
                }
            }
            if (temp >= 1000) {
                result += temp;
                temp = 0;
            }
        }
        return result + temp;
    }

    public static void main(String[] args){
        System.out.println(parseInt("one-thousand and one")); // Outputs: 1001
    }
}
