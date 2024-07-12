package com.girija.banking_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.girija.banking_app.dto.AccountDto;
import com.girija.banking_app.entity.Account;
import com.girija.banking_app.mapper.AccountMapper;
import com.girija.banking_app.repository.AccountRepository;
import com.girija.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		// TODO Auto-generated constructor stub
		this.accountRepository = accountRepository;
	}
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.maptoAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		
		Account account = accountRepository.getById(id);
		return AccountMapper.maptoAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, Double amount) {
		
		Account getAccount = accountRepository.getById(id);
		double total = getAccount.getBalance() + amount;
		getAccount.setBalance(total);
		
		Account saveAfterDeposit = accountRepository.save(getAccount); 
		return AccountMapper.maptoAccountDto(saveAfterDeposit);
	}

	@Override
	public AccountDto withdraw(Long id, Double amount) {
		
		Account getAccount = accountRepository.getById(id);
		getAccount.getBalance();
		if(getAccount.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance"); 
		}
		double total = getAccount.getBalance() - amount;
		getAccount.setBalance(total);
		Account saveAfterWithdraw = accountRepository.save(getAccount);
		return AccountMapper.maptoAccountDto(saveAfterWithdraw);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts = accountRepository.findAll();
		return accounts
				.stream()
				.map(account -> AccountMapper.maptoAccountDto(account))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		
		Account getAccount = accountRepository.getById(id);
		accountRepository.deleteById(id);

	}

}
