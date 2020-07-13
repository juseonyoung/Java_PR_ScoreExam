package com.biz.score.service;

public interface ScoreService {

	
	public boolean inputScore(); //입력하는 곳
	public void saveScore();
	public void loadScoreFile();
	
	public void calcSum();
	public void calcAvg();
	
	public void scoreList();
	public void saveLastscore();
}
