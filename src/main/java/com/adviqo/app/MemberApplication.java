package com.adviqo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}

	@Component
	public class ApplicationLoader implements CommandLineRunner {
		
		@Autowired
		MemberRepository memberRepository;

		@Override
		public void run(String... strings) throws Exception {
			for (Member m : memberRepository.findAll()) {
				System.out.println(m.toString());
			}
		}
	}
}
