package com.example.lavanduc.rxjava.file;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Customview extends View {

    private Rect rect;
    public Customview(Context context) {
        super(context);
    }

    public Customview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Customview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect=new Rect(20,20,300,200);
        Paint paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawColor(Color.RED);
        canvas.drawCircle(200,100,100,paint);

    }
}
