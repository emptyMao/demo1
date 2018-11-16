package com.empty.gradientview.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint({"NewApi", "DrawAllocation"})
public class GradientVIew extends View {
    public static final int ColorCount = 10; //两个baseColor之间分多少个色
    public static final String colorBase[] = { "#A37CED", "#25A6EB", "#69A0ED", "#e5824b", "#dd7474", "#cf5a8f","#D443A4", "#F13CAF"};
    private int colors[];
    private int ColorTargetIndex = 0;// 当前色组标号
    private boolean AUTO_START;

    public GradientVIew(Context context) {
        super(context);
        requestLayout();
        initColors();
    }

    public GradientVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        requestLayout();
        initColors();
    }

    public GradientVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        requestLayout();
        initColors();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void autoStart() {
        AUTO_START = false;
        AUTO_START = true;
        showView();
    }

    public void ManualautoStart() {
        ManualautoshowView();
    }

    private void initColors() {
        colors = ColorUtils.initColors();
    }

    public void autoStop() {
        AUTO_START = false;
    }

    public void ManuaautoStop() {
        AUTO_START = false;
    }

    public void change() {
        changeColor();
    }

    private void ManualautoshowView() {
        new Thread() {
            public void run() {
                changeColor();
            }
        }.start();
    }
    private void showView() {
        new Thread() {
            public void run() {
                while (AUTO_START) {
                    changeColor();
                    try {
                        if (ColorTargetIndex % colors.length == 0) {
                            AUTO_START = false;
                        }
                        sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void changeColor() {
        ColorTargetIndex++;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取View的宽高
        int width = getWidth();
        int height = getHeight();
        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(0, 0, width,
                width, putColor(), null, TileMode.CLAMP);
        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);
    }

    private int[] putColor() {
        int curr_colors[] = new int[20];
        for (int i = 0; i < curr_colors.length; i++) {
            curr_colors[curr_colors.length - 1 - i] = colors[(ColorTargetIndex + i) % colors.length];
        }
        return curr_colors;
    }
}
