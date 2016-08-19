package lomasky.ma.xui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lomasky.ma.R;

public class SpinerPopWindow extends ListPopupWindow {

    private Context mContext;
    private CommonAdapter adapter;
    public SpinerPopWindow(Context context) {
        super(context);
        mContext = context;
        init();
    }

    List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
        adapter.data=list;
        adapter.notifyDataSetChanged();
    }

    private void init() {
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        setBackgroundDrawable(ContextCompat.getDrawable(mContext,R.drawable.btn_colored_material_color_white));
        adapter = new CommonAdapter<String>(mContext, android.R.layout.simple_list_item_1) {
            @Override
            public void onBind(ViewHolderHelper holder, String item, int position) {
                TextView textView= holder.getTextView(android.R.id.text1);

                textView.setText(item);
//                textView.setTextColor(ContextCompat.getColor(mContext,R.color.bootstrap_brand_secondary_text));
            }
        };

        setAdapter(adapter);



    }





}