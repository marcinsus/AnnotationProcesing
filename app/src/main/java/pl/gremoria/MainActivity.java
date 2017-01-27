package pl.gremoria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.packageName.SampleServiceProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SampleServiceProvider.get();
    }
}
