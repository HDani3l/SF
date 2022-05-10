package hu.ps.sf;

import android.app.Activity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import hu.ps.sf.models.RendszerModelData;


/**
 * Created by nkaroly on 2017.08.25..
 */

public class RendszerListaHandler {
    Activity activity;
    StorageHandler handler;

    public RendszerListaHandler(Activity activity) {
        this.activity = activity;
        handler = new StorageHandler("SZRendszerlista",activity);
    }


    public ArrayList<RendszerModelData> getRendszerLista() {
        String value = handler.read();
        JSONArray jsonArray = new JSONArray();
        try{
            jsonArray = new JSONArray(value);
        } catch (Exception ignored) {

        }
        return (ArrayList<RendszerModelData>)RendszerModelData.fromJSONArray(jsonArray, (new RendszerModelData()).getClass());
    }

    public void storeRendszerLista(List<RendszerModelData> models) {
        handler.write(RendszerModelData.toJSONArray(models).toString());
    }
}
