package com.empty.gradientview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.empty.gradientview.widgets.GradientVIew;

public class MainActivity extends AppCompatActivity {
    GradientVIew gradual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gradual = findViewById(R.id.gradual);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gradual.autoStart();
            }
        });
    }
}
