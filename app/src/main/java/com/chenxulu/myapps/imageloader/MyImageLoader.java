package com.chenxulu.myapps.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

public class MyImageLoader {
    private static MyImageLoader instance;

    private MyImageLoader() {

    }

    public static MyImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new MyImageLoader();
                }
            }
        }
        return instance;
    }

    /**
     * @param uriStr
     * @param imageView
     */
    public void download(String uriStr, ImageView imageView) {
        download(uriStr, imageView, null, null, null);
    }

    /**
     * @param uriStr
     * @param imageView
     * @param builder
     * @param imageLoadingListener
     * @param progressListener
     */
    public void download(String uriStr, ImageView imageView, Builder builder,
                         MyImageLoadingListener imageLoadingListener,
                         MyImageLoadingProgressListener progressListener) {
        if (!TextUtils.isEmpty(uriStr)) {
            if (!uriStr.contains("http://") && !uriStr.contains("content://") &&
                    !uriStr.contains("assets://") && !uriStr.contains("drawable://")) {
                uriStr = "file:///" + uriStr;
            }
        }
        if (builder == null) {
            builder = getDefaultBuilder();
        }

        ImageLoader.getInstance().displayImage(uriStr, imageView, builder.build(),
                imageLoadingListener, progressListener);
    }

    public static DisplayImageOptions.Builder getDefaultBuilder() {
        return new DisplayImageOptions.Builder()
                // default false
                .resetViewBeforeLoading(false)
                // default true
                .cacheInMemory(true)
                // default true
                .cacheOnDisk(true)
                .preProcessor(null)
                .postProcessor(null)
                .extraForDownloader(null)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                // default
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                // default
                .displayer(new SimpleBitmapDisplayer())
                // default
                .handler(new Handler());
    }

    public void initImageLoader(Context context, String cachePath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        // default = device screen dimensions
        config.memoryCacheExtraOptions(480, 800);
        //
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        //
        config.taskExecutor(null);
        //
        config.taskExecutorForCachedImages(null);
        // default
        config.threadPoolSize(5);
        // default
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        // default
        config.tasksProcessingOrder(QueueProcessingType.FIFO);
        //
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        //
        config.denyCacheImageMultipleSizesInMemory();
        //
        config.diskCacheSize(50 * 1024 * 1024);
        //
        config.memoryCache(new LruMemoryCache(2 * 1024 * 1024));
        // default
        config.memoryCacheSizePercentage(13);
        // default
        config.imageDownloader(new BaseImageDownloader(context));
        // default
        config.defaultDisplayImageOptions(DisplayImageOptions.createSimple());
        ImageLoader.getInstance().init(config.build());
    }

    public void close() {
        ImageLoader.getInstance().clearMemoryCache();
    }

    public interface MyImageLoadingListener extends ImageLoadingListener {

    }

    public interface MyImageLoadingProgressListener extends ImageLoadingProgressListener {

    }
}
