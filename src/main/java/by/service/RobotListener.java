package by.service;

import by.model.Robot;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RobotListener implements iRobotListener{

    private static List<String> log = new ArrayList<String>(1000);

    @Override
    public synchronized void logRobotAction(Robot robot, LogTypes type) {
        switch (type) {
            case CREATE: {
                String text = robot.getRobotName() + " was CREATED";
                log.add(text);
                break;
            }
            case DO: {
                String text = robot.getRobotName() + " doing... " + robot.getCurrentRobotAction().getName().toUpperCase();
                log.add(text);
                break;
            }
            case STOP: {
                String text = robot.getRobotName() + " was STOPPED";
                log.add(text);
                break;
            }
            case DESTROY: {
                String text = robot.getRobotName() + " was DESTROYED";
                log.add(text);
                break;
            }
        }
    }


    public List<String> getLog() {
        List reversedLog = new ArrayList();
        reversedLog.addAll(log);
        Collections.reverse(reversedLog);
        return reversedLog;
    }



}
