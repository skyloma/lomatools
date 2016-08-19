package lomasky.ma.xui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CommonAdapter
 * @Description:通用的适配器
 * @author  WangLi
 * @data:  2015年1月15日 下午3:45:51
 * @version:  V1.0
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	protected List<T> data;
	protected final int mItemLayoutId;

	public CommonAdapter(Context context, List<T> data, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.data = data !=null? data :new ArrayList<T>();
		this.mItemLayoutId = itemLayoutId;
	}
	public CommonAdapter(Context context,  int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.data =  new ArrayList<T>();
		this.mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolderHelper viewHolder = getViewHolder(position, convertView, parent);
		onBind(viewHolder, getItem(position),position);
		return viewHolder.getConvertView();
	}

	/**
	 * @param holder
	 * @param item
	 * @param position
	 */
	public abstract void onBind(ViewHolderHelper holder, T item ,int position);

	private ViewHolderHelper getViewHolder(int position, View convertView, ViewGroup parent) {
		return ViewHolderHelper.get(mContext, convertView, parent, mItemLayoutId, position);
	}

}
