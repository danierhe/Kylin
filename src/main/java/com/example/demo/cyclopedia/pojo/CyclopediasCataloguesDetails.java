package com.example.demo.cyclopedia.pojo;

public class CyclopediasCataloguesDetails {
    private String id;

    private String cyclopediasId;

    private String catalogueId;

    private String content;

    private String userId;

    private String companyId;

    private String status;

    private String createTime;

    private String updateTime;

    private String cataloguesName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCyclopediasId() {
        return cyclopediasId;
    }

    public void setCyclopediasId(String cyclopediasId) {
        this.cyclopediasId = cyclopediasId == null ? null : cyclopediasId.trim();
    }

    public String getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(String catalogueId) {
        this.catalogueId = catalogueId == null ? null : catalogueId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getCataloguesName() {
        return cataloguesName;
    }

    public void setCataloguesName(String cataloguesName) {
        this.cataloguesName = cataloguesName;
    }
}