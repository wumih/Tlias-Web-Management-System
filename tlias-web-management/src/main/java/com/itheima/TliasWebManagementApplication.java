package com.itheima;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.List;
@ServletComponentScan //开启对Servlet组件的支持
@SpringBootApplication
public class TliasWebManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasWebManagementApplication.class, args);
	}

	@Autowired
	private EmpMapper empMapper;


}
