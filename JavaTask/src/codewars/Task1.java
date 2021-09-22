package codewars;

import java.util.Random;

//Преобразование числа в перевернутый массив цифр
//        Учитывая случайное неотрицательное число,
//        вы должны вернуть цифры этого числа в массиве в обратном порядке.
//        Пример:
//        348597 => [7,9,5,8,4,3]
public class Task1 {
    public static void main(String[] args) {
        //int n=getRandomIntegerBetweenRange();
        digitize(5);
    }

    public static int[] digitize(long n) {

        Random random = new Random();
        n = random.nextInt(9);
        int[] numbers = new int[(int) n];
        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(9);
        }
        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print(numbers[i] + " ");
        }
      return numbers;
    }
}


//        1) Можно создать отдельно метод. Пример: sort(array) -
//        2) В нем прописать:  а)
//        Сортировка массива от меньшего к большему через класс Arrays с методом sort
//        б) Делаем цикл for (int i=array.length -1; i>0; i--)
//            - от последнего числа (внешний)
//        в) Делаем еще цикл for(int j=0; j<i; j++)
//            - от первого числа (внутренний)
//        г)  Во внутреннем цикле: выбрасываем из нулевой-(j) ячейки число
//        - если меньше
//        д)  В первую ячейку кидаем число из второй - (j+1)
//        е) Во вторую ячейку -(j+1) кладем выбросившее число то есть (j)
//        3) В main вызываем наш метод с параметром массива - sort(array);
//        4) Вывод на печать через for each - это выглядит так
//        - for (int x : array) { System.out.println(x); }
