package okayyildirim.com.rollcall.EntityModels;

public class RollCall {
    private int Id;
    private String rollDate;
    private int attempCount;
    private String title;


    public RollCall(int Id, String rollDate, int attempCount, String title) {
        this.Id = Id;
        this.rollDate = rollDate;
        this.attempCount = attempCount;
        this.title = title;
    }

    public String getRollDate() {
        return rollDate;
    }

    public void setRollDate(String rollDate) {
        this.rollDate = rollDate;
    }

    public int getAttempCount() {
        return attempCount;
    }

    public void setAttempCount(int attempCount) {
        this.attempCount = attempCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
