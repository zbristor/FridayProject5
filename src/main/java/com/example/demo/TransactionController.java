package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Date;

/**
 * Created by student on 6/23/17.
 */
@Controller
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(new Transaction());
        return "index";
    }
    @GetMapping("/check")
    public String getLogin(Model model, @ModelAttribute Transaction transaction)
    {
        model.addAttribute(new Transaction());

        return "index";
    }
    @PostMapping("/check")
    public String goLogin( Model model, @ModelAttribute Transaction transaction, Principal principal) {
        model.addAttribute(new Transaction());

        Iterable<Transaction> values = transactionRepository.findByAccountNum(transaction.getAccountNum());

        model.addAttribute("values", values);
        Iterable<Transaction> transactionList = transactionRepository.findAllByAccountNum(transaction.getAccountNum());
        model.addAttribute("transactionList", transactionList);
        return "transactionhistory";
         // need to add in conditional to tell it to go to the next page IF the account number is in the db
    }
    @RequestMapping("/transactionhistory")
    public String goTransactionHistory( Model model, @ModelAttribute Transaction transaction) {
        Iterable<Transaction> transactionList = transactionRepository.findAllByAccountNum(transaction.getAccountNum());
        model.addAttribute("transactionList", transactionList);
        return "transactionhistory";
    }
    @GetMapping("/withdraw")
    public String getWithdraw(Model model, @ModelAttribute Transaction transaction)
    {
        transaction.setAmount(-transaction.getAmount());
        model.addAttribute(new Transaction());
        return "withdraw";
    }
    @PostMapping("/withdraw")
    public String postWithdraw(Model model, @ModelAttribute Transaction transaction)
    {
        model.addAttribute(new Transaction());
        transaction.setAmount(-transaction.getAmount());
        transaction.setAction("Withdraw");
        Iterable<Transaction> balanceList = transactionRepository.findAmountSumByAccount();
        model.addAttribute("balancelist",balanceList);
        transactionRepository.save(transaction);
        return "withdraw";
    }
    @GetMapping("/deposit")
    public String getDeposit(Principal principal, Model model, @ModelAttribute Transaction transaction)
    {
        model.addAttribute(new Transaction());
        return "deposit";
    }
    @PostMapping("/deposit")
    public String postDeposit(Model model, @ModelAttribute Transaction transaction)
    {
        model.addAttribute(new Transaction());
        transaction.setAction("Deposit");
        transactionRepository.save(transaction);
        return "deposit";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}