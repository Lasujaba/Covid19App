package pt.ipp.isep.g58;

import org.json.JSONObject;

import java.io.*;

public class Config {
    /**
     * Reads int from the config file and returns value
     * @return
     */
    public static int getInt(String key) {

        File file = new File("configuration.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");

            JSONObject obj = new JSONObject(str);

            int period = obj.getInt(key);

            return period;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
