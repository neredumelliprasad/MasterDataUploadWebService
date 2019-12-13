package com.eunice.sap.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SAPIntegrationController {

    @RequestMapping(value = "/process", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public String processData(@RequestBody String data)
    {
        return "Hello, we yet to process data...!!!";
    }
}
