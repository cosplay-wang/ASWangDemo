package as.founder.demo.wang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/6/14.
 */
public class SwipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> list;
    Context context;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private onItemClickListener monItemClickListener;

    public SwipeRecyclerAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            FootViewHolder holder = new FootViewHolder(LayoutInflater.from(context).inflate(R.layout.foot_layout, parent, false));

            return holder;
        } else {
            viewHolder holder = new viewHolder(LayoutInflater.from(context).inflate(R.layout.listview_item_layout, parent, false));
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof viewHolder) {
            ((viewHolder) holder).tv.setText(String.valueOf(list
                    .get(position)));
        }
        // holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    class viewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView tv;

        public viewHolder(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView.findViewById(R.id.item_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (monItemClickListener != null) {
                monItemClickListener.onItemClick(v, getAdapterPosition());

            }
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }

    public void setonItemClickListener(onItemClickListener listener) {
        this.monItemClickListener = listener;

    }

    public interface onItemClickListener {
         void onItemClick(View view, int postion);
    }
}
