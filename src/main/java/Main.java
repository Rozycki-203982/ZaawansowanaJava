import DataReader.FileReader;

/**
 * @author Wojciech Różycki
 *         Created on 06/2018.
 */
public class Main {

    public static void main(String[] args) {

        FileReader fileReader = FileReader.getInstance("eeg.txt");
        fileReader.readData();
    }
}
