package com.wt.xwttravel.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FlushedInputStream;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wt.xwttravel.model.MyColor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Genius on 1/28/16.
 */
public class ImageUtil {

    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .build();



    @Deprecated
    public static void loadImage(final ImageView imageView, String url){
        ImageLoader.getInstance().loadImage(url,options,new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                imageView.setImageBitmap(loadedImage);
            }
        });
    }


    @Deprecated
    public static void loadImage(final ImageView imageView, int maxSize, String url){
        ImageLoader.getInstance().loadImage(url,new ImageSize(maxSize,maxSize),options,new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                try {
                    imageView.setImageBitmap(loadedImage);
                }catch (OutOfMemoryError error){
                    System.gc();
                    System.out.println("worked");
                    imageView.setImageBitmap(loadedImage);
                }

            }
        });
    }

    public static void loadImage(SimpleDraweeView draweeView, String url){
        draweeView.setImageURI(Uri.parse(url));
    }

    public static void loadImage(SimpleDraweeView draweeView, Uri uri){
        draweeView.setImageURI(uri);
    }

    public static int getImageAverageColor(File file) throws Exception {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(),options);
        options.inSampleSize = calculateInSampleSize(options,30,30);

        Bitmap loadedImage = BitmapFactory.decodeFile(file.getPath(),options);
        int rgb[] = new int[3];
        int count = 0;
        for(int x = 0; x < loadedImage.getWidth(); ++x){
            for(int y = 0; y < loadedImage.getHeight(); ++y){
                int pixel = loadedImage.getPixel(x,y);
                rgb[0] += Color.red(pixel);
                rgb[1] += Color.green(pixel);
                rgb[2] += Color.blue(pixel);
                ++count;
            }
        }
        rgb[0] /= count;
        rgb[1] /= count;
        rgb[2] /= count;

        return Color.rgb(rgb[0],rgb[1],rgb[2]);

    }



    public static int getImageAverageColor(Context context, int resId) throws Exception {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        options.inSampleSize = calculateInSampleSize(options,30,30);
        options.inJustDecodeBounds = false;
        Bitmap loadedImage = BitmapFactory.decodeResource(context.getResources(), resId, options);
        int rgb[] = new int[3];
        int count = 0;
        for(int x = 0; x < loadedImage.getWidth(); ++x){
            for(int y = 0; y < loadedImage.getHeight(); ++y){
                int pixel = loadedImage.getPixel(x,y);
                rgb[0] += Color.red(pixel);
                rgb[1] += Color.green(pixel);
                rgb[2] += Color.blue(pixel);
                ++count;
            }
        }

        rgb[0] /= count;
        rgb[1] /= count;
        rgb[2] /= count;

        if(rgb[0]==rgb[1]&&rgb[1]==rgb[2]){
            return Color.rgb(127,127,127);
        }

        if(rgb[0] == rgb[1]){
            if(rgb[0] > rgb[2]){
                return Color.rgb(255,255,127);
            }else {
                return Color.rgb(127,127,255);
            }
        }

        if(rgb[1] == rgb[2]){
            if(rgb[1] > rgb[0]){
                return Color.rgb(127,255,255);
            }else {
                return Color.rgb(255,127,127);
            }
        }

        if(rgb[0] == rgb[2]){
            if(rgb[0] > rgb[1]){
                return Color.rgb(255,127,255);
            }else {
                return Color.rgb(127,255,127);
            }
        }


        if(rgb[0] < rgb[1] && rgb[1] < rgb[2]){
            rgb[0] = 0;
            rgb[1] /=2;
        }

        if(rgb[0] < rgb[2] && rgb[2] < rgb[1]){
            rgb[0] = 0;
            rgb[2] /=2;
        }

        if(rgb[1] < rgb[0] && rgb[0] < rgb[2]){
            rgb[1] = 0;
            rgb[0] /=2;
        }

        if(rgb[1] < rgb[2] && rgb[2] < rgb[0]){
            rgb[1] = 0;
            rgb[2] /=2;
        }

        if(rgb[2] < rgb[1] && rgb[1] < rgb[0]){
            rgb[2] = 0;
            rgb[1] /=2;
        }

        if(rgb[2] < rgb[0] && rgb[0] < rgb[1]){
            rgb[2] = 0;
            rgb[0] /=2;
        }




        return Color.rgb(rgb[0],rgb[1],rgb[2]);

    }


    private static MyColor findColor(List<MyColor> list,  int position){
        for(MyColor color : list){
            if(color.getPosition() == position){
                return color;
            }
        }
        return null;
    }

//    public static int getImageAverageColor(String url) throws Exception {
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeStream(file.getPath(),options);
//        options.inSampleSize = calculateInSampleSize(options,30,30);
//
//        Bitmap loadedImage = BitmapFactory.decodeFile(file.getPath(),options);
//        int rgb[] = new int[3];
//        int count = 0;
//        for(int x = 0; x < loadedImage.getWidth(); ++x){
//            for(int y = 0; y < loadedImage.getHeight(); ++y){
//                int pixel = loadedImage.getPixel(x,y);
//                rgb[0] += Color.red(pixel);
//                rgb[1] += Color.green(pixel);
//                rgb[2] += Color.blue(pixel);
//                ++count;
//            }
//        }
//        rgb[0] /= count;
//        rgb[1] /= count;
//        rgb[2] /= count;
//
//        return Color.rgb(rgb[0],rgb[1],rgb[2]);
//
//    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }
}
