package com.auction.TradeRevAuction.service;

import com.auction.TradeRevAuction.model.Account;
import com.auction.TradeRevAuction.repository.AccountRepository;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AccountServiceImpl {

  private final AccountRepository repository;

  AccountServiceImpl(AccountRepository repository) {
    this.repository = repository;
  }

  public List<Account> findAll() {
    return this.repository.findAll();
  }

  public Account get(Integer id) throws NotFoundException {

    Optional<Account> value = this.repository.findById(id);

    if(!value.isPresent())
      throw new NotFoundException("Account with ${id} doesnt exist");

    return value.get();
  }

  public Account update(Account account) throws NotFoundException {

    Optional<Account> value = this.repository.findById(account.getId());

    if(!value.isPresent())
      throw new NotFoundException("Account with ${id} doesnt exist");

    return this.repository.save(account);
  }

  public void delete(Integer id) throws NotFoundException {
    Optional<Account> value = this.repository.findById(id);

    if(!value.isPresent())
      throw new NotFoundException("Account with ${id} doesnt exist");

    this.repository.delete(value.get());
  }

  public Account create(Account account) {
    return this.repository
        .save(account);
  }
}
