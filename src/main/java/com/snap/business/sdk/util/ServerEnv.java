package com.snap.business.sdk.util;

public enum ServerEnv {
    PROD(0),
    STAGING(1);

    private final int index;

    ServerEnv(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
