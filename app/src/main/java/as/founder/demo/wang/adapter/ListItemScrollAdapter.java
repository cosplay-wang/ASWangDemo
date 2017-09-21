package as.founder.demo.wang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import as.founder.demo.wang.R;
import as.founder.demo.wang.bean.ListScrollItemBean;

/**
 * Created by zhiwei.wang on 2016/8/16.
 * 先生_不靠谱
 */
public class ListItemScrollAdapter extends BaseAdapter {

    Context context;
    List<ListScrollItemBean> datalist;
    public static final int SCROLL = 0;// 2种不同的布局
    public static final int TEXT = 1;


    public ListItemScrollAdapter(Context context, List<ListScrollItemBean> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
    /**
     * 根据数据源的position返回需要显示的的layout的type
     *
     * type的值必须从0开始
     *
     * */
    @Override
    public int getItemViewType(int position) {

        return datalist.get(position).isScroll()?SCROLL:TEXT;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       // ViewHolder viewHolder;
        ListScrollItemBean bean = datalist.get(position);
        int type;
        if(bean.isScroll()){
            type = SCROLL;
        }else{
            type = TEXT;
        }
        ViewHolder viewHolder= null;
        ViewHolderS viewHolderS = null;
        if(convertView == null){
            switch(type){
                case TEXT:
                    convertView = LayoutInflater.from(context).inflate(R.layout.down_list_item_layout, null);
                    //TextView tv = (TextView) convertView.findViewById(R.id.down_list_item_tv);
                    viewHolder = new ViewHolder();
                    viewHolder.tv = (TextView) convertView.findViewById(R.id.down_list_item_tv);
                    convertView.setTag(viewHolder);
                    break;
                case SCROLL:
                    convertView = LayoutInflater.from(context).inflate(R.layout.scroll_layout, null);
                    viewHolderS = new ViewHolderS();
                    viewHolderS.tv = (TextView) convertView.findViewById(R.id.scroll_tv);
                    viewHolderS.scroll_lin = (LinearLayout) convertView.findViewById(R.id.scroll_lin);
                    viewHolderS.tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,"text的点击事件"+datalist.get(position).isScroll(),Toast.LENGTH_SHORT).show();
                        }
                    });

                    convertView.setTag(viewHolderS);
                    break;
            }
        }else{
            switch(type){
                case TEXT:

                    viewHolder = (ViewHolder) convertView.getTag();

                    break;
                case SCROLL:

                    viewHolderS = (ViewHolderS) convertView.getTag();

                    break;
            }

            }
        switch(type){
            case TEXT:

              viewHolder.tv.setText(datalist.get(position).getName());

                break;
            case SCROLL:

              //  viewHolderS = (ViewHolderS) convertView.getTag();
                viewHolderS.tv.setText(datalist.get(position).getName());

                break;
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv;

        public ViewHolder() {

        }
    }
    class ViewHolderS {
        TextView tv;
        LinearLayout scroll_lin;
        public ViewHolderS() {

        }
    }
}
