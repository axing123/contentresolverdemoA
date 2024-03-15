package com.example.democontentb.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void saveJsonToFile(Context context, String json, String fileName) throws IOException {
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(json.getBytes());
        outputStream.close();

    }
}
