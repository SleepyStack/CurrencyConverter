package Backend;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRates {
    public static final Map<String, Double> exchangeRates = new HashMap<>();
    static {
        exchangeRates.put("USD", 1.00);      // United States Dollar
        exchangeRates.put("EUR", 0.93);      // Euro
        exchangeRates.put("JPY", 149.83);    // Japanese Yen
        exchangeRates.put("GBP", 0.82);      // British Pound
        exchangeRates.put("INR", 83.27);     // Indian Rupee
        exchangeRates.put("AUD", 1.56);      // Australian Dollar
        exchangeRates.put("CAD", 1.37);      // Canadian Dollar
    }
}
