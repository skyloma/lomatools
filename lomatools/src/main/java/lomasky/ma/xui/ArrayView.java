package lomasky.ma.xui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

/**
 * Created by ma on 2015/7/22.
 */
public class ArrayView extends SuperRecyclerView {

    private LinearLayoutManager mLayoutManager;

    public ArrayView(Context context) {
        super(context);
        init(context);

    }

    public void init(Context context) {
        mLayoutManager = new LinearLayoutManager( context);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(mLayoutManager);
        setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);

    }

    public ArrayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);


    }

    public ArrayView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);


    }


}
