package com.example.myapplication.until;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

public class DataClearer {

    private Context context;

    public DataClearer(Context context) {
        this.context = context;
    }

    public void clearAllData() {
        clearSharedPreferences();
        clearCache();
        clearDatabases();
        clearFiles();
    }

    private void clearSharedPreferences() {
        SharedPreferences preferences = context.getSharedPreferences("your_pref_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    private void clearCache() {
        try {
            File cacheDir = context.getCacheDir();
            if (cacheDir != null && cacheDir.isDirectory()) {
                deleteDir(cacheDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearDatabases() {
        String[] databases = context.databaseList();
        for (String database : databases) {
            context.deleteDatabase(database);
        }
    }

    private void clearFiles() {
        File filesDir = context.getFilesDir();
        if (filesDir != null && filesDir.isDirectory()) {
            deleteDir(filesDir);
        }
    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}