package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used for storing all EEG measurement data
 */
public class EEGData {

    private Map<Integer, Channel> channels;
    private int samplingRate;
    private int channelsNum;

    public EEGData() {

        channels = new HashMap<Integer, Channel>();
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

    public void read(){

        System.out.println(channelsNum);
        System.out.println(samplingRate);
        System.out.println(channels.get(0).getRawData().size());
    }
}
