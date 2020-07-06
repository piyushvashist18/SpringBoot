package com.hughes.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hughes.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
