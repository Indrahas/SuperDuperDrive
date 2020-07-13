package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {
    @Select("SELECT * FROM FILES ")
    public List<Files> getAll(Integer id);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    public List<Files> getById(Integer userid);

    @Insert("INSERT INTO FILES( filename , contenttype , filesize , filedata , userid)" +
            "VALUES( #{filename} , #{contenttype} , #{filesize} , #{filedata} , #{userid})")
    public int fileInsert(String filename, String contenttype, Integer filesize, byte[] filedata, Integer userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    public int fileDelete(Integer fileid);


}
