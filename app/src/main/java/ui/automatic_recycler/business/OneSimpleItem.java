package ui.automatic_recycler.business;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zhangtuo.learndeme.R;

import java.util.List;

import ui.automatic_recycler.SimpleItem;

public class OneSimpleItem extends SimpleItem {
    @Override
    public int getLayoutId() {
        return R.layout.one_layout;
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(View convertView) {
        return null;
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder holder, int position, List payloads) {

    }

    @Override
    public int getViewType() {
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
