package lomasky.ma.xui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用数据绑定
 */
public    abstract   class BaseRecyclerViewAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder>{

    protected final Context context;
 
    protected final int layoutResId;
    private int mDuration = 200;
    private Interpolator mInterpolator = new LinearInterpolator();

    public void setData(List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
    }

    public List<T> getData() {

        return data;
    }

    protected List<T> data;


 
    private OnItemClickListener onItemClickListener = null;

    public T lastItem() {
        return  data.get(data.size()-1);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
 

    public BaseRecyclerViewAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }
 

    public BaseRecyclerViewAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
    }


    public void add(T t) {
        insert(t, data.size());
    }

    public void insert(T t , int position) {
        data.add(position, t);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }


    public void addAll(List<T> list) {
        if (list.isEmpty()){
            return;
        }
        int startIndex = data.size();
        data.addAll(startIndex, list);
        notifyItemRangeInserted(startIndex, list.size());
    }
 
    @Override
    public int getItemCount() {
        return data.size();
    }
 
 
    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup,  int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        BaseViewHolder vh = new BaseViewHolder(view);
        return vh;
    }
    private boolean isFirstOnly = true;
    private int mLastPosition = 5;
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        if (onItemClickListener != null) {
            holder.itemView.setClickable(true);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position);
                }
            });

        }

        if (!isFirstOnly || position > mLastPosition) {
            for (Animator anim : getAdapterAnimations(holder.itemView, AdapterAnimationType.SlideInBottom)) {
                anim.setDuration(mDuration).start();
                anim.setInterpolator(mInterpolator);
            }
            mLastPosition = position;
        } else {
            ViewHelper.clear(holder.itemView);
        }

        onBind((H)holder,  getItem(position), position);



    }
    protected enum AdapterAnimationType {
        AlphaIn,
        SlideInBottom,
        ScaleIn,
        SlideInLeft,
        SlideInRight,
    }

    /**
     * 动画
     *
     * @param view the view
     * @param type the type of the animation
     * @return the animator in array
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected Animator[] getAdapterAnimations(View view, AdapterAnimationType type) {
        if (type == AdapterAnimationType.ScaleIn) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", .5f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", .5f, 1f);
            return new ObjectAnimator[]{scaleX, scaleY};
        } else if (type == AdapterAnimationType.AlphaIn) {
            return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", .5f, 1f)};
        } else if (type == AdapterAnimationType.SlideInBottom) {
            return new Animator[]{
                    ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
            };
        } else if (type == AdapterAnimationType.SlideInLeft) {
            return new Animator[]{
                    ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0)
            };
        } else if (type == AdapterAnimationType.SlideInRight) {
            return new Animator[]{
                    ObjectAnimator.ofFloat(view, "translationX", view.getRootView().getWidth(), 0)
            };
        }
        return null;
    }

    /**
     * @param holder
     * @param item
     * @param position
     */
    public abstract void onBind(H holder, T item ,int position);



    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}