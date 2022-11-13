package hotfix_coldlaunch

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.zhangtuo.learndeme.BaseActivity
import com.example.zhangtuo.learndeme.R

/**
 * dex替换方式实现热修复
 *
 * @author zhangtuo
 * @date 2022/11/13
 */
class HotFixActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotfix)
        findViewById<Button>(R.id.click).setOnClickListener {
            click()
        }
    }

    fun click() {
        val value = 10/0
        Toast.makeText(this, "bug修复成功", Toast.LENGTH_LONG).show()
    }
}