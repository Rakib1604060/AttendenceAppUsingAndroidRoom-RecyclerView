package com.ourcuet.attendtoday;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Fragment_TakeAttendence extends Fragment implements AttendenceAdapter.OnAttendenceListener {

   public static   DatePickerDialog listener;
   public static int  year=0,month=0,day=0;
   Button saveAttendence;

    static public Button DatePickerButton;
    RecyclerView recyclerView;
    AttendenceAdapter adapter;

    private  MyViewModel viewModel;

    RecyclerView.LayoutManager layoutManager;

    List<Integer> absentarray;
    HashSet<Integer> myHash=new HashSet<>();
    AlertDialog.Builder builder;
    MyViewModel myViewModel;
    List<Absent> absentTOday=new ArrayList<>();
    int classid;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootview=inflater.inflate(R.layout.fragment_takeattendence,container,false);
         Bundle bundle= getArguments();
         classid=bundle.getInt("class_id");
         int studentnumber=bundle.getInt("class_studentnumber");
          myViewModel=ViewModelProviders.of(getActivity()).get(MyViewModel.class);
         DatePickerButton=rootview.findViewById(R.id.datepickerbtn);
         recyclerView=rootview.findViewById(R.id.attendrecycler);
         recyclerView.setHasFixedSize(true);
         layoutManager= new GridLayoutManager(getActivity(),5);
         builder=new AlertDialog.Builder(getActivity());


         recyclerView.setLayoutManager(layoutManager);
         saveAttendence=rootview.findViewById(R.id.save_Attendencebtn);

         saveAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                absentarray=new ArrayList<>(myHash);
                if (day ==0||month==0||year==0) {


                    Toast.makeText(getActivity(), "Please Enter A date", Toast.LENGTH_LONG).show();
                }
                else {

                    make_Alert();

                }

                Toast.makeText(getActivity(), absentarray.toString(), Toast.LENGTH_LONG).show();


            }
        });

         adapter=new AttendenceAdapter(studentnumber,this,getActivity());

         recyclerView.setAdapter(adapter);





        DatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dp=new DatePicker_Fragment();
                dp.show(getFragmentManager(),"DATE PICKER");

            }
        });


       return rootview;
    }

    public static void setDatenow(int y , int m, int d){
        year=y;
        month=m;
        day=d;
        DatePickerButton.setText(day+"/"+month+"/"+year);
    }


    @Override
    public void onclickAttendence(int number) {

        if(myHash.contains(number+1)){
            myHash.remove(number+1);
            adapter.changecolor(number,true);

        }
        else
        {
            myHash.add(number+1);
            adapter.changecolor(number,false);
        }





    }

    void make_Alert(){
     builder.setTitle("ATTENTION!!");
     builder.setMessage("DO YOU WANT TO SAVE ??");
     builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialogInterface, int i) {
             makeAbsetnCLass();

         }
     });

     builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialogInterface, int i) {

         }
     });
     AlertDialog alertDialog=builder.create();
     alertDialog.show();

    }
    private  void makeAbsetnCLass(){
        if(myHash.size()==0){
            Toast.makeText(getActivity(), "GOOD, NO One Is Absent TOday", Toast.LENGTH_SHORT).show();

        }else{
            absentarray=new ArrayList<>(myHash);
            Toast.makeText(getActivity(), absentarray.size()+"", Toast.LENGTH_SHORT).show();
            for (int i=0;i<absentarray.size();i++){
                  Absent  absent=new Absent(classid,day*100000+month*10000+year,absentarray.get(i));
                  myViewModel.insertAbsent(absent);
                 Toast.makeText(getActivity(), "Value Saved", Toast.LENGTH_SHORT).show();

            }

        }

    }




}
