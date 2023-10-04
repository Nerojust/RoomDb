
package com.company.appintegration.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("displayOnSideBar")
    @Expose
    private Boolean displayOnSideBar;
    @SerializedName("createdOn")
    @Expose
    private List<Integer> createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDisplayOnSideBar() {
        return displayOnSideBar;
    }

    public void setDisplayOnSideBar(Boolean displayOnSideBar) {
        this.displayOnSideBar = displayOnSideBar;
    }

    public List<Integer> getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(List<Integer> createdOn) {
        this.createdOn = createdOn;
    }

}
