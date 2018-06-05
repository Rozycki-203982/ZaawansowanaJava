package Application;

import DataReader.FileReader;
import DataTransfer.Graph;
import DataTransfer.HTTPClient;
import Model.EEGData;
import WaveTransformations.SignalFiltration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Main application class
 */
public class Core {

    //private final String fileName = "eeg.txt";
    //private FileReader fileReader;
    private EEGData eegData;
    private SignalFiltration signalFiltration;
    private Graph graph;
    private HTTPClient httpClient;

    public Core() {

    }

    public void runCore(){

        httpClient = new HTTPClient();
        System.out.println(httpClient.getSamplingRate());
        //readFile();
        loadEEG();
        graph = new Graph("Pomiary EEG", eegData.getSamplingRate());
        getClearAlfaWaves();
    }

    private void loadEEG(){

        eegData = new EEGData();
        eegData.setSamplingRate(httpClient.getSamplingRate());
        //eegData.setChannelsNum(fileReader.getChannelsNum());

        /*for(int i = 0; i < eegData.getChannelsNum(); i++){

            eegData.saveData(i, fileReader.getRawData().get(i));
        }*/
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
