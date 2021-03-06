package com.example.administrator.tcdrainsurvey.utils;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * @Author HaiRun
 * @Time 2019/4/9.9:27
 */

public class InitWindowSize {
    private static InitWindowSize  m_instance  = null;

    public synchronized static InitWindowSize ins() {
        if (m_instance == null) {
            m_instance = new InitWindowSize();
        }
        return m_instance;
    }

    public  void initWindowSize(Activity context, Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        windowParams.y = 100;
        window.setAttributes(windowParams);
        Dialog _dialog =dialog;
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.98), (int) (dm.heightPixels * 0.94));
        }
    }
}
