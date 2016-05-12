package com.sda.lib.util;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by scorpio on 16/5/9.
 */
public class FixUtil {

    public static PatchManager patchManager;
    static Context context;

    public static void init(Context c) {
        patchManager = new PatchManager(c);
        patchManager.init(Tools.currentVersion(c));
        context = c;
        loadPatch();
    }

    /**
     * 加载.patch
     */
    private static void loadPatch() {
        try {
            Logs.Debug("File>>>>>" + FileUtil.appSavePath());
            if (new File(FileUtil.appSavePath() + "test.apatch").exists()) {
                patchManager.loadPatch();
                patchManager.addPatch(FileUtil.appSavePath() + "test.apatch");
            }else{
                Logs.Debug("files no exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Logs.Debug("error>>>>" + e.toString());
        }
    }


}
