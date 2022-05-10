package hu.ps.sf;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import hu.ps.sf.models.BaseModelData;


public class PsSpinnerAdapter extends ArrayAdapter<BaseModelData> {
	public static String NOTHING_SELECTED = "nothing_selected";

	public PsSpinnerAdapter(@NonNull Context context, int resource) {
		super(context, resource);
	}
	
	public PsSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}
	
	public PsSpinnerAdapter(@NonNull Context context, int resource, @NonNull BaseModelData[] objects) {
		super(context, resource, objects);
	}
	
	public PsSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull BaseModelData[] objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	public PsSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<BaseModelData> objects) {
		super(context, resource, objects);
	}
	
	public PsSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<BaseModelData> objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	public void submitList(List<BaseModelData> list) {
		clear();
		addAll(list);
		notifyDataSetChanged();
	}
	
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		TextView tv = view.findViewById(android.R.id.text1);
		tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
		tv.setTextSize(18);
		tv.setMaxLines(1);
		tv.setEllipsize(TextUtils.TruncateAt.END);
		
		return view;
	}
	
	@Override
	public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = super.getDropDownView(position, convertView, parent);
		TextView text = view.findViewById(android.R.id.text1);
		
		BaseModelData item = getItem(position);
		if (item != null) {
			text.setText((String) item.get("displayField"));
		}
		
		return view;
	}
}
