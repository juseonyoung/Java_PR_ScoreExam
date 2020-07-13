package com.biz.score.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.score.domain.ScoreVO;

public class ScoreServiceImplV1 implements ScoreService {

	protected List<ScoreVO> scoreList;
	protected Scanner scan;
	private String scoreFile = "";
	private String sFile="";

	public ScoreServiceImplV1() {
		scoreList = new ArrayList<>();
		scan = new Scanner(System.in);
		scoreFile = "src/com/biz/score/data/score.txt";
		sFile="src/com/biz/score/data/scoreList.txt";

		ScoreVO sv = new ScoreVO();
	}

	int size = 0;
	int count = 0;

	@Override
	public boolean inputScore() { // 성적 입력 부분
		ScoreVO sv = new ScoreVO();

		System.out.println("학번을 입력하세요(END:입력종료)>>");
		String st_num = scan.nextLine();
		if (st_num.equals("END")) {
			return false;
		}

		try {
			st_num = String.format("%03d", Integer.valueOf(st_num));
			sv.setNum(st_num);
			count++;

		} catch (Exception e) {

			System.out.println("숫자만 입력이 가능합니다!");
			System.out.println(st_num + "은 잘못된 입력입니다");
			return true;
		}

		for (ScoreVO svo : scoreList) {
			if (svo.getNum().equals(st_num)) {
				System.out.println(st_num + "번 학생의 성적이 이미 등록 되어 있어 입력할 수 없습니다");
				return false;
			}

		}

		// 국어 점수 입력 부분
		System.out.println("국어점수 입력(END:입력종료)");
		String st_kor = scan.nextLine();
		if (st_kor.equals("END")) {
			return false;
		}
		int intKor = 0;
		try {
			intKor = Integer.valueOf(st_kor);
		} catch (Exception e) {
			System.out.println("국어점수는 숫자만 입력 가능 합니다!");
			System.out.println(st_kor + "은 잘못된 입력 입니다");
			return true;
		}
		if (intKor < 0 || intKor > 100) {
			System.out.println("점수는 0점~100까지만 가능");
			System.out.println("다시 입력해 주세요");
			return true;
		}
		sv.setKor(intKor);

		// 영어 점수
		System.out.println("영어점수 입력(END:입력종료)");
		String st_eng = scan.nextLine();
		if (st_eng.equals("END")) {
			return false;
		}
		int intEng = 0;
		try {
			intEng = Integer.valueOf(st_eng);
		} catch (Exception e) {
			System.out.println("영어점수는 숫자만 입력 가능 합니다!");
			System.out.println(st_eng + "은 잘못된 입력 입니다");
			return true;
		}
		if (intEng < 0 || intEng > 100) {
			System.out.println("점수는 0점~100까지만 가능");
			System.out.println("다시 입력해 주세요");
			return true;
		}
		sv.setEng(intEng);

		// 수학 점수
		System.out.println("수학점수 입력(END:입력종료)");
		String st_math = scan.nextLine();
		if (st_math.equals("END")) {
			return false;
		}
		int intMath = 0;
		try {
			intMath = Integer.valueOf(st_math);
		} catch (Exception e) {
			System.out.println("수학점수는 숫자만 입력 가능 합니다!");
			System.out.println(st_math + "은 잘못된 입력 입니다");
			return true;
		}
		
		if (intMath < 0 || intMath > 100) {
			System.out.println("점수는 0점~100까지만 가능");
			System.out.println("다시 입력해 주세요");
			return true;
		}
		sv.setMath(intMath);

		// 음악 점수
		System.out.println("음악점수 입력(END:입력종료)");
		String st_music = scan.nextLine();
		if (st_music.equals("END")) {
			return false;
		}
		int intMusic = 0;
		try {
			intMusic = Integer.valueOf(st_music);
		} catch (Exception e) {
			System.out.println("음악점수는 숫자만 입력 가능 합니다!");
			System.out.println(st_music + "은 잘못된 입력 입니다");
			return true;
		}
		if (intMusic < 0 || intMusic > 100) {
			System.out.println("점수는 0점~100까지만 가능");
			System.out.println("다시 입력해 주세요");
			return true;
		}
		sv.setMusic(intMusic);

		scoreList.add(sv);
		this.saveScore(); // 추가 코드

		return true;
	}

	@Override
	public void saveScore() {
		PrintStream outPut = null;
		try {
			outPut = new PrintStream(scoreFile);
		} catch (Exception e) {
			// TODO: handle exception
		}

		for (ScoreVO svo : scoreList) {

			String stInfo = String.format("%s:%d:%d:%d:\n", svo.getNum(), svo.getKor(), svo.getEng(), svo.getMath(),
					svo.getMusic());
			outPut.println(stInfo);
		}
		outPut.close();
		System.out.println("학생의 성적 저장완료");

	}

	@Override
	public void loadScoreFile() {
		// TODO Auto-generated method stub
		FileReader fileReader = null;
		BufferedReader buffer = null;

		try {
			fileReader = new FileReader(scoreFile);
			buffer = new BufferedReader(fileReader);

			String reader = "";
			while (true) {
				reader = buffer.readLine();
				if (reader == null) {
					break;
				}

				String[] scores = reader.split(":");

				ScoreVO sv = new ScoreVO();
				sv.setNum(scores[0]);
				sv.setKor(Integer.valueOf(scores[1]));
				sv.setEng(Integer.valueOf(scores[2]));
				sv.setMath(Integer.valueOf(scores[3]));
				sv.setMusic(Integer.valueOf(scores[4]));
			}
			buffer.close();
			fileReader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void calcSum() {
		int sum = 0;
		for (ScoreVO sv : scoreList) {

			sum = sv.getKor();
			sum += sv.getEng();
			sum += sv.getMath();
			sum += sv.getMusic();

			sv.setSum(sum);

		}

	}

	@Override
	public void calcAvg() {
		for (ScoreVO sv : scoreList) {

			int sum = sv.getSum();
			float avg = (float) sum / 4;

			sv.setAvg(avg);

		}

	}

	@Override
	public void scoreList() {

		System.out.println("=============================================================");
		System.out.println("성적 일람표");
		System.out.println("=============================================================");
		System.out.println("학번\t|국어\t|영어\t|수학\t|음악\t|총점\t|평균\t|");
		System.out.println("--------------------------------------------------------------");
		for (ScoreVO sVO : scoreList) {
			System.out.printf("%s\t|", sVO.getNum());
			System.out.printf("%d\t|", sVO.getKor());
			System.out.printf("%d\t|", sVO.getEng());
			System.out.printf("%d\t|", sVO.getMath());
			System.out.printf("%d\t|", sVO.getMusic());
			System.out.printf("%d\t|", sVO.getSum());
			System.out.printf("%5.2f\t|\n", sVO.getAvg());
		}
		System.out.println("--------------------------------------------------------------");
		int korSum = 0;
		int engSum = 0;
		int mathSum = 0;
		int musicSum = 0;
		int sumSum = 0;
		float avgSum = 0;
		int size = 0;
		for (ScoreVO sVO : scoreList) {

			korSum += sVO.getKor();
			engSum += sVO.getEng();
			mathSum += sVO.getMath();
			musicSum += sVO.getMusic();
			sumSum = korSum;
			sumSum += engSum;
			sumSum += mathSum;
			sumSum += musicSum;
			avgSum = (float)sumSum / 4;
			size = scoreList.size();

		}

		System.out.printf("%s\t|%d\t|%d\t|%d\t|%d\t|%d\t|%5.2f\t|\n", "총점", korSum, engSum, mathSum, musicSum, sumSum,
				avgSum);
		System.out.printf("%s\t|%d\t|%d\t|%d\t|%d\t|%d\t|%5.2f\t|\n", "평균", korSum / size, engSum / size, mathSum / size,
				musicSum / size, sumSum / size, avgSum / size);
	}

	@Override
	public void saveLastscore() {
		PrintStream outPut = null;
		try {
			outPut = new PrintStream(sFile);
		} catch (Exception e) {
			// TODO: handle exception
		}

		for (ScoreVO svo : scoreList) {

			String stInfo = String.format("%s:%d:%d:%d:%d:%d:%5.2f\n", svo.getNum(), svo.getKor(), svo.getEng(), svo.getMath(),
					svo.getMusic(),svo.getSum(),svo.getAvg());
			outPut.println(stInfo);
		}
		outPut.close();
		System.out.println("학생의 성적이 저장 완료되었습니다");
		
	}
}
