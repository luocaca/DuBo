package shishicai.com.dubo.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.loader.ImageLoader;

import shishicai.com.dubo.R;

/**
 * Created by geyifeng on 2017/6/4.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

//        ImageOptions imageOptions = ImageOptions.DEFAULT;


//        x.image().bind(imageView, path.toString(),
//                new ImageOptions.Builder()
//                .setImageScaleType(ImageView.ScaleType.FIT_XY)
////              .setCrop(true)
//                .build());
        Glide.with(context.getApplicationContext())
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.icon)
                .into(imageView);
//        Glide.with(context.getApplicationContext())
//                .load(path)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .placeholder(R.mipmap.test)
//                .into(imageView);
    }

}
