package edu.washington.ischool.commoncents.commoncents.models1;

import java.util.UUID;

/**
 * Created by keegomyneego on 3/8/17.
 */

public class Indexable {

    public String getIndexKey() {
        if (indexKey == null) {
            indexKey = UUID.randomUUID().toString();
        }

        return indexKey;
    }

    public void setIndexKey(String indexKey) {
        this.indexKey = indexKey;
    }

    private String indexKey;
}
