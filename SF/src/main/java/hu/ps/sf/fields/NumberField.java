package hu.ps.sf.fields;


import android.text.InputType;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import hu.ps.sf.models.BaseModelData;

public class NumberField extends TextField {
    public NumberField(AppCompatActivity activity, LinearLayout insertPoint, BaseModelData model) {
        super(activity, insertPoint, model);
    }

    @Override
    public void createValueField() {
        super.createValueField();
        textField.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
}
