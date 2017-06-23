package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sun.awt.image.ImageWatched;

import java.util.List;

/**
 * Created by student on 6/23/17.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Query
    public List<Transaction> findByAccountNum(long accountNum);
    @Query
    public List<Transaction> findAllByAccountNum(long accountNum);
    @Query( value = "select SUM(amount) from transaction where account_num=0", nativeQuery=true)
    public List<Transaction> findAmountSumByAccount();
}
