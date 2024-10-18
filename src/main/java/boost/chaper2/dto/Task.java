package boost.chaper2.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Task {
    private int user_id;
    private String title;
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date date;
    private String owner;
    private int priority;
    private String progress;

    public Task() {

    }

    public Task(int userId, String title, Date date, String owner, int priority, String progress) {
        this.user_id = userId;
        this.title = title;
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

    public Date getDate() {
        return date;
    }

    public String getOwner() {
        return owner;
    }

    public int getPriority() {
        return priority;
    }
}
