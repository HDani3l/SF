package hu.ps.sf;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import hu.ps.sf.models.BaseModelData;


public class PsFirstDisabledSpinnerAdapter extends PsSpinnerAdapter {
	
	public PsFirstDisabledSpinnerAdapter(@NonNull Context context, int resource) {
		super(context, resource);
	}
	
	public PsFirstDisabledSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}
	
	public PsFirstDisabledSpinnerAdapter(@NonNull Context context, int resource, @NonNull BaseModelData[] objects) {
		super(context, resource, objects);
	}
	
	public PsFirstDisabledSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull BaseModelData[] objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	public PsFirstDisabledSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<BaseModelData> objects) {
		super(context, resource, objects);
	}
	
	public PsFirstDisabledSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<BaseModelData> objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	@Override
	public boolean isEnabled(int position) {
		if (position == 0)
			return false;
		else
			return super.isEnabled(position);
	}
	
}
