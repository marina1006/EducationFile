package codewars;

public class Task2 {
    public static void main(String[] args) {


    }

    public static String updateLight(String current) {

        switch (current) {
            case "green":
                return "yellow";
            case "yellow":
                return "red";
            default:
                return "green";
        }
    }

}
