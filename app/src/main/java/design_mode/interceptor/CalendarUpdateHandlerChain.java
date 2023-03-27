package design_mode.interceptor;

import java.util.ArrayList;
import java.util.List;

public class CalendarUpdateHandlerChain {
    private List<CalendarUpdateHandler> handlerList;

    public CalendarUpdateHandlerChain() {
        handlerList = new ArrayList<>();
    }

    public void addHandler(CalendarUpdateHandler handler) {
        handlerList.add(handler);
    }

    public boolean handleRequest(CalendarUpdateRequest request) {
        for (CalendarUpdateHandler handler : handlerList) {
            if (!handler.handle(request)) {
                return false;
            }
        }
        return true;
    }
}