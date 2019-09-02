package com.dbs.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.bank.model.Transaction;
import com.dbs.bank.service.EmailService;

@RestController
@RequestMapping("/api/v1")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
   @GetMapping(value = "/sendemail/{email}/{title}/{message}")
   public String sendEmail(@PathVariable("email") String email,@PathVariable("title") String title,@PathVariable("message") String message) {
	   this.emailService.sendMail(email, title, message);
      return "Email sent successfully";
   }   
}