package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sun.awt.image.ImageWatched;

import java.util.List;

/**
 * Created by student on 6/23/17.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Query( value = "select * from transaction, users where account_num=?0 AND account_num=users.username", nativeQuery=true)
    public List<Transaction> findByAccountNum(String accountNum);

    public List<Transaction> findAllByAccountNum(String accountNum);
    @Query( value = "select SUM(transaction.amount) from transaction, users where account_num=users.username", nativeQuery=true)
    public long findAmountSumByAccount(String accountNum);
}
