package lomasky.ma.xui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.io.File;

import lomasky.ma.R;


/**
 * Created by ma on 2015/6/24.
 * 网络图片显示控件
 *
 */
public class Img extends ImageView {
    Context context;
    public Img(Context context) {
        super(context);
        this.context=context;
    }

    public Img(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray a;
        a = context.obtainStyledAttributes(attrs,  R.styleable.networkimg);
        String url = a.getString(R.styleable.networkimg_url);
        if (url != null) {
            Integer id=Integer.valueOf(url);
            if (id!=null){
                setUrl(url);
            }else {
                setUrl(url);
            }


        }


    }

    public Img(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.networkimg);
        String url = a.getString(R.styleable.networkimg_url);
        if (url != null) {
            Integer id=Integer.valueOf(url);
            if (id!=null){
                setImage(id);
            }else {
                setUrl(url);
            }


        }

    }

    public void setImage(  File file ) {

        if (file != null) {
            Picasso.with(context)
                    .load(file)
                    .placeholder(R.mipmap.pic_thumb)
                    .error(R.mipmap.pic_thumb)
                    .config(Bitmap.Config.RGB_565)
                    .resize(200, 200)
                    .centerCrop()

                    .into(this);
        }


    }

    public void setUrl( String url ) {
        if (url != null) {
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.mipmap.pic_thumb)
                    .error(R.mipmap.pic_thumb)
                    .config(Bitmap.Config.RGB_565)
                    .resize(200, 200)
                    .centerCrop()

                    .into(this);

        }


    }

    public void setImage(  int drawableId ) {
        Picasso.with(context)
                .load(drawableId)
                .placeholder(R.mipmap.pic_thumb)
                .error(R.mipmap.pic_thumb)

                .into(this);
    }

}
