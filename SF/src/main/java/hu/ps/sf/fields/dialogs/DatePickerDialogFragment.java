package hu.ps.sf.fields.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import hu.ps.sf.R;

public class DatePickerDialogFragment extends DialogFragment {

    public static class DatePickerDialogListener {
        protected void onDateSelect(String date) {

        }
    }

    DatePickerDialogListener listener;

    public void setListener(DatePickerDialogListener listener) {
        this.listener = listener;
    }

    Dialog d;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.datepicker_layout, null))

                .setNegativeButton("Mégsem", (dialog, id) -> {

                });
        d = builder.create();
        return d;
    }

    String heading;

    public void setFelirat(String heading) {
        this.heading = heading;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((TextView) d.findViewById(R.id.feliratText)).setText(heading);
        CalendarView dp = d.findViewById(R.id.calendarView);
        dp.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            d.dismiss();
            listener.onDateSelect(createDateText(year, month, dayOfMonth));
        });
    }

    String createDateText(int year, int month, int day) {
        month = month + 1;
        String value = year + "-";
        if (month < 10) {
            value += "0";
        }
        value += month + "-";
        if (day < 10) {
            value += "0";
        }
        value += day;
        return value;
    }
}