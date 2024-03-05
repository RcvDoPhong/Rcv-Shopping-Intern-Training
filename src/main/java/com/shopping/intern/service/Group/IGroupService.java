package com.shopping.intern.service.Group;

import java.util.ArrayList;

import com.shopping.intern.model.Group;

public interface IGroupService {
    ArrayList<Group> findAll();

    Group findById(long groupId);

    void deleteById(long groupId);
}