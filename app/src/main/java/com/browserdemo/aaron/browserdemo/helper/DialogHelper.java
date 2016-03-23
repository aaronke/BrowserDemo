package com.browserdemo.aaron.browserdemo.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Aaronke on 3/22/2016.
 */
public class DialogHelper {

    public static void showOkDialog(Context context, String message, DialogInterface.OnClickListener callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", callback);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
