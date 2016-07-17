package com.bobomee.android.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import java.io.File;

/**
 * 综合工具
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public class Utility {

  public static void createDeskShortCut(Context cxt, String shortCutName, int icon, Class<?> cls) {
    Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    shortcutIntent.putExtra("duplicate", false);
    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
    Parcelable ico = Intent.ShortcutIconResource.fromContext(cxt.getApplicationContext(), icon);
    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);
    Intent intent = new Intent(cxt, cls);
    intent.setAction("android.intent.action.MAIN");
    intent.addCategory("android.intent.category.LAUNCHER");
    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
    cxt.sendBroadcast(shortcutIntent);
  }

  public static void createShortcut(Context ctx, String shortCutName, int iconId,
      Intent presentIntent) {
    Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    shortcutIntent.putExtra("duplicate", false);
    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutName);
    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
        Intent.ShortcutIconResource.fromContext(ctx, iconId));
    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, presentIntent);
    ctx.sendBroadcast(shortcutIntent);
  }

  public static void openImage(Context mContext, String imagePath) {
    Intent intent = new Intent("android.intent.action.VIEW");
    intent.addCategory("android.intent.category.DEFAULT");
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Uri uri = Uri.fromFile(new File(imagePath));
    intent.setDataAndType(uri, "image/*");
    mContext.startActivity(intent);
  }

  public static void openVideo(Context mContext, String videoPath) {
    Intent intent = new Intent("android.intent.action.VIEW");
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtra("oneshot", 0);
    intent.putExtra("configchange", 0);
    Uri uri = Uri.fromFile(new File(videoPath));
    intent.setDataAndType(uri, "video/*");
    mContext.startActivity(intent);
  }


}
