package boost.chaper2.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Task {
    int user_id;
    String title;
    @JsonFormat(pattern = "yyyy.MM.dd")
    java.sql.Date date;
    String owner;
    int priority;

    String progress;

    public Task(){

    }

    public Task(int userId,String title, java.sql.Date date, String owner, int priority, String progress){
        this.user_id = userId;
        this.title= title;
        this.date = date;
        this.owner = owner;
        this.priority = priority;
        this.progress = progress;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public java.sql.Date getDate() {return date;}

    public String getOwner() {
        return owner;
    }

    public int getPriority() {
        return priority;
    }
}
