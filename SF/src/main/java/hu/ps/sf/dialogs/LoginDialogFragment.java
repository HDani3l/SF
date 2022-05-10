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

import hu.ps.sf.*;
import hu.ps.sf.R;

/**
 * Created by nkaroly on 2017.08.25..
 */

public class LoginDialogFragment extends DialogFragment {


    public static class LoginDialogFragmentListener {
        protected void onOkClick(String username, String pwd) {

        }
    }
    String message;

    public void setMessage( String message) {

        this.message = message;
    }

    LoginDialogFragmentListener mListener;




    public void setmListener(LoginDialogFragmentListener mListener) {
        this.mListener = mListener;
    }
    Dialog d;
    EditText username;
    EditText pwd;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        setCancelable(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.login_layout, null))
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                                mListener.onOkClick(username.getText() == null ?"":username.getText().toString().trim(), pwd.getText() == null ?"":pwd.getText().toString());

                    }
                })
                .setNegativeButton("Mégsem", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                     LoginDialogFragment.this.getDialog().cancel();
                    }
                })
                .setCancelable(false);

        d = builder.create();
        d.setCanceledOnTouchOutside(false);
        d.setCancelable(false);
        return d;
    }


    @Override
    public void onStart() {
        super.onStart();
        (new Handler()).postDelayed(new Runnable() {

            public void run() {

                username = (EditText) d.findViewById(R.id.username);
                pwd = (EditText) d.findViewById(R.id.pwd);
                username.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                username.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
                pwd.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if(keyEvent.getAction() == KeyEvent.ACTION_UP && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            d.dismiss();
                            mListener.onOkClick(username.getText() == null ?"":username.getText().toString().trim(), pwd.getText() == null ?"":pwd.getText().toString());
                        }
                        return false;
                    }
                });
                if(message != null) {
                   // Sf.showSnackBar(username,message);
                    Sf.showToast(getActivity(),message);
                }

            }
        }, 400);

    }
}

