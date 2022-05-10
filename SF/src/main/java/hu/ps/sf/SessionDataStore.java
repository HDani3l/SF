package hu.ps.sf;

import java.util.HashMap;

import hu.ps.sf.models.BaseModelData;
import hu.ps.sf.models.RendszerModelData;


/**
 * Created by nkaroly on 2017.08.31..
 */

public class SessionDataStore {

    static HashMap<String, Object> map = new HashMap<>();

    static protected void set(String key, Object value){map.put(key,value);}
    static protected Object get(String key){return map.get(key);}

    public static String version = "Adatgyujtes-v1.00";

    public static void setStoredUsernameAndPwd(String rendszerazon, String username, String pwd) {
        set(rendszerazon+":username",username);
        set(rendszerazon+":pwd",pwd);
    }

    public static void clearStoredUsernameAndPwd(String rendszerazon) {
        set(rendszerazon+":username",null);
        set(rendszerazon+":pwd",null);
    }

    public static String getStoredUsername(String rendszerazon) {
        try{ return get(rendszerazon+":username").toString();}
        catch (Exception e) {
            return null;
        }
    }
    public static String getStoredPwd(String rendszerazon) {
        try{ return get(rendszerazon+":pwd").toString();}
        catch (Exception e) {
            return null;
        }
    }

    public static void setCurrentRendszer(RendszerModelData rendszer) { set("currentRendszer",rendszer);}

    public static RendszerModelData getCurrentRendszer() {
        try {
            return (RendszerModelData) get("currentRendszer");
        } catch (Exception e) {
            return null;
        }
    }



    public static void clearAllStoredData(){
        map = new HashMap<>();
    }


    public static void setCurrentAdagyujtes(BaseModelData model) {
        set("adatgyujtes",model);
    }
    public  static BaseModelData getCurrentAdatgyujtes(){
        return (BaseModelData) get("adatgyujtes");
    }
    //IKTRATOKONYV GETTER SETTER
    public static void setCurrentIktatokony(String string) {
        set("iktatokonyv",string);
    }

    public static String getCurrentIktatokony() {
        return (String) get("iktatokonyv");
    }

    public static void setPartner(BaseModelData data) {
        set("partner",data);
    }

    public static BaseModelData getPartner() {
        return (BaseModelData) get("partner");
    }

    public static void setCurrentFelhasznalo(String output) {
        set("currentFelhasznalo",output);
    }
    public static String getCurrentFelhasznalo() {
        return (String) get("currentFelhasznalo");
    }

}
