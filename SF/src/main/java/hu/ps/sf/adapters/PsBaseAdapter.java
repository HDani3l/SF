package hu.ps.sf.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.List;

import hu.ps.sf.Sf;
import hu.ps.sf.models.BaseModelData;


public abstract class PsBaseAdapter extends BaseAdapter {
	protected final List<BaseModelData> list;
	protected ListItemClickListener itemClickListener;
	protected LayoutInflater inflater;
	protected @LayoutRes
    int layout;
	
	public PsBaseAdapter(
			List<BaseModelData> list,
			ListItemClickListener itemClickListener,
			LayoutInflater inflater,
			@LayoutRes int layout
	) {
		this(list, inflater, layout);
		this.itemClickListener = itemClickListener;
	}
	
	public PsBaseAdapter(
			List<BaseModelData> list,
			LayoutInflater inflater,
			@LayoutRes int layout
	) {
		this.list = list != null ? list : new ArrayList<>();
		this.inflater = inflater;
		this.layout = layout;
	}
	
	public void setItemClickListener(ListItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(layout, parent, false);
		
		view.setOnClickListener(v -> {
			if (itemClickListener != null) {
				BaseModelData itemModel = list.get(position);
				itemClickListener.onListItemClick(position, itemModel);
			}
		});
		
		return view;
	}
	
	public List<BaseModelData> getList() {
		return Sf.deepCopyList(list);
	}
	
	public void clear() {
		list.clear();
		notifyDataSetChanged();
	}
	
	public void set(int position, BaseModelData item) {
		if (position >= list.size())
			return;
		
		list.set(position, item);
		notifyDataSetChanged();
	}
	
	public void add(BaseModelData item) {
		list.add(item);
		notifyDataSetChanged();
	}
	
	public void addAll(List<BaseModelData> listToAdd) {
		list.addAll(listToAdd);
		notifyDataSetChanged();
	}
	
	/**
	 * Clears the adapter's current list and submits the new values
	 * @param listToSubmit
	 */
	public void submitList(List<BaseModelData> listToSubmit) {
		list.clear();
		list.addAll(listToSubmit);
		notifyDataSetChanged();
	}
	
	public interface ListItemClickListener {
		void onListItemClick(int position, BaseModelData itemModel);
	}
}
