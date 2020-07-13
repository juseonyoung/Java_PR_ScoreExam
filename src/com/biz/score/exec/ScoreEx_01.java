package com.biz.score.exec;

import java.util.Scanner;

import com.biz.score.service.ScoreService;
import com.biz.score.service.ScoreServiceImplV1;

public class ScoreEx_01 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		ScoreService sService = new ScoreServiceImplV1();

		while (true) {

			System.out.println("==============================================");
			System.out.println("1.학생 성적입력");
			System.out.println("2.학생 정보출력");
			System.out.println("3.출력된 정보 저장");
			System.out.println("BYE.서비스 종료");
			System.out.println("==============================================");

			String stMenu = scan.nextLine();
			if (stMenu.equals("BYE")) {
				break;
			}
			int intMenu = 0;
			try {
				intMenu = Integer.valueOf(stMenu);
			} catch (Exception e) {
				System.out.println("메뉴는 숫자로만 선택할 수 있음!");
				continue;
			}

			if (intMenu == 1) {
				while (true) {
					if (!sService.inputScore()) {
						break;
					}
					sService.calcSum();
					sService.calcAvg();
					
				}
			} else if (intMenu == 2) { // 아까 1을 선택했으면 1번 무시해라(메뉴선택 2번이 나옴)

			
				sService.calcSum();
				sService.calcAvg();
				sService.scoreList();
			} else if (intMenu == 3) {
				System.out.println("파일을 저장하시겠습니까?");
				sService.saveLastscore();
			}
		}
		// while문 끝나고 !!!!
		System.out.println("업무종료! 퇴근~~");

		//sService.inputScore();
		//sService.calcSum();
		//sService.calcAvg();
		//sService.saveScore();
		//sService.loadScoreFile();
		//sService.scoreList();

	}
}
