package design_mode.package_observer;

/**
 * 主题对象持有观察者集合 和 全量通知方法（此方法中遍历所有的观察者 - 执行观察者动作方法）
 * <p>
 * 可用在：ugc相关：点赞、关注、收藏、评论数； 以及登录（动作触发登录的后续操作/跟登录态关联的相关节点）、退出登录
 * <p>
 * 思考点赞（同一id的点赞状态全局一致）：
 * 通知时机是在点赞完成后（列表或者详情点完赞后通知其他列表对应id的点赞状态、数字变更）
 * 注册时机是在列表（其他列表就是观察者，观察者去注册，做的操作应该是遍历数据 - 修改对应id点赞态以及数字 - 刷新列表）
 */
public class Main {

    public static void main(String[] args) {
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();

        ObservableApi observableApi = new Observable();
        observableApi.add(observerA);
        observableApi.add(observerB);
        observableApi.updateAll();
    }
}

