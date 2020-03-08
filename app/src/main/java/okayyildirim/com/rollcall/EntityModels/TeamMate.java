package okayyildirim.com.rollcall.EntityModels;

public class TeamMate {
    private String name;
    private int Id;

    public TeamMate(String name, int id) {
        this.name = name;
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
