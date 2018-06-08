package WebService;

import DataReader.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataCollector {

    private final String fileName = "eeg.txt";
    private FileReader fileReader;
    private int requestNumber = 0;
    private int timePeriod = 1;

    public DataCollector() {

        fileReader = new FileReader(fileName);
        fileReader.loadData();
    }

    public Object getRequest() {

        if (requestNumber == 0)
            return getFirstRequest();
        else
            return readFile();
    }

    public Object getRequest(int channelID) {

        if (requestNumber == 0)
            return getFirstRequest();
        else
            return readFile(channelID);
    }

    private List<List<Double>> readFile() {

        List<List<Double>> samples = new ArrayList<>();
        int startSample = requestNumber * fileReader.getSamplingRate() * timePeriod;
        int finalSample = (requestNumber + 1) * fileReader.getSamplingRate() * timePeriod;

        if (finalSample >= fileReader.getRawData().get(0).size()) {

            return samples;
        }

        requestNumber++;
        IntStream.range(0, fileReader.getChannelsNum()).forEach(
                i -> samples.add(fileReader.getRawData().get(i).subList(startSample, finalSample))
        );

        return samples;
    }

    private List<Double> readFile(int channelID) {

        List<Double> samples = new ArrayList<>();

        int startSample = requestNumber * fileReader.getSamplingRate() * timePeriod;
        int finalSample = (requestNumber + 1) * fileReader.getSamplingRate() * timePeriod;

        if (finalSample >= fileReader.getRawData().get(channelID).size()) {

            return samples;
        }

        requestNumber++;
        samples.addAll(fileReader.getRawData().get(channelID).subList(startSample, finalSample));

        return samples;
    }

    private List<Integer> getFirstRequest() {

        List<Integer> result = new ArrayList<>();
        result.add(fileReader.getSamplingRate());
        result.add(fileReader.getChannelsNum());
        requestNumber++;
        return result;
    }

    public void setTimePeriod(int timePeriod) {

        this.timePeriod = timePeriod;
    }
}
