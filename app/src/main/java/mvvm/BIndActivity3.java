package mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableList;
import android.databinding.ObservableMap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutByMapOrListBinding;

import java.util.Random;

/**
 * ObservableCollection
 * dataBinding 也提供了包装类用于替代原生的 List 和 Map，
 * 分别是 ObservableList 和 ObservableMap,当其包含的数据发生变化时，绑定的视图也会随之进行刷新
 * <p>
 * xml对< >敏感，必须转义 &lt; &gt;
 *
 * @author zhangtuo
 * @date 2018/9/29
 */

public class BIndActivity3 extends BaseActivity {
    ObservableMap<String, String> map;
    ObservableList<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindActivityLayoutByMapOrListBinding binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_by_map_or_list);
        map = new ObservableArrayMap<>();
        map.put("name", "zhangsan");
        map.put("age", "22");
        binding.setMap(map);
        list = new ObservableArrayList<>();
        list.add("first");
        list.add("second");
        binding.setList(list);

        binding.setIndex(0);
        binding.setKey("name");
    }

    public void onClick(View view) {
        map.put("name", "leavesC,hi" + new Random().nextInt(100));
    }
}
