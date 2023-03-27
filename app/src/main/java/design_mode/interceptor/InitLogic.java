package design_mode.interceptor;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/3/27
 */

/**
 * 日历更新
 *
 * Android中日历编辑更新功能涉及到表单校验、是否更新全部循环日程、是否对日程邀请人发送日程变动的提醒邮件。针对这种情况通过责任链模式的代码处理以上逻辑从而提高可维护性
 */
class InitLogic {

  public void init(){
     CalendarUpdateRequest request = new CalendarUpdateRequest();
     CalendarUpdateHandlerChain handlerChain = new CalendarUpdateHandlerChain();
     handlerChain.addHandler(new FormValidationHandler());
     handlerChain.addHandler(new UpdateAllHandler());
     handlerChain.addHandler(new ReminderEmailHandler());

     boolean isSuccess = handlerChain.handleRequest(request);
     if (isSuccess) {
        // 更新日历
     } else {
        // 处理失败
     }
  }

}
