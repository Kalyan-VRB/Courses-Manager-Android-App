package com.example.coursesmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder3> {



    Context context;
    ArrayList<Trending> list;

    public MyAdapter3(Context context, ArrayList<Trending> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.trending_item,parent,false);

        return  new MyViewHolder3(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {

        Trending trending = list.get(position);
        holder.courseType.setText(trending.getType());
        holder.courseName.setText(trending.getName());
        holder.courseLink.setText(trending.getLink());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder3 extends RecyclerView.ViewHolder{

        TextView courseType,courseName,courseLink;

        public MyViewHolder3(@NonNull View itemView){
            super(itemView);
            courseType = itemView.findViewById(R.id.tvcourseTypet);
            courseName = itemView.findViewById(R.id.tvcourseNamet);
            courseLink = itemView.findViewById(R.id.tvcourseLinkt);
        }
    }
}
