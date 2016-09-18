package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liang.mobilesafe.R;

import java.util.ArrayList;

import javabeen.HomeIcon;

/**
 * Created by liang on 2016/9/17.
 */

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HomeIcon> datas;
    private LayoutInflater inflater;

    public HomeAdapter(Context context,ArrayList<HomeIcon> datas) {
        this.context=context;
        this.datas=datas;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(covertView==null){
            holder=new ViewHolder();
            covertView=inflater.inflate(R.layout.item_home,null);
            holder.imageView=(ImageView)covertView.findViewById(R.id.iv_icon);
            holder.textView=(TextView)covertView.findViewById(R.id.tvv_name);
            covertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)covertView.getTag();
        }

        holder.imageView.setImageBitmap(datas.get(position).getPicture());
        holder.textView.setText(datas.get(position).getText());
        return covertView;
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView textView;

    }
}
