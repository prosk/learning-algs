package com.mycompany.mytests.numbers;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Formatter {

    public static DecimalFormat f;
    private static Locale locale         = new Locale("en", "EN");
    private static DecimalFormatSymbols symbols        = new DecimalFormatSymbols(locale);

    public static Map<String, String> cnrcyMap         = new HashMap<>();

    static {
        symbols.setGroupingSeparator('\u00A0');
        symbols.setDecimalSeparator(',');
        f = new DecimalFormat("#,##0.0#####", symbols); // new DecimalFormat("#,##0.00#", symbols);

        cnrcyMap.put("RUB", "₽");
        cnrcyMap.put("RUR", "₽");
        cnrcyMap.put("USD", "$");
        cnrcyMap.put("GBP", "£");
        cnrcyMap.put("EUR", "€");
        cnrcyMap.put("CHF", "₣");
        cnrcyMap.put("%", "%");
    }

    // repeat from Guava
    public static String repeat(String string, int count) {

        if (count <= 1) {
            return (count == 0) ? "" : string;
        }

        // IF YOU MODIFY THE CODE HERE, you must update StringsRepeatBenchmark
        final int len = string.length();
        final long longSize = (long) len * (long) count;
        final int size = (int) longSize;
        if (size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }

        final char[] array = new char[size];
        string.getChars(0, len, array, 0);
        int n;
        for (n = len; n < size - n; n <<= 1) {
            System.arraycopy(array, 0, array, n, n);
        }
        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);
    }

    public static DecimalFormat formatter(int scale) {
        return new DecimalFormat("#,##0" + (scale > 0 ? "." : "") + repeat("0", scale), symbols);
    }

    public static String formatNoStyle(Double _v, String crncy, Integer scala) {
        Double v = ifNull(_v, 0.0);
        String[] split = formatter(scala).format(v).split("\\.");
        return split.length > 1
                ? split[0] + "." + split[1] + " " + cnrcyMap.getOrDefault(crncy, crncy)
                : split[0] + " " + cnrcyMap.getOrDefault(crncy, crncy);
    }

    public static String formatNoStyle(Double _v) {
        Double v = ifNull(_v, 0.0);
        String[] split = f.format(v).split("\\.");
        return split.length > 1
                ? split[0] + "." + split[1]
                : split[0];
    }

    public static <T> T ifNull(T val, T defaultVal) {
        return val != null ? val : defaultVal;
    }

    public static void main(String[] args) {
        String uuid = "8678006d-a010-4d6b-9383-358ae4dc3441";

        /*System.out.println(formatNoStyle(null));
        System.out.println(formatNoStyle(2.0));
        System.out.println(formatNoStyle(2.45));
        System.out.println(formatNoStyle(0.0091234));
        System.out.println(formatNoStyle(3.84500000));
        System.out.println(formatNoStyle(323.87003400));

        System.out.println(formatNoStyle(null, "USD", 8));
        System.out.println(formatNoStyle(2.0, "USD", 8));
        System.out.println(formatNoStyle(2.45, "USD", 8));
        System.out.println(formatNoStyle(0.0091234, "USD", 8));
        System.out.println(formatNoStyle(3.84500000, "USD", 8));
        System.out.println(formatNoStyle(323.87003400, "USD", 8));*/

        System.out.println(getFormattedAmount(null, "USD", 8));
        System.out.println(getFormattedAmount(2.0, "USD", 0));
        System.out.println(getFormattedAmount(2.45, "USD", 0));
        System.out.println(getFormattedAmount(0.0091234, "USD", 8));
        System.out.println(getFormattedAmount(3.84500000, "USD", 8));
        System.out.println(getFormattedAmount(323.87003400, "USD", 8));
    }

    private static String getFormattedAmount(Double value, String currency, int fractionDigits) {
        if (value == null) {
            return "";
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();

        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(' ');

        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(symbols);
        df.setMinimumFractionDigits(Math.min(fractionDigits, 2));
        df.setMaximumFractionDigits(fractionDigits);
        df.setRoundingMode(RoundingMode.DOWN);

        String amount = df.format(value);

        if (currency != null) {
            amount += " " + currency;
        }

        return amount;
    }
}
