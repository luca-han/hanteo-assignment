package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.CategoryManager;

import lombok.RequiredArgsConstructor;


@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {
	@Autowired
	final CategoryManager manager;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
			
			System.out.println("초기 데이터 추가");
			// 카테고리 추가
			manager.addCategory(1, "남자", 0);  // 루트 아래 남자 카테고리
			manager.addCategory(2, "여자", 0);  // 루트 아래 여자 카테고리

			manager.addCategory(3, "엑소", 1); // 남자 아래 엑소 카테고리
			manager.addCategory(4, "방탄소년단", 1);  // 남자 아래 방탄소년단 카테고리
			manager.addCategory(5, "블랙핑크", 2);  // 여자 아래 블랙핑크 카테고리
			
			// 각 카테고리에 게시판 추가
			manager.addBoard(2, "첸", false, 3); // 엑소 카테고리 게시판
			manager.addBoard(3, "백현", false, 3); //엑소 카테고리 게시판
			manager.addBoard(4, "시우민", false, 3); //엑소 카테고리 게시판
			manager.addBoard(7, "뷔", false, 4); //방탄소년단 카테고리 게시판
			manager.addBoard(9, "로제", false, 3); //블랙핑크 카테고리 게시판
			
			// 공지사항: 이름은 같지만 서로 다른 게시판
			manager.addBoard(1, "공지사항", false, 3); // 엑소 카테고리의 공지사항
			manager.addBoard(5, "공지사항", false, 4); // 방탄소년단 카테고리의 공지사항
			manager.addBoard(8, "공지사항", false, 5); // 블랙핑크 카테고리의 공지사항
			
			// 익명 게시판: 같은 게시판이 여러 카테고리에 소속
			manager.addBoard(6, "익명게시판", true, 4); // 방탄소년단 카테고리의 익명게시판
			manager.addBoard(6, "익명게시판", true, 5); // 블랙핑크 카테고리의 익명게시판 (동일한 게시판)
		};
    }

}
