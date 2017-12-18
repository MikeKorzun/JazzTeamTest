package by.service;


import by.dao.RobotActionDAO;
import by.model.Robot;
import by.model.RobotAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class GameService {

    @Autowired
    private RobotActionDAO robotActionDAO;

    private final int startNumbersOfRobotActions = 8;
    private final int capacityOfRobotActionsQueue = 20;
    private final int startNumbersOfRobots = 4;
    private static Map<Long, Robot> robotMap = new HashMap<>();

    private BlockingQueue<RobotAction> queue = new ArrayBlockingQueue<>(capacityOfRobotActionsQueue);
    {
        for (int i=1; i<=startNumbersOfRobots; i++) {
            Robot robot = new Robot();
            robotMap.put(robot.getId(), robot);
        }
    }

    // Create new robot and initialize it in process
    private void createNewRobot() {
        Robot robot = new Robot();
        robot.setQueue(queue);
        robotMap.put(robot.getId(), robot);
        Thread thread = new Thread(robot);
        thread.start();
    }

    // Initialize all robots at start
    public void initRobots() {
        for (Robot r: robotMap.values()) {
            r.setQueue(queue);
            Thread thread = new Thread(r, r.getRobotName());
            thread.start();
        }
    }

    // Method fill Action queue with actions by random generated id
    public void createRobotActionsQueue() {
        for (int i=0; i<startNumbersOfRobotActions; i++) {
            int id = (int)(Math.random()*(robotActionDAO.getRobotActionList().size()-1));
            queue.add(robotActionDAO.getRobotActionList().get(id));
        }
    }

    // Method add the Robot Action to the queue for all robots
    public void addActionRAQueue(RobotAction robotAction) {
      //  checkRobotsForRemove();
        try {
            queue.put(robotAction);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Return list of all active robots
    public Collection<Robot> getRobotList() {
        return robotMap.values();
    }

    // Add certain action to the robot
    public void addActionRAQueueToRobot(RobotAction robotAction, Long idRobot) {
        //checkRobotsForRemove();
        try {
            robotMap.get(idRobot).getManualQueue().put(robotAction);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robotMap.get(idRobot).run();
        if (robotAction.getId()==0) {
            robotMap.remove(idRobot);
        }
    }

    // Remove all destroyed robots from map
    public void checkRobotsForRemove() {
        for (Long i: Robot.getIdForRemove()) {
            robotMap.remove(i);
            createNewRobot();
        }
        Robot.getIdForRemove().clear();
    }
}
