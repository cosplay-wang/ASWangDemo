package as.founder.demo.wang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/10/27.
 * 先生_不靠谱
 */

public class ScanningFileAdaper extends RecyclerView.Adapter<ScanningFileAdaper.fileViewHolder> {

    Context context;
    List<String> dataList;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;
    public ScanningFileAdaper(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ScanningFileAdaper.fileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_item_layout,null);
        fileViewHolder viewHolder = new fileViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScanningFileAdaper.fileViewHolder holder, int position) {
         holder.textView.setText(dataList.get(position));
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class fileViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener{
        TextView textView;
        public fileViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.item_tv);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null){
                onItemClickListener.ItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(onItemLongClickListener!=null){
                onItemLongClickListener.ItemLongClick(getAdapterPosition());
            }
            return true;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        void ItemLongClick(int position);
    }
}
