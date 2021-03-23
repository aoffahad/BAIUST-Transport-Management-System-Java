package schedule;


import java.sql.Time;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAMNOON
 */
public class ScheduleModelClass {
    public String from, to, busno;
    public Time time;
    public Time getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public ScheduleModelClass() {
    }

    public ScheduleModelClass(Time time, String from, String to, String busno) {
        this.time = time;
        this.from = from;
        this.to = to;
        this.busno = busno;
    }

    public String getBusno() {
        return busno;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }
    
}
