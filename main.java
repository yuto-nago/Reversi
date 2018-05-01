package osero;

import java.io.*;
public class main {

	//盤面の値を所持した二次元配列
	private static int[][] banmen;

	//黒が2、白が1
	private static int turn;

	public static void main(String[] args) {
		turn = 2;
		banmen = new int[8][8];
		clear(banmen);
		do{

			display();




		}while(retry() != 0);

	}

	//盤面を初期化するメソッド
	public static int[][] clear(int banmen[][]){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if((i == 3 && j == 3) || (i == 4 && j == 4)){
					banmen [i][j] = 1;
				}else if((i == 3 && j == 4) || (i == 4 && j == 3)){
					banmen [i][j] = 2;
				}else{
					banmen[i][j] = 0;
				}

			}
		}
		return banmen;
	}


	//ゲーム進行メソッド
	public static void play(){


		while(turn < 3){
			if(turn == 2){
				System.out.println("●のターン");
			}else if(turn == 1){
				System.out.println("○のターン");
			}
			if(turn == 2){
				turn = 1;
			}else if(turn == 1){
				turn = 2;
			}


		}

		judge();

	}
	//盤面をコンソールに出力するメソッド
	public static void display(){
		System.out.print("   ");
		for(int i = 0; i < 8; i++){
			System.out.print(i + 1 + "  ");
		}
		System.out.println();
		for(int i = 0; i < 8; i++){
			System.out.print((i + 1) * 10 + " ");
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 1){
					System.out.print("○ ");
				}else if(banmen[i][j] == 2){
					System.out.print("● ");
				}else{
					System.out.print("＊ ");
				}
			}
			System.out.println();
		}
	}
	//オセロを続けるか、やめるか選ぶメソッド
	public static int retry(){
		clear(banmen);
		int x;
		System.out.print("ゲームを続けますか？はい=1、いいえ=0 ");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{
                String buf = br.readLine();
                x = Integer.parseInt(buf);
        }catch(Exception e){
                x = 0;
        }
        return x;
	}
	//盤面の石の数を数えるメソッド
	public static void judge(){
		int black = 0;
		int white = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 2){
					black++;
				}else if(banmen[i][j] == 1){
					white++;
				}
			}
		}
		if(black < white){
			System.out.println("白の勝ち");
		}else if(black > white){
			System.out.println("黒の勝ち");
		}else if(black == white){
			System.out.println("引き分け");
		}
	}




}







