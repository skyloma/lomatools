package lomasky.ma.xui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @ClassName: SquareRelativeLayout
 * @Description: 正方形的ImageView
 * @author  WangLi
 * @data:  2014年12月5日 上午12:20:41
 * @version:  V1.0
 */
public class SquareImageView extends ImageView {
    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        //宽度和高度一样
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        heightMeasureSpec = widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
