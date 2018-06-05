package DataTransfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Class responsible for http requests
 */
public class HTTPClient {

    private final String API_URL = "http://localhost:8080/rest/eeg";

    public HTTPClient() {
    }

    private String readRespond(HttpURLConnection con) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String responseLine;
        StringBuffer totalResponse = new StringBuffer();

        while ((responseLine = in.readLine()) != null) {

            totalResponse.append(responseLine);
        }
        in.close();

        return totalResponse.toString();
    }

    private String sendRequest() {

        String response = "";
        try {

            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            response = readRespond(con);
            con.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public int getSamplingRate() {

        return Integer.parseInt(sendRequest());
    }
}
