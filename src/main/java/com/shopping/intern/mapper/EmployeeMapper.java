package com.shopping.intern.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.shopping.intern.model.Employee;

@Mapper
public interface EmployeeMapper {
    // @Select("SELECT * FROM employees")
    List<Employee> findAll();

    // @Insert("INSERT INTO employees (first_name, last_name, email_address) VALUES (#{first_name}, #{last_name}, #{email_address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Employee employee);

    // @Select("SELECT * FROM employees WHERE id = #{id}")
    Employee findById(long id);
}
