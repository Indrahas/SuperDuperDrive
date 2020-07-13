package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.BinaryFile;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FilesService {
    private final FilesMapper filesMapper;

    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public BinaryFile getBinary(Files file){
        String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
        String url = "data:"+file.getContenttype()+";base64"+base64;
        BinaryFile binaryFile = new BinaryFile(file.getFileid(),file.getFilename(),url);
        return binaryFile;
    }
    public List<BinaryFile> getNotesById(Integer userid) throws Exception {
        List<Files> list = filesMapper.getById(userid);
        List<BinaryFile> binaryFiles= new ArrayList<BinaryFile>();
        if(list == null){
            throw new Exception();
        }
        Integer fileSize = list.size();
        for(int i = 0; i<fileSize ; i++ ){
            binaryFiles.add(getBinary(list.get(i)));
        }
        return binaryFiles;
    }
    public void insert(MultipartFile file, Integer userid) throws IOException {
        filesMapper.fileInsert(file.getOriginalFilename(),file.getContentType(),(int) file.getSize(),file.getBytes(),userid);
    }
    public void delete(Integer fileid){
        filesMapper.fileDelete(fileid);

    }
}
