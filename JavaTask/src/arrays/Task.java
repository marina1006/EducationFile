package arrays;
import java.util.Arrays;
//Напишите программу, которая циклически сдвигает элементы данной последовательности
//        вправо (например, если элементы нумеруются, начиная с нуля,
//        то 0-й элемент становится 1-м, 1-й становится 2-м, ...,
//        последний становится 0-м,
//        то есть последовательность {3, 5, 7, 9}
//        превращается в {9, 3, 5, 7}).
public class Task {

        public static void main(String[] args) {
            int[] array = {1, 2, 3, 4, 5, 6};
            System.out.println(Arrays.toString(array));
            shift(array);
            System.out.println(Arrays.toString(array));
            shift2(array);
            System.out.println(Arrays.toString(array));
        }


        public static void shift(int[] arr) {
            int ind = arr.length - 1;
            int tmp = arr[ind];
            System.arraycopy(arr, 0, arr, 1, ind);
            arr[0] = tmp;
        }

        private static void shift2(int[] arr) {
            int ind = arr.length - 1;
            int tmp = arr[ind];
            for (int i = ind; i >0; i--) {
                arr[i]=arr[i-1];
            }
            arr[0] = tmp;
        }
    }

