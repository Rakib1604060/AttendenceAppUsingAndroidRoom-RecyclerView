package com.ourcuet.attendtoday;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Fragment_Addclass extends Fragment {
    EditText classid,classname,classsection,schoolname,studentnumber;
    Button create_class;
    String name,section,school;
    Integer id,number;
    AlertDialog.Builder builder;
    private  MyViewModel myViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootview=inflater.inflate(R.layout.fragment_addclass,container,false);
       create_class=rootview.findViewById(R.id.create_classbtn);
       classid=rootview.findViewById(R.id.classid_id);
       classname=rootview.findViewById(R.id.classname_id);
       classsection=rootview.findViewById(R.id.classsection_id);
       schoolname=rootview.findViewById(R.id.schoolname_id);
       studentnumber=rootview.findViewById(R.id.studentNumber_id);



       builder=new AlertDialog.Builder(getActivity());

        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);





       create_class.setOnClickListener(new View.OnClickListener() {


           @Override
           public void onClick(View view) {
                        getInput();
               boolean IsinputOk=checkInput();
               if(IsinputOk){
                   builder.setTitle(" CREATE A NEW CLASS?");
                   builder.setMessage("DO YOU WANT TO CREATE A NEW CLASS?" +
                           "ID: "+id+" student Number: "+number+"?");
                   builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           boolean checkid=true;
                           if(checkid){
                               myViewModel.insertClass(new Class(id,name,section,school,number));
                               getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_home())
                                       .commit();

                           }else{
                               Toast.makeText(getActivity(),"Sorry ID :"+ id +" ALready Exist", Toast.LENGTH_LONG).show();
                           }



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
           }
       });

       return rootview;
    }

    boolean checkInput(){
        if(id==0||number==0||name.equals("")||school.equals("")||section.equals("")){
            Toast.makeText(getActivity(), "Enter a Valid Input", Toast.LENGTH_LONG).show();
            return  false;
        }
        else if (number>500){
            Toast.makeText(getActivity(), "Student Cant Be greater then 500", Toast.LENGTH_LONG).show();
            return  false;
        }
        else if (id<=0){
            Toast.makeText(getActivity(), "Please Choose Id greter then 0", Toast.LENGTH_LONG).show();
            return  false;
        }
        return  true;
    }

    void getInput(){
        id=Integer.parseInt(classid.getText().toString());
        number=Integer.parseInt(studentnumber.getText().toString());
        name=classname.getText().toString();
        section=classsection.getText().toString();
        school=schoolname.getText().toString();
    }
//
//    boolean isIdexist(){
//        List<Class>exist=myViewModel.getAllClassList();
//        for (int i=0;i<exist.size();i++){
//            if(id==exist.get(i).class_id){
//                return  false;
//            }
//        }
//        return  true;
//
//    }


}
