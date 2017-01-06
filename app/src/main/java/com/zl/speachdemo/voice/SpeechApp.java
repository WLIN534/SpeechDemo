package com.zl.speachdemo.voice;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;
import com.zl.speachdemo.R;

/**
 * 作者：zhanglin on 2017/1/6 13:42
 * 邮箱：weiwei_534@163.com
 */

public class SpeechApp extends Application {
    @Override
    public void onCreate() {
        SpeechUtility.createUtility(SpeechApp.this, "appid=" + getString(R.string.app_id));
        super.onCreate();
    }
}
