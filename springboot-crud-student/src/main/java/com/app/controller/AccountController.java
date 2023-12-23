package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Account;
import com.app.repository.AccountRepository;
import com.app.service.Accountservice;

@RestController
//đây là api chung 
@RequestMapping("/api")
// đường link để đẩy dữ liệu lên FE
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class AccountController {

	// đây để trỏ tới AccountRepository trong mục Repositor
	@Autowired
	private AccountRepository accRepo;

	// đây là api lấy tất cả dữ liệu trong Acc
	@GetMapping("/listAccount")
	public ResponseEntity<List<Account>> getAllAcc() {
		List<Account> accLits = accRepo.findAll();
		if (accLits.isEmpty()) {
			// trả ra không có dữ liệu
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(accLits, HttpStatus.OK);
	}

	@Autowired
	public AccountController(Accountservice accService) {
		this.accService = accService;
	}

	private final Accountservice accService;

	@DeleteMapping("/accountDelete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		accService.deleteProductById(id);
		return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
	}

	@PostMapping("/accountAdd")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account savedAccount = accService.saveAccount(account);
		return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
	}

	@PutMapping("/accountUp/{Id}")
	public ResponseEntity<Account> updateAccount(@PathVariable("Id") Long accounttId,
			@RequestBody Account updatedAccount) {
		Optional<Account> existingAccountOptional = accService.getAccById(accounttId);

		if (existingAccountOptional.isPresent()) {
			Account existingAccount = existingAccountOptional.get();

			// Cập nhật các trường của sản phẩm hiện tại với các giá trị mới
			existingAccount.setUsername(updatedAccount.getUsername());
			existingAccount.setPasword(updatedAccount.getPasword());
			existingAccount.setPrivleges(updatedAccount.getPrivleges());

			// Lưu sản phẩm đã cập nhật
			Account savedAccount = accService.saveAccount(existingAccount);

			return ResponseEntity.ok(savedAccount);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//  API tìm ID tài khoản 
	@GetMapping("/account/{accountId}")
	public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Long accounttId) {
		Account account = accService.getAccountById(accounttId);
		if (account != null) {
			return ResponseEntity.ok(account);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
//	Thanh
	 @PostMapping("/login")
	    public String login(Account Acc) {
	        // Xử lý logic đăng nhập ở đây
	        return "Login successful";
	    }
}
