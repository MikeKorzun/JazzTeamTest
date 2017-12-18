package by.service;

import by.model.Robot;

/*
* Трекер активности роботов
 */
public interface iRobotListener {
    void logRobotAction(Robot robot, LogTypes type);
}
