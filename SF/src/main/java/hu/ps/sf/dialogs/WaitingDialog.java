package hu.ps.sf.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;

import hu.ps.sf.R;

/**
 * Created by nkaroly on 2017.08.25..
 */

public class WaitingDialog  {

    Dialog d;
    public WaitingDialog(Activity activity) {
        d = new Dialog(activity);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        d.setCancelable(false);
        d.setContentView(R.layout.progress_layout);

        d.show();
    }

    public void dismiss() {
        d.dismiss();
    }

}
