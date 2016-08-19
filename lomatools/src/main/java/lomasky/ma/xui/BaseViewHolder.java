package lomasky.ma.xui;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Date;

import lomasky.ma.R;


/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用ViewHolder
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> sparseArray;
     

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.sparseArray = new SparseArray<View>();
       
    }
    public <T extends View> T getView(int id) {
        View childView = sparseArray.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            sparseArray.put(id, childView);
        }
        return (T) childView;
    }

    public View getConvertView() {
        return itemView;
    }

    public TextView getTextView(int viewId) {
        return getView(viewId);
    }

    public KV getKV(int viewId) {
        return getView(viewId);
    }


    public RecyclerView getRecyclerView(int viewId) {
        return getView(viewId);
    }

    public CheckBox getCheckBox(int viewId) {
        return getView(viewId);
    }

    public Button getButton(int viewId) {
        return getView(viewId);
    }

    public Img getImg(int viewId) {
        return getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return getView(viewId);
    }




    public BaseViewHolder setText(int viewId, String value) {
        getTextView(viewId).setText(value == null ? "" : value);
        return BaseViewHolder.this;

    }
    public BaseViewHolder setValue(int viewId, Object value) {
        getKV(viewId).setValue(value == null ? "" : value.toString());
        return BaseViewHolder.this;
    }
    public BaseViewHolder setValue(int viewId, Date value) {
        getKV(viewId).setValue(value == null ? "" : TimeUtils.friendly_time(value) + "");
        return BaseViewHolder.this;
    }
    public BaseViewHolder setText(int viewId, int value) {
        getTextView(viewId).setText(value + "");
        return BaseViewHolder.this;
    }

    public BaseViewHolder setText(int viewId, float value) {
        getTextView(viewId).setText(value + "");
        return BaseViewHolder.this;
    }

    public BaseViewHolder setText(int viewId, double value) {
        getTextView(viewId).setText(value + "");
        return BaseViewHolder.this;
    }

    public BaseViewHolder setText(int viewId, long value) {
        getTextView(viewId).setText(value + "");
        return BaseViewHolder.this;
    }

    public BaseViewHolder setText(int viewId, Date value) {
        getTextView(viewId).setText(value == null ? "" : TimeUtils.friendly_time(value) + "");
        return BaseViewHolder.this;
    }

    public BaseViewHolder setChecked(int viewId, boolean value) {
        getCheckBox(viewId).setChecked(value);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setChecked(int viewId, boolean value, String text) {
        getCheckBox(viewId).setChecked(value);
        getCheckBox(viewId).setText(text);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return BaseViewHolder.this;
    }

    public BaseViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return BaseViewHolder.this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param path
     * @return
     */
    public BaseViewHolder setImage(int viewId, String path ) {

        if (!TextUtils.isEmpty(path) ) {
            if (!StringUtil.isHttp(path) ){
                path="file://"+path;
            }
            setUrl(viewId, path);
        }

        return BaseViewHolder.this;

    }
    public BaseViewHolder setImage(int viewId, File file ) {

        if (file != null) {
            Picasso.with(itemView.getContext()).load(file) .config(Bitmap.Config.RGB_565).placeholder(R.mipmap.pic_thumb).error(R.mipmap.pic_thumb).resize(200, 200)   .centerCrop().into(getImageView(viewId));
        }
        return BaseViewHolder.this;

    }

    public BaseViewHolder setUrl(int viewId, String url ) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(itemView.getContext()).load(url) .config(Bitmap.Config.RGB_565).placeholder(R.mipmap.pic_thumb).error(R.mipmap.pic_thumb).resize(200,200)   .centerCrop().into(getImageView(viewId));

        }
        return BaseViewHolder.this;

    }

    public BaseViewHolder setImage(int viewId, int drawableId ) {
        Picasso.with(itemView.getContext()).load(drawableId) .into(getImageView(viewId));
        return BaseViewHolder.this;
    }

    public BaseViewHolder setKey(int viewId, String key) {
        getKV(viewId).setKey(key == null ? "" : key.toString());
        return BaseViewHolder.this;
    }
}