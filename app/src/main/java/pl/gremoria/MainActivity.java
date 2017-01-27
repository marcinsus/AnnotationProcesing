package pl.gremoria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.annotation.CustomAnnotation;

public class MainActivity extends AppCompatActivity {

    @CustomAnnotation
    int exampleField;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
