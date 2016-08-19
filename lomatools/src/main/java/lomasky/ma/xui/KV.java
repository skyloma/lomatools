package lomasky.ma.xui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lomasky.ma.R;


/**
 * TODO: document your custom view class.
 */
public class KV extends RelativeLayout {


    private String lable, text, help_text;
    private TextView lableTextView;
    public EditText editText;
    public TextView textView;
    public CheckBox checkBox;

    private boolean isEdit = false, isUnderLine = false, isClear = false, isPwd, isCheck;
    private int textcolor;
    private int rightIcon;
    private Context context;

    public int getRightIcon() {
        return rightIcon;
    }

    public void setRightIcon(int rightIcon) {
        this.rightIcon = rightIcon;
        if (isEdit) {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
        }

    }

    public void setLeftIcon(int leftIcon) {
        this.leftIcon = leftIcon;
        if (isEdit) {
            editText.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, 0, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, 0, 0);
        }
    }

    public int getLeftIcon() {
        return leftIcon;
    }

    private int leftIcon;

    public int max_len, min_len;
    private ColorStateList dividerColor;
    private ColorStateList editDividerColor;


    public KV(Context context) {
        super(context);
        this.context = context;
        init(null, 0, context);
    }

    public KV(Context context, boolean isEdit) {
        super(context);
        this.isEdit = isEdit;
        this.context = context;
        init(null, 0, context, isEdit);
    }

    public KV(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0, context);
    }

    public KV(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(attrs, defStyle, context);
    }
      TypedArray a;
    private void init(AttributeSet attrs, int defStyle, Context context) {
        // Load attributes
        a = getContext().obtainStyledAttributes(  attrs, R.styleable.KV, defStyle, 0);
        View view;

        isEdit = a.getBoolean(R.styleable.KV_isEdit, false);
        isCheck = a.getBoolean(R.styleable.KV_isCheck, false);
        isPwd = a.getBoolean(R.styleable.KV_isPwd, false);

        isClear = a.getBoolean(R.styleable.KV_isClearButton, false);
        isUnderLine = a.getBoolean(R.styleable.KV_isUnderLine, false);
        help_text = a.getString(R.styleable.KV_help_text);
        max_len = a.getInt(R.styleable.KV_max_len, 32);
        min_len = a.getInt(R.styleable.KV_min_len, 0);
        lable = a.getString(R.styleable.KV_k);
        text = a.getString(R.styleable.KV_v);
        textcolor = a.getColor(R.styleable.KV_c, -1);
        leftIcon = a.getResourceId(R.styleable.KV_LeftIcon, 0);
        rightIcon = a.getResourceId(R.styleable.KV_rightIcon, 0);

        if (isEdit) {
            view = LayoutInflater.from(context).inflate(R.layout.custome_view_edit_text, this, true);
            lableTextView = ((TextView) view.findViewById(R.id.k));
            lableTextView.setPadding(0, Density.dp2px(context, 8), Density.dp2px(context, 16), Density.dp2px(context, 8));
            editText = ((EditText) view.findViewById(R.id.v));
            lableTextView.setText(lable);
            if (textcolor != -1) {
                editText.setTextColor(textcolor);
            }
            if (isUnderLine) {

                if (editDividerColor == null) {
                    int[][] states = new int[][]{
                            new int[]{-android.R.attr.state_focused},
                            new int[]{android.R.attr.state_focused, android.R.attr.state_enabled},
                    };
                    int[] colors = new int[]{
                            ThemeUtil.colorControlNormal(context, 0xFF000000),
                            ThemeUtil.colorControlNormal(context, 0xFF000000),
                    };

                    editDividerColor = new ColorStateList(states, colors);
                }
                Drawable bg = new DividerDrawable(Density.dp2px(context, 1), 0, 0, editDividerColor, 100);
                if (Build.VERSION.SDK_INT >= 16) {
                    editText.setBackground(bg);
                } else {
                    editText.setBackgroundDrawable(bg);
                }

            } else {

                editText.setBackgroundResource(android.R.color.transparent);
            }

            if (rightIcon != 0) {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
            }
            if (leftIcon != 0) {
                editText.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, 0, 0);
            }
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max_len)});

            if (min_len != 0) {
                editText.setMinEms(min_len);
            }
            if (help_text != null) {
                editText.setHint(help_text);
            }
            if (isPwd) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            editText.setText(text);
            editText.setPadding(0, Density.dp2px(context, 8), 0, Density.dp2px(context, 8));

        } else if (isCheck) {
            view = LayoutInflater.from(context).inflate(R.layout.custome_view_check, this, true);
            lableTextView = ((TextView) view.findViewById(R.id.k));
            lableTextView.setText(lable);
            lableTextView.setPadding(0, Density.dp2px(context, 8), Density.dp2px(context, 16), Density.dp2px(context, 8));
            checkBox = ((CheckBox) view.findViewById(R.id.v));
            checkBox.setText(text);
        } else {

            view = LayoutInflater.from(context).inflate(R.layout.custome_view_label_text, this, true);
            lableTextView = ((TextView) view.findViewById(R.id.k));
            lableTextView.setText(lable);
            lableTextView.setPadding(0, Density.dp2px(context, 8), Density.dp2px(context, 16), Density.dp2px(context, 8));
            textView = (TextView) findViewById(R.id.v);
            textView.setText(text);

            if (textcolor != -1) {
                textView.setTextColor(textcolor);
            }

            if (rightIcon != 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
            }
            if (leftIcon != 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, 0, 0);
            }
            if (isUnderLine) {
                if (dividerColor == null) {
                    int[][] states = new int[][]{
                            new int[]{-android.R.attr.state_pressed},
                            new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled},
                    };
                    int[] colors = new int[]{
                            ThemeUtil.colorControlNormal(context, 0xFF000000),
                            ThemeUtil.colorControlActivated(context, 0xFF000000),
                    };

                    dividerColor = new ColorStateList(states, colors);
                }
                Drawable bg = new DividerDrawable(Density.dp2px(context, 1), 0, 0, dividerColor, 100);
                if (Build.VERSION.SDK_INT >= 16) {
                    textView.setBackground(bg);
                } else {
                    textView.setBackgroundDrawable(bg);
                }


            }
            if (max_len != 0) {
                textView.setMaxEms(max_len);
            }

            if (help_text != null) {
                textView.setHint(help_text);
            }
            if (isPwd) {
                textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            textView.setPadding(0, Density.dp2px(context, 8), 0, Density.dp2px(context, 8));

        }

        setGravity(Gravity.TOP);
        a.recycle();


    }

    private void init(AttributeSet attrs, int defStyle, Context context, boolean isEdit) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.KV, defStyle, 0);


        View view;

        //isEdit = a.getBoolean(R.styleable.KV_isEdit, false);
        isPwd = a.getBoolean(R.styleable.KV_isPwd, false);

        isClear = a.getBoolean(R.styleable.KV_isClearButton, false);
        isUnderLine = a.getBoolean(R.styleable.KV_isUnderLine, true);
        help_text = a.getString(R.styleable.KV_help_text);
        max_len = a.getInt(R.styleable.KV_max_len, 32);
        min_len = a.getInt(R.styleable.KV_min_len, 0);
        lable = a.getString(R.styleable.KV_k);
        text = a.getString(R.styleable.KV_v);
        textcolor = a.getColor(R.styleable.KV_c, -1);
        leftIcon = a.getResourceId(R.styleable.KV_LeftIcon, 0);
        rightIcon = a.getResourceId(R.styleable.KV_rightIcon, 0);

        if (isEdit) {
            view = LayoutInflater.from(context).inflate(R.layout.custome_view_edit_text, this, true);
            lableTextView = ((TextView) view.findViewById(R.id.k));
            lableTextView.setPadding(0, Density.dp2px(context, 8), Density.dp2px(context, 16), Density.dp2px(context, 8));
            editText = ((EditText) view.findViewById(R.id.v));
            lableTextView.setText(lable);
            if (textcolor != -1) {
                editText.setTextColor(textcolor);
            }
            if (isUnderLine) {

                if (editDividerColor == null) {
                    int[][] states = new int[][]{
                            new int[]{-android.R.attr.state_focused},
                            new int[]{android.R.attr.state_focused, android.R.attr.state_enabled},
                    };
                    int[] colors = new int[]{
                            ThemeUtil.colorControlNormal(context, 0xFF000000),
                            ThemeUtil.colorControlActivated(context, 0xFF000000),
                    };

                    editDividerColor = new ColorStateList(states, colors);
                }
                Drawable bg = new DividerDrawable(Density.dp2px(context, 1), 0, 0, editDividerColor, 100);
                if (Build.VERSION.SDK_INT >= 16) {
                    editText.setBackground(bg);
                } else {
                    editText.setBackgroundDrawable(bg);
                }

            } else {

                editText.setBackgroundResource(android.R.color.transparent);
            }

            if (rightIcon != 0) {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
            }
            if (leftIcon != 0) {
                lableTextView.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, 0, 0);
            }
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max_len)});

            if (min_len != 0) {
                editText.setMinEms(min_len);
            }
            if (help_text != null) {
                editText.setHint(help_text);
            }
            if (isPwd) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            editText.setText(text);
            editText.setPadding(0, Density.dp2px(context, 8), 0, Density.dp2px(context, 8));

        } else {
            view = LayoutInflater.from(context).inflate(R.layout.custome_view_label_text, this, true);
            lableTextView = ((TextView) view.findViewById(R.id.k));
            lableTextView.setText(lable);
            lableTextView.setPadding(0, Density.dp2px(context, 8), Density.dp2px(context, 16), Density.dp2px(context, 8));
            textView = (TextView) findViewById(R.id.v);
            textView.setText(text);

            if (textcolor != -1) {
                textView.setTextColor(textcolor);
            }

            if (rightIcon != 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);

            }
            if (leftIcon != 0) {
                lableTextView.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, 0, 0);
            }
            if (isUnderLine) {
                if (dividerColor == null) {
                    int[][] states = new int[][]{
                            new int[]{-android.R.attr.state_pressed},
                            new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled},
                    };
                    int[] colors = new int[]{
                            ThemeUtil.colorControlNormal(context, 0xFF000000),
                            ThemeUtil.colorControlActivated(context, 0xFF000000),
                    };

                    dividerColor = new ColorStateList(states, colors);
                }
                Drawable bg = new DividerDrawable(Density.dp2px(context, 1), 0, 0, dividerColor, 100);
                if (Build.VERSION.SDK_INT >= 16) {
                    textView.setBackground(bg);
                } else {
                    textView.setBackgroundDrawable(bg);
                }


            }
            if (max_len != 0) {
                textView.setMaxEms(max_len);
            }
            if (isPwd) {
                textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            textView.setPadding(0, Density.dp2px(context, 8), 0, Density.dp2px(context, 8));
        }

        setGravity(Gravity.TOP);
        a.recycle();


    }


    public void setError(String text) {


        if (isEdit) {
            editText.setError(text == null ? "" : text);
        }


    }

    public String getValue() {
        if (isEdit) {
            return editText.getText().toString();
        } else {
            return textView.getText().toString();
        }

    }

    public void setValue(String text) {


        if (isEdit) {
            editText.setText(text == null ? "" : text);
        } else if (isCheck) {
            checkBox.setText(text == null ? "" : text);
        } else {
            textView.setText(text == null ? "" : text);
        }


    }

    public void setValue(Object obj) {

        if (isEdit) {
            editText.setText(obj == null ? "" : obj.toString());
        } else if (isCheck) {
            checkBox.setText(obj == null ? "" : obj.toString());
        } else {
            textView.setText(obj == null ? "" : obj.toString());
        }


    }

    public void setChecked(boolean checked) {
        if (isCheck) {
            checkBox.setChecked(checked);
        }
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
        a.recycle();
    }


    public void setKey(String text) {
        lableTextView.setText(text == null ? "" : text);
    }

    public void setUnderLine(boolean isUnderLine){
        this.isUnderLine = isUnderLine;
        if (isUnderLine) {
            if (dividerColor == null) {
                int[][] states = new int[][]{
                        new int[]{-android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled},
                };
                int[] colors = new int[]{
                        ThemeUtil.colorControlNormal(context, 0xFF000000),
                        ThemeUtil.colorControlActivated(context, 0xFF000000),
                };

                dividerColor = new ColorStateList(states, colors);
            }
            Drawable bg = new DividerDrawable(Density.dp2px(context, 1), 0, 0, dividerColor, 100);
            if (Build.VERSION.SDK_INT >= 16) {
                textView.setBackground(bg);
            } else {
                textView.setBackgroundDrawable(bg);
            }


        }
    }
}
