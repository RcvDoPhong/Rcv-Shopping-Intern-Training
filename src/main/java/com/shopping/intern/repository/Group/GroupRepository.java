package com.shopping.intern.repository.Group;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.shopping.intern.mapper.GroupMapper;
import com.shopping.intern.model.Group;

@Repository
@MapperScan("com.shopping.intern.mapper")
public class GroupRepository implements IGroupRepository {

    private final GroupMapper groupMapper;

    public GroupRepository(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public ArrayList<Group> findAll() {
        return this.groupMapper.findAll();
    }

    @Override
    public Group findById(long groupId) {
        return this.groupMapper.findById(groupId);
    }

    @Override
    public void deleteById(long groupId) {
        this.groupMapper.deleteById(groupId);
    }
}
