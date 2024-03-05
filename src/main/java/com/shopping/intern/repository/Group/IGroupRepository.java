package com.shopping.intern.repository.Group;

import java.util.ArrayList;

import com.shopping.intern.model.Group;

public interface IGroupRepository {
    ArrayList<Group> findAll();

    Group findById(long groupId);

    void deleteById(long groupId);
}
