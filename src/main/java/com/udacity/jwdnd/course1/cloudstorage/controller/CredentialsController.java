package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialsController {
    private final CredentialsService credentialsService;

    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @GetMapping("credential/delete")
    public String deleteCredentials(@RequestParam("id") int id){
        if(id > 0){
            credentialsService.delete(id);
            return "redirect:/result?success";
        }
        return "redirect:/result?error" ;
    }

    @PostMapping("/credential")
    public String postCredentials(Authentication authentication, Credentials credentials){

        if(credentials.getCredentialid()>0){
            credentialsService.update(credentials.getUrl(),credentials.getUsername(),credentials.getKey(),credentials.getPassword(),credentials.getUserid());
            return "redirect:/result?success";
        }
        credentialsService.insert(credentials.getUrl(),credentials.getUsername(),credentials.getKey(),credentials.getPassword(),credentials.getUserid());
        return "redirect:/result?success";
    }


}
