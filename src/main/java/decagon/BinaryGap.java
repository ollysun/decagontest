package decagon;

import java.util.*;

class BinaryGap {

    public static int solution(int N) {
        String binary = Integer.toBinaryString(N);
        int count = 0;
        int tmpCount = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                if (i > 0 && binary.charAt(i - 1) == '1') {
                    tmpCount++;
                } else {
                    if (tmpCount > 0) tmpCount++;
                }
            } else if (binary.charAt(i) == '1') {
                if (tmpCount > 0 && tmpCount > count) {
                    count = tmpCount;
                }
                tmpCount = 0;
            }
        }
        return count;
    }

    public static int solution2(int N) {
        String value = Integer.toBinaryString(N);
        int counter = 0;
        List<Integer> counters = new ArrayList<>();
        for (int i = 0; i < value.length(); i++) {
            char current = value.charAt(i);
            if (current == '0') {
                counter += 1;
            } else {
                counters.add(counter);
                counter = 0;
            }
        }
        return Collections.max(counters);
    }

    public static Integer findBinaryGap(String binVal) {
        Integer retVal = 0;
        String splitVal[] = binVal.split("1");
        int endVal = splitVal.length;
        if (binVal.endsWith("0")) {
            endVal = endVal - 1;
        }
        for (int incr = 0; incr < endVal; incr++) {
            if (retVal < splitVal[incr].length()) {
                retVal = splitVal[incr].length();
            }
        }
        return retVal;
    }

    // driver program 
    public static void main(String[] args) {
        int n = 1041;
        System.out.println(solution(1041));

        System.out.println(solution2(1041));

        Integer val = 32;
        String vals = val.toBinaryString(val);
        int gapVal = findBinaryGap(vals);
        System.out.println(vals);
    }
}