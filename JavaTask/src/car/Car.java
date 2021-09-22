package car;

import java.util.ArrayList;
import java.util.Collections;

public class Car {

        private static String[] array;

        public static void main(String[] args) {
            String code = "";
            int carNumber = 0;
            int mileage = 0;
            final String code1 = "C100";
            final String code2 = "C200";
            final String code3 = "C300";
            final String code4 = "C400";
            array = new String[]{"C100_1-100", "C200_1-120-1200",
                    "C300_1-120-30",
                    "C400_1-80-20", "C100_2-50", "C200_2-40-1000",
                    "C300_2-200-45", "C400_2-10-20", "C100_3-10",
                    "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28",
                    "C100_1-300", "C200_1-100-750", "C300_1-32-15"};

            ArrayList<String> carType1 = new ArrayList<>();

            ArrayList<String> lines = searchFromStart(array, code);
            System.out.println(lines);

//        for (String line : lines) {
//            String[] car = line.split("[_\\W]");
//
//            System.out.println(Arrays.toString(car));
//          String[] carType = car;
//            for (String s : car) {
//                String[] codes = s.split("[_\\W]");
//                System.out.println(Arrays.toString(codes));
//                //carType.addAll(Arrays.asList(codes));
//            }
//        }

        }


        public static void print(ArrayList<String> type) {
            type.forEach(System.out::println);
        }

        public static ArrayList<String> searchFromStart(String[] beginArray, String searchText) {
            ArrayList<String> outputArray = new ArrayList<>();

            for (int i = 0; i < beginArray.length; i++) {
                if (searchText.compareToIgnoreCase(beginArray[i].substring(0,
                        searchText.length())) == 0) {
                    outputArray.add(beginArray[i]);
                }
            }
            Collections.sort(outputArray);
            return outputArray;
        }


}
