package com.akokko.mapper;

import com.akokko.domain.Account;

import java.util.List;

public interface AccountMapper {

    void save(Account account);

    List<Account> findAll();

}
