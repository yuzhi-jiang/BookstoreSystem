package com.example.BookstoreSystem.client.bean;

import com.example.BookstoreSystem.client.entity.Permission;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Set;
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BRole implements Serializable {
    private int id;
    private String name;




    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * 角色对应权限集合
     */
    private Set<Permission> permissions;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//
//    public Role(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Role(int id, String name, Set<Permissions> permissions) {
//        this.id = id;
//        this.name = name;
//        this.permissions = permissions;
//    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
