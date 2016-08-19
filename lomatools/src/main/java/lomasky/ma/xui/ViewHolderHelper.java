package lomasky.ma.xui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Date;

import lomasky.ma.R;

public class ViewHolderHelper {
    private SparseArray<View> sparseArray;
    private View view; 
 
    public static ViewHolderHelper getViewHolder(View view){
        ViewHolderHelper viewHolder = (ViewHolderHelper) view.getTag();
        if (viewHolder == null) { 
            viewHolder = new ViewHolderHelper(view);
            view.setTag(viewHolder); 
        } 
        return viewHolder; 
    }
    public ViewHolderHelper(View view) {
        this.view = view; 
        sparseArray = new SparseArray<View>();
        view.setTag(this);
    } 
    public <T extends View> T getView(int id) { 
        View childView = sparseArray.get(id);
        if (childView == null) { 
            childView = view.findViewById(id); 
            sparseArray.put(id, childView);
        } 
        return (T) childView; 
    } 
 
    public View getConvertView() { 
        return view; 
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


     

    public ViewHolderHelper setText(int viewId, String value) {
        getTextView(viewId).setText(value == null ? "" : value);
        return ViewHolderHelper.this;

    }
    public ViewHolderHelper setValue(int viewId, Object value) {
        getKV(viewId).setValue(value == null ? "" : value.toString());
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setText(int viewId, int value) {
        getTextView(viewId).setText(value + "");
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setText(int viewId, float value) {
        getTextView(viewId).setText(value + "");
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setText(int viewId, double value) {
        getTextView(viewId).setText(value + "");
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setText(int viewId, long value) {
        getTextView(viewId).setText(value + "");
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setText(int viewId, Date value) {
        getTextView(viewId).setText(value == null ? "" : TimeUtils.friendly_time(value) + "");
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setChecked(int viewId, boolean value) {
        getCheckBox(viewId).setChecked(value);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setChecked(int viewId, boolean value, String text) {
        getCheckBox(viewId).setChecked(value);
        getCheckBox(viewId).setText(text);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return ViewHolderHelper.this;
    }

    public ViewHolderHelper setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return ViewHolderHelper.this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param path
     * @return
     */
    public ViewHolderHelper setImage(int viewId, String path ) {

        if (!TextUtils.isEmpty(path)) {
            if (!StringUtil.isHttp(path) ){
                path="file://"+path;
            }
            setUrl(viewId, path);
        }

        return ViewHolderHelper.this;

    }
    public ViewHolderHelper setImage(int viewId, File file ) {

        if (file != null) {
            Picasso.with(view.getContext()).load(file) .config(Bitmap.Config.RGB_565).placeholder(R.mipmap.pic_thumb).error(R.mipmap.pic_thumb).resize(200, 200)   .centerCrop().into(getImageView(viewId));

        }
        return ViewHolderHelper.this;

    }

    public ViewHolderHelper setUrl(int viewId, String url ) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext()).load(url) .config(Bitmap.Config.RGB_565).placeholder(R.mipmap.pic_thumb).error(R.mipmap.pic_thumb).resize(200,200)   .centerCrop().into(getImageView(viewId));


        }
        return ViewHolderHelper.this;

    }

    public ViewHolderHelper setImage(int viewId, int drawableId ) {
        Picasso.with(view.getContext())
                .load(drawableId)
                .into(getImageView(viewId));
        return ViewHolderHelper.this;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolderHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            convertView=  LayoutInflater.from(context).inflate(layoutId, parent, false);
            return new ViewHolderHelper(convertView);
        }
        return (ViewHolderHelper) convertView.getTag();
    }
}