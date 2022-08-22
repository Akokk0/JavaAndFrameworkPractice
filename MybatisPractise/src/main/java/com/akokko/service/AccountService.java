package com.akokko.service;

import com.akokko.domain.Account;

import java.util.List;

public interface AccountService {

    void save(Account account);

    List<Account> findAll();
}
