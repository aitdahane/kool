package com.example.med.bottommenuapp.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class User {

    @Element
    public long mId;
    @Element
    public String mName;
    @Element
    public String mEmail;
    @Element
    public String mMobile;
    @Element
    public String mPassword;
    public static long nbUser = 0;

    public User() {

    }
    public User(String name, String email, String mobile, String password) {
        nbUser++;
        mId = nbUser;
        mName = name;
        mEmail = email;
        mMobile = mobile;
        mPassword = password;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mobile) {
        mMobile = mobile;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if ((mEmail != null ? !mEmail.equals(user.mEmail) : user.mEmail != null)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mMobile != null ? mMobile.hashCode() : 0);
        return result;
    }
}
