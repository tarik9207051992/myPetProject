import java.util.Hashtable;
import java.util.TreeMap;

public class Value implements Part {

    private int value;

    public Value(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int parseRoman(String number, Hashtable<Character, Integer> hashTable) {
        int intNum=0;
        int prev = 0;
        for(int i = number.length()-1; i>=0 ; i--)
        {
            int temp = hashTable.get(number.charAt(i));
            if(temp < prev)
                intNum-=temp;
            else
                intNum+=temp;
            prev = temp;
        }
        return intNum;
    }

    public static String toRoman(int number, TreeMap<Integer, String> treeMap) {
        int l = treeMap.floorKey(number);
        if ( number == l ) {
            return treeMap.get(number);
        }
        return treeMap.get(l) + toRoman(number-l, treeMap);
    }

    public static boolean isRoman(String number) {
        for (char romanChar:
                romanCharList) {
            if (number.contains(Character.toString(romanChar))) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValue(String number) {
        if (isRoman(number)) {
            return true;
        }
        try {
            Integer.valueOf(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static final char[] romanCharList = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

}
