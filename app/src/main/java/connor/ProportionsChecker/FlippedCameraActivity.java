package connor.proportionschecker;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.TextureView;
import android.view.WindowManager;
import android.widget.VideoView;
import android.hardware.Camera;

import java.io.IOException;

public class FlippedCameraActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private TextureView videoView;
    private static Camera camera = null;
    private boolean isPreviewRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipped_camera);

        videoView = findViewById(R.id.videoView);
        videoView.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        try {
            resetCamera();
            camera = Camera.open();
        }
        catch (Exception e)
        {
            Log.e("Checker", "Failed to open camera.");
            e.printStackTrace();
        }

        if (isPreviewRunning) {
            camera.stopPreview();
        }

        Camera.Parameters parameters = camera.getParameters();
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        if(display.getRotation() == Surface.ROTATION_0) {
            camera.setDisplayOrientation(90);
        }

        if(display.getRotation() == Surface.ROTATION_270) {
            camera.setDisplayOrientation(180);
        }

        camera.setParameters(parameters);
        previewCamera(surfaceTexture);
    }

    public void previewCamera(SurfaceTexture texture) {
        try{
            //camera.setPreview(videoView);
            camera.setPreviewTexture(texture);
            camera.startPreview();
            isPreviewRunning = true;
        } catch(Exception e) {
            Log.d("PreviewError", "Cannot start preview", e);
        }
    }
    private void resetCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        camera.stopPreview();
        camera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

}
