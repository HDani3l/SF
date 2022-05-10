package hu.ps.sf.fields;

import android.view.View;


public abstract class Field {

    abstract int getViewId();

    abstract void createLabelField();

    abstract void createValueField();

    abstract public void setFieldLabel(String string);

    abstract public String getValue();

    abstract public void setValue(String string);

    abstract public View getView();

    abstract public String getAzon();

}
