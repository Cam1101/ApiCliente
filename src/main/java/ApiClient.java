import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiClient {
    public static void main(String[] args){
        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:5092/api/Email";
        String json = """
                {
                  "para": "caloxe8257@fincainc.com",
                  "direccionEnvio": "Calle 150 # 80 - 96",
                  "productos": [
                    {
                      "nombre": "Waffle de Chocolate",
                      "precio": 5.99,
                      "cantidad": 2
                    },
                    {
                      "nombre": "Frutas Mixtas",
                      "precio": 3.50,
                      "cantidad": 1
                    },
                    {
                      "nombre": "Bicileta BMW",
                      "precio": 50.00,
                      "cantidad": 1
                    },
                    {
                      "nombre": "Monitor HP",
                      "precio": 25.00,
                      "cantidad": 1
                    }
                  ],
                  "totalPago": 90.48,
                  "fechaPedido": "2024-05-26T06:41:16.597Z"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }
}
