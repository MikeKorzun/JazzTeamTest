package by.model;

public class RobotAction {
    private int id;
    private String name;
    private int timeInSec;

    public RobotAction(int id, String name, int timeInSec) {
        this.id = id;
        this.name = name;
        this.timeInSec = timeInSec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeInSec() {
        return timeInSec;
    }

    public void setTimeInSec(int timeInSec) {
        this.timeInSec = timeInSec;
    }

    @Override
    public String toString() {
        return "RobotAction{" +
                "name='" + name + '\'' +
                '}';
    }
}
