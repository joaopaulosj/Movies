package com.joaopaulosj.movies.ui.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class DialogHelper extends BaseDialogHelper {
    public static AlertDialog createAlertDialog(Context context, String title, String msg, String buttonText) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage(msg).setTitle(title).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return alertDialogBuilder.create();
    }

    public static AlertDialog createAlertDialog(Context context, String title, String msg,
                                                String buttonText, final DialogInterface.OnClickListener positiveButtonListener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage(msg).setTitle(title)
                .setPositiveButton(buttonText, positiveButtonListener);
        return alertDialogBuilder.create();
    }

    public static AlertDialog createAlertDialog(Context context, String title, String msg,
                                                String positiveButtonText, final DialogInterface.OnClickListener positiveButtonListener,
                                                String negativeButtonText, final DialogInterface.OnClickListener negativeButtonListener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage(msg).setTitle(title)
                .setPositiveButton(positiveButtonText, positiveButtonListener)
                .setNegativeButton(negativeButtonText, negativeButtonListener);
        return alertDialogBuilder.create();
    }
}
