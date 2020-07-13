package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("SELECT * FROM NOTES")
    List<Notes> findAll();

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Notes> findById(Integer userid);

    @Insert("INSERT INTO NOTES(notetitle , notedescription , userid)" +
            "VALUES (#{notetitle} , #{notedescription} , #{userid})")
    int noteInsert(String notetitle ,String notedescription, Integer userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int noteDelete(Integer noteid);

    @Update("UPDATE NOTES SET notetitle = #{notetitle} notedescription = #{notedescription} where noteid = #{noteid}")
    int noteUpdate(String notetitle , String notedescription , Integer noteid);
}
