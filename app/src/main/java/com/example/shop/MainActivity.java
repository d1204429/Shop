package com.example.shop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private boolean isTextView2Visible = false;
    private ImageView imageViewSwitcher;
    private int[] imageArray = {R.drawable.recycler1, R.drawable.recycler2};
    private int currentIndex = 0;
    private Handler handler = new Handler();
    private Runnable runnable;

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

        // 獲取 TextView 和 ImageView 控件
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        ImageView iconImageView = findViewById(R.id.icon_image);
        imageViewSwitcher = findViewById(R.id.imageView);

        // 設置點擊事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleTextViewVisibility(textView2);
            }
        });

        iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleTextViewVisibility(textView2);
            }
        });

        // 定義 Runnable 來自動切換圖片
        runnable = new Runnable() {
            @Override
            public void run() {
                // 設置圖片切換時間
                int delay = (currentIndex == 0) ? 5000 : 5000;

                // 切換到下一張圖片
                currentIndex = (currentIndex + 1) % imageArray.length;

                // 設置淡入淡出效果
                fadeOutAndInImage(imageArray[currentIndex]);

                // 設置延遲以實現定時切換
                handler.postDelayed(this, delay);
            }
        };

        // 開始圖片輪播
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 停止輪播以避免內存洩漏
        handler.removeCallbacks(runnable);
    }

    // 切換 textView2 的可見性
    private void toggleTextViewVisibility(TextView textView2) {
        if (isTextView2Visible) {
            textView2.setVisibility(View.GONE);
            isTextView2Visible = false;
        } else {
            textView2.setVisibility(View.VISIBLE);
            isTextView2Visible = true;
        }
    }

    // 設置淡入淡出效果
    private void fadeOutAndInImage(int newImageResId) {
        // 淡出動畫
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageViewSwitcher, "alpha", 1f, 0f);
        fadeOut.setDuration(500);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 設置新的圖片
                imageViewSwitcher.setImageResource(newImageResId);
                // 淡入動畫
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageViewSwitcher, "alpha", 0f, 1f);
                fadeIn.setDuration(500);
                fadeIn.start();
            }
        });
        fadeOut.start();
    }
}
