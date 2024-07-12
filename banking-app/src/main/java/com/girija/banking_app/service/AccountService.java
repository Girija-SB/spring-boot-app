package com.girija.banking_app.service;

import java.util.List;

import com.girija.banking_app.dto.AccountDto;
import com.girija.banking_app.entity.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, Double amount);
	
	AccountDto withdraw(Long id, Double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
