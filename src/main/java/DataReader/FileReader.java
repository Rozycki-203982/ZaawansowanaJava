package DataReader;

import jdk.nashorn.internal.runtime.Debug;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used for reading hdf5 files
 */

public class FileReader {

    private static FileReader ourInstance;

    private String fileName;
    private List<List<Double>> rawData;
    private int samplingRate;
    private int channelsNum;

    public static FileReader getInstance(String fileName)
    {
        if(ourInstance == null)
            ourInstance = new FileReader(fileName);

        return ourInstance;
    }

    private FileReader(String fileName) {

        this.fileName = fileName;
        this.rawData = new ArrayList<>();
        this.samplingRate = 0;
        this.channelsNum = 0;
    }

    private void readFile() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        FileInputStream fileStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
        String actualLine;

        while ((actualLine = bufferedReader.readLine()) != null){

            deserializeData(actualLine);
        }

        bufferedReader.close();
    }

    private void deserializeData(String textLine){

        if(textLine.contains("SampleRate"))
            readSamplingRate(textLine);
        else
            extractRawData(textLine);
    }

    private void readSamplingRate(String textLine){

        this.samplingRate = Integer.parseInt(textLine.split("SampleRate: ")[1]);
    }

    private void extractRawData(String textLine){

        int lastIndex = 0, nextIndex;

        if(channelsNum == 0)
            channelsNum = countChannelsAmount(textLine);

        for(int i = 0; i < channelsNum; i++){

            if(rawData.size() < channelsNum) {

                rawData.add(new ArrayList<Double>());
            }

            nextIndex = textLine.indexOf(',', lastIndex);

            if(nextIndex == -1)
                nextIndex = textLine.length() - 1;

            rawData.get(i).add(Double.parseDouble(textLine.substring(lastIndex, nextIndex)));
            lastIndex = nextIndex + 1;
        }
    }

    private int countChannelsAmount(String textLine){

        int lastIndex = 0;
        int occupancies = 1;

        while(lastIndex != -1){

            lastIndex = textLine.indexOf(',', lastIndex);

            if(lastIndex != -1){
                occupancies++;
                lastIndex++;
            }
        }

        return occupancies;
    }

    public void readData(){

        try {
            readFile();
        } catch (IOException e) {

            System.out.println("Blad odczytu pliku, FileReader.java : 109");
        }
    }

    public int getSamplingRate() {

        return samplingRate;
    }
}
