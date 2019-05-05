package com.ourcuet.attendtoday;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {
    List<Class> myClasses=new ArrayList<>();
     private  OnclassListener onclassListener;

  MyAdapter (OnclassListener onclassListener){
      this.onclassListener=onclassListener;
  }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View V= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_class,viewGroup,false);

        return new viewHolder(V,onclassListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
       Class temp=myClasses.get(i);
       viewHolder.classname.setText("Name: "+temp.getClass_name());
       viewHolder.section.setText("Section: "+temp.getClass_section());
       viewHolder.student.setText("Student: "+temp.getClass_studentnumber()+"");
       viewHolder.id.setText("Class ID: "+temp.getClass_id());

    }

    @Override
    public int getItemCount() {
        if(myClasses!=null){
            return myClasses.size();
        }
     return  0;
    }

    public void setclasses(List<Class> cl){
                this.myClasses=cl;
                notifyDataSetChanged();
    }

    public Class getClassAt(int position){

             return  this.myClasses.get(position);
    }


    public class  viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         public TextView classname,student,section,id;
         OnclassListener onclassListener;

        public viewHolder(@NonNull View itemView,OnclassListener onclassListener) {
            super(itemView);
            classname=itemView.findViewById(R.id.classnameid);
            student=itemView.findViewById(R.id.studentnumberid);
            section=itemView.findViewById(R.id.sectionid);
            id=itemView.findViewById(R.id.classid);
            this.onclassListener=onclassListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onclassListener.onClassClick(getAdapterPosition());
        }
    }

    public interface OnclassListener{
        void onClassClick(int position);

    }


}
