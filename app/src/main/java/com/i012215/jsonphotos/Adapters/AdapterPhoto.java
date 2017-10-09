package com.i012215.jsonphotos.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i012215.jsonphotos.Models.Photo;
import com.i012215.jsonphotos.R;
import com.i012215.jsonphotos.Views.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 8/10/2017.
 */

public class AdapterPhoto extends RecyclerView.Adapter<AdapterPhoto.ViewHolder>{
    List<Photo> photoList=new ArrayList<>();
    Context context;
    public AdapterPhoto (List<Photo> photoList, Context context){
        this.photoList=photoList;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_uno, parent, false);

        ViewHolder viewHolder=new ViewHolder(item);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(AdapterPhoto.ViewHolder holder, int position) {
    holder.textViewTitle.setText(photoList.get(position).getTitle());
        Picasso.with(context).load(photoList.get(position).getUrl()).into(holder.imageViewUrl);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewTitle;
        ImageView imageViewUrl;
        public ViewHolder(View item){
            super(item);
            item.setOnClickListener(this);
            textViewTitle=(TextView) item.findViewById(R.id.id_tv_title);
            imageViewUrl=(ImageView) item.findViewById(R.id.id_img_item_cardview);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void onClick(View view){
        Context contextItem=view.getContext();
        Intent intent=new Intent(context, DetailActivity.class);
        contextItem.startActivity(intent);
    }
}
