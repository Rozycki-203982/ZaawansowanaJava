package Application;

import DataReader.FileReader;
import Model.EEGData;

/**
 * Main application class
 */
public class Core {

    final String fileName = "eeg.txt";
    private FileReader fileReader;
    private EEGData eegData;

    public Core(){ }

    public void runCore(){

        readFile();
        loadEEG();
    }

    private void readFile(){

        fileReader = new FileReader(fileName);
        fileReader.loadData();
    }

    private void loadEEG(){

        eegData = new EEGData();
        eegData.setChannelsNum(fileReader.getChannelsNum());
        eegData.setSamplingRate(fileReader.getSamplingRate());

        for(int i = 0; i < eegData.getChannelsNum(); i++){

            eegData.saveData(i, fileReader.getRawData().get(i));
        }

        eegData.read();
    }
}
