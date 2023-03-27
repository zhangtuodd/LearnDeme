package design_mode.interceptor;

import java.util.ArrayList;
import java.util.List;

public interface CalendarUpdateHandler {
    boolean handle(CalendarUpdateRequest request);

    void setNextHandler(CalendarUpdateHandler nextHandler);
}



 class FormValidationHandler extends BaseCalendarUpdateHandler {
    @Override
    public boolean handle(CalendarUpdateRequest request) {
        // 表单校验逻辑
        boolean isFormValid = checkForm(request);
        if (!isFormValid) {
            return false;
        }

        // 校验通过，继续下一步处理
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return true;
    }

    private boolean checkForm(CalendarUpdateRequest request) {
        // TODO: 表单校验逻辑
        return true;
    }
}

 class UpdateAllHandler extends BaseCalendarUpdateHandler {
    @Override
    public boolean handle(CalendarUpdateRequest request) {
        // 是否更新全部循环日程逻辑
        boolean isUpdateAll = shouldUpdateAll(request);
        request.setUpdateAll(isUpdateAll);

        // 继续下一步处理
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return true;
    }

    private boolean shouldUpdateAll(CalendarUpdateRequest request) {
        // TODO: 是否更新全部循环日程逻辑
        return false;
    }
}

 class ReminderEmailHandler extends BaseCalendarUpdateHandler {
    @Override
    public boolean handle(CalendarUpdateRequest request) {
        // 是否发送邮件提醒
        boolean isSendEmail = shouldSendEmail(request);
        request.setSendEmail(isSendEmail);

        // 继续下一步处理
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return true;
    }

    private boolean shouldSendEmail(CalendarUpdateRequest request) {
        // TODO: 是否发送邮件提醒逻辑
        return false;
    }
}

 class CalendarUpdateRequest {
    private boolean isUpdateAll;
    private boolean isSendEmail;

    public boolean isUpdateAll() {
        return isUpdateAll;
    }

    public void setUpdateAll(boolean updateAll) {
        isUpdateAll = updateAll;
    }

    public boolean isSendEmail() {
        return isSendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        isSendEmail = sendEmail;
    }
}


