package com.example.a3d;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGlRender implements GLSurfaceView.Renderer {
    private Triangle triangle;
    public static int loadShader(int type,String shaderCode){
        //create a vertex shader type {GLES20.GL_VERTEX_SHADER
        //create a fragment shader type {GLES20.GL_FRAGMENT_SHADER
        int shader= GLES20.glCreateShader(type);
        //add the source code to the shader and compile it
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;

    }
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(1.0f,0.0f,1.0f,1.0f);
    ///initialize a traingle
        triangle=new Triangle();

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
     GLES20.glViewport(0,0,i,i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    triangle.draw();
    }
}
