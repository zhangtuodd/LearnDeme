package mvvm;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;

import com.example.base.util.GlideUtils;
import com.example.zhangtuo.learndeme.BR;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import mvvm.adapter.RecyclerViewAdapter;
import mvvm.vm.ItemViewModel;
import mvvm.vm.ViewModel;

/**
 * 转换器
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class BindAdapters {

    //mvvm初始化binder
    public static ViewModelBinder viewModelBinder = new ViewModelBinder() {
        @Override
        public void bind(ViewDataBinding binding, ViewModel viewModel) {
            binding.setVariable(BR.vm, viewModel);
        }
    };

    @Nullable
    private static ViewModelBinder defaultBinder = null;

    @Nullable
    public static ViewModelBinder getDefaultBinder() {
        return defaultBinder;
    }

    public static void setBinder(@NonNull ViewModelBinder binder) {
        defaultBinder = binder;
    }

    /**
     * 点击跳转的中转
     * --BindingConversion ： 方法可以直接在xml跟踪，比BindingAdapter方便，但是系全局，慎用
     *
     * @param listener
     * @return
     */
    @BindingConversion
    public static View.OnClickListener toOnClickListener(final Action listener) {
        if (listener != null) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        listener.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        } else {
            return null;
        }
    }

    @BindingAdapter({"items", "verticle"})
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, Observable<List<ItemViewModel>> items, boolean vertical) {
        int orientation = vertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), orientation, false));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items, defaultBinder);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("imageRes")
    public static void loadImage(ImageView imageView, @DrawableRes int imageRes) {
        GlideUtils.getResource(imageView.getContext(), imageView, imageRes);
    }

}
