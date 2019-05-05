package com.ourcuet.attendtoday;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home extends Fragment implements MyAdapter.OnclassListener {

    private MyViewModel myViewModel;
    RecyclerView recyclerView;
    List<Class> classarray=new ArrayList<>();
     MyAdapter adapter;
     AlertDialog.Builder builder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootview=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=rootview.findViewById(R.id.recyclerview);
         builder=new AlertDialog.Builder(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         adapter= new MyAdapter(this);


        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);

        myViewModel.getClassLivedata().observe(this, new Observer<List<Class>>() {
            @Override
            public void onChanged(@Nullable List<Class> classes) {
                adapter.setclasses(classes);
                classarray=classes;

            }
        });

        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT
                |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                build_alert(viewHolder);


            }
        }).attachToRecyclerView(recyclerView);




        return rootview;


    }




    @Override
    public void onClassClick(int position) {
        Class temp =adapter.getClassAt(position);
        Toast.makeText(getActivity(), temp.getClass_name(), Toast.LENGTH_SHORT).show();

           Bundle bundle=new Bundle();
                bundle.putInt("class_id",temp.class_id);
                bundle.putInt("class_studentnumber",temp.class_studentnumber);

              Fragment_TakeAttendence fragment_takeAttendence=new Fragment_TakeAttendence();
                        fragment_takeAttendence.setArguments(bundle);

                 getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment_takeAttendence)
                .commit();



    }


    void build_alert(final RecyclerView.ViewHolder viewHolder){
        builder.setTitle("ATTENTION!!");
        builder.setMessage("DO YOU WANT TO DELTE THE CLASS?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myViewModel.DeleteClass(adapter.getClassAt(viewHolder.getAdapterPosition()));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               adapter.notifyDataSetChanged();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
