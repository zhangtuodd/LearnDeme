package proxy;

/**
 * Created by zhangtuo on 2017/10/12.
 * <p>
 * 明星的基本行为 接口
 */

public interface IStarBehavior {

    /**
     * 演电影
     */
    void movieShow(int money);


    /**
     * 演电视剧
     */
    void tvShow(int money);

}
