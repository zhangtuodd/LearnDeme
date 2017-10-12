package proxy;

import android.util.Log;

/**
 * Created by zhangtuo on 2017/10/12.
 * <p>
 * 明星
 */

public class Star implements IStarBehavior, IStarBehaviorPlus {

    private String mName;

    public Star(String mName) {
        this.mName = mName;
    }

    @Override
    public void movieShow(int money) {
        Log.i("tag", mName + "出演了一部片酬" + money + "的电影");
    }

    @Override
    public void tvShow(int money) {
        Log.i("tag", mName + "出演了一部片酬" + money + "的电视剧");
    }

    @Override
    public void sing(int num) {
        Log.i("tag", mName + "出道唱过" + num + "首歌曲");
    }
}
