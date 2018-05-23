package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for storing single channel measurements
 */
public class Channel {

    private List<Double> rawData;

    public Channel() {

        rawData = new ArrayList<>();
    }

    public List<Double> getRawData(){

        return rawData;
    }

    public void setRawData(List<Double> rawData){

        this.rawData.addAll(rawData);
    }
}
