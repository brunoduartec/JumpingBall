package ploobs.plantevolution.Input;

import android.view.MotionEvent;

/**
 * Created by Bruno on 07/12/2015.
 */
public class InputSystem {


    private MotionEvent _inputEvent;
    private final float TOUCH_SCALE_FACTOR = 0.02f;
    private float mPreviousX;
    private float mPreviousY;



    private InputSystem()
    {

    }

    public MotionEvent get_inputEvent() {
        return _inputEvent;
    }

    public void set_inputEvent(MotionEvent _inputEvent) {
        this._inputEvent = _inputEvent;
    }

    public float getmPreviousX() {
        return mPreviousX;
    }

    public void setmPreviousX(float mPreviousX) {
        this.mPreviousX = mPreviousX;
    }

    public float getmPreviousY() {
        return mPreviousY;
    }

    public void setmPreviousY(float mPreviousY) {
        this.mPreviousY = mPreviousY;
    }


    private static class SingletonHolder {
        private static final InputSystem INSTANCE = new InputSystem();
    }

    public static InputSystem getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
