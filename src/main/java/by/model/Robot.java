package by.model;

import by.service.LogTypes;
import by.service.RobotListener;
import by.service.iRobotListener;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Robot implements Runnable {

    private static int count = 0;
    protected static ArrayList<Long> idForRemove = new ArrayList<>();
    private long id;
    private String robotName;
    private RobotAction currentRobotAction;
    private BlockingQueue<RobotAction> queue;
    private BlockingQueue<RobotAction> manualQueue = new ArrayBlockingQueue<>(5);
    private iRobotListener listener;

    public Robot() {
        id = count++;
        robotName = "Robot-" + id;
        listener = new RobotListener();
        listener.logRobotAction(this, LogTypes.CREATE);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Robot.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public RobotAction getCurrentRobotAction() {
        return currentRobotAction;
    }

    public void setCurrentRobotAction(RobotAction currentRobotAction) {
        this.currentRobotAction = currentRobotAction;
    }

    public BlockingQueue<RobotAction> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<RobotAction> queue) {
        this.queue = queue;
    }

    public BlockingQueue<RobotAction> getManualQueue() {
        return manualQueue;
    }

    public void setManualQueue(BlockingQueue<RobotAction> manualQueue) {
        this.manualQueue = manualQueue;
    }

    public static ArrayList<Long> getIdForRemove() {
        return idForRemove;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (manualQueue.isEmpty()) {
                    currentRobotAction = queue.take();
                } else {
                    currentRobotAction = manualQueue.take();
                }
                if (currentRobotAction.getId()==0) {
                    listener.logRobotAction(this, LogTypes.DESTROY);
                    break;
                }
                doRobotAction();
                int time = currentRobotAction.getTimeInSec();
                Thread.sleep(time*1000);
                listener.logRobotAction(this, LogTypes.STOP);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        idForRemove.add(id);
    }

    private void doRobotAction() {
        listener.logRobotAction(this, LogTypes.DO);
    }
}
