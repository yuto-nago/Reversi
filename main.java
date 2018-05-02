package osero;

import java.io.*;
public class main {

	//盤面の値を所持した二次元配列
	private static int[][] banmen;

	//黒が2、白が1
	private static int turn;

	//入力された値を保持する変数
	private static int place;

	public static void main(String[] args) {
		turn = 2;
		banmen = new int[8][8];
		clear(banmen);
		display();
		if(turn == 2){
			System.out.println("●のターン");
		}else if(turn == 1){
			System.out.println("○のターン");
		}
		place = input();

		int i = place / 10;
		int j = place % 10;

		white_flip(i, j);
		display();

//		do{
//
//			display();
//
//
//
//
//		}while(retry() != 0);

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
//	public static void play(){
//
//
//		while(turn < 3){
//			if(turn == 2){
//				System.out.println("●のターン");
//			}else if(turn == 1){
//				System.out.println("○のターン");
//			}
//			if(turn == 2){
//				turn = 1;
//			}else if(turn == 1){
//				turn = 2;
//			}
//
//
//		}
//
//		judge();
//
//	}
	//盤面をコンソールに出力するメソッド
	public static void display(){
		System.out.print("   ");
		for(int i = 0; i < 8; i++){
			System.out.print(i + "  ");
		}
		System.out.println();
		for(int i = 0; i < 8; i++){
			System.out.print((i) * 10 + " ");
			if(i == 0){
				System.out.print(" ");
			}
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

	//白の石を挟めるか調べるメソッド
	public static int black_check(int i, int j){

		//上方向を調べる
		if(banmen[i-1][j] == 1){
			for(int a = i - 1; a > 0; a--){
				if(banmen[a][j] == 2){
					return 1;
				}
			}

		//右斜め上方向を調べる
		}else if(banmen[i-1][j+1] == 1){
			int b = j + 1;
			for(int a = i - 1; a > 0 || b < 8; a--){
				if(banmen[a][b] == 2){
					return 1;
				}
				b++;
			}

		//右方向を調べる
		}else if(banmen[i][j+1] == 1){
			for(int a = j + 1; a < 8; a++){
				if(banmen[i][a] == 2){
					return 1;
				}
			}

		//右斜め下方向を調べる
		}else if(banmen[i+1][j+1] == 1){
			int b = j + 1;
			for(int a = i + 1; a < 8 || b < 8; a++){
				if(banmen[a][b] == 2){
					return 1;
				}
				b++;
			}

		//下方向を調べる
		}else if(banmen[i+1][j] == 1){
			for(int a = i + 1; a < 8; a++){
				if(banmen[a][j] == 2){
					return 1;
				}
			}

		//左斜め下方向を調べる
		}else if(banmen[i+1][j-1] == 1){
			int b = j - 1;
			for(int a = i + 1; a < 8 || b > 0; a++){
				if(banmen[a][b] == 2){
					return 1;
				}
				b--;
			}

		//左方向を調べる
		}else if(banmen[i][j-1] == 1){
			for(int a = j - 1; a < 0; a--){
				if(banmen[i][a] == 2){
					return 1;
				}
			}

		//左斜め上方向を調べる
		}else if(banmen[i-1][j-1] == 1){
			int b = j - 1;
			for(int a = i - 1; a > 0 || b > 0; a--){
				if(banmen[a][b] == 2){
					return 1;
				}
				b--;
			}
		}

		return 0;
	}


	//白の石を裏返すメソッド
	public static void white_flip(int i, int j){
		//上方向への処理
		if(banmen[i-1][j] == 1){
			for(int a = i - 1; a > 0; a--){
				if(banmen[a][j] == 0){
					break;
				}else if(banmen[a][j] == 2){
					for(int b = a; b <= i; b++){
						banmen[b][j] = 2;
					}
				}
			}
		}

		//右方向への処理
		if(banmen[i][j+1] == 1){
			for(int a = j + 1; a < 8; a++){
				if(banmen[i][a] == 0){
					break;
				}else if(banmen[i][a] == 2){
					for(int b = a; b >= j ; b--){
						banmen[i][b] = 2;
					}
				}
			}
		}

		//下方向への処理
		if(banmen[i+1][j] == 1){
			for(int a = i + 1; a < 8; a++){
				if(banmen[a][j] == 0){
					break;
				}else if(banmen[a][j] == 2){
					for(int b = a; b >= i; b--){
						banmen[b][j] = 2;
					}
				}
			}
		}

		//左方向への処理
		if(banmen[i][j-1] == 1){
			for(int a = j - 1; a < 0; a--){
				if(banmen[i][a] == 0){
					break;
				}else if(banmen[i][a] == 2){
					for(int b = a; b <= j ; b++){
						banmen[i][b] = 2;
					}
				}
			}
		}

		//右斜め上方向への処理
		if(banmen[i-1][j+1] == 1){
			int a; int b = j + 1;
			for(a = i - 1; a > 0 || b < 8; a--){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 2){
					for(int c = a; c <= i; c++){
						banmen[c][b--] = 2;
					}
				}
				b++;
			}
		}

		//右斜め下方向への処理
		if(banmen[i+1][j+1] == 1){
			int a; int b = j + 1;
			for(a = i + 1; a < 8 || b < 8; a++){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 2){
					for(int c = a; c >= i; c--){
						banmen[c][b--] = 2;
					}
				}
				b++;
			}
		}

		//左斜め下方向への処理
		if(banmen[i+1][j-1] == 1){
			int a; int b = j - 1;
			for(a = i + 1; a < 8 || b > 0; a++){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 2){
					for(int c = a; c >= i; c--){
						banmen[c][b++] = 2;
					}
				}
				b--;
			}
		}

		//左斜め上方向への処理
		if(banmen[i-1][j-1] == 1){
			int a; int b = j - 1;
			for(a = i - 1; a > 0 || b > 0; a--){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 2){
					for(int c = a; c <= i; c++){
						banmen[c][b++] = 2;
					}
				}
				b--;
			}
		}

	}
	//黒の石を裏返すメソッド
	public static void black_flip(int i, int j){

		//上方向への処理
		if(banmen[i-1][j] == 2){
			for(int a = i - 1; a > 0; a--){
				if(banmen[a][j] == 0){
					break;
				}else if(banmen[a][j] == 1){
					for(int b = a; b <= i; b++){
						banmen[b][j] = 1;
					}
				}
			}
		}

		//右方向への処理
		if(banmen[i][j+1] == 2){
			for(int a = j + 1; a < 8; a++){
				if(banmen[i][a] == 0){
					break;
				}else if(banmen[i][a] == 1){
					for(int b = a; b >= j ; b--){
						banmen[i][b] = 1;
					}
				}
			}
		}

		//下方向への処理
		if(banmen[i+1][j] == 2){
			for(int a = i + 1; a < 8; a++){
				if(banmen[a][j] == 0){
					break;
				}else if(banmen[a][j] == 1){
					for(int b = a; b >= i; b--){
						banmen[b][j] = 1;
					}
				}
			}
		}

		//左方向への処理
		if(banmen[i][j-1] == 2){
			for(int a = j - 1; a < 0; a--){
				if(banmen[i][a] == 0){
					break;
				}else if(banmen[i][a] == 1){
					for(int b = a; b <= j ; b++){
						banmen[i][b] = 1;
					}
				}
			}
		}


		//右斜め上方向への処理
		if(banmen[i-1][j+1] == 2){
			int a; int b = j + 1;
			for(a = i - 1; a > 0 || b < 8; a--){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 1){
					for(int c = a; c <= i; c++){
						banmen[c][b--] = 1;
					}
				}
				b++;
			}
		}

		//右斜め下方向への処理
		if(banmen[i+1][j+1] == 2){
			int a; int b = j + 1;
			for(a = i + 1; a < 8 || b < 8; a++){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 1){
					for(int c = a; c >= i; c--){
						banmen[c][b--] = 1;
					}
				}
				b++;
			}
		}

		//左斜め下方向への処理
		if(banmen[i+1][j-1] == 2){
			int a; int b = j - 1;
			for(a = i + 1; a < 8 || b > 0; a++){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 1){
					for(int c = a; c >= i; c--){
						banmen[c][b++] = 1;
					}
				}
				b--;
			}
		}

		//左斜め上方向への処理
		if(banmen[i-1][j-1] == 2){
			int a; int b = j - 1;
			for(a = i - 1; a > 0 || b > 0; a--){
				if(banmen[a][b] == 0){
					break;
				}else if(banmen[a][b] == 1){
					for(int c = a; c <= i; c++){
						banmen[c][b++] = 1;
					}
				}
				b--;
			}
		}
	}

	//石を置く場所をコンソールから入力させるメソッド
	public static int input(){
		int x;
		System.out.print("石を置く場所を指定してください");
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

	//黒が打てるかどうか調べるメソッド
	public static int black_pass(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 0){
					if(black_check(i, j) == 1){
						return 1;
					}
				}
			}
		}
		return 0;
	}


	public static int game_check(){

	}




}







