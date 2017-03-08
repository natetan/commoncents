package edu.washington.ischool.commoncents.commoncents.Helpers;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import edu.washington.ischool.commoncents.commoncents.Models.Friend;

/**
 * Created by keegomyneego on 3/6/17.
 */

public class ComponentHelper {

    //----------------------------------------------------------------------------------------------
    // Singleton Pattern
    //----------------------------------------------------------------------------------------------

    private static ComponentHelper instance;

    public static ComponentHelper getInstance() {
        if (instance == null) {
            instance = new ComponentHelper();
        }

        return instance;
    }

    private ComponentHelper() {}

    //----------------------------------------------------------------------------------------------
    // Public Helper Methods
    //----------------------------------------------------------------------------------------------

    /**
     * Generates a profile picture for the given friend and stores
     * it in the given image view.
     *
     * @param imageView ImageView to put the pic in
     * @param friend The Friend to generate the picture for
     */
    public void setProfilePicture(ImageView imageView, Friend friend) {

        // Generate arbitrary color based on friends name
        float h = (float)Math.abs(friend.getName().hashCode()) % 360;
        float s = 0.5f;
        float v = 0.8f;

        // Calculate the initials for this friends name
        String initials = friend.getName().substring(0, 2);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(initials, Color.HSVToColor(new float[]{ h, s, v }));

        imageView.setImageDrawable(drawable);
    }

    /**
     * Stylizes a string based on the amount of cents owed and puts it
     * in the given TextView
     * @param textView
     * @param centsOwed
     */
    public void setOweAmount(Context context, TextView textView, int centsOwed) {
        String amountOwed;

        if (centsOwed >= 0) {
            amountOwed = "$" + (centsOwed / 100) + "." + (centsOwed % 100);
            textView.setTextColor(ThemeHelper.getInstance(context).COLOR_AMOUNT_OWED_POSITIVE);
        } else {
            amountOwed = "($" + (-centsOwed / 100) + "." + (-centsOwed % 100) + ")";
            textView.setTextColor(ThemeHelper.getInstance(context).COLOR_AMOUNT_OWED_NEGATIVE);
        }

        textView.setText(amountOwed);
    }
}
