package com.example.democontentb;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ReadData {
    private static final String TAG = "ReadData";
    private static final String AUTHORITY = "com.demoa.mservicecontentprovider";
    private static final String ASSET_JSON_FILE = "/service_definition";
    private static final Uri ASSET_CONTENT_URI = Uri.parse("content://" + AUTHORITY + ASSET_JSON_FILE);

    public void getAssetFiledataFromA(ContentResolver contentResolver) {
        AssetFileDescriptor fileDescriptor = null;
        FileInputStream fis = null;
        InputStream inputStream = null;
        try {
            Log.d(TAG, "getAssetFiledataFromA: 打开文件");
            fileDescriptor = contentResolver.openAssetFileDescriptor(ASSET_CONTENT_URI, "r");
            Log.d(TAG, "getAssetFiledataFromA: 读取数据");
            fis = new FileInputStream(fileDescriptor.getFileDescriptor());
            inputStream = fileDescriptor.createInputStream();
            if (fileDescriptor != null) {
                // 读取文件数据
              /*  BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fis));
                StringBuilder stringBuilder=new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();*/
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                String json = new String(buffer, StandardCharsets.UTF_8);
               /* JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonServices = jsonObject.getJSONArray("services");*/
                //  String stringJson=stringBuilder.toString();
                Log.d(TAG, "getAssetFiledataFromA: " + json);

                // 解析 JSON 数据并进行格式检查
                checkJsonString(json);
                // 保存json文件到本地

                fis.close();
                fileDescriptor.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //check json数据
    public void checkJsonString(String stringJson) {
        if (stringJson != null) {
            try {
                JSONObject jsonObject = new JSONObject(stringJson);
                JSONArray jsonServices = jsonObject.getJSONArray("services");
                for (int i = 0; i < jsonServices.length(); i++) {
                    JSONObject jsonService = jsonServices.getJSONObject(i);
                    String name = jsonService.getString("name");
                    String id = jsonService.getString("id");
                    String type = jsonService.getString("type");
                    String desc = jsonService.getString("desc");
                    boolean enable = jsonService.getBoolean("enable");
                    Log.d(TAG, "服务json" + name + id + desc);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
