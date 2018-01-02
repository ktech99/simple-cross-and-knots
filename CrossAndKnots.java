import java.util.Scanner;
import java.util.Arrays;
public class CrossAndKnots{

  private boolean player1;
  //private String board[][];
  //private int boardNum[][];
  private int turns;
  private boolean win;
  private boolean end;
  private boolean play;
  private int max = -999;

  public CrossAndKnots(){
    player1 = true;
    // boardNum = new int[3][3];
    // board = new String[3][3];
    turns = 0;
    win = false;
    end = false;
    play = true;
    int number = 0;
    // for(int i = 0; i < 3; i++){
    //   for(int j = 0; j < 3; j++, number++){
    //     board[i][j] = "" + (number);
    //   }
    // }
  }

  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    CrossAndKnots ck = new CrossAndKnots();
    do {
      ck.play(sc);
      System.out.println("Do you want to keep playing? y/n");
      String choice = sc.next();
      if(choice.equalsIgnoreCase("y")){
        ck = new CrossAndKnots();
        for(int i = 0; i < 10; i++){
          System.out.println();
        }
      }else{
        ck.play = false;
      }
    } while (ck.play);
  }

  private void play(Scanner sc){
    String board[][] = new String[3][3];
    int boardNum[][] = new int[3][3];
    int number  = 0;
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++, number++){
        board[i][j] = "" + (number);
      }
    }
    while(!end){
      printBoard(board);
      turns++;
      playerMove(sc, board, boardNum);
      player1 = !player1;
      win = hasWon(board);
      if(turns >= 9 || win){
        end = true;
      }
    }
    if(turns == 9 && !win){
      System.out.println("it's a tie");
    }else if(turns % 2 == 0){
      System.out.println("O wins");
      turns = 0;
    }else{
      System.out.println("X wins");
      turns = 0;
    }
  }

  private boolean hasWon(String board[][]){
    //horizontal
    if(board[0][0] == board[0][1] && board[0][0] == board[0][2]){
      return true;
    }else if(board[1][0] == board[1][1] && board[1][0] == board[1][2]){
      return true;
    }else if(board[2][0] == board[2][1] && board[2][0] == board[2][2]){
      return true;
    }
     //vertical
    if(board[0][0] == board[1][0] && board[0][0] == board[2][0]){
      return true;
    }else if(board[0][1] == board[1][1] && board[0][1] == board[2][1]){
      return true;
    }else if(board[0][2] == board[1][2] && board[0][2] == board[2][2]){
      return true;
    }

    //diagonal
    if(board[0][0] == board[1][1] && board[0][0] == board[2][2]){
      return true;
    }else if(board[0][2] == board[1][1] && board[0][2] == board[2][0]){
      return true;
    }

    return false;
  }

  private void printBoard(String board[][]){
    for(int i = 0;i < 3; i++){
      for(int j = 0 ;j < 3 ; j++){
        System.out.print(board[i][j] + "|");
      }
      System.out.println();
    }
  }

  private void playerMove(Scanner sc, String board[][], int boardNum[][]){
    int i = 10;
    int j = 10;
    //if(player1){
      System.out.println("enter where you want to place the move:");
      int move = sc.nextInt();
      switch(move){
      case 0:
        i = 0;
        j = 0;
        break;
      case 1:
        i = 0;
        j = 1;
        break;
      case 2:
        i = 0;
        j = 2;
        break;
      case 3:
        i = 1;
        j = 0;
        break;
      case 4:
        i = 1;
        j = 1;
        break;
      case 5:
        i = 1;
        j = 2;
        break;
      case 6:
        i = 2;
        j = 0;
        break;
      case 7:
        i = 2;
        j = 1;
        break;
      case 8:
        i = 2;
        j = 2;
        break;
      default:
      System.out.println("incorrect move:");
      System.exit(0);
      }
    /*} else{
      max = bestMove(board, boardNum);
      switch(max){
        case 0:
          i = 0;
          j = 0;
          break;
        case 1:
          i = 0;
          j = 1;
          break;
        case 2:
          i = 0;
          j = 2;
          break;
        case 3:
          i = 1;
          j = 0;
          break;
        case 4:
          i = 1;
          j = 1;
          break;
        case 5:
          i = 1;
          j = 2;
          break;
        case 6:
          i = 2;
          j = 0;
          break;
        case 7:
          i = 2;
          j = 1;
          break;
        case 8:
          i = 2;
          j = 2;
          break;
        default:
        System.out.println("incorrect move:");
        System.exit(0);
        }
    }*/
    check(i, j, boardNum);
    if(player1){
      board[i][j] = "X";
    }else{
      board[i][j] = "O";
    }
    boardNum[i][j] = 1;
  }

  private void check(int i, int j, int boardNum[][]){
    if(boardNum[i][j] == 1){
      System.out.println("place taken");
      System.exit(0);
    }
  }

/*  public int bestMove(String board2[][], int boardNum2[][]){
    int fakeTurns = turns; // to be class variable
    boolean fakeEnd = false;
    boolean fakeWin = false;
    int i = 10;
    int j = 10;
    String fakeBoard[][] = new String[3][3];
    int fakeBoardNum[][] = new int[3][3];
    int moveWins[] = new int[9];
    for(int k = 0; k < 9; k++){
      for(int p = 0; p < 3; p++){
        for(int t = 0; t < 3; t++){
          fakeBoard[p][t] = board2[p][t];
          fakeBoardNum[p][t] = boardNum2[p][t];
        }
      }
      switch(k){
        case 0:
          i = 0;
          j = 0;
          break;
        case 1:
          i = 0;
          j = 1;
          break;
        case 2:
          i = 0;
          j = 2;
          break;
        case 3:
          i = 1;
          j = 0;
          break;
        case 4:
          i = 1;
          j = 1;
          break;
        case 5:
          i = 1;
          j = 2;
          break;
        case 6:
          i = 2;
          j = 0;
          break;
        case 7:
          i = 2;
          j = 1;
          break;
        case 8:
          i = 2;
          j = 2;
          break;
        default:
        break;
      }
      if(fakeBoardNum[i][j] != 1){
        moveWins[k] = wins(fakeBoard, fakeBoardNum, false, turns);
      }else{
        moveWins[k] = -999;
      }
    }
    for(int k = 0; k < 9; k++){
      if(max < moveWins[k]){
        max = moveWins[k];
      }
    }
    return max;
  }

  public int wins(String b[][], int bn[][], boolean player1, int turns){
    String fakeBoard[][] = new String[3][3];
    int fakeBoardNum[][] = new int[3][3];
    for(int p = 0; p < 3; p++){
      for(int t = 0; t < 3; t++){
        fakeBoard[p][t] = b[p][t];
        fakeBoardNum[p][t] = bn[p][t];
      }
    }
    int fakeTurns = turns;
    boolean falseWin = false;
    int wins = 0;
    for(int  i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(fakeBoardNum[i][j] != 1){
          if(player1){
            fakeBoard[i][j] = "X";
          }else{
            fakeBoard[i][j] = "O";
          }
          fakeBoardNum[i][j] = 1;
          turns++;
          player1 = !player1;
        }
        falseWin  = hasWon(fakeBoard);
        if(falseWin){
          if(turns == 9 & !falseWin){
            return 0;
          }else if(turns % 2 == 0){
            return +20;
          }else{
            return -20;
          }
        }else if(!falseWin && turns == 9){
         return 0;
        }
      }
    }
    return wins + wins(fakeBoard, fakeBoardNum, player1, turns);
  }*/

}
