package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private final NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }
    public List<Notes> getAllNotes(Integer userid) throws Exception {
        List<Notes> list = notesMapper.findById(userid);
        if(list == null){throw new Exception();}
        return list;
    }
    public void insert(String notetitle ,String notedescription, Integer userid){
        notesMapper.noteInsert(notetitle,notedescription,userid);
    }
    public void delete(Integer noteid){
        notesMapper.noteDelete(noteid);
    }
    public void update(String notetitle , String notedescription , Integer noteid){
        notesMapper.noteUpdate(notetitle,notedescription,noteid);
    }
}
