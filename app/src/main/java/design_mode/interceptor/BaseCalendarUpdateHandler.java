package design_mode.interceptor;

public abstract class BaseCalendarUpdateHandler implements CalendarUpdateHandler {
    protected CalendarUpdateHandler nextHandler;

    @Override
    public void setNextHandler(CalendarUpdateHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}