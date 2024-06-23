package com.lesnoy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lesnoy.config.GHRepositoryDeserializer;

import java.util.Date;

@JsonDeserialize(using = GHRepositoryDeserializer.class)
public class GHRepositoryDTO {
    private String fullname;
    private String repUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updated;
    private String language;

    public GHRepositoryDTO() {
    }

    public GHRepositoryDTO(String fullname, String repUrl, Date created, Date updated, String language) {
        this.fullname = fullname;
        this.repUrl = repUrl;
        this.created = created;
        this.updated = updated;
        this.language = language;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRepUrl() {
        return repUrl;
    }

    public void setRepUrl(String repUrl) {
        this.repUrl = repUrl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
