package boost.chaper2.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Task {
    int user_id;
    String title;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    Date date;
    String owner;
    int priority;

    public Task(){

    }
    public Task(String title, Date date, String owner, int priority){
        this.title= title;
        this.date = date;
        this.owner = owner;
        this.priority = priority;
    }

    public Task(int userId,String title, Date date, String owner, int priority){
        this.user_id = userId;
        this.title= title;
        this.date = date;
        this.owner = owner;
        this.priority = priority;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {return date;}

    public String getOwner() {
        return owner;
    }

    public int getPriority() {
        return priority;
    }
}
