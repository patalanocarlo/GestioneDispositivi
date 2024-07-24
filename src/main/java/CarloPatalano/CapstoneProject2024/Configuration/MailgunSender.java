package CarloPatalano.CapstoneProject2024.Configuration;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {

    @Value("${mailgun.apikey}")
    private String apiKey;

    @Value("${mailgun.domainname}")
    private String domainName;

    private final String FROM_EMAIL = "patalanocarlo674@gmail.com";

    public void sendEmail(String recipient, String subject, String text) {
        try {
            String apiUrl = String.format("https://api.mailgun.net/v3/%s/messages", domainName);

            HttpResponse<JsonNode> response = Unirest.post(apiUrl)
                    .basicAuth("api", apiKey)
                    .queryString("from", FROM_EMAIL)
                    .queryString("to", recipient)
                    .queryString("subject", subject)
                    .queryString("text", text)
                    .asJson();

            // Print the response
            System.out.println("Response Status: " + response.getStatus());
            System.out.println("Response Body: " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
