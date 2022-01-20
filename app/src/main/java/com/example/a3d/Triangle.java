package com.example.a3d;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL;

public class Triangle {
    private final int mprogram;

    private final String vertexShaderCode =
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position =vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;"
                    + "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";


    private FloatBuffer vertexBuffer;
      //number of the coordinate in the array
    static final int COOR_PER_VERTEX=3;
    static float traingleCoords[]={
            0.0f,0.5f,0.0f,  //top part
            0.5f,0.0f,0.0f,  //bottom right
            -0.5f,0.0f,0.0f   //bottom left
    };
    //set color of the traingle with RGBA
    float TraingleColor[]={1.0f,0.0f,0.1f,1.0f};
    public Triangle(){

        //initlize vetex byte buffer for shape coordinate
        ByteBuffer bb=ByteBuffer.allocateDirect(
                ///number of coordante values
                traingleCoords.length*4
        );
        //use the device hardware native byte other
        bb.order(ByteOrder.nativeOrder());
        //create a floating point buffer from the bytebuffer
        vertexBuffer=bb.asFloatBuffer();
        ///add the coordantees to the floatbuffer
        vertexBuffer.put(traingleCoords);
        //set the buffer to read the first coordinate
        vertexBuffer.position(0);
        int vertexShader=MyGlRender.loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShader=MyGlRender.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);
        ///create empty openGl ES program
        mprogram=GLES20.glCreateProgram();
        ///add the vertex shader to program
        GLES20.glAttachShader(mprogram,vertexShader);
        ///add the fragment shader to program
        GLES20.glAttachShader(mprogram,fragmentShader);
        //create opengl es program exectables
        GLES20.glLinkProgram(mprogram);
    }
    ///create draw function
       private int positionHandle;
    private int colorHandle;
    private final int vertexCount=traingleCoords.length/COOR_PER_VERTEX;
    private  final int vertexStride=COOR_PER_VERTEX*4;
    public void draw(){
        //adding program to opengl env
        GLES20.glUseProgram(mprogram);
        ///get a handle to the traingle vertcies
        GLES20.glEnableVertexAttribArray(positionHandle);
        //enable a handle to the traingle vertices
        GLES20.glVertexAttribPointer(positionHandle,COOR_PER_VERTEX,
                GLES20.GL_FLOAT,false,
                vertexStride,vertexBuffer);
        //get handle to fragment shader vcolor member
        colorHandle=GLES20.glGetAttribLocation(mprogram,"vColor");
        ///set the traingle color
        GLES20.glUniform4fv(colorHandle,1,TraingleColor,0);
        //draw the traingle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);
        //disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

}
