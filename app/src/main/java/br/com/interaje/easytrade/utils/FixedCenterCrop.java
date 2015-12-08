package br.com.interaje.easytrade.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by charles on 26/11/15.
 */
public class FixedCenterCrop extends ImageView {

    public FixedCenterCrop(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
    {
        final Drawable d = this.getDrawable();

        if(d != null) {
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int intrinsicHeight = 115;
            int intrinsicWidth = 204;

            if(width >= height)
                height = (int) Math.ceil(width * (float) intrinsicHeight / intrinsicWidth);
            else
                width = (int) Math.ceil(height * (float) intrinsicWidth / intrinsicHeight);

            this.setMeasuredDimension(width, height);

        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
