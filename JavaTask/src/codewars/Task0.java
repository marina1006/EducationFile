package codewars;

import java.util.Scanner;

//Создайте функцию, которая принимает целое число в качестве аргумента
//        и возвращает «Четный» для четных чисел
//        или «Нечетный» для нечетных чисел.
public class Task0 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();

        System.out.println("Число четное или нечетное");
        EvenOrOdd.even_or_odd(a);
    }

}
