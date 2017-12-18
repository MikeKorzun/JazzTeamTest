package by.dao;

import by.model.RobotAction;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RobotActionDAO {

    private static ArrayList<RobotAction> robotActions;
    {
        robotActions = new ArrayList<>();
        robotActions.add(new RobotAction(0,"DestroyYourself", 0));
        robotActions.add(new RobotAction(1,"Attack", 10));
        robotActions.add(new RobotAction(2,"Build", 15));
        robotActions.add(new RobotAction(3,"Mine", 20));
        robotActions.add(new RobotAction(99,"Stop", 0));
    }

    public void addRobotAction(RobotAction robotAction) {
        robotActions.add(robotAction);
    }

    public int deleteRobotAction(int id) {
        for (RobotAction ra : robotActions) {
            if (ra.getId()== id) {
                robotActions.remove(ra);
                return id;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public RobotAction getRobotAction(int id) {
        for (RobotAction ra : robotActions) {
            if (ra.getId()== id) {
                return ra;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<RobotAction> getRobotActionList() {
        return robotActions;
    }

}
