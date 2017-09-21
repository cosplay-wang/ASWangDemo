package as.founder.demo.wang.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.net.URI;
import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.activity.VolleyAC;

/**
 * Created by zhiwei.wang on 2016/6/13.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MYViewHolder>{
    Context context;
    List<String> list;
    ImageLoader mImageLoader;
    RequestQueue mRequestQueue;

    public RecyclerViewAdapter (Context context,  List<String> list){
        this.context = context;
        this.list = list;
        mRequestQueue = Volley.newRequestQueue(context);//这里的this指的是Context
        mImageLoader = new ImageLoader(mRequestQueue,new BitmapCache());
    }
    @Override
    public MYViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MYViewHolder viewHolder = new MYViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_layout,null));
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MYViewHolder holder, int position) {
        holder.tv.setText(list.get(position));

        holder.iv_vo.setImageUrl(list.get(position),mImageLoader);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.iv,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        mImageLoader.get(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }


    class MYViewHolder extends RecyclerView.ViewHolder{

        NetworkImageView iv_vo;
        TextView tv;
        ImageView iv;
        public MYViewHolder(View view){
            super(view);
            this.iv_vo = (NetworkImageView) view.findViewById(R.id.recyclerView_item_im);
            this.tv = (TextView) view.findViewById(R.id.recyclerView_item_tv);
            this.iv = (ImageView) view.findViewById(R.id.volley_imageview);
        }
    }
    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }
}
