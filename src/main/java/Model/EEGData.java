package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used for storing all EEG data
 */
public class EEGData {

    private Map<Integer, Channel> channels;
    private int samplingRate;
    private int channelsNum;

    public EEGData() {

        channels = new HashMap<>();
    }

    public void saveData(int channel, List<Double> rawData){

        if( !(channel < channelsNum))
            return;

        channels.putIfAbsent(channel, new Channel());
        channels.get(channel).setRawData(rawData);
    }

    public int getSamplingRate(){

        return samplingRate;
    }

    public void setSamplingRate(int samplingRate){

        this.samplingRate = samplingRate;
    }

    public int getChannelsNum(){

        return channelsNum;
    }

    public void setChannelsNum(int channelsNum){

        this.channelsNum = channelsNum;
    }

    public List<Double> getChannelData(int channel) {

        return channels.get(channel).getRawData();
    }
}
