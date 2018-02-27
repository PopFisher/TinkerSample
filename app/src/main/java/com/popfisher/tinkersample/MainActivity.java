package com.popfisher.tinkersample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {

    private final static String TAG = "TinkerLog";
    private Button mButton;
    private Button mLoadPatchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_test);
        mLoadPatchButton = (Button) findViewById(R.id.btn_load_patch);
        mButton.setOnClickListener(this);
        mLoadPatchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btn_test:
                showToast();
                break;
            case R.id.btn_load_patch:
                loadSDCardPatch();
                break;
        }
    }

    private void showToast() {
        Toast.makeText(this, R.string.toast_text_base_apk, Toast.LENGTH_SHORT).show();
        // 把Toast弹出的文案修改掉
//        Toast.makeText(this, R.string.toast_text_patch_apk, Toast.LENGTH_SHORT).show();
    }

    private void loadSDCardPatch() {
        String patchPath  = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk";
        File file = new File(patchPath);
        if (file.exists()) {
            Log.v(TAG,"补丁文件存在 " + patchPath);
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchPath);
        } else {
            Log.v(TAG,"补丁文件不存在 " + patchPath);
        }
    }
}
