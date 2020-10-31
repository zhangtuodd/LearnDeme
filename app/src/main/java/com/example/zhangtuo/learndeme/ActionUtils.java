package com.example.zhangtuo.learndeme;

import eventbus.EventActivity;
import z_router.IRouter;
import z_router.MyRouter;

/**
 * 先写死实现，当作编译时注解写入文件时候的参考
 *
 *
 * 写死实现无法引用到所有module中的activity，
 * （央视频是通过app引用到所有module，就能拿到module下的所有类，将路径和类通过map映射关联，再将map存储搭配base，这样所有业务都能通过path获取到对应activity。接口下发再跳转）
 * （央视频这种方式优缺点，解耦了类之间强关联，也可以处理降级：获取不到不跳转即可。但是需要去维护类：增删类需要手动操作类，不够灵活；组件化下随意插拔这种方式不满足）
 *
 * 编译时注解通过扫描注解找到对应的activity，有注解就能找到对应类，就能存储映射关系。有映射关系通过key就能跳转
 *
 * @author zhangtuo
 * @date 2020-10-30
 */
public class ActionUtils implements IRouter {
    @Override
    public void putActivity() {
        MyRouter.getInstance().addActivity("xx/path", EventActivity.class);
    }
}
