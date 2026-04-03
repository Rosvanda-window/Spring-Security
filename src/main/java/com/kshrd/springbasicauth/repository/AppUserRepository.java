package com.kshrd.springbasicauth.repository;

import com.kshrd.springbasicauth.model.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface AppUserRepository {


    @Result(property = "userId", column = "user_id")
    @Result(property = "fullName", column = "full_name")
    @Result(property = "role", column = "role_id",
        one = @One(select = "findRoleNameById")
    )
    @Select("""
    SELECT * FROM app_users
    WHERE email = #{email}
    """)
    AppUser findUserByEmail(String email); //we want to return as AppUser so we changed data type it to AppUser
    //by default we can't change to AppUser but in AppUser class we implemented UserDetail so we can change to AppUser


    //we create this method because when spring security check in table app_user it can't find role name so we make
    //other method tp find role name from  table role
    @Select("""
    SELECT role_name from roles
    WHERE role_id = #{roleId}
    """)
    String findRoleNameById(Integer roleId);


}
