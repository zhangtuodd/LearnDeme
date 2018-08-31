package ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.zhangtuo.learndeme.R;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/8/20
 */

public class CommonDialog extends Dialog implements View.OnClickListener {
    public TextView tvTitle, tvLeft, tvRight;
    String title, left, right;
    public ActionListener listener;

    public CommonDialog(@NonNull Context context, String title, String left, String right, ActionListener listener) {
        super(context, R.style.BaseDialog);
        this.listener = listener;
        this.title = title;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_layout);
        setCancelable(false);//点击外部不可dismiss
        setCanceledOnTouchOutside(false);//控制返回键是否dismiss
        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
        tvTitle = (TextView) findViewById(R.id.discovery_id_common_title);
        tvLeft = (TextView) findViewById(R.id.discovery_id_common_bt_left);
        tvLeft.setOnClickListener(this);
        tvRight = (TextView) findViewById(R.id.discovery_id_common_bt_right);
        tvRight.setOnClickListener(this);
        tvTitle.setText(title);
        tvLeft.setText(left);
        tvRight.setText(right);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.discovery_id_common_bt_left) {//左边button
            listener.clickLeft();
        } else if (id == R.id.discovery_id_common_bt_right) {//右边button
            listener.clickRight();
        }
    }

    public interface ActionListener {
        void clickLeft();

        void clickRight();
    }
}