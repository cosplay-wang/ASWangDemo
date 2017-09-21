package as.founder.demo.wang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import as.founder.demo.wang.R;

/**
 * Created by zhiwei.wang on 2016/6/12.
 */
public class MainListviewAdapter extends BaseAdapter{


    public List<String>  list;
    Context context;
    public MainListviewAdapter(List<String>  list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewholer ;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_layout,null);
            viewholer = new viewHolder();
            viewholer.tv =(TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(viewholer);
        }else{
           viewholer = (viewHolder) convertView.getTag();
        }
        viewholer.tv.setText(list.get(position));

        return convertView;
    }
    class viewHolder{
        TextView tv;
    }
}
