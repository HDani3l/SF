package hu.ps.sf;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by nkaroly on 2017.08.23..
 */

public class StorageHandler {

    String filename;
    Activity activity;
    public StorageHandler(String filename, Activity activity) {
        this.filename = filename;
        this.activity = activity;
    }

    public String read() {
        try {
            String storedString = "";
            FileInputStream fis = activity.openFileInput(filename);
            //DataInputStream dataIO = new DataInputStream(fis);
            byte[] data = new byte[(int) fis.available()];
            fis.read(data);
            fis.close();

            storedString= new String(data, "UTF-8");
            fis.close();

            return storedString;
        } catch (Exception e) {
            return "";
        }

    }

    public void write(String value) {
        try{
            FileOutputStream fos = activity.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(value.getBytes());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
            return ;
        }
    }


}
