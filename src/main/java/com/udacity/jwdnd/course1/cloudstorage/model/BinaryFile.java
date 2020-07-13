package com.udacity.jwdnd.course1.cloudstorage.model;

public class BinaryFile {
    private Integer fileid;
    private String filename;
    private String dataurl;

    public BinaryFile(Integer fileid, String filename, String dataurl) {
        this.fileid = fileid;
        this.filename = filename;
        this.dataurl = dataurl;
    }


    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDataurl() {
        return dataurl;
    }

    public void setDataurl(String dataurl) {
        this.dataurl = dataurl;
    }


}
