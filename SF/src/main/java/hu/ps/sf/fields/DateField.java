package hu.ps.sf.fields;

import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


import hu.ps.sf.R;
import hu.ps.sf.fields.dialogs.DatePickerDialogFragment;
import hu.ps.sf.models.BaseModelData;

public class DateField extends TextField {
    ImageView imageField;

    public DateField(AppCompatActivity activity, LinearLayout insertPoint, BaseModelData model) {
        super(activity, insertPoint, model);
        createDateImage();
        textField.setClickable(false);
        textField.setFocusable(false);

    }

    private void createDateImage() {
        imageField = view.findViewById(R.id.imageField);
        imageField.setOnClickListener(v -> showDatumValaszto());
    }

    private void showDatumValaszto() {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();
        dialog.setFelirat("Dátum");
        dialog.setListener(new DatePickerDialogFragment.DatePickerDialogListener() {
            @Override
            protected void onDateSelect(String date) {


                textField.setText(date);
            }
        });

        dialog.show(activity.getSupportFragmentManager(), "datepickerDialog");

    }

    @Override
    int getViewId() {
        return R.layout.datefield_layout;
    }
}
