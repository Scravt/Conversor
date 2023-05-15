package com.mycompany.conversor;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JComboBox;

public class ConsumoAPI {
    public StringBuilder llamarAPi(String monedaBase, String monedaAConvertir, double cantidad) {
        try {
            String apiKey = "5a49e33c7162a10ed975988e6d0ba727a7c2b1c3";
            String fromCurrency = monedaBase;
            String toCurrency = monedaAConvertir;
            double amount = cantidad;

            String apiUrl = "https://api.getgeoapi.com/v2/currency/convert?" +
                    "api_key=" + apiKey +
                    "&from=" + fromCurrency +
                    "&to=" + toCurrency +
                    "&amount=" + amount +
                    "&format=json";

            URL url = new URL(apiUrl);
            
            
            
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Ocurrió un error: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                informationString.append(scanner.nextLine());
                scanner.close();
                
                String jsonString =informationString.toString();
                
                int startIndex = jsonString.indexOf("\"rate_for_amount\":\"") + "\"rate_for_amount\":\"".length();
                int endIndex = jsonString.indexOf("\"", startIndex);

                String rateForAmount = jsonString.substring(startIndex, endIndex);

                System.out.println("rate_for_amount: " + rateForAmount);
                 System.out.println(informationString);
                return informationString;
            }
        } catch (IOException e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
        return null;
    }
}
