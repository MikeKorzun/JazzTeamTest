package by.controller;

import by.dao.RobotActionDAO;
import by.service.GameService;
import by.service.RobotListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FieldController {

	@Autowired
	private GameService gameService;
	@Autowired
	private RobotActionDAO robotActionDAO;
	@Autowired
	private RobotListener robotListener;
	
	 @RequestMapping(value = "/field", method = RequestMethod.GET)
	    public ModelAndView dashboard() {
	    	ModelAndView model = new ModelAndView();
	    	gameService.checkRobotsForRemove();
	    	model.addObject("robotList", gameService.getRobotList());
		 	model.addObject("logList", robotListener.getLog());
	    	model.setViewName("field");
	    	return model;
	    }

	@RequestMapping(value = "/StartGame")
	public String startGame(Model model) {
		gameService.createRobotActionsQueue();
		gameService.initRobots();
		model.addAttribute("robotsList", gameService.getRobotList());
		return "redirect:/field";
	}

	// Add Destroy action to the robot
	@RequestMapping(value = "/addDestroyYourselfToRobot/{id}")
	public String addDestroyYourselfToRobot(@PathVariable("id") Long id) {
		gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(0), id);
		return "redirect:/field";
	}

	// Add Attack action to the robot
	@RequestMapping(value = "/addAttackActionToRobot/{id}")
	public String addAttackActionToRobot(@PathVariable("id") Long id) {
		gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(1), id);
		return "redirect:/field";
	}

	// Add Build action to the robot
	@RequestMapping(value = "/addBuildActionToRobot/{id}")
	public String addBuildActionToRobot(@PathVariable("id") Long id) {
		gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(2), id);
		return "redirect:/field";
	}

	// Add Mine action to the robot
	@RequestMapping(value = "/addMineActionToRobot/{id}")
	public String addMineActionToRobot(@PathVariable("id") Long id) {
		gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(3), id);
		return "redirect:/field";
	}

	// Add Destroy action to RobotAction Queue for all robots
	@RequestMapping(value = "/addDestroyYourselfAction")
	public String addDestroyYourselAction() {
		gameService.addActionRAQueue(robotActionDAO.getRobotAction(0));
		return "redirect:/field";
	}

	// Add Destroy action to RobotAction Queue for all robots
	@RequestMapping(value = "/addAttackAction")
	public String addAttackAction(Model model) {
		gameService.addActionRAQueue(robotActionDAO.getRobotAction(1));
		return "redirect:/field";
	}
	// Add Destroy action to RobotAction Queue for all robots
	@RequestMapping(value = "/addBuildAction")
	public String addBuildAction() {
		gameService.addActionRAQueue(robotActionDAO.getRobotAction(2));
		return "redirect:/field";
	}
	// Add Destroy action to RobotAction Queue for all robots
	@RequestMapping(value = "/addMineAction")
	public String addMineAction() {
		gameService.addActionRAQueue(robotActionDAO.getRobotAction(3));
		return "redirect:/field";
	}
}
