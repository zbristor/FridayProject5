package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/login")
    public String getLogin(Model model, @ModelAttribute Transaction transaction)
    {
        model.addAttribute(new Transaction());

        return "index";
    }
    @PostMapping("/login")
    public String goLogin( Model model, @ModelAttribute Transaction transaction) {
        model.addAttribute(new Transaction());
        Iterable<Transaction> values = transactionRepository.findByAccountNum(transaction.getAccountNum());
        model.addAttribute("values", values);
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
    public String getDeposit(Model model, @ModelAttribute Transaction transaction)
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
/*  @GetMapping(/withdraw)
        Iterable<Transaction> transactionList = transactionRepository.findAllByAccountNum(transaction.getAccountNum());
        model.addAttribute("transactionList", transactionList);
    @RequestMapping("/transactionhistory")
    public String goUserSubmission( Model model) {
        Iterable<Link> linkListUser = linkRepository.findAllByUser("Zack");
        model.addAttribute("linkListUser", linkListUser);
        return "Usersubmissions";
    }

    @PostMapping("/addsubmit")
    public String addSubmit(@ModelAttribute Link link, Model model) {
        link.setTime(new Date());
        model.addAttribute(new Link());
        linkRepository.save(link);
        Iterable<Link> linkList = linkRepository.findAllOrderedByTime();
        model.addAttribute("linkList", linkList);

        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
*/
}