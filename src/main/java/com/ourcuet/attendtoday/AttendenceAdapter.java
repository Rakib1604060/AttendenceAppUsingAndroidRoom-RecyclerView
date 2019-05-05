package com.ourcuet.attendtoday;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AttendenceAdapter extends RecyclerView.Adapter<AttendenceAdapter.viewHolder> {
 ImageView myimage;
  int index=-1;

     Activity activity;
     ArrayList<Item>myAllItems=new ArrayList<>();
     OnAttendenceListener onAttendenceListener;


    public AttendenceAdapter(int length, OnAttendenceListener onAttendenceListener, Activity activity){

        this.onAttendenceListener=onAttendenceListener;
        this.activity=activity;
        for(int i=1;i<=length;i++){
           myAllItems.add(new Item(i,true));
        }


    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View V= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attendence_recycleritem,viewGroup,false);


        return new viewHolder(V,onAttendenceListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, final int i) {

        Item item=myAllItems.get(i);
        viewHolder.student_id.setText(item.getStudent_id()+"");

        if(!item.color){
            viewHolder.imageView.setImageResource(R.drawable.ic_redperson);
            viewHolder.student_id.setTextColor(activity.getResources().getColor(R.color.colorAccent));
        }
        else{
            viewHolder.imageView.setImageResource(R.drawable.ic_person);
            viewHolder.student_id.setTextColor(activity.getResources().getColor(R.color.colorblack));
        }





    }

    @Override
    public int getItemCount() {

        return myAllItems.size();
    }

    public void changecolor(int i,boolean color){
     Item item =myAllItems.get(i);
     item.setColor(color);
     notifyDataSetChanged();
    }

    public class  viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
           public  ImageView imageView;
           public TextView student_id;
            OnAttendenceListener onAttendenceListener;



        public viewHolder(@NonNull View itemView,OnAttendenceListener onAttendenceListener) {
            super(itemView);
            student_id=itemView.findViewById(R.id.persontexid);
            imageView=itemView.findViewById(R.id.personimage);

            this.onAttendenceListener=onAttendenceListener;
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            onAttendenceListener.onclickAttendence(getAdapterPosition());


         //   view.setBackgroundColor(activity.getResources().getColor(R.color.colorAccent));
        }
    }



    public interface OnAttendenceListener{
        void onclickAttendence(int position);

    }


}
