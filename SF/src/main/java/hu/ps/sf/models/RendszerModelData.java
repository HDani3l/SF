package hu.ps.sf.models;


import hu.ps.sf.SessionDataStore;

/**
 * Created by nkaroly on 2017.08.28..
 */

public class RendszerModelData extends BaseModelData {


    @Override
    public String toString() {
        String pwd = SessionDataStore.getStoredPwd(get("azonosito").toString());


        return get("nev") == null ? "-" : (get("nev").toString() + (pwd == null ? "" : " (Bejelentkezve)"));
    }

    public void setInitData(String output) {
        /* INICIÁLANDÓ RENDSZER SZINTŰ VÁLTOZÓK FELDOLGOZÁSA
         */
        SessionDataStore.setCurrentFelhasznalo(output);

    }



}
