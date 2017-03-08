package edu.washington.ischool.commoncents.commoncents.models;

import com.google.firebase.database.Exclude;

/**
 * Created by keegomyneego on 3/8/17.
 */

public interface Indexable {

    @Exclude
    String getKey();
}
