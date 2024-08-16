package com.example.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean isTextView2Visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 設置邊緣到邊緣佈局
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 獲取 TextView 控件
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        ImageView imageView = findViewById(R.id.icon_image);

        // 設置點擊事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTextView2Visible) {
                    textView2.setVisibility(View.GONE);
                    isTextView2Visible = false;
                } else {
                    textView2.setVisibility(View.VISIBLE);
                    isTextView2Visible = true;
                }
            }

        });

        // icon_image設置點擊事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTextView2Visible) {
                    textView2.setVisibility(View.GONE);
                    isTextView2Visible = false;
                } else {
                    textView2.setVisibility(View.VISIBLE);
                    isTextView2Visible = true;
                }
            }
        });
    }
}
