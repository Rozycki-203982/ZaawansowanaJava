package Application;

import DataReader.FileReader;
import DataTransfer.Graph;
import Math.MathHelper;
import Model.EEGData;
import WaveTransformations.SignalFiltration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Main application class
 */
public class Core {

    private final String fileName = "eeg.txt";
    private FileReader fileReader;
    private EEGData eegData;
    private SignalFiltration signalFiltration;
    private Graph graph;

    public Core() {

    }

    public void runCore(){

        readFile();
        loadEEG();
        graph = new Graph("Pomiary EEG", eegData.getSamplingRate());
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

        for (int i = 0; i < 10; i++) {

            List<Double> data = eegData.getChannelData(0).subList(i * 256, (i + 1) * 256);

            signalFiltration.generateFourierTransform(data, eegData.getSamplingRate());
            List<Double> filteredSignal = signalFiltration.alfaWaveFiltration();

            graph.vizualizeData(data, "Czysty sygnal");
            graph.vizualizeData(filteredSignal, "Fale alfa");

            try {

                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }


}
