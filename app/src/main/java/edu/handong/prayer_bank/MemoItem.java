package edu.handong.prayer_bank;

public class MemoItem {
    String date;
    String category;
    String prayer_topic;

    public MemoItem(String date, String category, String prayer_topic) {
        this.date = date;
        this.category = category;
        this.prayer_topic = prayer_topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return prayer_topic;
    }

    public void setContent(String prayer_topic) {
        this.prayer_topic = prayer_topic;
    }
}
