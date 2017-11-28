package as.founder.demo.wang.activity;

import android.content.Context;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import as.founder.demo.wang.R;

public class JsonCompareActivity extends AppCompatActivity {
    Context context;
    ImageView dadaImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_compare);
        context = this;
        dadaImage = (ImageView) findViewById(R.id.dada_image);
        saveGlide("http://g.hiphotos.baidu.com/image/pic/item/7a899e510fb30f246c5e69c9c195d143ac4b0337.jpg");
        String str1 = "{\"properties\":{\"packet\":{\"recorded_at\":\"2015-09-02 04:45:45 +0000\",\"userId\":\"100000000000001\",\"meta\":{\"account\":\"xxx\",\"event\":\"track\"},\"fields\":{\"gyroData\":{\"rotation_y\":-1,\"rotation_z\":-1,\"rotation_x\":-1},\"accelerometerData\":{\"acceleration_x\":-1,\"acceleration_z\":-1,\"acceleration_y\":-1},\"location\":{\"speed\":4.68,\"speed_course\":0.7,\"horizontal_accuracy\":10,\"longtitude\":-122.02359082,\"vertical_accuracy\":-1,\"latitude\":37.33385024},\"pedometerData\":{\"step_count\":0}},\"recorded_sample_rate\":5}},\"geometry\":{\"type\":\"Point\",\"coordinates\":[37.33385024,-122.02359082]},\"type\":\"Feature\"}";
        String str2 = "{\"properties\":{\"packet\":{\"recorded_at\":\"2015-09-02 04:45:45 +0000\",\"userId\":\"100000000000001\",\"meta\":{\"account\":\"xxx\",\"event\":\"track\"},\"fields\":{\"gyroData\":{\"rotation_y\":-1,\"rotation_z\":-1,\"rotation_x\":-1},\"accelerometerData\":{\"acceleration_x\":-1,\"acceleration_z\":-1,\"acceleration_y\":-1},\"location\":{\"speed\":4.68,\"speed_course\":0.7,\"horizontal_accuracy\":10,\"longtitude\":-122.02359082,\"vertical_accuracy\":-1,\"latitude\":37.33385024},\"pedometerData\":{\"step_count\":0}},\"recorded_sample_rate\":5}},\"geometry\":{\"type\":\"Point\",\"coordinates\":[37.33385024,-122.02359082]},\"type\":\"Feature\"}";
        if(compareJson(str1,str2)){
            Log.e("da","ddd");
        }else{
            Log.e("da","dnonononodd");
        };

    }
    private boolean compareJson(String str1,String str2){
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(str1);
        JsonParser parser1 = new JsonParser();
        JsonObject obj1 = (JsonObject) parser1.parse(str2);

        return  obj.equals(obj1);
    }
    private void saveGlide(String imgUrl) {
        /**
         * Glide 加载图片保存到本地
         *
         * imgUrl 图片地址
         * imgName 图片名称
         */
//        Glide.with(context).load(imgUrl).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
//            @Override
//            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
//                try {
//                    savaBitmap("ssdaa.jpg", bytes);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        Glide.with(context).load(imgUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        // 可替换成进度条
                        Toast.makeText(context, "图片加载失败", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        // 图片加载完成，取消进度条
                        Toast.makeText(context, "图片加载成功", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }).error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dadaImage);


    }

    // 保存图片到手机指定目录
    public void savaBitmap(String imgName, byte[] bytes) {

            String filePath = null;
            FileOutputStream fos = null;
            try {
                filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/MyImg";
                File imgDir = new File(filePath);
                if (!imgDir.exists()) {
                    imgDir.mkdirs();
                }
                imgName = filePath + "/" + imgName;
                fos = new FileOutputStream(imgName);
                fos.write(bytes);
                Toast.makeText(context, "图片已保存到" + filePath, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }


}
