package hu.ps.sf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.ps.sf.dialogs.WaitingDialog;
import hu.ps.sf.models.BaseModelData;


/**
 * Created by nkaroly on 2017.08.24..
 */

public class Sf {


    static String imei = "";

    public static String getIMEI(Activity v) {
        imei = Settings.Secure.getString(v.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return imei;
    }


   /* public static void sendRequest(Activity v) {


        JSONObject obj = new JSONObject();

        RequestQueue queue = Volley.newRequestQueue(v);
        String url ="https://app.ps.hu/droidroot/droid.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       System.out.println("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }*/

    public static void sendRequest(Activity parent, String url, String action, HashMap<String, String> data, AsyncResponseHandler responseHandler) {

        responseHandler.setDialog(new WaitingDialog(parent));
        if (data == null) {
            data = new HashMap<>();
        }
        data.put("action", action);
        try {
            RequestTask task = new RequestTask();
            task.curl = url;
            task.delegate = responseHandler;


            task.execute(data);
        } catch (Exception ignored) {

        }
    }

    static String pin = "";

    public static void setPin(String pin) {
        Sf.pin = pin;
    }

    public static String getPin() {
        return Sf.pin;
    }

    public static void sendRendszerRequest(Activity parent, BaseModelData rendszermodel, String action, HashMap<String, String> data, AsyncResponseHandler responseHandler, boolean waiting) {
        if (waiting) {
            responseHandler.setDialog(new WaitingDialog(parent));
        }
        if (data == null) {
            data = new HashMap<>();
        }
        if (rendszermodel.get("username") != null) {
            data.put("username", rendszermodel.get("username").toString());
        }
        if (rendszermodel.get("pwd") != null) {
            data.put("pwd", rendszermodel.get("pwd").toString());
        }
        data.put("version", SessionDataStore.version);
        data.put("action", action);
        /*if(data.get("pin") == null) {
            data.put("pin",pin);
        }*/
        try {
            RequestTask task = new RequestTask();
            task.curl = rendszermodel.get("url").toString();
            responseHandler.setActivity(parent);
            task.delegate = responseHandler;

            task.execute(data);
        } catch (Exception ignored) {

        }
    }

    public static void sendRendszerRequest(Activity parent, BaseModelData rendszermodel, String action, HashMap<String, String> data, AsyncResponseHandler responseHandler) {
        sendRendszerRequest(parent, rendszermodel, action, data, responseHandler, true);
    }

    public static void sendRendszerRequest(
            Activity activity,
            String action,
            HashMap<String, String> data,
            PsResponseHandler.SuccessHandler successHandler,
            PsResponseHandler.ErrorHandler errorHandler
    ) {
        PsResponseHandler responseHandler = new PsResponseHandler()
                .setSuccessHandler(successHandler)
                .setErrorHandler(errorHandler);

        sendRendszerRequest(
                activity,SessionDataStore.getCurrentRendszer(), action, data, responseHandler, false
        );
    }


    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        params.put("imei", imei);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        //System.out.println(result.toString());

        Log.i("POSTDATA", result.toString());
        return result.toString();
    }

   /* public static void showSnackBar(View v, String msg) {

        Snackbar s = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        View view = s.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        s.show();
    }*/

    public static void showToast(Activity a, String msg) {
        Toast.makeText(a, msg,
                Toast.LENGTH_SHORT).show();
    }

    public static void showAlertDialog(Activity activity, String title, String msg, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        builder.setPositiveButton("OK", listener != null ? listener : new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle(title == null ? "Értesítés" : title);
        builder.setMessage(msg == null ? "" : Html.fromHtml(msg));

        AlertDialog d = builder.create();
        d.getButton(DialogInterface.BUTTON_POSITIVE);
        d.setOnKeyListener((dialogInterface, i, keyEvent) -> true);
        d.show();
    }

    public static void showInfoDialog(Activity activity, String title, String msg, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        builder.setPositiveButton("OK", listener != null ? listener : (DialogInterface.OnClickListener) (dialogInterface, i) -> {
        });
        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(title == null ? "Értesítés" : title);
        builder.setMessage(msg == null ? "" : msg);

        AlertDialog d = builder.create();
        d.getButton(DialogInterface.BUTTON_POSITIVE);
        d.setOnKeyListener((dialogInterface, i, keyEvent) -> true);
        d.show();
    }

    public static void showLinkDialog(Activity activity, String title, String msg, final String url, DialogInterface.OnClickListener listener) {


        AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        builder.setPositiveButton("OK", listener != null ? listener : (DialogInterface.OnClickListener) (dialogInterface, i) -> {

        });
        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(title == null ? "Értesítés" : title);
        builder.setMessage(msg == null ? "" : msg);

        AlertDialog d = builder.create();
        d.getButton(DialogInterface.BUTTON_POSITIVE);
        d.setOnKeyListener((dialogInterface, i, keyEvent) -> true);
        d.show();

    }


    public static AlertDialog.Builder getValasztoDialog(Activity activity, String title, String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(title == null ? "Értesítés" : title);
        builder.setMessage(msg == null ? "" : msg);

        return builder;
    }

    public static boolean validateDecimalField(EditText decimalField) {
        if (decimalField.getText() == null || decimalField.getText().toString().equals("")) {
            decimalField.setError("Kérem adjon meg értéket");
            return false;
        }
        String value = decimalField.getText().toString();

        return true;
    }


    private static MediaPlayer fxPlayer;

    public static void playSound(Context context, int id) {
        if (fxPlayer != null) {
            fxPlayer.stop();
            fxPlayer.release();
        }
        fxPlayer = MediaPlayer.create(context, id);
        if (fxPlayer != null) {
            fxPlayer.start();
        }
    }

    public static String format(Number numberString) {
        return format(numberString.toString());
    }

    public static String format(String numberString) {
        numberString = numberString.replace(".", ",");
        String[] s = numberString.split(",");

        String s1 = s[0];
        String egeszResz = "";
        String substring = s1.substring(s1.length() - 6, s1.length() - 3);
        String substring1 = s1.substring(s1.length() - 3);
        if (s1.length() > 9) { //1000000000 -> 1 000 000 000
            egeszResz = s1.substring(0, s1.length() - 9) + " " + s1.substring(s1.length() - 9, s1.length() - 6) + " " + substring + " " + substring1;
        } else if (s1.length() > 6) { //1 000 000
            egeszResz = s1.substring(0, s1.length() - 6) + " " + substring + " " + substring1;
        } else if (s1.length() > 3) { //1 000
            System.out.println("ITT");
            egeszResz = s1.substring(0, s1.length() - 3) + " " + substring1;
        } else {
            egeszResz = s1;
        }

        String s2 = "";
        try {
            s2 = s[1];
            while (s2.startsWith("0", s2.length() - 1)) {
                s2 = s2.substring(0, s2.length() - 1);
            }
        } catch (Exception ignored) {
        }

        if (s2.equals("")) {
            return egeszResz;
        } else {
            return egeszResz + "," + s2;
        }

    }

    public static Double round(String numberString, int prec) {
        try {
            double number = Double.parseDouble(numberString);
            return round(number, prec);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer round(String numberString) {
        try {
            Double number = Double.parseDouble(numberString);
            return round(number);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer round(Double number) {
        return number.intValue();
    }

    public static Double round(double number, int prec) {
        if (number < 0) {
            return -1 * round(-number, prec);
        }
        int szorzo = 1;
        for (int i = 0; i < prec; i++) {
            szorzo *= 10;
        }

        number = (number * szorzo) + 0.0000001; //Idióta Java néhány x.005 -re végződőre itt number=x.4999999999
        ////System.out.println("1-lépés:" + number );
        long tmp = Math.round(number);
        ////System.out.println("tmp:" + tmp );
        double ret = (double) tmp / szorzo;
        ////System.out.println("Ret:" + ret );
        ////System.out.println("------------");
        return ret;
    }

    public static BaseModelData deepCopy(BaseModelData toCopy, Gson gson) {
        BaseModelData copy = new BaseModelData();

        for (String key : toCopy.getKeySet()) {
            Object value = toCopy.get(key);

            if (value == null) continue;

            // JSONArray és JSONObject-ek kezelése, mert a GSON elrontja őket
            if (value instanceof JSONArray) {
                try {
                    JSONArray childCopy = new JSONArray(value.toString());
                    copy.set(key, childCopy);
                } catch (JSONException e) {
                    e.printStackTrace();
                    copy.set(key, "JSONArray másolási hiba");
                }
            } else if (value instanceof JSONObject) {
                try {
                    JSONObject childCopy = new JSONObject(value.toString());
                    copy.set(key, childCopy);
                } catch (JSONException e) {
                    e.printStackTrace();
                    copy.set(key, "JSONObject másolási hiba");
                }
            }
            // BaseModelData rekurzív másolása
            else if (value instanceof BaseModelData) {
                BaseModelData childCopy = deepCopy((BaseModelData) value, gson);
                copy.set(key, childCopy);
            }
            // Sima GSON copy
            else {
                Object childCopy = gson.fromJson(gson.toJson(value), value.getClass());
                copy.set(key, childCopy);
            }
        }

        return copy;
    }

    public static BaseModelData deepCopy(BaseModelData toCopy) {
        Gson gson = new Gson();
        return deepCopy(toCopy, gson);
    }


    public static List<BaseModelData> deepCopyList(List<BaseModelData> toCopy) {
        List<BaseModelData> copies = new ArrayList<>();
        Gson gson = new Gson();

        for (BaseModelData model : toCopy) {
            copies.add(deepCopy(model, gson));
        }

        return copies;
    }

    public static String getValue(Object text) {
        try {
            return text.toString();
        } catch (NullPointerException e) {
            return "";
        }
    }
}
