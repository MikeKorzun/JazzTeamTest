package by.controller;

import by.dao.RobotActionDAO;
import by.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
* REST Endpoint	HTTP Method	Description
* GET	Returns the list of customers
* GET	Returns customer detail for given customer {id}
* POST	Creates new customer from the post data
* PUT	Replace the details for given customer {id}
* DELETE	Delete the customer for given customer {id}
*/

@RestController
public class FieldControllerREST {

    @Autowired
    private GameService gameService;
    @Autowired
    private RobotActionDAO robotActionDAO;

    @GetMapping(value = "/StartGameREST")
    public void startGameREST(Model model) {
        gameService.createRobotActionsQueue();
        gameService.initRobots();
        model.addAttribute("robotsList", gameService.getRobotList());
    }

    @GetMapping(value = "/addDestroyYourselfActionREST")
    public ResponseEntity addDestroyYourselfActionREST() {
        gameService.addActionRAQueue(robotActionDAO.getRobotAction(0));
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addAttackActionREST")
    public ResponseEntity addAttackActionREST() {
        gameService.addActionRAQueue(robotActionDAO.getRobotAction(1));
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addBuildActionREST")
    public ResponseEntity addBuildActionREST() {
        gameService.addActionRAQueue(robotActionDAO.getRobotAction(2));
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addMineActionREST")
    public ResponseEntity addMineActionREST() {
        gameService.addActionRAQueue(robotActionDAO.getRobotAction(3));
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/addDestroyYourselfToRobotREST/{id}")
    public ResponseEntity addDestroyYourselfToRobotREST(@PathVariable("id") Long id) {
        gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(0), id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addAttackToRobotREST/{id}")
    public ResponseEntity addAttackToRobotREST(@PathVariable("id") Long id) {
        gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(1), id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addBuildToRobotREST/{id}")
    public ResponseEntity addBuildToRobotREST(@PathVariable("id") Long id) {
        gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(2), id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addMineToRobotREST/{id}")
    public ResponseEntity addMineToRobotREST(@PathVariable("id") Long id) {
        gameService.addActionRAQueueToRobot(robotActionDAO.getRobotAction(3), id);
        return new ResponseEntity(HttpStatus.OK);
    }
}