import java.util.List;

public class Converter {

        public static int romanToArabic(String input) {
            String romanNumeral = input.toUpperCase();
            int result = 0;

            List romanNumerals = Numbers.getReverseSortedValues();

            int i = 0;

            while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
                Numbers symbol = (Numbers) romanNumerals.get(i);
                if (romanNumeral.startsWith(symbol.name())) {
                    result += symbol.getValue();
                    romanNumeral = romanNumeral.substring(symbol.name().length());
                } else {
                    i++;
                }
            }

            if (romanNumeral.length() > 0) {
                throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
            }

            return result;
        }
}
