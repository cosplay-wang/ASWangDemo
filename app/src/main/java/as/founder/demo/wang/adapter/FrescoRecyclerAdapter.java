package as.founder.demo.wang.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/6/13.
 */
public class FrescoRecyclerAdapter extends RecyclerView.Adapter<FrescoRecyclerAdapter.MYViewHolder>{
    Context context;
    List<String> list;

    public FrescoRecyclerAdapter (Context context,  List<String> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public MYViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MYViewHolder viewHolder = new MYViewHolder(LayoutInflater.from(context).inflate(R.layout.fresco_recycler_item_layout,parent,false));

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MYViewHolder holder, int position) {
       // holder.tv.setText(list.get(position));
        Uri uri = Uri.parse(list.get(position));
     //  holder.iv.setImageURI(uri);


    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }


    class MYViewHolder extends RecyclerView.ViewHolder{

    //    SimpleDraweeView iv;
       // TextView tv;
        public MYViewHolder(View view){
            super(view);
           // this.iv = (SimpleDraweeView) view.findViewById(R.id.fresco_image);
         //   this.tv = (TextView) view.findViewById(R.id.recyclerView_item_tv);
        }
    }
//    public class BitmapCache implements ImageLoader.ImageCache {
//
//        private LruCache<String, Bitmap> mCache;
//
//        public BitmapCache() {
//            int maxSize = 10 * 1024 * 1024;
//            mCache = new LruCache<String, Bitmap>(maxSize) {
//                @Override
//                protected int sizeOf(String key, Bitmap bitmap) {
//                    return bitmap.getRowBytes() * bitmap.getHeight();
//                }
//            };
//        }
//
//        @Override
//        public Bitmap getBitmap(String url) {
//            return mCache.get(url);
//        }
//
//        @Override
//        public void putBitmap(String url, Bitmap bitmap) {
//            mCache.put(url, bitmap);
//        }
//
//    }
}