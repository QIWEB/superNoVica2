package com.qiweb.vica;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupervicaApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println((int)((Math.random()*9+1)*10000));
		System.out.println((int)((Math.random()*9+1)*10000));
	}

}
