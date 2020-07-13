package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FilesController {
    private final FilesService filesService;
    private final UserMapper userMapper;

    public FilesController(FilesService filesService, UserMapper userMapper) {
        this.filesService = filesService;
        this.userMapper = userMapper;
    }

    @GetMapping("files/delete")
    public String deleteFile(@RequestParam("id") int id){
        if(id>0){
            filesService.delete(id);
            return "redirect:result?success";
        }
        return "redirect:/result?error";
    }
    @PostMapping("/files")
    public String insertFile(Authentication authentication , MultipartFile file) throws IOException {
        String name = authentication.getName();
        User user = userMapper.selectUser(name);
        if(file.isEmpty()){
            return "redirect:/result?error";
        }
        filesService.insert(file,user.getUserid());
        return "edirect:/result?success";
    }

}
