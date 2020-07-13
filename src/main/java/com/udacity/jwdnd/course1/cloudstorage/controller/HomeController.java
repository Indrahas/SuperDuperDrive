package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/home")
public class HomeController {
    private final FilesService filesService;
    private final NotesService notesService;
    private final CredentialsService credentialsService;
    private final UserMapper userMapper;

    public HomeController(FilesService filesService, NotesService notesService, CredentialsService credentialsService, UserMapper userMapper) {
        this.filesService = filesService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.userMapper = userMapper;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model ) throws Exception {
        System.out.println(authentication.getName());
        String name = authentication.getName();
        User user = userMapper.selectUser(name);
        System.out.println(user.getUserid());

        System.out.println(2);
        model.addAttribute("notes", notesService.getAllNotes(user.getUserid()));
        System.out.println(3);
        model.addAttribute("credentials", credentialsService.getCredentials(user.getUserid()));
        System.out.println(4);
        model.addAttribute("files", filesService.getNotesById(user.getUserid()));
        System.out.println(5);
        return "home";
    }

}
