package com.dbs.bank.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tid;
	private long ammount;
	
	private Date date; 
	
	private LocalTime time;

	@Column(columnDefinition="boolean default false")
	private boolean flag;
	
    @ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="from_account", nullable = false)
    private Account fromAccount;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="to_account", nullable = false)
    private Account toAccount;
    
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public long getAmmount() {
		return ammount;
	}
	public void setAmmount(long ammount) {
		this.ammount = ammount;
	}
 
	public Transaction() {
		super();
	}


	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime localTime) {
		this.time = localTime;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public Transaction(long tid, long ammount, Account fromAccount, Account toAccount) {
		super();
		this.tid = tid;
		this.ammount = ammount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
}
