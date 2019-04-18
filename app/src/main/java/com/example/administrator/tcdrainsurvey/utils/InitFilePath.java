package com.example.administrator.tcdrainsurvey.utils;

/**
 * @author HaiRun
 * @time 2019/4/18.9:10
 */

import android.util.Log;

import java.io.File;

/**
 * 初始化一些文件夹
 */
public class InitFilePath {

    public static final String SD = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String PRJ_PATH = SD + "/TC排水口调查";
    public static final String PRJ_PATH_PIC = PRJ_PATH + "/照片/";

    public static void initFolders() {
        String folders[] = new String[]{PRJ_PATH, PRJ_PATH_PIC};
        for (String _folder : folders) {
            FileUtils.getInstance().mkdirs(_folder);
        }
    }
}
