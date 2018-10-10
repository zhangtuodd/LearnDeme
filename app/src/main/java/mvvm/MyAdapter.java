package mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.ItemPeopleBinding;

import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/8
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "MyAdapter";
    private List<People> peoples = null;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public MyAdapter(List<People> peoples) {
        this.peoples = peoples;
    }

    public interface OnRecyclerViewItemClickListener {
        void OnItemClick(View view, People people);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//        ItemPeopleBinding binding = ItemPeopleBinding.inflate(inflater, parent, false);
        ItemPeopleBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_people, parent, false);
        binding.getRoot().setOnClickListener(this);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindto(peoples.get(position));
        holder.binding.getRoot().setTag(peoples.get(position));
    }

    @Override
    public int getItemCount() {
        if (peoples == null)
            return 0;
        return peoples.size();
    }


    @Override
    public void onClick(View v) {
        if (this.mOnItemClickListener != null) {
            mOnItemClickListener.OnItemClick(v, ((People) v.getTag()));
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding binding = null;

        public ViewHolder(ItemPeopleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindto(People people) {
            binding.setPeople(people);
            binding.executePendingBindings();//解决databinding闪烁问题
        }
    }
}