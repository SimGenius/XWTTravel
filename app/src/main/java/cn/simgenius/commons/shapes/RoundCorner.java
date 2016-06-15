package cn.simgenius.commons.shapes;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;

/**
 * Created by Genius on 4/19/16.
 */
public class RoundCorner extends StateListDrawable {

    float density;
    int normalColor;
    int pressedColor;
    float radiusDp;

    public RoundCorner(int normalColor, int pressedColor, float radiusDp, float density) {
        super();
        this.density = density;
        this.normalColor = normalColor;
        this.pressedColor = pressedColor;
        this.radiusDp = radiusDp;
    }

    public RoundCorner(String normalColorString, String pressedColorString, float radiusDp, float density) {
        super();
        this.density = density;
        this.normalColor = Color.parseColor(normalColorString);
        this.pressedColor = Color.parseColor(pressedColorString);
        this.radiusDp = radiusDp;
    }

    public Drawable getDrawable(){
        addState(new int[]{android.R.attr.state_pressed},new RoundCornerDrawable(pressedColor,radiusDp*density));
        addState(new int[0],new RoundCornerDrawable(normalColor,radiusDp*density));
        return this;
    }
}
