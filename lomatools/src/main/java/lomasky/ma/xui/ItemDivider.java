package lomasky.ma.xui;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import lomasky.ma.R;

public class ItemDivider extends RecyclerView.ItemDecoration {

    /*
     * RecyclerView的布局方向，默认先赋值
     * 为纵向布局
     * RecyclerView 布局可横向，也可纵向
     * 横向和纵向对应的分割想画法不一样
     * */
    private int mOrientation = LinearLayoutManager.VERTICAL ;

    /**
     * item之间分割线的size，默认为1
     */
    private int mItemSize = 1 ;

    /**
     * 绘制item分割线的画笔，和设置其属性
     * 来绘制个性分割线
     */
    private Paint mPaint ;



    /**
     * 构造方法
     * @param context
     *
     */
    public ItemDivider(Context context) {



        mItemSize =Density.dip2px(context,0.5f);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;//创建一个画笔
        mPaint.setStyle(Paint.Style.STROKE);//设置非填充
        mPaint.setStrokeWidth(Density.dip2px(context,0.5f));//笔宽0.5dp
        mPaint.setColor(ContextCompat.getColor(context,R.color.dividerColor));//设置颜色
        mPaint.setAntiAlias(true);//锯齿不显示
    }
    /**
     * 构造方法
     * @param context
     *
     */
    public ItemDivider(Context context,int color) {


        mItemSize =Density.dip2px(context,0.5f);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;//创建一个画笔
        mPaint.setStyle(Paint.Style.STROKE);//设置非填充
        mPaint.setStrokeWidth(Density.dip2px(context,0.5f));//笔宽0.5dp
        mPaint.setColor(ContextCompat.getColor(context,color));//设置颜色
        mPaint.setAntiAlias(true);//锯齿不显示

    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawVertical(c,parent) ;
        }else {
            drawHorizontal(c,parent) ;
        }
    }

    /**
     * 绘制纵向 item 分割线
     * @param canvas
     * @param parent
     */
    private void drawVertical(Canvas canvas,RecyclerView parent){
        final int left = parent.getPaddingLeft() ;
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight() ;
        final int childSize = parent.getChildCount() ;
        for(int i = 0 ; i < childSize ; i ++){
            final View child = parent.getChildAt( i ) ;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final float top = child.getBottom() + layoutParams.bottomMargin ;
            final float bottom = top + mItemSize ;
            canvas.drawLine(left, top, right, bottom, mPaint);

//            }else {
//
//                Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
//                mPaint1.setColor(Color.TRANSPARENT);
//
//         /*设置填充*/
//                mPaint1.setStyle(Paint.Style.FILL);
//                mPaint1.setStrokeWidth(0.5f);
//                canvas.drawRect(left,top,right,bottom,mPaint1);
//            }
        }
    }

    /**
     * 绘制横向 item 分割线
     * @param canvas
     * @param parent
     */
    private void drawHorizontal(Canvas canvas,RecyclerView parent){
        final int top = parent.getPaddingTop() ;
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom() ;
        final int childSize = parent.getChildCount() ;
        for(int i = 0 ; i < childSize ; i ++){
            final View child = parent.getChildAt( i ) ;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final float left = child.getRight() + layoutParams.rightMargin ;
            final float right = left + mItemSize ;
            canvas.drawLine(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 设置item分割线的size
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0,0,0,mItemSize);
        }else {
            outRect.set(0,0,mItemSize,0);
        }
    }
}