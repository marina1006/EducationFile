package codewars;

public class EvenOrOdd {
    public static String even_or_odd(int number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }

    public static void main(String[] args) {
        even_or_odd(5);
    }
}
