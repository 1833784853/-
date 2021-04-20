package com.imcode.rls.daily.model;

import com.imcode.rls.user.model.Loginregister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Daily{
    private int dailyID;    //日常id
    private Loginregister userID;  //用户信息
    private String dailyTitle;  //标题
    private String dailyContent;    //内容
    private String dailyTime;   //时间

    public int getDailyID() {
        return dailyID;
    }

    public void setDailyID(int dailyID) {
        this.dailyID = dailyID;
    }


    public Loginregister getUserID() {
        return userID;
    }

    public void setUserID(Loginregister userID) {
        this.userID = userID;
    }

    public String getDailyTitle() {
        return dailyTitle;
    }

    public void setDailyTitle(String dailyTitle) {
        this.dailyTitle = dailyTitle;
    }

    public String getDailyContent() {
        return dailyContent;
    }

    public void setDailyContent(String dailyContent) {
        this.dailyContent = dailyContent;
    }

    public String getDailyTime() {
        return dailyTime;
    }

    public void setDailyTime(String dailyTime) {
        this.dailyTime = dailyTime;
    }
}
