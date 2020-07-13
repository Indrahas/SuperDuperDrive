package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialsService {
    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }
    public Credentials encPassword(Credentials credentials){
        String key = RandomStringUtils.random(16,true,true);
        credentials.setKey(key);
        credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(),key));
        return credentials;
    }

    public String decPassword(String password , String key){
        return encryptionService.decryptValue(password,key);
    }

    public List<Credentials> getCredentials(int userid) throws Exception{
        List<Credentials> list= credentialsMapper.getById(userid);
        List<Credentials> credentials = new ArrayList<Credentials>();
        if (list == null){throw new Exception();}
        for(int i=0;i<list.size();i++){
            Credentials credential = list.get(i);
            credential.setPassword(decPassword(credential.getPassword(),credential.getKey()));
            credentials.add(credential);
        }
        return credentials;
    }
    public void insert(String url, String username, String key, String password, Integer userid){
        credentialsMapper.insertCredentials(url, username, key, password, userid);
    }
    public void delete(int credentialid){
        credentialsMapper.deleteCredentials(credentialid);
    }
    public void update(String url, String username, String key, String password, Integer userid){
        credentialsMapper.updateCredentials(url, username, key, password, userid);
    }


}
