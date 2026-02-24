package com.dubstep.eq;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothReceiver extends BroadcastReceiver {

    public static final String ACTION_DEVICE_UPDATE =
            "com.dubstep.eq.DEVICE_UPDATE";

    private static final String TARGET_DEVICE = "Dubstep BX";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(intent.getAction())) {

            BluetoothDevice device =
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (device == null || device.getName() == null) return;

            String name = device.getName();

            // Update UI
            Intent uiIntent = new Intent(ACTION_DEVICE_UPDATE);
            uiIntent.putExtra("device_name", name);
            context.sendBroadcast(uiIntent);

            // 🔥 AUTO APPLY EQ ONLY FOR DUBSTEP BX
            if (TARGET_DEVICE.equals(name)) {
                EQManager.init(context);
            }
        }
    }
}
