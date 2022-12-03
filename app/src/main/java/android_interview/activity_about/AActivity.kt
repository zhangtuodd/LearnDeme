package android_interview.activity_about

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.zhangtuo.learndeme.R

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2022/12/2
 */
class AActivity : CommonActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_a)
        findViewById<View>(R.id.tv1).setOnClickListener {
            startActivity(Intent(this, BActivity::class.java))
        }
    }
}