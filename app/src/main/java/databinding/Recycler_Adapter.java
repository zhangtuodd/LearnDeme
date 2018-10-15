package databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.RecyclerDataItemBinding;

import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/10
 */

public class Recycler_Adapter extends RecyclerView.Adapter {

    private Context context;
    List<Recycler_Data> list;


    public Recycler_Adapter(Context context) {
        this.context = context;
    }

    public void addData(List<Recycler_Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//       RecyclerDataItemBinding binding =
        RecyclerDataItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_data_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Recycler_Data data = list.get(position);
        if (holder instanceof MyViewHolder) {
            MyViewHolder vh = (MyViewHolder) holder;
            vh.binding.setData(data);
            vh.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "onclick - " + position, Toast.LENGTH_SHORT).show();
                }
            });
            vh.binding.executePendingBindings();//先计算预留出控件变更的位置、大小，然后再布局。有动画
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerDataItemBinding binding;

        public MyViewHolder(RecyclerDataItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
