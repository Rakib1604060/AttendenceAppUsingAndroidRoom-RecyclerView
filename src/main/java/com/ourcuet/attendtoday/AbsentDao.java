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
public interface AbsentDao {
    @Insert
    void Insert_absent(Absent absent);
    @Delete
    void Delete_absent(Absent absent);

    @Query("Select * From Absent ")
    public  abstract LiveData<List<Absent>> GetAbsent();

}
