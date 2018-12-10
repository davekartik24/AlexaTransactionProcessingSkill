import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import com.amazon.ask.model.Response;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class SandboxHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = sandboxStatus();
        return handlerInput.getResponseBuilder().withSpeech(speechText).build();
    }

    public String sandboxStatus() {

        try {
            URL siteURL = new URL("https://www.testvantivcnp.com/sandbox/status");
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();

            if (connection.getResponseCode() == 200) {
                return "sandbox is up";
            } else {
                return "sandbox is down";
            }
        } catch (Exception e) {
            return "error in connecting please contact world pay";

        }
    }
}
