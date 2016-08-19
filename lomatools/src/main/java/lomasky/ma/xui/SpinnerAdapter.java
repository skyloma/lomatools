package lomasky.ma.xui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
    private final Helper mDropDownHelper;
    int gravity = Gravity.LEFT;

    public void setmData(String[] mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public String[] mData;

    public SpinnerAdapter(Context context, String[] objects) {

        super(context, android.R.layout.simple_list_item_1, objects);
        mDropDownHelper = new Helper(context);
    }

    public SpinnerAdapter(Context context, List objects) {

        super(context, android.R.layout.simple_list_item_1, objects);
        mDropDownHelper = new Helper(context);
    }

    public SpinnerAdapter(Context context, String[] objects, int gravity) {
        super(context, android.R.layout.simple_list_item_1, objects);
        mDropDownHelper = new Helper(context);
        this.gravity = gravity;
    }
    public SpinnerAdapter(Context context, List objects, int gravity) {
        super(context, android.R.layout.simple_list_item_1, objects);
        mDropDownHelper = new Helper(context);
        this.gravity = gravity;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            // Inflate the drop down using the helper's LayoutInflater
            LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = convertView;
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setGravity(gravity | Gravity.START | Gravity.CENTER_VERTICAL);
        textView.setText(getItem(position));
        return view;
    }

    @Override
    public void setDropDownViewTheme(Resources.Theme theme) {
        mDropDownHelper.setDropDownViewTheme(theme);
    }

    @Override
    public Resources.Theme getDropDownViewTheme() {
        return mDropDownHelper.getDropDownViewTheme();
    }
}
