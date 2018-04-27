package osero;

import java.io.*;
public class main {

	//�Ֆʂ̒l�����������񎟌��z��
	private static int[][] banmen;

	//�c��
	private static int[] tate = {-1, -1, 0, 1, 1, 1, 0, -1};

	//����
	private static int[] yoko = {0, 1, 1, 1, 0, -1, -1, -1};

	//����2�A����1
	private static int turn;

	public static void main(String[] args) {
		turn = 2;
		banmen = new int[8][8];
		clear(banmen);
		do{

			display();




		}while(retry() != 0);

	}

	//�Ֆʂ����������郁�\�b�h
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



	public static void play(){


		while(turn < 3){
			if(turn == 2){
				System.out.println("���̃^�[��");
			}else if(turn == 1){
				System.out.println("���̃^�[��");
			}
			display();
			input(turn);
			if(turn == 2){
				turn = 1;
			}else if(turn == 1){
				turn = 2;
			}


		}

		judge();

	}
	//�Ֆʂ��R���\�[���ɏo�͂��郁�\�b�h
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
					System.out.print("�� ");
				}else if(banmen[i][j] == 2){
					System.out.print("�� ");
				}else{
					System.out.print("�� ");
				}
			}
			System.out.println();
		}
	}
	//�I�Z���𑱂��邩�A��߂邩�I�ԃ��\�b�h
	public static int retry(){
		clear(banmen);
		int x;
		System.out.print("�Q�[���𑱂��܂����H�͂�=1�A������=0 ");
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
	//�Ֆʂ̐΂̐��𐔂��郁�\�b�h
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
			System.out.println("���̏���");
		}else if(black > white){
			System.out.println("���̏���");
		}else if(black == white){
			System.out.println("��������");
		}
	}

	public static int Check(int y, int x, int turn){

		for(int i = 0; i < 8; i++){
			if(Check_stone(y,x,turn,i) == 1){
				return 1;
			}
		}
		return 0;
	}

	public static int Check_stone(int y, int x, int turn, int vec){
		boolean flag = false;

		while(true){
			 y += tate[vec];
			 x += yoko[vec];

			 if(x < 0 || y < 0 || x > 7 || y > 7){
				 return 0;
			 }
			 if(banmen[y][x] == 0){
				 return 0;
			 }
			 if(banmen[y][x] == turn){
				 flag = true;
				 continue;
			 }
			 if(flag == true){
				 break;
			 }
		}
		return 1;
	}

	public static void input(int turn){

		int place;
		int x, y;

		while(true){
			System.out.println("�l����͂��Ă�������");
			InputStreamReader isr = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(isr);
	        try{
	                String buf = br.readLine();
	                place = Integer.parseInt(buf);
	        }catch(Exception e){
	                place = 0;
	        }

	        if(place == 0){
	        	System.out.println("���l������������܂���");
	        	continue;
	        }

	        if(place < 11 || place > 88){
	        	System.out.println("11����88�̊Ԃœ��͂��Ă�������");
	        	continue;
	        }

	        y = place / 10;
	        x = place % 10;

	        if(x < 1 || y < 1 || x > 8 || y > 8){
	        	System.out.println("���l������������܂���");
	        	continue;
	        }

	        if(put(y-1,x-1,turn) == 1){
	        	break;
	        }else{
	        	System.out.println("�u���܂���");
	        }



		}
	}


	public static int put(int y, int x, int turn){
		boolean flag = false;
		if(banmen[y][x] != 0){
			return 0;
		}
		for(int i = 0; i < 8; i++){
			if(Check_stone(y,x,turn,i) == 1){
				flip(y,x,turn,i);
				flag = true;
			}
		}

		if(flag == true){
			banmen[y][x] = turn;
			return 1;
		}
		return 0;

	}


	public static void flip(int y, int x, int turn, int vec){

		while(true){
			y += tate[vec];
			x += yoko[vec];

			if(banmen[y][x] == turn){
				break;
			}else{
				banmen[y][x] = turn;
			}

		}
	}

	public static int Check_end(int turn){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 0 && Check(i,j,turn) == 1){
					return 0;
				}
			}
		}
		if(turn == 2){
			turn = 1;
		}else if(turn == 1){
			turn = 2;
		}
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 0 && Check(i,j,turn) == 1){
					return 1;
				}
			}
		}
		return

	}


//	public static boolean search(){
//
//	}


//	public static int end(){
//		int n = 0;
//		for(int i = 0; i < 8; i++){
//			for(int j = 0; j < 8;){
//				if(banmen[i][j] == 0){
//					n = 1;
//				}
//			}
//		}
//		return n;
//	}
//
//
//	public static void black_tate(int a, int b){
//		for(int i = a; i < 0; i--){
//			if(banmen[i][b] == 1 && banmen[i-1][b] == 2){
//				for(int j = i; j <= a; j++){
//					banmen[j][b] = 2;
//				}
//			}
//		}
//	}
//
//	public static void black_sita(int a, int b){
//		for(int i = a; i < 8; i++){
//			if(banmen[i][b] == 1 && banmen[i+1][b] == 2){
//				for(int j = i; j <= a; j--){
//					banmen[j][b] = 2;
//				}
//			}
//		}
//	}
//
//	public static void black_right(int a, int b){
//		for(int i = b; i < 8; i++){
//			if(banmen[a][i] == 1 && banmen[a][i+1] == 2){
//				for(int j = i; j <= b; j--){
//					banmen[a][j] = 2;
//				}
//			}
//		}
//	}
//
//
//	public static void black_left(int a, int b){
//		for(int i = b; i < 0; i--){
//			if(banmen[a][i] == 1 && banmen[a][i-1] == 2){
//				for(int j = i; j <= b; j++){
//					banmen[a][j] = 2;
//				}
//			}
//		}
//	}

//	public static void black_NE(int a, int b){
//		for(int i = a; i < 0; i--){
//			if(banmen[i][b] == 1 && banmen[i-1][b+1] == 2){
//
//			}
//		}
//	}



}







