package hu.ps.sf.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import hu.ps.sf.Sf;


/**
 * Created by nkaroly on 2017.08.25..
 */

public class BaseModelData implements Serializable {


    HashMap<String, Object> map = new HashMap<String, Object>();
    public Set<String> getKeySet() {
       return map.keySet();
    }

    public void set(String key, Object value) {
        map.put(key,value);
    }

    public Object get(String key) {
        return map.get(key) == null ? null:map.get(key);
    }

    @Override
    public String toString() {

        return map.get("displayField") == null? "":map.get("displayField").toString();
    }



    public static <T extends hu.ps.sf.models.BaseModelData> JSONObject toJSONObject(T model) {
        JSONObject obj = new JSONObject();
        for (String key: model.getKeySet()) {
            try{obj.put(key,model.get(key));} catch (Exception e){}
        }
        return obj;
    }

    public static  <T extends hu.ps.sf.models.BaseModelData> JSONArray toJSONArray(List<T> models) {
        JSONArray array = new JSONArray();
        for(T model: models) {
            array.put(toJSONObject(model));
        }
        return array;
    }

    public static <T extends hu.ps.sf.models.BaseModelData> T fromJSONObject(JSONObject obj, Class<T> tClass) {
       try {
           T m = tClass.newInstance();

           Iterator<String> it = obj.keys();
           while (it.hasNext()) {
               String key = it.next();
               try {
                   m.set(key, obj.get(key) == null || obj.get(key).toString().equals("null") ? null : obj.get(key));
               } catch (Exception e) {
               }
           }
           return m;
       } catch (Exception e){return null;}
    }



    public static <T extends hu.ps.sf.models.BaseModelData> ArrayList< T> fromJSONArray(JSONArray array, Class<T> tClass) {
        ArrayList<T> models = new ArrayList<T>();
        for(int i = 0; i<array.length();i++) {
            try{ models.add(fromJSONObject(array.getJSONObject(i), tClass));} catch (Exception e){}
        }
        return models;
    }


    public static <T extends hu.ps.sf.models.BaseModelData> T clone(T model, T cloneModel) {

        for (String key: model.getKeySet()) {
            cloneModel.set(key,model.get(key));
        }
        return cloneModel;
    }


    public static <T extends hu.ps.sf.models.BaseModelData> boolean find(String key, String value, ArrayList<T> list) {
        for (T m:list) {
            if(m.get(key) != null && m.get(key).toString().trim().equals(value.trim())) {
                return true;
            }
        }
        return false;
    }
    public HashMap<String, String> toPostData(){
            HashMap<String, String> data = new HashMap<>();
        for (String key : map.keySet()) {
            data.put(key, Sf.getValue(map.get(key)));
        }
        return  data;

    }
}
