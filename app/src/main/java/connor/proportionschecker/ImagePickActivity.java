package connor.proportionschecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ImagePickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pick);

        final Button button = findViewById(R.id.quit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("Test", "test message");
            }
        });
    }
}
