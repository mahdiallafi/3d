package com.example.a3d;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MyOpenGlView extends GLSurfaceView {
    private MyGlRender renderer;
    public MyOpenGlView(Context context) {
        super(context);
        initopenGlView();
        renderer=new MyGlRender();
        setRenderer(renderer);
    }

    public MyOpenGlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initopenGlView();
        renderer=new MyGlRender();
    }
    private void initopenGlView(){
        //create open es
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);


    }
}
