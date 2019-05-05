package com.ourcuet.attendtoday;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Class.class,Absent.class},version = 7,exportSchema = false)
 public abstract class MyDatabase extends RoomDatabase {

    public abstract  AbsentDao absentDao();
    public abstract  ClassDao classDao();

    private static  volatile MyDatabase Instance;

    static  MyDatabase getDatabse(final Context context){

        if(Instance==null){
            synchronized( MyDatabase.class){
                if(Instance==null){
                    Instance= Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class,"MyDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return Instance;
    }
    private  static  RoomDatabase.Callback roomcallBack=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAysunckTask(Instance).execute();
        }
    };

    private static class PopulateDbAysunckTask extends AsyncTask<Void,Void,Void> {
        ClassDao classDao;
        private  PopulateDbAysunckTask(MyDatabase myDatabase){
            this.classDao=myDatabase.classDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

          classDao.InsertClass(new Class(5,"nine","A","lakhpur",60));

            return null;
        }
    }


}
