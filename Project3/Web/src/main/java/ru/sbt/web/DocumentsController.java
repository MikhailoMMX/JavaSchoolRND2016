package ru.sbt.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sbt.data.Account;
import ru.sbt.data.Document;
import ru.sbt.hibernateutil.DataController;
import ru.sbt.hibernateutil.IDataController;

import java.util.List;

@Controller
public class DocumentsController {
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/documents/{accNum}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> showDocuments(@PathVariable String accNum){
        IDataController dataController = new DataController();

        Account account = dataController.getAccountByNumber(accNum);
        List<Document> documents = dataController.getDocumentsForAccount(account);

        return new ResponseEntity<List<Document>>(documents, HttpStatus.OK);
    }
}
