package lomasky.ma.xui;

import android.content.Context;

import java.util.List;

/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用数据绑定
 */
public abstract     class RecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T, BaseViewHolder>  {

    /**
     * Create a RecyclerViewAdapter.
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public RecyclerViewAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as RecyclerViewAdapter#RecyclerViewAdapter(Context,int) but with
     * some initialization data.
     * @param context     The context.   cvu
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public RecyclerViewAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }


}