public class SpeedRussia {
    public static void main (String[] args) throws java.lang.Exception {
        System.out.println("Система расчёта штрафов в России");

        boolean isTown = true;  //скорость будет меняться: 60, если true, и 90, если false.
        int countrySpeed = 90; //скорость за пределами населённых пунктов

        int carSpeed = 92;

        int townSpeed = 60;

        int overSpeed = carSpeed - townSpeed;

        if (!isTown) {
            overSpeed = carSpeed - countrySpeed;
            System.out.println("Вы за пределами города");
        }
        calculateSpeed(overSpeed);
    }

    private static void calculateSpeed(int overSpeed)
        {
            int fineFor20to40 = 500;
            int fineFor40to60 = 1000;
            int fineFor60to80 = 2000;
            int fineFor80andMore = 5000;
            if (overSpeed < 20) {
                System.out.println("Скорость не превышена или превышена незначительно");
            } else if (overSpeed >= 20 && overSpeed < 40) {
                System.out.println("Штраф: " + fineFor20to40);
            } else if (overSpeed >= 40 && overSpeed < 60) {
                System.out.println("Штраф: " + fineFor40to60);
            } else if (overSpeed >= 60 && overSpeed < 80) {
                System.out.println("Штраф: " + fineFor60to80);
            } else if (overSpeed >= 80) {
                System.out.println("Штраф: " + fineFor80andMore);
            }
        }
}
