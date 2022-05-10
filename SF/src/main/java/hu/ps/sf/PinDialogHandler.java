package hu.ps.sf;

import android.app.Activity;

import java.util.HashMap;

import hu.ps.sf.dialogs.LoginPinDialogFragment;


public class PinDialogHandler  {

    public  static void showPinDialog(final Activity activity, final PinOkListener listener){
        showPinDialog( activity, "Kérem adja meg a munkavállalói kódját",listener);
    }


    private static void showPinDialog(final Activity activity, final String message, final PinOkListener listener){
        Sf.setPin("");
        LoginPinDialogFragment dialog = new LoginPinDialogFragment();
        dialog.setMessage(message);
        dialog.setmListener(new LoginPinDialogFragment.LoginDialogPinFragmentListener(){
            @Override
            protected void onOkClick(String pin) {
                if( pin.equals("")) {
                    showPinDialog(activity,message,listener);
                } else {
                    checkPin(activity, pin,listener);
                }
            }
        });
        dialog.show(activity.getFragmentManager(),"loginDialog2");
    }

    private static void checkPin(final Activity activity, final String pin, final PinOkListener listener) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("pin",pin);
        Sf.sendRendszerRequest(activity, SessionDataStore.getCurrentRendszer(),"checkPin",data,new AsyncResponseHandler(){
            @Override
            protected void onSuccess(String output) {
                super.onSuccess(output);
                if(output.equals("OK")) {
                    Sf.setPin(pin);
                    listener.pinOk(pin);
                } else if(output.equals("MAS")){
                    showPinDialog(activity,"A tevékenység más felhasználóhoz tartozik",listener);
                } else {
                    showPinDialog(activity, "Hibás munkavállalói kód",listener);
                }
            }
        });
    }
}
