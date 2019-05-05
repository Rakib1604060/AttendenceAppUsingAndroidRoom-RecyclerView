package com.ourcuet.attendtoday;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Class {
    @PrimaryKey
    int class_id;

    String class_name;
    String class_section;
    String school_name;
    int class_studentnumber;

    public Class( int class_id,String class_name, String class_section, String school_name, int class_studentnumber) {
        this.class_id=class_id;
        this.class_name = class_name;
        this.class_section = class_section;
        this.school_name = school_name;
        this.class_studentnumber = class_studentnumber;
    }

    public int getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getClass_section() {
        return class_section;
    }

    public String getSchool_name() {
        return school_name;
    }

    public int getClass_studentnumber() {
        return class_studentnumber;
    }
}
