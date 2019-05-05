package com.ourcuet.attendtoday;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class MyRepository {
    private AbsentDao absentDao;
    private  ClassDao classDao;

     private LiveData<List<Absent>> AbsentLivedata;
     private  LiveData<List<Class>> ClassLivedata;


    public  MyRepository(Application application ){
         MyDatabase db=MyDatabase.getDatabse(application);
         absentDao=db.absentDao();
         classDao=db.classDao();

         AbsentLivedata=absentDao.GetAbsent();
         ClassLivedata=classDao.GetAllClasses();

     }

     LiveData<List<Absent>> getAbsentLivedata(){
         return  AbsentLivedata;
     }
    LiveData<List<Class>> getClassLivedata(){
        return  ClassLivedata;
    }


    public void insertAbsent(Absent absent){
    new insertAbsetnAsynctask(absentDao).execute(absent);
   }

   public void deleteClass(Class cl){
         new deleteClass(classDao).execute(cl);
   }



   public void insertClass(Class cl){
         new insertAsyncTask(classDao).execute(cl);
   }



    private static class insertAsyncTask extends AsyncTask<Class, Void, Void> {

        private ClassDao mAsyncTaskDao;

        insertAsyncTask(ClassDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Class... params) {
            mAsyncTaskDao.InsertClass(params[0]);
            return null;
        }
    }
    private static class deleteClass extends AsyncTask<Class, Void, Void> {

        private ClassDao mAsyncTaskDao;

        deleteClass(ClassDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Class... params) {
            mAsyncTaskDao.DeleteClass(params[0]);
            return null;
        }
    }



    private static class insertAbsetnAsynctask extends AsyncTask<Absent, Void, Void> {

        private AbsentDao mAsyncTaskDao;

        insertAbsetnAsynctask(AbsentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Absent... params) {
            mAsyncTaskDao.Insert_absent(params[0]);
            return null;
        }
    }


}
