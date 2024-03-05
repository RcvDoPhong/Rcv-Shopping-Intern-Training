package com.shopping.intern.service.Group;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.shopping.intern.model.Group;
import com.shopping.intern.repository.Group.IGroupRepository;

@Service
public class GroupService implements IGroupService {

    private final IGroupRepository groupRepo;

    public GroupService(IGroupRepository groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Override
    public ArrayList<Group> findAll() {
        return this.groupRepo.findAll();
    }

    @Override
    public Group findById(long groupId) {
        return this.groupRepo.findById(groupId);
    }

    @Override
    public void deleteById(long groupId) {
        this.groupRepo.deleteById(groupId);
    }
    
}
