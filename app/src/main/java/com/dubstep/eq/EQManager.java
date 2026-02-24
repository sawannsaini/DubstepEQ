package com.dubstep.eq;

import android.content.Context;
import android.media.audiofx.Equalizer;

public class EQManager {

    private static Equalizer equalizer;

    public static void init(Context context) {
        if (equalizer != null) return;
        equalizer = new Equalizer(0, 0);
        equalizer.setEnabled(true);
        applyDefaultPreset();
    }

    private static void applyDefaultPreset() {
        short[] preset = {200, 300, 250, 0, 0, 100, 150, 200, 150, 100};
        short bands = equalizer.getNumberOfBands();
        for (short i = 0; i < bands && i < preset.length; i++) {
            equalizer.setBandLevel(i, preset[i]);
        }
    }
}
