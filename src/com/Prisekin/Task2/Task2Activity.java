package com.Prisekin.Task2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.AbsListView;
import android.content.Context;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.content.Intent;

import java.util.ArrayList;

public class Task2Activity extends Activity implements OnItemClickListener{
    /**
     * Called when the activity is first created.
     */
    static Activity activity;
    ListView list_view; String[] web_addresses,place_titles,place_images;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        web_addresses=getResources().getStringArray(R.array.web_addresses);
        place_titles=getResources().getStringArray(R.array.place_titles);
        place_images=getResources().getStringArray(R.array.place_images);
        setContentView(R.layout.main);
        list_view=(ListView)findViewById(R.id.list_view);

        ArrayList<ListContainer> list_container=new ArrayList<ListContainer>();
        for(int i=0;i<place_titles.length;i++){
         list_container.add(new ListContainer((int)(getResources().getIdentifier(place_images[i],"drawable",this.getPackageName())),place_titles[i],web_addresses[i]));
        }
        SuperAdapter adapter=new SuperAdapter(this,list_container);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(this);
    }
@Override
public void onItemClick(AdapterView<?> a,View v,int position,long l){
    String url=web_addresses[position];
    Intent web_intent=new Intent(this,WebPage.class);
    web_intent.putExtra("page_url",url);
    startActivity(web_intent);
}
class ListContainer{
    int image_id; String text,url;
    public ListContainer(int image_id,String text,String url){
        this.image_id=image_id; this.text=text; this.url=url;
    }
}
}
class SuperAdapter extends BaseAdapter{
  Context context;
  ArrayList<Task2Activity.ListContainer> list_container;
  LinearLayout layout; LayoutInflater layout_inflater;
  TextView text; ImageView image;
    public SuperAdapter(Context context,ArrayList<Task2Activity.ListContainer> list_container){
        this.context=context; this.list_container=list_container;
        layout_inflater=Task2Activity.activity.getLayoutInflater();
    }
@Override
    public int getCount(){
    return list_container.size();}
@Override
public long getItemId(int position){
    return position;}
@Override
        public Object getItem(int position){
        return list_container.get(position);}

@Override
    public View getView(int position,View convert_view, ViewGroup parent){
    /*TextView text; //text=new TextView(context);
    if(convert_view==null){text=new TextView(context);
        text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        text.setTextColor(0xff00cfcf);}
    else{text=(TextView)convert_view;}
    text.setText("test");
    return text;*/
    if(convert_view==null){
    convert_view=layout_inflater.inflate(R.layout.list_view_item_template,null);
    convert_view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    image=(ImageView)convert_view.findViewById(R.id.image);
    text=(TextView)convert_view.findViewById(R.id.text);
    ViewHolder hold_view=new ViewHolder();
    hold_view.image_holder=image; hold_view.text_holder=text;
    convert_view.setTag(hold_view);
    }
    ViewHolder holder=(ViewHolder)convert_view.getTag();
    holder.text_holder.setText(list_container.get(position).text);
    holder.image_holder.setImageResource(list_container.get(position).image_id);
    return convert_view;
}
}
class ViewHolder{
    public TextView text_holder; public ImageView image_holder;
}