package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotesController {
    private final NotesService notesService;
    private final UserMapper userMapper;

    public NotesController(NotesService notesService, UserMapper userMapper) {
        this.notesService = notesService;
        this.userMapper = userMapper;
    }

    @GetMapping("/notes/delete")
    public String DelNotes(@RequestParam("id") int id){
        if(id > 0){
            notesService.delete(id);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }

    @PostMapping("/notes")
    public String Insert_and_Update_Notes(Authentication authentication , Notes notes){
        String name = authentication.getName();
        User user = userMapper.selectUser(name);
        if(notes.getNoteid() > 0){
            notesService.update(notes.getNotetitle(), notes.getNotedescription(), notes.getNoteid());
        }
        else{
            notesService.insert(notes.getNotetitle(), notes.getNotedescription(), user.getUserid());
        }
        return "redirect:/result?success";
    }

}
