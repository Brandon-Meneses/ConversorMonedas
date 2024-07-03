package org.example;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_KEY = "11f110c95f02c00f1bd76bea";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private static ExchangeRateResponse exchangeRateResponse;
    private static List<ConversionRecord> historialConversiones = new ArrayList<>();

    public static void main(String[] args) {
        obtenerTasasDeCambio();

        if (exchangeRateResponse != null) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Bienvenido al Convertidor de Monedas");
                System.out.println("Por favor seleccione una opción:");
                System.out.println("1. Convertir USD a PEN");
                System.out.println("2. Convertir USD a ARS");
                System.out.println("3. Convertir PEN a USD");
                System.out.println("4. Convertir ARS a USD");
                System.out.println("5. Mostrar historial de conversiones");
                System.out.println("6. Salir");

                int opcion = scanner.nextInt();
                if (opcion == 6) {
                    break;
                }

                if (opcion == 5) {
                    mostrarHistorial();
                    continue;
                }

                System.out.print("Ingrese la cantidad: ");
                double cantidad = scanner.nextDouble();

                switch (opcion) {
                    case 1:
                        convertirYMostrar(cantidad, "USD", "PEN");
                        break;
                    case 2:
                        convertirYMostrar(cantidad, "USD", "ARS");
                        break;
                    case 3:
                        convertirYMostrar(cantidad, "PEN", "USD");
                        break;
                    case 4:
                        convertirYMostrar(cantidad, "ARS", "USD");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor intente nuevamente.");
                }
            }
            scanner.close();
        }
    }

    private static void obtenerTasasDeCambio() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());



            // Crear instancia de Gson
            Gson gson = new Gson();
            // Parsear la respuesta JSON a un objeto Java
            exchangeRateResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

            // Mostrar el resultado y la moneda base
            System.out.println("Resultado: " + exchangeRateResponse.getResult());
            System.out.println("Moneda Base: " + exchangeRateResponse.getBase_code());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void convertirYMostrar(double cantidad, String monedaDesde, String monedaHacia) {
        if (exchangeRateResponse == null || exchangeRateResponse.getConversion_rates() == null) {
            System.out.println("Los datos de las tasas de cambio no están disponibles.");
            return;
        }

        Map<String, Double> tasas = exchangeRateResponse.getConversion_rates();
        if (tasas.containsKey(monedaDesde) && tasas.containsKey(monedaHacia)) {
            double tasaDesde = tasas.get(monedaDesde);
            double tasaHacia = tasas.get(monedaHacia);
            double cantidadConvertida = (cantidad / tasaDesde) * tasaHacia;
            ConversionRecord registro = new ConversionRecord(cantidad, monedaDesde, monedaHacia, cantidadConvertida);
            historialConversiones.add(registro);
            System.out.println(registro);
        } else {
            System.out.println("La conversión de moneda no está soportada.");
        }
    }

    private static void mostrarHistorial() {
        if (historialConversiones.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
        } else {
            System.out.println("Historial de Conversiones:");
            for (ConversionRecord registro : historialConversiones) {
                System.out.println(registro);
            }
        }
    }
}
