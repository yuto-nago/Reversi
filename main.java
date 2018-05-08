package osero;

import java.io.*;
public class main {

	//�Ֆʂ̒l�����������񎟌��z��
	private static int[][] banmen = new int[8][8];

	//����2�A����1
	private static int turn;

	//���͂��ꂽ�l��ێ�����ϐ�
	private static int place;

	//�r�b�g���Z�Ŏg�p����ϐ�
	private static int bit = 3;

	//�Ֆʂ̍��̐΂̐�
	private static int black = 0;

	//�Ֆʂ̔��̐΂̐�
	private static int white = 0;

	public static void main(String[] args) {
		turn = 2;
		clear(banmen);


		do{
			display();
			//�Ֆʂ����ׂĖ��܂邩�A�Е��̐΂������Ȃ����ہA�I������
			while(game_end() != 0 || black != 0 || white != 0){
				if(turn == 2){
					if(pass() == 1){
						System.out.println("���̃^�[��");
						place = input();
						int i = place / 10;
					    int j = place % 10;
					    flip(i, j);
					    display();
					    turn = 1;
					}
				}else if(turn == 1){
					if(pass() == 1){
						System.out.println("���̃^�[��");
						place = input();
						int i = place / 10;
					    int j = place % 10;
					    flip(i, j);
					    display();
					    turn = 2;
					}
				}
			}

			if(black < white){
				System.out.println(white + "���Ŕ��̏���");
			}else if(black == white){
				System.out.println(black + "���ň�������");
			}else{
				System.out.println(black + "���ō��̏���");
			}


		}while(retry() != 0);

	}

	/**
	 * �Ֆʂ̏���ێ�����z������������郁�\�b�h
	 *
	 * @param int�^�̓񎟌��z��
	 * @return �l���������񎟌��z��
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
	 * �z��̏����R���\�[���ɏo�͂��郁�\�b�h
	 */
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
					white++;
					System.out.print("�� ");
				}else if(banmen[i][j] == 2){
					black++;
					System.out.print("�� ");
				}else{
					System.out.print("�� ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * �I�Z���𑱂��邩�A��߂邩�I�ԃ��\�b�h
	 * @return �R���\�[��������͂��ꂽ�l
	 */
	public static int retry(){
		clear(banmen);
		black = 0;
		white = 0;
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



	/**
	 * ����̐΂𗠕Ԃ����\�b�h
	 *
	 * @param i �R���\�[��������͂��ꂽ�\�̈ʂ̐���
	 * @param j �R���\�[��������͂��ꂽ��̈ʂ̐���
	 */
	public static void flip(int i, int j){

		//�z��[0][0]�̎��A�E�A���A�E�΂߉��ɂ����������Ȃ�
		if(i == 0 && j == 0){

			//�E�����ւ̏���
			if(banmen[i][j + 1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//�E�΂߉������ւ̏���
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; a < 8 || b < 8; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//�������ւ̏���
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == 2){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
					}
				}
			}

		//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
		}else if(i == 0 && j == 7){

			//�������ւ̏���
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a < 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//���΂߉������ւ̏���
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; a < 8 || b > 0; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}

			//�������ւ̏���
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == 2){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
					}
				}
			}

		//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
		}else if(i == 7 && j == 0){

			//������ւ̏���
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a > 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//�E�΂ߏ�����ւ̏���
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; a > 0 || b < 8; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//�E�����ւ̏���
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
					}
				}
			}

		//�z��[7][7]�̎��A���A��A���΂ߏ�ɂ���
		}else if(i == 7 && j == 7){

			//������ւ̏���
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a > 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//���΂ߏ�����ւ̏���
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; a > 0 || b > 0; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}

			//�������ւ̏���
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a < 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
					}
				}
			}

		//�z��[0][j]�̎��A�㑤���������Ȃ�
		}else if(i == 0){

			//�������ւ̏���
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a < 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//���΂߉������ւ̏���
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; a < 8 || b > 0; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}

			//�������ւ̏���
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == 2){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//�E�΂߉������ւ̏���
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; a < 8 || b < 8; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//�E�����ւ̏���
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
					}
				}
			}

		//�z��[i][7]�̎��A�E�����������Ȃ�
		}else if(j == 7){

			//������ւ̏���
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a > 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//���΂ߏ�����ւ̏���
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; a > 0 || b > 0; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}

			//�������ւ̏���
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a < 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//���΂߉������ւ̏���
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; a < 8 || b > 0; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}

			//�������ւ̏���
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == 2){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
					}
				}
			}


		//�z��[i][0]�̎��A�������������Ȃ�
		}else if(j == 0){

			//������ւ̏���
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a > 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//�E�΂ߏ�����ւ̏���
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; a > 0 || b < 8; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//�E�����ւ̏���
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//�E�΂߉������ւ̏���
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; a < 8 || b < 8; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//�������ւ̏���
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == 2){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
					}
				}
			}


		//�z��[7][j]�̎��A�������������Ȃ�
		}else if(i == 7){

			//�������ւ̏���
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a < 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//���΂ߏ�����ւ̏���
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; a > 0 || b > 0; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}

			//������ւ̏���
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a > 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//�E�΂ߏ�����ւ̏���
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; a > 0 || b < 8; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//�E�����ւ̏���
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
					}
				}
			}

		//���̍��W�͎�������ׂĒ��ׂ�
		}else{

			//������ւ̏���
			if(banmen[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a > 0; a--){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == turn){
						for(int b = a; b <= i; b++){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//�E�����ւ̏���
			if(banmen[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b >= j ; b--){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//�������ւ̏���
			if(banmen[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(banmen[a][j] == 0){
						break;
					}else if(banmen[a][j] == 2){
						for(int b = a; b >= i; b--){
							banmen[b][j] = turn;
						}
					}
				}
			}

			//�������ւ̏���
			if(banmen[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a < 0; a--){
					if(banmen[i][a] == 0){
						break;
					}else if(banmen[i][a] == turn){
						for(int b = a; b <= j ; b++){
							banmen[i][b] = turn;
						}
					}
				}
			}

			//�E�΂ߏ�����ւ̏���
			if(banmen[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; a > 0 || b < 8; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//�E�΂߉������ւ̏���
			if(banmen[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; a < 8 || b < 8; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b--] = turn;
						}
					}
					b++;
				}
			}

			//���΂߉������ւ̏���
			if(banmen[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; a < 8 || b > 0; a++){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c >= i; c--){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}

			//���΂ߏ�����ւ̏���
			if(banmen[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; a > 0 || b > 0; a--){
					if(banmen[a][b] == 0){
						break;
					}else if(banmen[a][b] == turn){
						for(int c = a; c <= i; c++){
							banmen[c][b++] = turn;
						}
					}
					b--;
				}
			}
		}
	}


	/**
	 * �΂�u���ꏊ����͂��郁�\�b�h
	 * @return ���͂��ꂽ�l
	 */
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




	/**
	 * ���߂�΂����邩���ׂ郁�\�b�h
	 * @return ���ސ΂��������0��Ԃ��B���ސ΂������1��Ԃ��B
	 */
	public static int pass(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				//�z��[0][0]�̎��A�E�A���A�E�΂߉��ɂ����������Ȃ�
				if(banmen[i][j] == 0 && i == 0 && j == 0){
					//�E�����ɑ���̐΂��������ۂɂ��̐�Ɏ����̐΂�����΋��ނ��Ƃ��ł���̂Ńp�X�ł͂Ȃ�
					if(banmen[i][j + 1] == (turn ^ bit)){
						for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

					}

					//�������ւ̌���
					if(banmen[i + 1][j] == (turn ^ bit)){
						for(int a = i + 1; a < 8; a++){
							if(banmen[a][j] == 0){
								break;
							}else if(banmen[a][j] == turn){
								return 1;
							}
						}

					}

					//�E�΂߉��ւ̌���
					if(banmen[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						for(int a = i + 1; a < 8 || b < 8; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
					}

				//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
				}else if(banmen[i][j] == 0 && i == 0 && j == 7){

					//�������ւ̌���
					if(banmen[i][j-1] == (turn ^ bit)){
						for(int a = j - 1; a < 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

				    }
					//�������ւ̌���
					if(banmen[i + 1][j] == (turn ^ bit)){
				    	for(int a = i + 1; a < 8; a++){
				    		if(banmen[a][j] == 0){
				    			break;
				    		}else if(banmen[a][j] == 1){
								return 1;
							}
						}

				    }
					//���΂߉��ւ̌���
					if(banmen[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
						for(int a = i + 1; a < 8 || b > 0; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
				    }

				//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
			    }else if(banmen[i][j] == 0 && i == 7 && j == 0){

			    	//������ւ̌���
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a > 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�����ւ̌���
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�΂ߏ�����ւ̌���
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; a > 0 || b < 8; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
			    	}

			    //�z��[7][7]�̎��A���A��A���΂ߏ�ɂ����������Ȃ�
			    }else if(banmen[i][j] == 0 && i == 7 && j == 7){

			    	//�������ւ̌���
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a < 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//������ւ̌���
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a > 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂ߏ�����ւ̌���
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; a > 0 || b > 0; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
			    	}

			    //�z��[0][j]�̎��A�㑤���������Ȃ�
			    }else if(banmen[i][j] == 0 && i == 0){

			    	//�������ւ̌���
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a < 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂߉������ւ̌���
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; a < 8 || b > 0; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
			    	}
			    	//�������ւ̌���
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; a < 8 || b < 8; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
			    	}

			    	//�E�����ւ̌���
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][0]�̎��A�������������Ȃ�
			    }else if(banmen[i][j] == 0 && j == 0){

			    	//������ւ̌���
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a > 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; a > 0 || b < 8; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
			    	}

			    	//�E�����ւ̌���
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; a < 8 || b < 8; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
			    	}

			    	//�������ւ̌���
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][7]�̎��A�E�����������Ȃ�
			    }else if(banmen[i][j] == 0 && j == 7){

			    	//������ւ̌���
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a > 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//���΂ߏ�����ւ̌���
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; a > 0 || b > 0; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
			    	}

			    	//�������ւ̌���
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a < 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ւ̌���
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; a < 8 || b > 0; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
			    	}

			    	//�������ւ̌���
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[7][j]�̎��A�������������Ȃ�
			    }else if(banmen[i][j] == 0 && i == 7){

			    	//�������ւ̌���
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a < 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ւ̌���
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; a > 0 || b > 0; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
			    	}

			    	//������ւ̌���
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a > 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; a > 0 || b < 8; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
			    	}

			    	//�E�����ւ̌���
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //���̍��W�͎�������ׂĒ��ׂ�
			    }else{

			    	//������ւ̌���
			    	if(banmen[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a > 0; a--){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
			    	if(banmen[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; a > 0 || b < 8; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
			    	}

			    	//�E�����ւ̌���
			    	if(banmen[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
			    	if(banmen[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; a < 8 || b < 8; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b++;
						}
			    	}

			    	//�������ւ̌���
			    	if(banmen[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(banmen[a][j] == 0){
			    				break;
			    			}else if(banmen[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ւ̌���
			    	if(banmen[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; a < 8 || b > 0; a++){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
			    	}

			    	//�������ւ̌���
			    	if(banmen[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a < 0; a--){
							if(banmen[i][a] == 0){
								break;
							}else if(banmen[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ւ̌���
			    	if(banmen[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; a > 0 || b > 0; a--){
							if(banmen[a][b] == 0){
								break;
							}else if(banmen[a][b] == turn){
								return 1;
							}
							b--;
						}
			    	}

			    }
			}
		}
		return 0;
	}

	/**
	 * �Ֆʂɋ󂫂����邩���ׂ郁�\�b�h
	 * @return �󂫂������1��Ԃ��B�������0��Ԃ��B
	 */
	public static int game_end(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 0){
					return 1;
				}
			}
		}
		return 0;
	}




}







