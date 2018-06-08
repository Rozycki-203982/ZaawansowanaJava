package DataTransfer;

import Parser.Parser;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Objects;

/**
 * Class responsible for http requests
 */
public class HTTPClient {

    private final String API_URL = "http://localhost:8080/rest/eeg";

    private int acquisitionTimePeriod = 1;

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

    private String sendGetRequest() {

        String response = "";
        try {

            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            response = readRespond(con);
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private String sendPUTRequest(Integer timeValue) {

        String response = "";

        try {

            URL url = new URL(API_URL + "/" + timeValue);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");

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

    public List<Integer> getFirstRespond() {

        return Parser.parseStringToIntList(sendGetRequest());
    }

    public List<List<Double>> getData() {

        return Parser.parseStringToDoubleListList(sendGetRequest());
    }

    public int getAcquisitionTimePeriod() {

        return acquisitionTimePeriod;
    }

    public String setAcquisitionTimePeriod(int acquisitionTimePeriod) {

        String response = sendPUTRequest(acquisitionTimePeriod);

        if (Objects.equals(response, "Success")) {

            this.acquisitionTimePeriod = acquisitionTimePeriod;
        }

        return response;
    }
}
