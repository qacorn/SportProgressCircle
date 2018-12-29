package com.qacorn.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.qacorn.R;

public class SportProgressCircle extends View {

    //circle center (240,260)   radius 150
    private int centerX = 240, centerY = 240, wheelRadius = 160,outestRadius = 205;
    private int runningProgress = 20;
    private Paint paint;
    private RectF rect3,rect4;
    private Bitmap runningPeople = null;
    private Context context;
    public SportProgressCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public SportProgressCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SportProgressCircle(Context context) {
        super(context);
        this.context = context;
        init();
    }


    private void init() {
        SharedPreferences sportProgressCircle = context.getSharedPreferences("SportProgressCircle", Context.MODE_PRIVATE);
        int displayWidth = sportProgressCircle.getInt("DISPLAY_WIDTH", 480);
//        int displayWidth = 480*2;
        centerX = displayWidth/2;
        paint = new Paint();
        paint.setAntiAlias(true);
        rect3 = new RectF(centerX-wheelRadius, centerY-wheelRadius, centerX+wheelRadius, centerY+wheelRadius);
        rect4 = new RectF(centerX-outestRadius, centerY-outestRadius, centerX+outestRadius, centerY+outestRadius);
        runningPeople = ((BitmapDrawable)getResources().getDrawable(R.mipmap.running_people)).getBitmap();
    }



    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        paint.setColor(0x80FFFFFF);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
        float itemDegree = 0.5f;
        float gapDegree = 1.5f;
        int number = (int) (360 / (itemDegree+gapDegree));
        for (int i = 0; i < number; i++) {
            canvas.drawArc(rect3, 270 + i * (itemDegree+gapDegree) , itemDegree, false, paint);
        }
        paint.setStrokeWidth(2);
        canvas.drawArc(rect4, 279, 341, false, paint);

        canvas.drawBitmap(runningPeople, centerX-15,0, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(40);
        int runnedNumber = Math.round(runningProgress*number/100f);
        for (int i = 0; i < runnedNumber; i++) {
            canvas.drawArc(rect3, 270 + i * (itemDegree+gapDegree), itemDegree, false, paint);
        }
    }
}
