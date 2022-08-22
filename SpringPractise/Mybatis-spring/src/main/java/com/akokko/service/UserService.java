package com.akokko.service;

import com.akokko.domain.Account;

import java.util.List;

public interface UserService {

    void save();

    List<Account> findAll();

}
