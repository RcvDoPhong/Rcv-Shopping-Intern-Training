package com.shopping.intern.mapper;

import java.util.ArrayList;

import com.shopping.intern.model.Group;

public interface GroupMapper {
    ArrayList<Group> findAll();

    Group findById(long groupId);

    void deleteById(long groupId);
}
