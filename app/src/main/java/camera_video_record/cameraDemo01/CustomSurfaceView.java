package camera_video_record.cameraDemo01;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView {
    public CustomSurfaceView(Context context) {
        super(context);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ONTouchEvent onTouchEvent;
    public void setCustomEvent(ONTouchEvent onTouchEvent) {
        this.onTouchEvent = onTouchEvent;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event != null){
            onTouchEvent.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    public interface ONTouchEvent {
        void onTouchEvent(MotionEvent event);
    }
}
