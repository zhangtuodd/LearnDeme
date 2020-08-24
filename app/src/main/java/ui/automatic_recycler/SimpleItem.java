package ui.automatic_recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class SimpleItem<Model> {

    public abstract  int getLayoutId();

    /**
     * Create view holder by item
     */
    protected abstract RecyclerView.ViewHolder createHolder(View convertView);


    /**
     * Bind view's data and ui, through SimpleAdapter.notifyItem** with payload <p>
     * so we cant use payloads to refresh part of ui pieces
     */
    protected abstract void bindView(RecyclerView.ViewHolder holder, int position, List payloads);

    /**
     * Specify item's type
     */
    public abstract int getViewType();
}
