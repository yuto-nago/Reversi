package osero;

import java.io.*;
public class main {

	//�Ֆʂ̒l�����������񎟌��z��
	private static int[][] banmen;

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


	//�Q�[���i�s���\�b�h
	public static void play(){


		while(turn < 3){
			if(turn == 2){
				System.out.println("���̃^�[��");
			}else if(turn == 1){
				System.out.println("���̃^�[��");
			}
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




}







