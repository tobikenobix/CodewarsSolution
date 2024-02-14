package DetectPangram;

public class PangramChecker {
    public boolean check(String sentence){


        char[] alphabet = {'a','b', 'c','d','e','f','g','h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
        'v', 'w', 'x', 'y', 'z'};
        for (char c : alphabet) {
            if (sentence.toLowerCase().indexOf(c) == -1) return false;
        }

        return true;
    }
}
