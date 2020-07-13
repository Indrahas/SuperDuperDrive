package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS")
    public List<Credentials> getAll(Integer id);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    public List<Credentials> getById(Integer credentialid);

    @Insert("INSERT INTO CREDENTIALS(url , username , key , password , userid)" +
            "VALUES(#{url} , #{username} , #{key} , #{password} , #{userid})")
    public Integer insertCredentials(String url, String username, String key, String password, Integer userid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    public Integer deleteCredentials(Integer credentialid);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    public Integer updateCredentials(String url, String username, String key, String password, Integer userid);
}
