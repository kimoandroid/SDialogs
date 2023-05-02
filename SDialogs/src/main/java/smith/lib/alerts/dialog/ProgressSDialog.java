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
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import smith.lib.alerts.dialog.callbacks.OnProgressCallBack;

public class ProgressSDialog extends SDialog {
    
    private OnProgressCallBack callback;
    private final int MAX = 100;
    private final int MIN = 0;
    
    public ProgressSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_progress, null);
        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            b.progress.setMin(MIN);
        }
        b.progress.setMax(MAX);
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
        b.text.setText(text);
    }
    
    public void setText(int text) {
        b.text.setText(text);
    }
    
    public void setMin(int min) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            b.progress.setMin(min);
        }
    }
    
    public void setMax(int max) {
        b.progress.setMax(max);
    }
    
    public void setProgress(int progress) {
        b.progress.setProgress(progress);
        
        setProgressText(getProgress());
        
        if (getProgress() == getMax()) {
            dismiss();
            if (callback != null) callback.onFinish();
        }
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
    
    private void setProgressText(int progress) {
        int percent = (getProgress() * 100) / getMax();
        String info = getProgress() + "/" + getMax() + " (" + percent + "%)";
        b.percent.setText(info);
        if (callback != null) callback.onProgress(getProgress(), percent);
    }
    
    public void setOnProgressCallBack(OnProgressCallBack callback) {
        this.callback = callback;
    }
    
    public int getProgress() {
        return b.progress.getProgress();
    }
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getMin() {
        return b.progress.getMin();
    }
    
    public int getMax() {
        return b.progress.getMax();
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
        setCancelable(false);
        update();
        super.show();
    }
    
    @Override
    public void show(long dur) {
        setCancelable(false);
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
        b.percent.setTextColor(textColor);
        b.progress.setProgressTintList(ColorStateList.valueOf(accentColor));
    }
}
