package WebService;

import DataReader.FileReader;
import Parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.enterprise.context.ApplicationScoped;

/**
 * Class which includes methods which are executed by webservice
 */
@ApplicationScoped
public class DataCollector {

    private final String fileName = "eeg.txt";
    private FileReader fileReader;
    private int timePeriod = 1;

    public DataCollector() {

        fileReader = new FileReader(fileName);
        fileReader.loadData();
    }

    public Object getRequest(int requestId) {

        if (requestId == 0)
            return getFirstRequest();
        else
            return readFile(requestId);
    }

    public Object getRequest(int requestID, int channelID) {

        if (requestID == 0)
            return getFirstRequest();
        else
            return readFile(requestID, channelID);
    }

    private List<List<Double>> readFile(int requestNumber) {

        List<List<Double>> samples = new ArrayList<>();
        int startSample = requestNumber * fileReader.getSamplingRate() * timePeriod;
        int finalSample = (requestNumber + 1) * fileReader.getSamplingRate() * timePeriod;

        if (finalSample >= fileReader.getRawData().get(0).size()) {

            return samples;
        }

        IntStream.range(0, fileReader.getChannelsNum()).forEach(
                i -> samples.add(fileReader.getRawData().get(i).subList(startSample, finalSample))
        );

        return samples;
    }

    private List<Double> readFile(int requestNumber, int channelID) {

        List<Double> samples = new ArrayList<>();

        int startSample = requestNumber * fileReader.getSamplingRate() * timePeriod;
        int finalSample = (requestNumber + 1) * fileReader.getSamplingRate() * timePeriod;

        if (finalSample >= fileReader.getRawData().get(channelID).size()) {

            return samples;
        }

        samples.addAll(fileReader.getRawData().get(channelID).subList(startSample, finalSample));

        return samples;
    }

    private List<Integer> getFirstRequest() {

        List<Integer> result = new ArrayList<>();
        result.add(fileReader.getSamplingRate());
        result.add(fileReader.getChannelsNum());
        return result;
    }

    public void setTimePeriod(int timePeriod) {

        this.timePeriod = timePeriod;
    }
}
