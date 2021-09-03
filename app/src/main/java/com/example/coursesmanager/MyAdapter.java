package com.example.coursesmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    Context context;
    ArrayList<Ongoing> list;

    public MyAdapter(Context context, ArrayList<Ongoing> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Ongoing ongoing = list.get(position);
        holder.courseType.setText(ongoing.getType());
        holder.courseName.setText(ongoing.getName());
        holder.courseLink.setText(ongoing.getLink());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView courseType,courseName,courseLink;

       public MyViewHolder(@NonNull View itemView){
           super(itemView);
           courseType = itemView.findViewById(R.id.tvcourseType);
           courseName = itemView.findViewById(R.id.tvcourseName);
           courseLink = itemView.findViewById(R.id.tvcourseLink);
       }
    }
}
