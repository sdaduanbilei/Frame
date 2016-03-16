package com.sda.lib.view.pulltorefreshlib.smooth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sda.lib.R;

/**
 * Created by scorpio on 16/1/26.
 */
public class VoteView extends View {

    private Paint paint ;

    private int width ,height ;

    private int dashWidth = 50;

    private float max  ;
    private float progress  ;

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getProgress() {
        return progress;
    }

    public synchronized void setProgress(float progress) {
        if (progress > max){
            this.progress = max ;
        }else{
            this.progress = progress;
            postInvalidate();
        }

    }

    public VoteView(Context context) {
        super(context);
    }

    public VoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VoteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        paint =new Paint();


        // 画一个底部 柱状图
        paint.setColor(getResources().getColor(R.color.gray));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dashWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(width/2 ,0,width/2  ,height,paint);

        // 画一个进度
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dashWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(width/2   ,height -  (progress / max ) * height ,width/2  ,height,paint);

    }
}
