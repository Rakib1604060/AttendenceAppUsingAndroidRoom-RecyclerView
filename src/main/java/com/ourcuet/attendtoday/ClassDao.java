package com.ourcuet.attendtoday;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ClassDao {
    @Insert
    void InsertClass(Class cl);
    @Delete
    void DeleteClass(Class cl);
    @Update
    void UpdateClass(Class cl);

    @Query("SELECT * from Class ORDER BY class_name ASC")
    LiveData<List<Class>> GetAllClasses();




}
