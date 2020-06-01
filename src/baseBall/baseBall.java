package baseBall;

import java.util.Scanner;

public class baseBall {
	
	int r_num[];   // 3개의 랜덤 수를 저장할 변수.
	int u_num[];   // 게임의 유저가 입력할 변수.
	boolean b[];   // 중복된 랜덤수를 체크할 판정형 변수.
	boolean check; // 중복된 입력수를 판정할 변수. 
	boolean clear; // 스트라이크 3개일 때 판정할 변수. 
	int r; //랜덤 수 
	int w; //
	
	
	
	public void init() {// 변수 및 랜덤 수를 초기화.
		
		r_num = new int[3];
		u_num = new int[3];
		b = new boolean[10];
		check = false;
		clear = false;
		
		r = 0;
		w = 0;
		rand_num();
	}
	
	public void rand_num() { //3개의 난수 생성.
		
		for (int i = 0; i < b.length; i++) {
			b[i] =false;
		}		
		while(r<3) { //중복없는 3개의 수를 생성할 때 까지 반복.
			r = (int)(Math.random()*10);
			if(b[r]==false) {
				b[r]=true;
				r_num[w]=r+1;
				r++;
			}
		}
		for (int i = 0; i < r_num.length; i++) {
			System.out.println(r_num[i]);
		}
	}
	
	public void u_Input() { //사용자 입력.
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("세 개의 수를 입력하세요.");
			
			for (int i = 0; i < u_num.length; i++) {
				u_num[i]=sc.nextInt();
			}
			
			out:for (int i = 0; i < u_num.length; i++) {
					for (int j = 0; j < u_num.length; j++) {
						if(u_num[i]==u_num[j]&&i!=j) {//입력한 수들을 전부 비교해서 자신을 뺀 나머지에서 중복이 있으면 순환을 빠져나가서 다시 입력 반복.
							check =true;
							break out;
						}		
				}	
			}
			if(check) {
				System.out.println("중복된 수가 있습니다.");
				continue;
			}
			else break;		
		}			
	}
	
	public boolean finding(){
		
		int strike=0;
		int ball=0;
		boolean clear = false;
		for (int i = 0; i < u_num.length; i++) {
			for (int j = 0; j < r_num.length; j++) {
				if(u_num[i]==r_num[j]&&i==j) {// 스트라이크일 경우
					strike++;
				}
				else if(u_num[i]==r_num[j]) {//볼일 경우
					ball++;
				}
			}	
		}
		if(strike>2) {//스트라이크가 3개이면 clear에 true를 대입해서 빠져나가 클리어했다는 내용을 출력.
			clear = true;
		}
		
		System.out.println("스트라이크 : "+ strike+" 볼 : "+ball);
		return clear;
	}
	
	public void result(boolean clear) {
		
		if(clear) {
			System.out.println("Game Clear!!");
		}else {
			System.out.println("Game Over~ ");
		}	
	}
	
	public void loop() {
		
		while(w<10) {
			u_Input();
			clear = finding();
			if(clear) break;
			w++;	
		}
		result(clear);
	}	
}
