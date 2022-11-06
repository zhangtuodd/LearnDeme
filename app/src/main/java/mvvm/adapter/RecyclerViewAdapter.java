package mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhangtuo.learndeme.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import mvvm.ViewModelBinder;
import mvvm.vm.ItemViewModel;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/16
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataBindingViewHolder> {

    private List<ItemViewModel> viewModels = new ArrayList<>();
    private Observable<List<ItemViewModel>> source;
    private ViewModelBinder defaultBinder;
    private final @NonNull
    HashMap<RecyclerView.AdapterDataObserver, Disposable> subscriptions = new HashMap<>();


    public RecyclerViewAdapter(Observable<List<ItemViewModel>> items, ViewModelBinder defaultBinder) {
        this.defaultBinder = defaultBinder;
        source = items.observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<List<ItemViewModel>>() {
            @Override
            public void accept(List<ItemViewModel> itemViewModels) throws Exception {
                viewModels = itemViewModels != null ? itemViewModels : new ArrayList<ItemViewModel>();
                notifyDataSetChanged();
            }
        }).share();
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
        return new DataBindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        defaultBinder.bind(holder.binding, viewModels.get(position));
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return viewModels == null ? 0 : viewModels.size();
    }

    @Override
    public void onViewRecycled(@NonNull DataBindingViewHolder holder) {
        super.onViewRecycled(holder);
        defaultBinder.bind(holder.binding, null);
        holder.binding.executePendingBindings();
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        Disposable subscribe = source.subscribe();
        subscriptions.put(observer, subscribe);
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        Disposable subscription = subscriptions.remove(observer);
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }


    class DataBindingViewHolder extends RecyclerView.ViewHolder {
        public ViewDataBinding binding;

        public DataBindingViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
