package hu.ps.sf;

import android.app.Activity;
import android.util.Log;

import hu.ps.sf.dialogs.WaitingDialog;


/**
 * Created by nkaroly on 2017.08.24..
 */

public class AsyncResponseHandler {

        WaitingDialog waiting;
        Activity activity;
        protected boolean stop = false;

        public void setActivity(Activity activity) {
                this.activity = activity;
        }

        protected void onSuccess(String output){
               // System.out.println(output);
                if(output.startsWith("version") && activity != null) {

                        stop = true;
                }
                Log.i("RESPONSE", output);
                try{waiting.dismiss();} catch (Exception ignored){}
        }
        protected void onError(){

                Log.i("RESPONSE", "ONERROR");try{waiting.dismiss();} catch (Exception ignored){}
        }

        public void setDialog(WaitingDialog waiting) {
                this.waiting = waiting;
        }
}
