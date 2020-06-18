package jp.ac.titech.itpro.sdl.die;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class SimpleRenderer implements GLSurfaceView.Renderer {
    private final static String TAG = SimpleRenderer.class.getSimpleName();

    private Obj obj;
    private float x, y, z;     // object position
    private float rx, ry, rz;  // object rotation

    SimpleRenderer() {}

    void setObj(Obj obj, float x, float y, float z) {
        this.obj = obj;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    void setObj(Obj obj) {
        setObj(obj, 0, 0, 0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "onSurfaceCreated");
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged");
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 30, ((float) width) / height, 1, 50);
        GLU.gluLookAt(gl, 0, 0, 10, 0, 0, 0, 0, 1, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.1f, 0.1f, 0.1f, 0.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(x, y, z);
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);
        gl.glScalef(1, 1, 1);
        obj.draw(gl);
    }

    void rotateObjX(float th) {
        rx = th;
    }

    void rotateObjY(float th) {
        ry = th;
    }

    void rotateObjZ(float th) {
        rz = th;
    }
}
