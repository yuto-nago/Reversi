package osero;

import java.io.*;
public class main {

	//盤面の値を所持した二次元配列
	private static int[][] banmen = new int[8][8];

	//黒が2、白が1
	private static int turn;

	//入力された値を保持する変数
	private static int place;

	//ビット演算で使用する変数
	private static int bit = 3;

	//盤面の黒の石の数
	private static int black = 0;

	//盤面の白の石の数
	private static int white = 0;

	//盤面のx座標
	private static int x;

	//盤面のy座標
	private static int y;

	public static void main(String[] args) {
		turn = 2;
		clear(banmen);
		int pass;

		do{
			display();
			//盤面がすべて埋まるか、片方の石が無くなった際、終了する
			while(game_end() != 0){
				if(turn == 2){
					if(pass() == 1){
						System.out.println("●のターン");
						System.out.println("黒色" + black + " 個");
						place = input();
						y = place / 10;
					    x = place % 10;
					    while(check(y,x) == 0){
					    	System.out.println("挟める石がありません");
					    	display();
					    	System.out.println("●のターン");
					    	System.out.println("黒色" + black + " 個");
					    	place = input();
							y = place / 10;
						    x = place % 10;
					    }
					    flip(y, x);
					    display();
					    turn = 1;
					}else{
						System.out.println("黒はパスです");
						turn = 1;
						break;
					}
				}else if(turn == 1){
					if(pass() == 1){
						System.out.println("○のターン");
						System.out.println("白色" + white + " 個");
						place = input();
						y = place / 10;
					    x = place % 10;
					    while(check(y,x) == 0){
					    	System.out.println("挟める石がありません");
					    	display();
					    	System.out.println("○のターン");
					    	System.out.println("白色" + white + " 個");
					    	place = input();
							y = place / 10;
						    x = place % 10;
					    }
					    flip(y, x);
					    display();
					    turn = 2;
					}else{
						System.out.println("白はパスです");
						turn = 2;
						break;
					}
				}
			}

			if(black < white){
				System.out.println(white + "枚で白の勝ち");
			}else if(black == white){
				System.out.println(black + "枚で引き分け");
			}else{
				System.out.println(black + "枚で黒の勝ち");
			}


		}while(retry() != 0);

	}

	/**
	 * 盤面の情報を保持する配列を初期化するメソッド
	 *
	 * @param int型の二次元配列
	 * @return 値を代入した二次元配列
	 */
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




	/**
	 * 配列の情報をコンソールに出力するメソッド
	 */
	public static void display(){
		black = 0;

		white = 0;

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
					white++;
					System.out.print("○ ");
				}else if(banmen[i][j] == 2){
					black++;
					System.out.print("● ");
				}else{
					System.out.print("＊ ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * オセロを続けるか、やめるか選ぶメソッド
	 * @return コンソールから入力された値
	 */
	public static int retry(){
		clear(banmen);
		black = 0;
		white = 0;
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



	/**
	 * 相手の石を裏返すメソッド
	 *
	 * @param i コンソールから入力された十の位の数字
	 * @param j コンソールから入力された一の位の数字
	 */
	public static void flip(int i, int j){

		//配列[0][0]の時、右、下、右斜め下にしか検索しない
		if(i == 0 && j == 0){

			//右方向への処理
			if(banmen[i][j + 1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//右斜め下方向への処理
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

		//配列[0][7]の時、左、下、左斜め下にしか検索しない
		}else if(i == 0 && j == 7){

			//左方向への処理
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

		//配列[7][0]の時、上、右、右斜め上にしか検索しない
		}else if(i == 7 && j == 0){

			//上方向への処理
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

		//配列[7][7]の時、左、上、左斜め上にしか
		}else if(i == 7 && j == 7){

			//上方向への処理
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}

			//左方向への処理
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

		//配列[0][j]の時、上側を検索しない
		}else if(i == 0){

			//左方向への処理
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め下方向への処理
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

		//配列[i][7]の時、右側を検索しない
		}else if(j == 7){

			//上方向への処理
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}

			//左方向への処理
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}


		//配列[i][0]の時、左側を検索しない
		}else if(j == 0){

			//上方向への処理
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//右斜め下方向への処理
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}


		//配列[7][j]の時、下側を検索しない
		}else if(i == 7){

			//左方向への処理
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}

			//上方向への処理
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

		//他の座標は周りをすべて調べる
		}else{

			//上方向への処理
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
						break;
					}
				}
			}

			//左方向への処理
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
						break;
					}

				}
			}

			//右斜め下方向への処理
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
						break;
					}
				}
			}
		}
	}


	/**
	 * 石を置く場所を入力するメソッド
	 * @return 入力された値
	 */
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




	/**
	 * 挟める石があるか調べるメソッド
	 * @return 挟む石が無ければ0を返す。挟む石があれば1を返す。
	 */
	public static int pass(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				//配列[0][0]の時、右、下、右斜め下にしか検索しない
				if(banmen[i][j] == 0 && i == 0 && j == 0){
					//右方向に相手の石を見つけた際にその先に自分の石があれば挟むことができるのでパスではない
					if(banmen[i][j + 1] == (turn ^ bit)){
						for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

					}

					//下方向への検索
					if(banmen[i + 1][j] == (turn ^ bit)){
						for(int a = i + 1; a < 8; a++){
							if(banmen[a][j] == 0){
								break;
							}else if(banmen[a][j] == turn){
								return 1;
							}
						}

					}

					//右斜め下への検索
					if(banmen[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
					}

				//配列[0][7]の時、左、下、左斜め下にしか検索しない
				}else if(banmen[i][j] == 0 && i == 0 && j == 7){

					//左方向への検索
					if(banmen[i][j-1] == (turn ^ bit)){
						for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

				    }
					//下方向への検索
					if(banmen[i + 1][j] == (turn ^ bit)){
				    	for(int a = i + 1; a < 8; a++){
				    		if(banmen[a][j] == 0){
				    			break;
				    		}else if(banmen[a][j] == 1){
								return 1;
							}
						}

				    }
					//左斜め下への検索
					if(banmen[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
				    }

				//配列[7][0]の時、上、右、右斜め上にしか検索しない
			    }else if(banmen[i][j] == 0 && i == 7 && j == 0){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][7]の時、左、上、左斜め上にしか検索しない
			    }else if(banmen[i][j] == 0 && i == 7 && j == 7){

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[0][j]の時、上側を検索しない
			    }else if(banmen[i][j] == 0 && i == 0){

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め下方向への検索
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}
			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][0]の時、左側を検索しない
			    }else if(banmen[i][j] == 0 && j == 0){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][7]の時、右側を検索しない
			    }else if(banmen[i][j] == 0 && j == 7){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][j]の時、下側を検索しない
			    }else if(banmen[i][j] == 0 && i == 7){

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a > 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //他の座標は周りをすべて調べる
			    }else if(banmen[i][j] == 0){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    }
			}
		}
		return 0;
	}

	/**
	 * 盤面に空きがあるか調べるメソッド
	 * @return 空きがあれば1を返す。無ければ0を返す。
	 */
	public static int game_end(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 0){
					return 1;
				}
			}
		}

		if(black != 0 || white != 0){
			return 1;
		}
		return 0;
	}


	public static int check(int i, int j){
				//配列[0][0]の時、右、下、右斜め下にしか検索しない
				if(banmen[i][j] == 0 && i == 0 && j == 0){
					//右方向に相手の石を見つけた際にその先に自分の石があれば挟むことができるのでパスではない
					if(banmen[i][j + 1] == (turn ^ bit)){
						for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

					}

					//下方向への検索
					if(banmen[i + 1][j] == (turn ^ bit)){
						for(int a = i + 1; a < 8; a++){
							if(banmen[a][j] == 0){
								break;
							}else if(banmen[a][j] == turn){
								return 1;
							}
						}

					}

					//右斜め下への検索
					if(banmen[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
					}

				//配列[0][7]の時、左、下、左斜め下にしか検索しない
				}else if(banmen[i][j] == 0 && i == 0 && j == 7){

					//左方向への検索
					if(banmen[i][j-1] == (turn ^ bit)){
						for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

				    }
					//下方向への検索
					if(banmen[i + 1][j] == (turn ^ bit)){
				    	for(int a = i + 1; a < 8; a++){
				    		if(banmen[a][j] == 0){
				    			break;
				    		}else if(banmen[a][j] == turn){
								return 1;
							}
						}

				    }
					//左斜め下への検索
					if(banmen[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
				    }

				//配列[7][0]の時、上、右、右斜め上にしか検索しない
			    }else if(banmen[i][j] == 0 && i == 7 && j == 0){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][7]の時、左、上、左斜め上にしか検索しない
			    }else if(banmen[i][j] == 0 && i == 7 && j == 7){

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[0][j]の時、上側を検索しない
			    }else if(banmen[i][j] == 0 && i == 0){

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め下方向への検索
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}
			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; a < 8 || b < 8; a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][0]の時、左側を検索しない
			    }else if(banmen[i][j] == 0 && j == 0){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][7]の時、右側を検索しない
			    }else if(banmen[i][j] == 0 && j == 7){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][j]の時、下側を検索しない
			    }else if(banmen[i][j] == 0 && i == 7){

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //他の座標は周りをすべて調べる
			    }else if(banmen[i][j] == 0){

			    	//上方向への検索
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}

						}
			    	}

			    	//右方向への検索
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
						}
			    	}

			    }


		return 0;
	}


}







