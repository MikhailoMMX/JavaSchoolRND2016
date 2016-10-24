package ru.sbt.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class TestController {
    @GetMapping("/world")
    //@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> showAccounts(@PathVariable String name){
        return new ResponseEntity<String>("Hello, world", HttpStatus.OK);
    }
}
