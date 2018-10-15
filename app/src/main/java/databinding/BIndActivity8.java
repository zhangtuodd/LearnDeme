package databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.zhangtuo.learndeme.BaseActivity;
import com.example.zhangtuo.learndeme.R;
import com.example.zhangtuo.learndeme.databinding.BindActivityLayoutByBindingRecyclerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/10/8
 */

public class BIndActivity8 extends BaseActivity {

    private static final String TAG = "BIndActivity8";
    private List<People> peoples;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindActivityLayoutByBindingRecyclerBinding binding = DataBindingUtil.setContentView(this, R.layout.bind_activity_layout_by_binding_recycler);

        peoples = new ArrayList<People>();


        for (int i = 1; i <= 10; i++) {
            People people = new People();
            people.name.set("Jim" + i);
            Random random = new Random();
            people.gender.set(random.nextBoolean() ? "male" : "female");
            people.age.set("age - "+(20 + random.nextInt(10)));
            peoples.add(people);
        }

        adapter = new MyAdapter(peoples);
        adapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View view, People people) {
                Toast.makeText(BIndActivity8.this, "你点击的是：" + people.name.get(), Toast.LENGTH_SHORT).show();
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        // delayChange();
    }

    private void delayChange() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    peoples.get(i - 1).name.set("Tom" + i);
                    Random random = new Random();
                    peoples.get(i - 1).gender.set(random.nextBoolean() ? "male" : "female");
                    peoples.get(i - 1).age.set("age - " + (20 + random.nextInt(10)));
                }
                Toast.makeText(BIndActivity8.this, "Hi!" + peoples.size(), Toast.LENGTH_SHORT).show();
            }
        }, 2000);

    }

}
