package com.joaopaulosj.movies.ui.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.joaopaulosj.movies.R;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class BaseDialogHelper {

    public static ProgressDialog createProgressDialog(Context context) {
        return createProgressDialog(context, context.getString(R.string.progress_dialog_standard_msg));
    }

    public static ProgressDialog createProgressDialog(Context context, String msg) {
        ProgressDialog dialog = buildProgressDialog(context);
        dialog.setMessage(msg);
        return dialog;
    }

    private static ProgressDialog buildProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static AlertDialog createDisclaimerDialog(Context context, String title, String msg, String positiveText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage(msg).setTitle(title).setPositiveButton(positiveText, listener).setCancelable(false);
        return alertDialogBuilder.create();
    }

    public static AlertDialog createAlertDialog(Context context, String msg, String positiveText, String negativeText, DialogInterface.OnClickListener positiveButtonListener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage(msg).setPositiveButton(positiveText, positiveButtonListener)
                .setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return alertDialogBuilder.create();
    }

    public static AlertDialog createAlertDialog(Context context, String msg, String positiveText, String negativeText, DialogInterface.OnClickListener postiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage(msg).setPositiveButton(positiveText, postiveButtonListener)
                .setNegativeButton(negativeText, negativeButtonListener);
        return alertDialogBuilder.create();
    }

    public static AlertDialog createSimpleDialog(Context context, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage(msg)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return alertDialogBuilder.create();
    }

}
