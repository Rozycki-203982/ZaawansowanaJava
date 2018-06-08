package Application;

import DataReader.FileReader;
import DataTransfer.Graph;
import DataTransfer.HTTPClient;
import Model.EEGData;
import WaveTransformations.SignalFiltration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Main application class
 */
public class Core {

    private EEGData eegData;
    private SignalFiltration signalFiltration;
    private Graph graph;
    private HTTPClient httpClient;
    private int channelID = 0;

    public Core() {

    }

    public void runCore(){

        httpClient = new HTTPClient();
        initializeEEG();
        graph = new Graph("Pomiary EEG", eegData.getSamplingRate());
        getClearWaves();
    }

    private void initializeEEG() {

        eegData = new EEGData();
        httpClient.setAcquisitionTimePeriod(2);
        List<Integer> headerData = httpClient.getFirstRespond();
        eegData.setSamplingRate(headerData.get(0));
        eegData.setChannelsNum(headerData.get(1));
    }

    private void receiveData() {

        List<List<Double>> data;
        data = httpClient.getData();

        for (int i = 0; i < data.size(); i++) {

            eegData.saveData(i, data.get(i));
        }
    }

    private void getClearWaves() {

        int requestID = 0;
        signalFiltration = new SignalFiltration();
        receiveData();
        List<Double> data = eegData.getChannelData(channelID).subList(0, eegData.getSamplingRate());

        while (data.size() > 0) {

            signalFiltration.generateFourierTransform(data, eegData.getSamplingRate());
            List<Double> filteredAlfaSignal = signalFiltration.alfaWaveFiltration();
            List<Double> filteredBetaSignal = signalFiltration.betaWaveFiltration();
            List<Double> filteredThetaSignal = signalFiltration.thetaWaveFiltration();
            List<Double> filteredDeltaSignal = signalFiltration.deltaWaveFiltration();

            graph.vizualizeData(data, "Czysty sygnal");
            graph.vizualizeData(filteredAlfaSignal, "Fale alfa");
            graph.vizualizeData(filteredBetaSignal, "Fale beta");
            graph.vizualizeData(filteredThetaSignal, "Fale theta");
            graph.vizualizeData(filteredDeltaSignal, "Fale delta");

            receiveData();
            requestID++;
            int firstSampleId = requestID * eegData.getSamplingRate() * httpClient.getAcquisitionTimePeriod();
            int lastSampleId = (requestID + 1) * eegData.getSamplingRate() * httpClient.getAcquisitionTimePeriod();
            data = eegData.getChannelData(channelID).subList(firstSampleId, lastSampleId);

            try {

                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }


}
