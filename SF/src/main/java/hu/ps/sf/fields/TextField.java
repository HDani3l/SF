package hu.ps.sf.fields;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.ps.sf.R;
import hu.ps.sf.models.BaseModelData;

public class TextField extends Field {

    View view;
    TextView labelField;
    EditText textField;
    BaseModelData model;
    AppCompatActivity activity;


    public TextField(AppCompatActivity activity, LinearLayout insertPoint, BaseModelData model) {
        this.activity = activity;
        view = LayoutInflater.from(activity).inflate(getViewId(), insertPoint, false);
        this.model = model;
        createLabelField();
        createValueField();
    }

    int getViewId() {
        return R.layout.textfield_layout;
    }

    void createLabelField() {
        labelField = view.findViewById(R.id.labelField);
        labelField.setText(model.get("nev").toString() + ":");
    }

    void createValueField() {
        textField = view.findViewById(R.id.textField);
    }

    public void setFieldLabel(String string) {
        labelField.setText(string);
    }

    public String getValue() {
        return textField.getText().toString();
    }

    public void setValue(String string) {
        textField.setText(string);
    }

    public String getAzon() {
        return model.get("nev").toString();
    }

    public View getView() {
        return view;
    }
}
