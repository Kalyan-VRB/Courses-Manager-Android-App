package com.example.coursesmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2> {



    Context context;
    ArrayList<Upcoming> list;

    public MyAdapter2(Context context, ArrayList<Upcoming> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.upcoming_tem,parent,false);

        return  new MyViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        Upcoming upcoming = list.get(position);
        holder.courseType.setText(upcoming.getType());
        holder.courseName.setText(upcoming.getName());
        holder.courseLink.setText(upcoming.getLink());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView courseType,courseName,courseLink;

        public MyViewHolder2(@NonNull View itemView){
            super(itemView);
            courseType = itemView.findViewById(R.id.tvcourseTypeu);
            courseName = itemView.findViewById(R.id.tvcourseNameu);
            courseLink = itemView.findViewById(R.id.tvcourseLinku);
        }
    }
}
