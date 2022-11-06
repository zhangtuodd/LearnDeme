package databinding;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutByMyRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView databinding
 *
 * @author zhangtuo
 * @date 2018/10/10
 */

public class BIndActivity9 extends BaseActivity {
    private List<Recycler_Data> list;
    private Recycler_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindActivityLayoutByMyRecyclerBinding binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_by_my_recycler);
        list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Recycler_Data data = new Recycler_Data();
            data.imageUrl.set("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2261055620,1765857650&fm=26&gp=0.jpg");
            data.text.set("内容----" + i);
            list.add(data);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.myRecycler.setLayoutManager(layoutManager);
        adapter = new Recycler_Adapter(this);
        binding.myRecycler.setAdapter(adapter);

        adapter.addData(list);
    }

}
