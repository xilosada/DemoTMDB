package com.xilosada.demotmdb;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by xilosada on 28/11/16.
 */

public class ImageLoader {

    public static final String TMDB_IMAGE_BUCKET = "https://image.tmdb.org/t/p";

    private final Picasso picasso;
    private DeviceInfo deviceInfo;

    public ImageLoader(Context context, DeviceInfo deviceInfo) {
        this.picasso = Picasso.with(context);
        this.deviceInfo = deviceInfo;
    }

    public void loadImage(String posterPath, ImageView imageView) {
        picasso.load(getTMDBImageUrl(posterPath))
                .resize(deviceInfo.getDeviceWidth(), 400)
                .centerCrop()
                .into(imageView);
    }

    private String getTMDBImageUrl(String posterPath) {
        return TMDB_IMAGE_BUCKET + "/w" + deviceInfo.getDeviceWidth() + posterPath;
    }
}
