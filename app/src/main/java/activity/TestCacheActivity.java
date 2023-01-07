package activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.base.util.StringUtils;
import com.example.zhangtuo.learndeme.R;

import cache.Person;
import cache.SoftReferenceCache;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author： zhangtuo
 * @date： 2019-11-07
 * @description：
 */
public class TestCacheActivity extends Activity {

    SoftReferenceCache<String, Person> cache = new SoftReferenceCache<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_cache_layout);
        StringUtils.test();
        final Person person = new Person("zhang", "22");
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cache.put("p", person);
            }
        });

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person1 = cache.get("p");
                Log.i("TestCacheActivity", "person---------" + (person1 == null ? "null" : person1.toString()));
            }
        });
        findViewById(R.id.gc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();
            }
        });

        Request request = new Request.Builder()
                .get()
                .url("https:www.baidu.com")
                .build();
        OkHttpClient client = new OkHttpClient();

//        client.newCall(request).execute()
//        client.newCall(request).enqueue();


    }

    @Override
    public ClassLoader getClassLoader() {
        return super.getClassLoader();
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }
}
