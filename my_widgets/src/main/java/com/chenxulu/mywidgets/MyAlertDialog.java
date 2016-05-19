package com.chenxulu.mywidgets;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by xulu on 16/3/29.
 */
public class MyAlertDialog {
    private Dialog mDialog;

    private View layout;
    private TextView titleTxt;
    private TextView messageTxt;

    private ListView listView;

    private LinearLayout buttonLayout;
    private Button positiveBtn;
    private Button negativeBtn;

    public MyAlertDialog(Context context) {
        mDialog = new Dialog(context, R.style.MyAlertDialog);
        layout = LayoutInflater.from(context).inflate(R.layout.my_alert_dialog_layout, null);
        titleTxt = (TextView) layout.findViewById(R.id.title_txt);
        messageTxt = (TextView) layout.findViewById(R.id.message_txt);

        listView = (ListView) layout.findViewById(R.id.list_view);

        buttonLayout = (LinearLayout) layout.findViewById(R.id.button_layout);
        positiveBtn = (Button) layout.findViewById(R.id.positive_btn);
        negativeBtn = (Button) layout.findViewById(R.id.negative_btn);
        mDialog.setContentView(layout);

        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        Window window = mDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = Double.valueOf(context.getResources().getDisplayMetrics().widthPixels * 0.80).intValue();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        titleTxt.setVisibility(View.VISIBLE);
        titleTxt.setText(title);
    }

    /**
     * 设置提示文字
     *
     * @param message
     */
    public void setMessage(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        messageTxt.setVisibility(View.VISIBLE);
        messageTxt.setText(message);
    }

    /**
     * 设置列表
     *
     * @param items
     * @param onItemClickListener
     */
    public void setItems(final CharSequence[] items, final AdapterView.OnItemClickListener onItemClickListener) {
        listView.setVisibility(View.VISIBLE);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public Object getItem(int i) {
                return items[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_alert_dialog_text_item_layout, null);
                textView.setText(items[i]);
                return textView;
            }
        };
        listView.setAdapter(baseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mDialog.dismiss();
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(adapterView, view, i, l);
            }
        });
    }

    /**
     * 设置PositiveButton
     *
     * @param text
     * @param onClickListener
     */
    public void setPositiveButton(CharSequence text, final View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        buttonLayout.setVisibility(View.VISIBLE);
        positiveBtn.setVisibility(View.VISIBLE);
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                if (onClickListener != null)
                    onClickListener.onClick(view);
            }
        });
    }

    /**
     * 设置NegativeButton
     *
     * @param text
     * @param onClickListener
     */
    public void setNegativeButton(CharSequence text, final View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        buttonLayout.setVisibility(View.VISIBLE);
        negativeBtn.setVisibility(View.VISIBLE);
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                if (onClickListener != null)
                    onClickListener.onClick(view);
            }
        });
    }

    /**
     * Sets whether this dialog is cancelable with the BACK key.
     * Default true.
     *
     * @param flag
     */
    public void setCancelable(boolean flag) {
        mDialog.setCancelable(flag);
    }

    /**
     * Sets whether this dialog is canceled when touched outside the window's bounds.
     * Default true.
     *
     * @param flag
     */
    public void setCanceledOnTouchOutside(boolean flag) {
        mDialog.setCanceledOnTouchOutside(flag);
    }


    /**
     * 显示对话框
     */
    public void show() {
        mDialog.show();
    }
}
