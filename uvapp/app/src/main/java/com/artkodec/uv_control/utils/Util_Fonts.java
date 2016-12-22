package com.artkodec.uv_control.utils;

import android.content.Context;
import android.graphics.Typeface;

public class Util_Fonts {
    public static Typeface setFontCursivaLight(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), "fonts/helveticalightoblique.otf");
    }

    public static Typeface setFontLight(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), "fonts/helveticalight.otf");
    }

    public static Typeface setFontNormal(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), "fonts/helvetica.otf");
    }

    public static Typeface setFontBold(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), "fonts/helveticacondensedbold.otf");
    }

}
//