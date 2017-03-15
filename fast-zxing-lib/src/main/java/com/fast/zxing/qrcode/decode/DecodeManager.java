package com.fast.zxing.qrcode.decode;

import com.google.zxing.client.android.R;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 二维码解析管理。
 */
public class DecodeManager {

    public void showPermissionDeniedDialog(Context context) {
        // 权限在安装时被关闭了，如小米手机
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.qr_code_notification);
        builder.setMessage(R.string.qr_code_camera_not_open);
        builder.setPositiveButton(R.string.qr_code_positive_button_know, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void showResultDialog(Activity activity, String resultString, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.qr_code_notification);
        builder.setMessage(resultString);
        builder.setPositiveButton(R.string.qr_code_positive_button_confirm, listener).show();
    }

    public void showCouldNotReadQrCodeFromScanner(Context context, final OnRefreshCameraListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.qr_code_notification);
        builder.setMessage(R.string.qr_code_could_not_read_qr_code_from_scanner).setPositiveButton(
                R.string.qc_code_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (listener != null) {
                            listener.refresh();
                        }
                    }
                }).show();
    }

    public void showCouldNotReadQrCodeFromPicture(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.qr_code_notification);
        builder.setMessage(R.string.qr_code_could_not_read_qr_code_from_picture).setPositiveButton(
                R.string.qc_code_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public interface OnRefreshCameraListener {
        void refresh();
    }

}
