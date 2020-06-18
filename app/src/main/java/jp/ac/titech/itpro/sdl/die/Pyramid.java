package jp.ac.titech.itpro.sdl.die;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Pyramid implements Obj {

    private final static float[] VERTICES = {
            // bottom
            -1, 0, -1,
            1, 0, -1,
            -1, 0, 1,
            1, 0, 1,
            // left
            -1, 0, -1,
            0, 1, 0,
            -1, 0, 1,
            // right
            1, 0, -1,
            0, 1, 0,
            1, 0, 1,
            // back
            -1, 0, -1,
            0, 1, 0,
            1, 0, -1,
            // front
            -1, 0, 1,
            0, 1, 0,
            1, 0, 1
    };

    private final FloatBuffer vbuf;

    Pyramid() {
        vbuf = ByteBuffer
                .allocateDirect(VERTICES.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vbuf.put(VERTICES);
        vbuf.position(0);
    }


    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // left
        gl.glNormal3f(-1, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 4, 3);

        // right
        gl.glNormal3f(1, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 7, 3);

        // back
        gl.glNormal3f(0, 1, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 10, 3);

        // front
        gl.glNormal3f(0, 1, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 13, 3);
    }
}
