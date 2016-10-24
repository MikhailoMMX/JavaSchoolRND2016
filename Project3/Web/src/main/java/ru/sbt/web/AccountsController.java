package ru.sbt.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sbt.data.Account;
import ru.sbt.data.Client;
import ru.sbt.hibernateutil.DataController;
import ru.sbt.hibernateutil.IDataController;

import java.util.List;

@RestController
public class AccountsController {
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/accounts/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> showAccounts(@PathVariable String name){
        IDataController dataController = new DataController();

        Client client = dataController.getClientByName(name);
        List<Account> accounts = dataController.getAccounts(client);

        return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
    }
}
