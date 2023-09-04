package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Event implements ModelOperations {
    private String title;
    private String organizer;
    private String summary;
    private String startDateAndTime;
    private String endDateAndTime;

    public Event(String title, String organizer, String summary, String startDateAndTime, String endDateAndTime) {
        this.title = title;
        this.organizer = organizer;
        this.summary = summary;
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
    }

    @Override
    public String toSchema() {
        return "BEGIN:VEVENT" + "\n" +
                "UID:" + title + "\n" +
                "ORGANIZER:" + organizer + "\n" +
                "DTSTART:" + startDateAndTime + "\n" +
                "DTEND:" + endDateAndTime + "\n" +
                "SUMMARY:" + summary + "\n" +
                "EVENT:VEVENT";
    }
}
