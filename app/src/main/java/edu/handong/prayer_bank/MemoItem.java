package edu.handong.prayer_bank;

public class MemoItem {
    String date;
    String category;
    String content;

    public MemoItem(String date, String category, String content) {
        this.date = date;
        this.category = category;
        this.content = content;
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
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
