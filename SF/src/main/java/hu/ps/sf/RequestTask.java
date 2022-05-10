package hu.ps.sf;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by nkaroly on 2017.08.24..
 */

public class RequestTask extends AsyncTask<HashMap<String, String>, Void, String> {

    @Override
    protected String doInBackground(HashMap<String, String>... datas) {

        String response = "";
        try {
            URL url = new URL(curl);
            if(curl.startsWith("https://")) {
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                HashMap<String, String> data = datas[0];
                writer.write(Sf.getPostDataString(data));
                writer.flush();
                writer.close();
                os.close();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }
                else {
                    response="ERROR";
                }
            } else {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                HashMap<String, String> data = datas[0];
                writer.write(Sf.getPostDataString(data));
                writer.flush();
                writer.close();
                os.close();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }
                else {
                    response="ERROR";
                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
        return response;
    }
    public AsyncResponseHandler delegate = null;

    public String curl = "";

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("ERROR")) {
            delegate.onError();
        } else {
            delegate.onSuccess(result);
        }
    }

}
