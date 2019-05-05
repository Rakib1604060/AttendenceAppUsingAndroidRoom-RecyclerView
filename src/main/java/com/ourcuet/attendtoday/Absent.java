package com.ourcuet.attendtoday;

import android.arch.persistence.room.Entity;


import java.util.Date;

@Entity(primaryKeys = {"class_id","class_date","student_id"})
public class Absent {

    int class_id;

    int  class_date;

    int student_id;

    public Absent(int class_id, int class_date, int student_id) {
        this.class_id = class_id;
        this.class_date = class_date;
        this.student_id = student_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public int getClass_date() {
        return class_date;
    }

    public int getStudent_id() {
        return student_id;
    }
}
