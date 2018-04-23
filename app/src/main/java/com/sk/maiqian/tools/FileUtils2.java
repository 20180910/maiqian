package com.sk.maiqian.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by aspsine on 15-4-19.
 */
public class FileUtils2 {
    private static final String DOWNLOAD_DIR = "yangyudownload";

    public static final File getDownloadDir() {
        /*if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(context.getExternalCacheDir(), DOWNLOAD_DIR);
        }
        return new File(context.getCacheDir(), DOWNLOAD_DIR);*/
        File file = new File(Environment.getExternalStorageDirectory(), DOWNLOAD_DIR);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    public static final String getPrefix(@NonNull String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static final String getSuffix(@NonNull String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /*public static boolean deleteFile(String fileName) {
        File file = new File(getDownloadDir()+"/"+fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Log.i("===","删除单个文件" + fileName + "成功！");
                return true;
            } else {
                Log.i("===","删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            Log.i("===","文件" + fileName + "不存在！");
            return false;
        }
    }*/

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param file 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(File file) {
        if (!file.exists()) {
            Log.i("===","删除文件失败:" + file.getPath() + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(file.getPath());
            else
                return deleteDirectory(file.getPath());
        }
    }
    public static boolean delete(String fileName) {
        File file = new File(fileName);
       return delete(file);
    }
    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Log.i("===","删除单个文件" + fileName + "成功！");
                return true;
            } else {
                Log.i("===","删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            Log.i("===","删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            Log.i("===","删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            Log.i("===","删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            Log.i("===","删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }


    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static String getDownloadPerSize(long finished, long total) {
        return DF.format((float) finished / (1024 * 1024)) + "M/" + DF.format((float) total / (1024 * 1024)) + "M";
    }

    public static void installApp(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /*public static void unInstallApp(Context context, String packageName) {
        Uri packageUri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, packageUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }*/

    public static String getApkFilePackage(Context context, File apkFile) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkFile.getPath(), PackageManager.GET_ACTIVITIES);
        if (info != null){
            return info.applicationInfo.packageName;
        }
        return null;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        if (!com.aspsine.multithreaddownload.util.ListUtils.isEmpty(packages)) {
            for (PackageInfo packageInfo : packages) {
                if (packageInfo.packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }


}
