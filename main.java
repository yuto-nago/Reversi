package osero;

import java.io.*;
public class main {

	//�Ֆʂ̒l�����������񎟌��z��
	private static int[][] banmen;

	//����2�A����1
	private static int turn;

	//���͂��ꂽ�l��ێ�����ϐ�
	private static int place;

	public static void main(String[] args) {
		turn = 2;
		banmen = new int[8][8];
		clear(banmen);
		display();
		if(turn == 2){
			System.out.println("���̃^�[��");
		}else if(turn == 1){
			System.out.println("���̃^�[��");
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


	//�Q�[���i�s���\�b�h
//	public static void play(){
//
//
//		while(turn < 3){
//			if(turn == 2){
//				System.out.println("���̃^�[��");
//			}else if(turn == 1){
//				System.out.println("���̃^�[��");
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
	//�Ֆʂ��R���\�[���ɏo�͂��郁�\�b�h
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

	//���̐΂����߂邩���ׂ郁�\�b�h
	public static int black_check(int i, int j){

		//������𒲂ׂ�
		if(banmen[i-1][j] == 1){
			for(int a = i - 1; a > 0; a--){
				if(banmen[a][j] == 2){
					return 1;
				}
			}

		//�E�΂ߏ�����𒲂ׂ�
		}else if(banmen[i-1][j+1] == 1){
			int b = j + 1;
			for(int a = i - 1; a > 0 || b < 8; a--){
				if(banmen[a][b] == 2){
					return 1;
				}
				b++;
			}

		//�E�����𒲂ׂ�
		}else if(banmen[i][j+1] == 1){
			for(int a = j + 1; a < 8; a++){
				if(banmen[i][a] == 2){
					return 1;
				}
			}

		//�E�΂߉������𒲂ׂ�
		}else if(banmen[i+1][j+1] == 1){
			int b = j + 1;
			for(int a = i + 1; a < 8 || b < 8; a++){
				if(banmen[a][b] == 2){
					return 1;
				}
				b++;
			}

		//�������𒲂ׂ�
		}else if(banmen[i+1][j] == 1){
			for(int a = i + 1; a < 8; a++){
				if(banmen[a][j] == 2){
					return 1;
				}
			}

		//���΂߉������𒲂ׂ�
		}else if(banmen[i+1][j-1] == 1){
			int b = j - 1;
			for(int a = i + 1; a < 8 || b > 0; a++){
				if(banmen[a][b] == 2){
					return 1;
				}
				b--;
			}

		//�������𒲂ׂ�
		}else if(banmen[i][j-1] == 1){
			for(int a = j - 1; a < 0; a--){
				if(banmen[i][a] == 2){
					return 1;
				}
			}

		//���΂ߏ�����𒲂ׂ�
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


	//���̐΂𗠕Ԃ����\�b�h
	public static void white_flip(int i, int j){
		//������ւ̏���
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

		//�E�����ւ̏���
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

		//�������ւ̏���
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

		//�������ւ̏���
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

		//�E�΂ߏ�����ւ̏���
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

		//�E�΂߉������ւ̏���
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

		//���΂߉������ւ̏���
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

		//���΂ߏ�����ւ̏���
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
	//���̐΂𗠕Ԃ����\�b�h
	public static void black_flip(int i, int j){

		//������ւ̏���
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

		//�E�����ւ̏���
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

		//�������ւ̏���
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

		//�������ւ̏���
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


		//�E�΂ߏ�����ւ̏���
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

		//�E�΂߉������ւ̏���
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

		//���΂߉������ւ̏���
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

		//���΂ߏ�����ւ̏���
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

	//�΂�u���ꏊ���R���\�[��������͂����郁�\�b�h
	public static int input(){
		int x;
		System.out.print("�΂�u���ꏊ���w�肵�Ă�������");
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

	//�����łĂ邩�ǂ������ׂ郁�\�b�h
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







