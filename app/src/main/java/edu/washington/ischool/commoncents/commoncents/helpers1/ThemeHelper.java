package edu.washington.ischool.commoncents.commoncents.helpers1;

import android.content.Context;
import android.content.res.Resources;

import edu.washington.ischool.commoncents.commoncents.R;

/**
 * Created by keegomyneego on 3/6/17.
 */

public class ThemeHelper {

    //----------------------------------------------------------------------------------------------
    // Singleton Pattern
    //----------------------------------------------------------------------------------------------

    private static ThemeHelper instance;

    public static ThemeHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ThemeHelper(context);
        }

        return instance;
    }

    private ThemeHelper(Context context) {
        Resources resources = context.getResources();

        COLOR_AMOUNT_OWED_POSITIVE = resources.getColor(R.color.colorAmountOwedPositive);
        COLOR_AMOUNT_OWED_NEGATIVE = resources.getColor(R.color.colorAmountOwedNegative);

        COLOR_FOR_TAB_EVENTS = resources.getColor(R.color.colorMaterialUIBlue);
        COLOR_FOR_TAB_FRIENDS = resources.getColor(R.color.colorMaterialUILightBlue);
        COLOR_FOR_TAB_SETTINGS = resources.getColor(R.color.colorMaterialUICyan);

        COLOR_WHITE = resources.getColor(R.color.colorMaterialUIWhite);
    }

    //----------------------------------------------------------------------------------------------
    // Public Helper Methods
    //----------------------------------------------------------------------------------------------

    public final int COLOR_AMOUNT_OWED_POSITIVE;
    public final int COLOR_AMOUNT_OWED_NEGATIVE;

    public final int COLOR_FOR_TAB_EVENTS;
    public final int COLOR_FOR_TAB_FRIENDS;
    public final int COLOR_FOR_TAB_SETTINGS;

    public final int COLOR_WHITE;
}
