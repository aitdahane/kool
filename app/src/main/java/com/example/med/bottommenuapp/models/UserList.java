package com.example.med.bottommenuapp.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Med on 6/11/2017.
 */
@Root
public class UserList {
    @ElementList
    public ArrayList<User> userlist;

}
