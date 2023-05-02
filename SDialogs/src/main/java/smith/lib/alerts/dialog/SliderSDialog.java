    /*
     *
     *
     *    THIS LIBRARY CREATED BY HUSSEIN SHAKIR (SMITH)
     *
     *	TELEGRAM : @SMITHDEV
     *	YOUTUBE : HUSSEIN SMITH (@SMITH8H)
     *
     *	YOU GUYS ARE NOT ALLOWED TO MODIFY THIS LIBRARY,
     *	WITHOT ANY PERMISSION FROM ME PERSONALLY..
     *	ALL RIGHTS RESERVED © HUSSEIN SHAKIR, Dec 2022.
     *
     *
     */
     

package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.slider.Slider;
import android.graphics.*;
import smith.lib.alerts.dialog.callbacks.OnSlideCallBack;

public class SliderSDialog extends SDialog {
    
    private final int MAX = 100;
    private final int MIN = 0;
    
    public SliderSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_seek, null);
        init();
        
        b.seek.setValueFrom(MIN);
        b.seek.setValueTo(MAX);
        
        int height = b.seek.getTrackHeight();
        b.seek.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override public void onStartTrackingTouch(Slider slider) { slider.setTrackHeight(50); }
            @Override public void onStopTrackingTouch(Slider slider) { slider.setTrackHeight(height); }
        });
    }
    
    public void setIconResource(int icon) {
        b.icon.setVisibility(View.VISIBLE);
    	b.icon.setImageResource(icon);
    }
    
    public void setIconDrawable(Drawable icon) {
        b.icon.setVisibility(View.VISIBLE);
    	b.icon.setImageDrawable(icon);
    }
    
    public void setIconBitmap(Bitmap icon) {
        b.icon.setVisibility(View.VISIBLE);
    	b.icon.setImageBitmap(icon);
    }

    public void setTitle(String title) {
        b.title.setText(title);
    }

    public void setText(String text) {
        b.text.setVisibility(View.VISIBLE);
        b.text.setText(text);
    }
    
    public void setText(int text) {
        b.text.setText(text);
    }
    
    public void setMin(float min) {
        b.seek.setValueFrom(min);
        b.seek.setValue(min);
    }
    
    public void setMax(float max) {
        b.seek.setValueTo(max);
    }
    
    public void setValue(float value) {
        b.seek.setValue(value);
    }
    
    public void setStepBy(float stepBy) {
    	b.seek.setStepSize(stepBy);
    }
    
    public void setPositiveButtonAction(String positive, OnSlideCallBack callback) {
        b.positive.setText(positive);
        b.positive.setOnClickListener(v-> {
            callback.onValueSelected(b.seek.getValue());
            dismiss();
        });
    }
    
    public void setNegativeButtonText(String negative) {
        b.negative.setText(negative);
        b.negative.setOnClickListener(v-> dismiss());
    }
    
    public void setAccentColor(int color) {
        accentColor = color;
    }

    public void setAccentColor(String color) {
        accentColor = Color.parseColor(color);
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
    
    public float getValue() {
        return b.seek.getValue();
    }
    
    public float getMinValue() {
        return b.seek.getValueFrom();
    }
    
    public float getMaxValue() {
        return b.seek.getValueTo();
    }
    
    public int getAccentColor() {
        return accentColor;
    }
    
    public int getTitleColor() {
        return titleColor;
    }
    
    public int getBackgroundColor() {
        return backgroundColor;
    }
    
    public int getTextColor() {
        return textColor;
    }
    
    @Override
    public void show() {
        update();
        super.show();
    }
    
    @Override
    public void show(long dur) {
        update();
        super.show();
    }
    
    private void update() {
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();

        setBackgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.positive.setTextColor(accentColor);
        b.negative.setTextColor(accentColor);
        
        int[][] states = new int[][] {
            new int[] { android.R.attr.state_active },
            new int[] { android.R.attr.state_hovered },
            new int[] { android.R.attr.state_enabled },
            new int[] { -android.R.attr.state_active }
        };
        int[] colors = new int[] {
            accentColor,
            accentColor,
            accentColor,
            hintColor
        };
        
        b.seek.setThumbTintList(ColorStateList.valueOf(accentColor));
        b.seek.setTrackTintList(new ColorStateList(states, colors));
    }
}
