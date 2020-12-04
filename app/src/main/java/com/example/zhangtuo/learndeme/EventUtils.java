package com.example.zhangtuo.learndeme;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-26
 */
public class EventUtils {

    public static final int ACTION_DOWN = 0;

    /**
     * Constant for {@link #getActionMasked}: A pressed gesture has finished, the
     * motion contains the final release location as well as any intermediate
     * points since the last down or move event.
     */
    public static final int ACTION_UP = 1;

    /**
     * Constant for {@link #getActionMasked}: A change has happened during a
     * press gesture (between {@link #ACTION_DOWN} and {@link #ACTION_UP}).
     * The motion contains the most recent point, as well as any intermediate
     * points since the last down or move event.
     */
    public static final int ACTION_MOVE = 2;

    /**
     * Constant for {@link #getActionMasked}: The current gesture has been aborted.
     * You will not receive any more points in it.  You should treat this as
     * an up event, but not perform any action that you normally would.
     */
    public static final int ACTION_CANCEL = 3;


    public static String getEvent(int event) {
        if (event == 0) {
            return "ACTION_DOWN";
        } else if (event == 1) {
            return "ACTION_UP";
        } else if (event == 2) {
            return "ACTION_MOVE";
        } else if (event == 3) {
            return "ACTION_CANCEL";
        } else {
            return "OTHER-" + event;
        }
    }
}
