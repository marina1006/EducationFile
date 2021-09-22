package codewars;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
    digitize(5);

    }
        public static int[] digitize(long n) {
            StringBuilder sb = new StringBuilder(Long.toString(n)).reverse();

            int[] arr = new int[10];
            int count = 0;
            for (String s : sb.toString().split("")) {
                int i = Integer.parseInt(s);
                if (arr.length == count) arr = Arrays.copyOf(arr, count * 2);
                arr[count++] = i;
            }
            arr = Arrays.copyOfRange(arr, 0, count);
            return arr;
        }
}
