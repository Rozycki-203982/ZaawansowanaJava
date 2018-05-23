package Application;

import DataReader.FileReader;
import Model.EEGData;
import WaveTransformations.SignalFiltration;

import java.util.List;

/**
 * Main application class
 */
public class Core {

    private final String fileName = "eeg.txt";
    private FileReader fileReader;
    private EEGData eegData;
    private SignalFiltration signalFiltration;

    public Core(){ }

    public void runCore(){

        readFile();
        loadEEG();
        getClearAlfaWaves();
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
    }

    private void getClearAlfaWaves() {

        signalFiltration = new SignalFiltration();
        List<Double> data = eegData.getChannelData(0);
        signalFiltration.generateFourierTransform(data.subList(0, 256 * 4), eegData.getSamplingRate());
        Double[] filteredSignal = signalFiltration.alfaWaveFiltration();
        System.out.println(filteredSignal.length);
    }
}
