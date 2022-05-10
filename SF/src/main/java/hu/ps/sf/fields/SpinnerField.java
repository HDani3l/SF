package hu.ps.sf.fields;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import hu.ps.sf.PsFirstDisabledSpinnerAdapter;
import hu.ps.sf.PsSpinnerAdapter;
import hu.ps.sf.R;
import hu.ps.sf.models.BaseModelData;

public class SpinnerField extends Field {
    View view;
    TextView labelField;
    Spinner spinnerField;
    BaseModelData model;
    AppCompatActivity activity;
    PsSpinnerAdapter adapter;

    public SpinnerField(AppCompatActivity activity, LinearLayout insertPoint, BaseModelData model) {

        this.activity = activity;
        view = LayoutInflater.from(activity).inflate(getViewId(), insertPoint, false);
        this.model = model;
        createLabelField();
        createValueField();

    }


    @Override
    int getViewId() {
        return R.layout.spinnerfield_layout;

    }

    @Override
    void createLabelField() {
        labelField = view.findViewById(R.id.labelField);
        labelField.setText(model.get("nev").toString() + ":");
    }

    @Override
    void createValueField() {
        spinnerField = view.findViewById(R.id.spinnerField);
        PsSpinnerAdapter adapter = new PsFirstDisabledSpinnerAdapter(
                activity,
                android.R.layout.simple_spinner_dropdown_item,
                new ArrayList<>()
        );
        spinnerField.setAdapter(adapter);
        loadSpinnerOptions(
                spinnerField
        );
    }

    private void loadSpinnerOptions(
            Spinner spinner
    ) {
        String str = model.get("listItems").toString();
        String[] array = str.substring(1, str.length() - 1).split(",");


        ArrayList<BaseModelData> list = new ArrayList<BaseModelData>();

        BaseModelData item = new BaseModelData();
        item.set("displayField", "Válasszon értéket");
        list.add(0, item);
        for (int i = 0; i < array.length; i++) {
            item = new BaseModelData();
            item.set("displayField", array[i].replace("\"", ""));
            list.add(i + 1, item);
        }
        adapter = (PsSpinnerAdapter) spinner.getAdapter();
        adapter.submitList(list);

    }


    @Override
    public void setFieldLabel(String string) {
        labelField.setText(string);
    }

    @Override
    public String getValue() {
        BaseModelData selected = (BaseModelData) spinnerField.getSelectedItem();
        if (spinnerField.getSelectedItemPosition() > 0) {
            return ((String) selected.get("displayField"));
        } else {
            return "";
        }
    }

    @Override
    public void setValue(String string) {
        BaseModelData bm = new BaseModelData();

        bm.set("displayField", string);
        for (int position = 0; position < adapter.getCount(); position++) {
            BaseModelData item = adapter.getItem(position);
            if (item == null || item.get("displayField") == null)
                continue;

            String currentValue = item.get("displayField").toString();

            if (currentValue.replace("\"", "").equals(string)) {
                spinnerField.setSelection(position);
                break;
            }
        }


    }

    public String getAzon() {
        return model.get("nev").toString();
    }

    @Override
    public View getView() {
        return view;
    }
}
