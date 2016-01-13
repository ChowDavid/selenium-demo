package com.david.demo.selenium_demo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;



public class AppTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	//@Test
	public void testm1(){
		System.setOut(new PrintStream(outContent));
		App.main(null);
		assertEquals("Expected Equal", "Hello World !\n", outContent.toString());
		
		System.setOut(null);
	}

}
