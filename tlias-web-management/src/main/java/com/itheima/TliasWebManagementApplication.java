package com.itheima;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.List;
@MapperScan("com.itheima.mapper") // 扫描MyBatis的Mapper接口
@ServletComponentScan //开启对Servlet组件的支持
@SpringBootApplication
public class TliasWebManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasWebManagementApplication.class, args);
	}

	@Autowired
	private EmpMapper empMapper;


}
