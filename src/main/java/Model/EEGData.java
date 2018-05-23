package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used for storing all EEG measurement data
 */
public class EEGData {

    private Map<Integer, Channel> channels;

    public EEGData() {

        channels = new HashMap<Integer, Channel>();
    }
}
