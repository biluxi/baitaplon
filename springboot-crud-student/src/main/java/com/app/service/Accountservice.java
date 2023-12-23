package com.app.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Account;
import com.app.entity.Product;
import com.app.repository.AccountRepository;

@Service
public class Accountservice  {
	
	private final AccountRepository accountRepository;

	@Autowired
    public Accountservice(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        
    }
	
	 @Transactional
	    public void deleteProductById(Long id) {
	        try {
	        	accountRepository.deleteById(id);
	            // Log thông báo xóa thành công
	            System.out.println("account with ID " + id + " deleted successfully");
	        } catch (Exception e) {
	            // Log lỗi xóa
	            System.err.println("Error deleting product with ID " + id);
	            e.printStackTrace();
	            throw new RuntimeException("Error deleting product with ID " + id, e);
	        }
	    }
	 @Transactional
	    public Account saveAccount(Account account) {
	        return accountRepository.save(account);
	    }
	  public Account getAccountById(Long accountId) {
	        return accountRepository.findById(accountId).orElse(null);
	    }
	  public Optional<Account> getAccById(Long accountId) {
	        return accountRepository.findById(accountId);
	    }
	  public boolean isValidLogin(String username, String password) {
	        // Thực hiện kiểm tra đăng nhập dựa trên tên người dùng và mật khẩu
	        Account account = accountRepository.findByUsername(username);
	        return account != null && account.getPasword().equals(password);
	    }
	
}
