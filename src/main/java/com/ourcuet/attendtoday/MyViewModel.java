package com.ourcuet.attendtoday;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private  MyRepository repository;
    private LiveData<List<Absent>> absentLivedata;
    private  LiveData<List<Class>> classLivedata;


    public MyViewModel(Application application){

        super(application);
        repository=new MyRepository(application);
        absentLivedata=repository.getAbsentLivedata();
        classLivedata=repository.getClassLivedata();


    }

    public LiveData<List<Absent>> getAbsentLivedata() {
        return absentLivedata;
    }

    public LiveData<List<Class>> getClassLivedata() {
        return classLivedata;
    }

    public void insertAbsent(Absent absent){repository.insertAbsent(absent);}

    public void insertClass(Class cl){repository.insertClass(cl);}

    public void DeleteClass(Class cl){
        repository.deleteClass(cl);
    }


}
