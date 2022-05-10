package hu.ps.sf.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import hu.ps.sf.R;


/**
 * Created by nkaroly on 2017.08.25..
 */

public class AddRendszerDialogFragment extends DialogFragment {


    public static class AddRendszerDialogListener {
        protected void onOkClick(String value) {

        }
    }

    AddRendszerDialogListener mListener;

    public void setmListener(AddRendszerDialogListener mListener) {
        this.mListener = mListener;
    }
    Dialog d;
    EditText rendszerazon;
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.addrendszer_layout, null))
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onOkClick(rendszerazon.getText() == null ?"":rendszerazon.getText().toString().trim() );


                    }
                })
                .setNegativeButton("Mégsem", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        hu.ps.sf.dialogs.AddRendszerDialogFragment.this.getDialog().cancel();
                    }
                });
        d = builder.create();





        return d;
    }

    @Override
    public void onStart() {
        super.onStart();
        (new Handler()).postDelayed(new Runnable() {

            public void run() {

                rendszerazon = (EditText) d.findViewById(R.id.rendszerazon);
                rendszerazon.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                rendszerazon.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));

                rendszerazon.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if(keyEvent.getAction() == KeyEvent.ACTION_UP && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            d.dismiss();
                            mListener.onOkClick(rendszerazon.getText() == null ?"":rendszerazon.getText().toString().trim() );
                        }
                        return false;
                    }
                });
            }
        }, 400);
    }
}
