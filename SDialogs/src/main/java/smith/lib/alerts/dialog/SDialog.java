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

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ImageView;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import smith.lib.views.recyclerview.SRecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.andrognito.patternlockview.PatternLockView;
import smith.lib.alerts.dialog.callbacks.OnBindCustomViewCallBack;
import smith.lib.alerts.dialog.callbacks.OnDismissCallBack;

public class SDialog {
    
    public static final int COLOR_DEFAULT = 0xFFA7B4C5;
    
    public static final int THEME_BY_SYSTEM = 0;
    public static final int THEME_DARK = 1;
    public static final int THEME_LIGHT = 2;
    
    public static final String KEY_ITEM_ID = "id";
    public static final String KEY_ITEM_TEXT = "text";
    public static final String KEY_ITEM_CHECKED = "checked";
    
    public static final int PATTERN_MODE_CORRECT  = PatternLockView.PatternViewMode.CORRECT;
    public static final int PATTERN_MODE_WRONG  = PatternLockView.PatternViewMode.WRONG;
    
    protected Context context;
    protected View dialogView;
    protected AlertDialog alertdialog;
    protected DialogBinding b;
    
    protected int iconColor;
    protected int titleColor;
    protected int textColor;
    protected int accentColor = COLOR_DEFAULT;
    protected int backgroundColor;
    protected int theme = THEME_BY_SYSTEM;
    protected int hintColor;
    
    protected void init() {
        alertdialog = new AlertDialog.Builder(context).create();
        alertdialog.setView(dialogView);
        alertdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        b = new DialogBinding(dialogView);
    }
    
    public void setCancelable(boolean cancelable) {
        alertdialog.setCancelable(cancelable);
    }
    
    public void setOnDismissCallBack(OnDismissCallBack callback) {
        alertdialog.setOnDismissListener(dialogInterface -> callback.onDismiss());
    }
    
    public void show() {
        animateView(dialogView);
        alertdialog.show();
    }
    
    public void show(long dur) {
        animateView(dialogView);
        alertdialog.show();
        new CountDownTimer(dur, 1) {
            @Override public void onTick(long duration) {}
            @Override public void onFinish() { dismiss(); }
        }.start();
    }
    
    public void dismiss() {
        alertdialog.dismiss();
    }
    
    protected void lightThemeColors() {
        iconColor = darkerColor(accentColor, .2f);
        titleColor = darkerColor(accentColor, .2f);
        textColor = darkerColor(accentColor, .35f);
        backgroundColor = lighterColor(accentColor, .9f);
        hintColor = darkerColor(accentColor, .6f);
        accentColor = darkerColor(accentColor, .9f);
    }

    protected void darkThemeColors() {
        iconColor = lighterColor(accentColor, .8f);
        titleColor = lighterColor(accentColor, .8f);
        textColor = lighterColor(accentColor, .65f);
        backgroundColor = darkerColor(accentColor, .1f);
        hintColor = lighterColor(accentColor, .4f);
        accentColor = lighterColor(accentColor, .1f);
    }
    
    protected void animateView(View v) {
        v.setAlpha(0f);
        ObjectAnimator anim = new ObjectAnimator();
        anim.setDuration(620);
        anim.setFloatValues(0f, 1f);
        anim.setPropertyName("alpha");
        anim.setTarget(v);
        anim.start();
    }

    protected boolean nightModeON() {
        int flags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isNightModeOn = flags == Configuration.UI_MODE_NIGHT_YES;
        if (isNightModeOn) return true;
        else return false;
    }

    protected void setBackgroundColor(View view, int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(80f);
        view.setBackground(gradientDrawable);
    }

    protected int darkerColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha,
                Math.max((int)(red * factor), 0),
                Math.max((int)(green * factor), 0),
                Math.max((int)(blue * factor), 0));
    }

    protected int lighterColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(alpha, red, green, blue);
    }

    protected int oppositeColor(int color) {
        float[] hsv = new float[3];
        Color.RGBToHSV(Color.red(color),
                Color.green(color),
                Color.blue(color), hsv);
        hsv[0] = (hsv[0] + 180) % 360;
        return Color.HSVToColor(hsv);
    }
    
    protected int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        int px = Math.round(dp * density);
        return px;
    }
    
    protected class DialogBinding {
        
        LinearLayout main, holder;
        ImageView icon;
        TextView title, text, positive, negative, neutral, percent;
        SRecyclerView recycler;
        TextInputLayout input;
        TextInputEditText inputed;
        ProgressBar loading, progress;
        PatternLockView pattern;
        Slider seek;
        
        public DialogBinding(View view) {
            main = view.findViewById(R.id.main);
            holder = view.findViewById(R.id.holder);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
            text = view.findViewById(R.id.text);
            positive = view.findViewById(R.id.positive);
            negative = view.findViewById(R.id.negative);
            neutral = view.findViewById(R.id.percent);
            percent = view.findViewById(R.id.neutral);
            recycler = view.findViewById(R.id.recycler);
            input = view.findViewById(R.id.input);
            inputed = view.findViewById(R.id.inputed);
            loading = view.findViewById(R.id.loading);
            pattern = view.findViewById(R.id.pattern);
            progress = view.findViewById(R.id.progress);
            seek = view.findViewById(R.id.seek);
        }
    }
}
