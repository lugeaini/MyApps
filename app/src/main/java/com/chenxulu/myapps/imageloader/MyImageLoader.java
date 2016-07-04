package com.chenxulu.myapps.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import okhttp3.OkHttpClient;

public class MyImageLoader {
    private static MyImageLoader instance;
    private OkHttpClient okHttpClient;

    private MyImageLoader() {
        okHttpClient = new OkHttpClient();
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
     * 下载图片
     *
     * @param uriStr
     * @param imageView
     */
    public void download(ImageView imageView, String uriStr) {
        download(imageView, uriStr, null);
    }

    /**
     * 下载图片
     *
     * @param uriStr
     * @param imageView
     * @param options
     */
    public void download(ImageView imageView, String uriStr, MyImageOptions options) {
        download(imageView, uriStr, options, null, null);
    }

    /**
     * 下载图片
     *
     * @param uriStr
     * @param imageView
     * @param options
     * @param imageLoadingListener
     * @param progressListener
     */
    public void download(ImageView imageView, String uriStr, MyImageOptions options,
                         MyImageLoadingListener imageLoadingListener,
                         MyImageLoadingProgressListener progressListener) {
        if (!TextUtils.isEmpty(uriStr)) {
            if (!uriStr.contains("http://") && !uriStr.contains("https://") && !uriStr.contains("content://") &&
                    !uriStr.contains("assets://") && !uriStr.contains("drawable://")) {
                uriStr = "file:///" + uriStr;
            }
        }

        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                // default false
                .resetViewBeforeLoading(false)
                // default true
                .cacheInMemory(true)
                // default true
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                // default
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                // default
                .displayer(new SimpleBitmapDisplayer())
                // default
                .handler(new Handler());

        if (options != null) {
            builder.showImageForEmptyUri(options.getImageResForEmptyUri());
            builder.showImageOnLoading(options.getImageResOnLoading());
            builder.showImageOnFail(options.getImageResOnFail());
            builder.displayer(options.isRounded() ? new RoundedBitmapDisplayer(2) : new SimpleBitmapDisplayer());
        }

        ImageLoader.getInstance().displayImage(uriStr, imageView, builder.build(),
                imageLoadingListener, progressListener);
    }

    /**
     * init image loader
     *
     * @param context
     * @param cachePath
     */
    public void initImageLoader(Context context, File cachePath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        // default = device screen dimensions
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        config.memoryCacheExtraOptions(width, height);
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
        config.diskCacheSize(100 * 1024 * 1024);
        //
        config.diskCache(new UnlimitedDiskCache(cachePath, StorageUtils.getCacheDirectory(context)));
        //
        config.memoryCache(new LruMemoryCache(10 * 1024 * 1024));
        // default
        config.memoryCacheSizePercentage(13);
        // default
        config.imageDownloader(new OkHttpImageDownloader(context, okHttpClient));
        // default
        config.defaultDisplayImageOptions(DisplayImageOptions.createSimple());
        ImageLoader.getInstance().init(config.build());
    }

    /**
     * 关闭，并清理内存
     */
    public void close() {
        ImageLoader.getInstance().clearMemoryCache();
    }
}
