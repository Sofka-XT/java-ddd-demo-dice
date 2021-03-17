package org.example.nomemientan.infra.command;

import co.com.sofka.application.ApplicationCommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping(value = "api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommandController {

    @Autowired
    private ApplicationCommandExecutor applicationCommandExecutor;

    @PostMapping(value = "command", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void command(@RequestBody final Map<String, String> args )  {
        applicationCommandExecutor.execute(args);
    }


}