package hu.ps.sf.adapters;

import android.content.Context;
import android.os.Handler;
import android.widget.ArrayAdapter;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.Comparator;
import java.util.List;

import hu.ps.sf.models.BaseModelData;


/**
 * Created by nkaroly on 2017.09.14..
 */

public class PsArrayAdapter<T extends BaseModelData> extends ArrayAdapter<T> {
    public PsArrayAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public PsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public PsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull T[] objects) {
        super(context, resource, objects);
    }

    public PsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public PsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
    }

    public PsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }


    @Override
    public void notifyDataSetChanged() {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                PsArrayAdapter.super.notifyDataSetChanged();
            }
        }, 200);
        super.notifyDataSetChanged();
    }

    public void psSort(){
        sort(new Comparator<T>() {
            @Override
            public int compare(T t, T t1) {
                return t.toString().compareTo(t1.toString());
            }
        });
    }
}
