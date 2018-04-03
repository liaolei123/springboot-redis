package com.kk.domain;


import java.io.Serializable;

/**
 * @author Mr.kk
 */
public class User implements Serializable{
    //id
    private String id;
    //姓名
    private String name;
    //电子邮箱
    private String eMial;
    //地址
    private String address;

    public User(String id, String name, String eMial, String address) {
        this.id = id;
        this.name = name;
        this.eMial = eMial;
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void seteMial(String eMial) {
        this.eMial = eMial;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String geteMial() {
        return eMial;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", eMial='").append(eMial).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
