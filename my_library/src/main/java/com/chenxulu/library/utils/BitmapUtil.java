package com.chenxulu.library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {
    public static final int MIN = 640;

    /**
     * 640 scale
     *
     * @param filePath
     * @param resultPath
     */
    public static void scalePhoto(String filePath, String resultPath) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = getSampleSize(options, MIN);
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        if (bitmap != null) {
            saveBitmap(bitmap, resultPath);
            bitmap.recycle();
        }
    }

    /**
     * the scale's big size
     *
     * @param options
     * @return
     */
    private static int getSampleSize(Options options, int minSize) {
        int sampleSize = 1;
        int width = options.outWidth;
        int height = options.outHeight;
        if (width <= minSize || height <= minSize) {
            sampleSize = 1;
        }
        if (width >= height) {
            sampleSize = Math.round((float) height / (float) minSize);
        } else {
            sampleSize = Math.round((float) width / (float) minSize);
        }
        return sampleSize;
    }

    /**
     * save bitmap to this file path
     *
     * @param bitmap
     * @param filePath
     */
    public static void saveBitmap(Bitmap bitmap, String filePath) {
        FileOutputStream outputStream = null;
        try {
            File file = new File(filePath);
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
