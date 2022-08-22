package com.akokko.mapper;

import com.akokko.domain.Account;

import java.util.List;

public interface UserMapper {

    void save();

    List<Account> findAll();

}
