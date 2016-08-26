package lomasky.ma.xui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import lomasky.ma.R;

/**
 * TODO: document your custom view class.
 */
public class Spinner extends TextView implements CompoundButton.OnClickListener, AdapterView.OnItemClickListener {


    private String android_namespace = "http://schemas.android.com/apk/res/android";
    private ColorStateList dividerColor;


    public void setArrowDrawable(Context context, int mArrowDrawable) {
        this.mArrowDrawable = ContextCompat.getDrawable(context, mArrowDrawable);
    }

    private Drawable mArrowDrawable;
    private int arrowSize = 15;
    private AccelerateDecelerateInterpolator arrowInterpolator;
    private DividerDrawable dividerDrawable;

    public void setBackground(int background) {
        this.background = background;
        setBackgroundResource(background);
    }

    private int background;

    public int getSelectedItemPosition() {
        return selectIndex;
    }

    public void setSelectedItemPosition(int selectIndex) {
        this.selectIndex = selectIndex;
        if (selectIndex < 0) {
            setText("");
        }
    }

    private int selectIndex = -1;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        popWindow.setOnItemClickListener(onItemClickListener);


    }

    AdapterView.OnItemClickListener onItemClickListener;

    public Spinner(Context context) {
        super(context);
        init(null, 0, context);
    }

    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, context);
    }

    public Spinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle, context);
    }


    public SpinerPopWindow popWindow;

    private void init(AttributeSet attrs, int defStyle, Context context) {

        setPadding(Density.dp2px(context, 16), Density.dp2px(context, 8), 0, Density.dp2px(context, 8));
        popWindow = new SpinerPopWindow(super.getContext());

        popWindow.setAnchorView(this);
        setOnClickListener(this);
        popWindow.setOnItemClickListener(this);





        background = attrs.getAttributeResourceValue(android_namespace, "background", 0);


        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_pressed},
                new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled},
        };
        int[] colors = new int[]{
                ThemeUtil.colorControlNormal(context, 0xFF000000),
                ThemeUtil.colorControlActivated(context, 0xFF000000),
        };

        dividerColor = new ColorStateList(states, colors);
        arrowInterpolator = new AccelerateDecelerateInterpolator();
        mArrowDrawable = new ArrowDrawable(ArrowDrawable.MODE_DOWN, Density.dp2px(context, 8.0f), dividerColor, 200, arrowInterpolator, true);
        mArrowDrawable.setCallback(this);

        dividerDrawable = new DividerDrawable(Density.dp2px(context, 1), dividerColor, 100);
        if (background == 0) {
            if (Build.VERSION.SDK_INT >= 16) {
                setBackground(dividerDrawable);
            } else {
                setBackgroundDrawable(dividerDrawable);
            }

        } else {
            setBackgroundResource(background);

        }


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        mArrowDrawable.setBounds(
                paddingLeft + contentWidth - contentHeight,
                paddingTop,
                paddingLeft + contentWidth,
                paddingTop + contentHeight);
        mArrowDrawable.draw(canvas);
    }

    List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
        popWindow.setList(list);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setText(getList().get(position));
        popWindow.dismiss();
        setSelectedItemPosition(position);
    }


    @Override
    public void onClick(View v) {

        if (!popWindow.isShowing()) {
            popWindow.show();


        }
    }


}