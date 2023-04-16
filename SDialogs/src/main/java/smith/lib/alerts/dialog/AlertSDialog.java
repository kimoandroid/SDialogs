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
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import smith.lib.alerts.dialog.callbacks.OnClickCallBack;

public class AlertSDialog extends SDialog {

    public AlertSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_alert, null);
        init();
    }
    
    public void setIconResource(int icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageResource(icon);
    }
    
    public void setIconDrawable(Drawable icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageDrawable(icon);
    }
    
    public void setIconBitmap(Bitmap icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageBitmap(icon);
    }

    public void setTitle(String title) {
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
    }

    public void setText(CharSequence text) {
        ((TextView) dialogView.findViewById(R.id.text)).setText(text);
    }

    public void setPositiveButton(String positive, OnClickCallBack callback) {
        ((LinearLayout) dialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.positive)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.positive)).setText(positive);
        ((TextView) dialogView.findViewById(R.id.positive)).setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    public void setNegativeButton(String negative, OnClickCallBack callback) {
        ((LinearLayout) dialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.negative)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.negative)).setText(negative);
        ((TextView) dialogView.findViewById(R.id.negative)).setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    public void setNeutralButton(String neutral, OnClickCallBack callback) {
        ((LinearLayout) dialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.neutral)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.neutral)).setText(neutral);
        ((TextView) dialogView.findViewById(R.id.neutral)).setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    public void setAccentColor(int color) {
        accentColor = color;
    }

    public void setAccentColor(String color) {
        accentColor = Color.parseColor(color);
    }

    public void setTheme(int theme) {
        super.theme = theme;
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
        super.show(dur);
    }
    
    private void update() {
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();
        
        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((ImageView) dialogView.findViewById(R.id.icon)).setColorFilter(iconColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        ((TextView) dialogView.findViewById(R.id.text)).setTextColor(textColor);
        ((TextView) dialogView.findViewById(R.id.positive)).setTextColor(accentColor);
        ((TextView) dialogView.findViewById(R.id.negative)).setTextColor(accentColor);
        ((TextView) dialogView.findViewById(R.id.neutral)).setTextColor(accentColor);
    }
}
