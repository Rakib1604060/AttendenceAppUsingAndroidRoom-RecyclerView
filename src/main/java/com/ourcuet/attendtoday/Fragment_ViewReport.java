package com.ourcuet.attendtoday;

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
import android.widget.EditText;

import java.util.ArrayList;

public class Fragment_ViewReport extends Fragment {
    RecyclerView myrecycler;
    static Button dateButton;
            Button ViewBuuton;
    EditText classid;

    static int yearr,monthh,dayy;
    static  int maindate;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootview=inflater.inflate(R.layout.fragment_viewreport,container,false);
          myrecycler=rootview.findViewById(R.id.lastrecycler);
          myrecycler.setHasFixedSize(true);
          myrecycler.setLayoutManager(new GridLayoutManager(getActivity(),5));
          dateButton=rootview.findViewById(R.id.choosedate);
          classid=rootview.findViewById(R.id.classidid);
          ViewBuuton=rootview.findViewById(R.id.viewid);
          //datepicker

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dp=new DatePicker_Fragment();
                dp.show(getFragmentManager(),"DATE PICKER");

            }
        });

          ViewBuuton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

              }
          });





       return rootview;
    }


    public static void setDatenow(int y , int m, int d){
        yearr=y;
        monthh=m;
        dayy=d;
        dateButton.setText(dayy+"/"+monthh+"/"+yearr);
        maindate=dayy*100000+monthh*10000+yearr;

    }


}
