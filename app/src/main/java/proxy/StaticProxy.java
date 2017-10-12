package proxy;

import android.util.Log;

/**
 * Created by zhangtuo on 2017/10/12.
 * <p>
 * 静态代理
 * <p>
 * 有通告先找经纪人
 * <p>
 * 静态代理的弊端：
 * 在运行前手动创建代理类，这意味着如果有很多代理的话会很累哎；
 * - 其次代理类 Agent 和 被代理类 Star 必须实现同样的接口，万一接口有变动，代理、被代理类都得修改，容易出问题
 */

public class StaticProxy implements IStarBehavior {

    Star star;

    public StaticProxy(Star star) {
        this.star = star;
    }

    @Override
    public void movieShow(int money) {
        if (money < 300000) {
            Log.i("tag", money + "块钱，你找别人演电影吧");
            return;
        }
        star.movieShow(money);
    }

    @Override
    public void tvShow(int money) {
        if (money < 300000) {
            Log.i("tag", money + "块钱，你找别人演电视剧吧");
            return;
        }
        star.tvShow(money);

    }
}
