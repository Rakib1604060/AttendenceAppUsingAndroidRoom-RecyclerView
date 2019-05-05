package com.ourcuet.attendtoday;

import android.widget.ImageView;

public class Item {
   boolean color;
    int student_id;


    public Item(int student_id, boolean color) {
        this.student_id = student_id;
        this.color=color;

    }

    public int getStudent_id() {
        return student_id;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
