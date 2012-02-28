import java.util.Scanner;

import javax.swing.JOptionPane;


public class ultimatechess {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		//char[][] b=new char[8][8];
		game mygame = new game();
		int i=0,j=0,k=0,l=0;
		java.io.File file = new java.io.File(System.getProperty("user.dir")+"/StrikerChessGameData.txt");
		java.io.PrintWriter fout = new java.io.PrintWriter(file);
		//Initialize
		for(i=0;i<8;i++) for(j=0;j<8;j++) mygame.chessboard[i][j]='0';
		mygame.chessboard[0][0]='r';
		mygame.chessboard[0][1]='n';
		mygame.chessboard[0][2]='b';
		mygame.chessboard[0][3]='q';
		mygame.chessboard[0][4]='k';
		mygame.chessboard[0][5]='b';
		mygame.chessboard[0][6]='n';
		mygame.chessboard[0][7]='r';
		for(i=0;i<8;i++) mygame.chessboard[1][i]='p';
		for(i=0;i<8;i++) mygame.chessboard[6][i]='P';
		mygame.chessboard[7][0]='R';
		mygame.chessboard[7][1]='N';
		mygame.chessboard[7][2]='B';
		mygame.chessboard[7][3]='Q';
		mygame.chessboard[7][4]='K';
		mygame.chessboard[7][5]='B';
		mygame.chessboard[7][6]='N';
		mygame.chessboard[7][7]='R';
		//initialize ends
		//display board
		
		System.out.print("   ________________________________\n");
		for(i=0;i<8;i++)
		{
			switch(i)
			{
			case 0 : System.out.print(" h ");break;
			case 1 : System.out.print(" g ");break;
			case 2 : System.out.print(" f ");break;
			case 3 : System.out.print(" e ");break;
			case 4 : System.out.print(" d ");break;
			case 5 : System.out.print(" c ");break;
			case 6 : System.out.print(" b ");break;
			case 7 : System.out.print(" a ");break;
			}
			System.out.print("|");
			for(j=0;j<8;j++)
			{	
				if(mygame.chessboard[i][j]!='0') System.out.print(" "+mygame.chessboard[i][j]+" |");
				else System.out.print("   |");
			}
			System.out.print("\n   |___|___|___|___|___|___|___|___|\n");
		}
		System.out.println("     1   2   3   4   5   6   7   8");
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				if(mygame.chessboard[i][j]=='0') fout.print(". ");
				else fout.print(mygame.chessboard[i][j]+" ");
			}
			fout.println("");
		}
		fout.close();
		
		//display board ends
		char x='0',y='0';
		int p=0,q=0,r=0,s=0,win,wcastle=0,bcastle=0,bluah=0;
		while(mygame.checkmate(1)==0)
		{
			//get white input
			do
			{
			if(bluah==1) 
			{
				mygame.chessboard[p][q] = x;
				 mygame.chessboard[r][s] = y;
				 mygame.display();
				 bluah=0;
			}
			if(ischeck()==1) JOptionPane.showMessageDialog(null,"Your king is under attack da....protect it");
			String winput = JOptionPane.showInputDialog(null,"Enter your move. eg: a1 to b4 OR castle", "WHITE'S TURN", 1);
			if(winput.length()<6) continue;
			if(winput.charAt(0)=='c'&&winput.charAt(1)=='a'&&winput.charAt(2)=='s'&&winput.charAt(3)=='t'&&winput.charAt(4)=='l'&&winput.charAt(5)=='e') 
				{if(mygame.ifdocastling(1)) {JOptionPane.showMessageDialog(null, "castling positive");wcastle=1;break;}else continue;}
			if(winput.charAt(0)!='c'||winput.charAt(1)!='a'||winput.charAt(2)!='s'||winput.charAt(3)!='t'||winput.charAt(4)!='l'||winput.charAt(5)!='e') if(winput.length()<8) continue;
			 p = resolve1(winput);
			 q = resolve2(winput);
			 r = resolve3(winput);
			 s = resolve4(winput);
			 //System.out.print("p ="+p+" q="+q+" r="+r+" s="+s );
			 if(mygame.ispossible(p,q,r,s,1)==false) JOptionPane.showMessageDialog(null, "Invalid move macha...try again");
			 else
			 {
				 x = mygame.chessboard[p][q];
				 y = mygame.chessboard[r][s];
					mygame.chessboard[r][s]=mygame.chessboard[p][q];
					mygame.chessboard[p][q]='0';
					 mygame.display();
					 if(ischeck()==1)
				     {
						 bluah=1;
						 continue;
					 }
					 else break;
			 }
			
			}
			while(mygame.ispossible(p,q,r,s,1)==false||ischeck()==1);
			//get white input ends
			//if(wcastle==0)
			//{
			//mygame.chessboard[r][s]=mygame.chessboard[p][q];
			//mygame.chessboard[p][q]='0';
			//}
			wcastle=0;
			mygame.display();
			//if() break;//mygame.checkmate()>0
				do
				{
					if(bluah==1) 
					{
						mygame.chessboard[p][q] = x;
						 mygame.chessboard[r][s] = y;
						 mygame.display();
						 bluah=0;
					}
				if(ischeck()==2) JOptionPane.showMessageDialog(null,"Your king is under attack da....protect it");
				String winput = JOptionPane.showInputDialog(null, "Enter your move. eg: a1 to b4 OR castle","BLACK'S TURN", 1);
				if(winput.length()<6) continue;
				if(winput.charAt(0)=='c'&&winput.charAt(1)=='a'&&winput.charAt(2)=='s'&&winput.charAt(3)=='t'&&winput.charAt(4)=='l'&&winput.charAt(5)=='e') {if(mygame.ifdocastling(2)) {bcastle=1;break;}else continue;}
				if(winput.charAt(0)=='c'||winput.charAt(1)=='a'||winput.charAt(2)=='s'||winput.charAt(3)=='t'||winput.charAt(4)=='l'||winput.charAt(5)=='e') if(winput.length()<8) continue;
				 p = resolve1(winput);
				 q = resolve2(winput);
				 r = resolve3(winput);
				 s = resolve4(winput);
				 //System.out.print("p ="+p+" q="+q+" r="+r+" s="+s );
				 if(mygame.ispossible(p,q,r,s,2)==false) JOptionPane.showMessageDialog(null, "Invalid move macha...try again");
				 else
				 {
					 x = mygame.chessboard[p][q];
					 y = mygame.chessboard[r][s];
						mygame.chessboard[r][s]=mygame.chessboard[p][q];
						mygame.chessboard[p][q]='0';
						mygame.display();
						if(ischeck()==2)
					     {
							bluah =1;
							 continue;
						 }
						 else break;
				 }
				 
				}
				while(mygame.ispossible(p,q,r,s,2)==false||ischeck()==2);	
			//black input ends
			//if(bcastle==0)
			//{
			//mygame.chessboard[r][s]=mygame.chessboard[p][q];
			//mygame.chessboard[p][q]='0';
			//}
			bcastle=0;
			mygame.display();
			if(mygame.checkmate(1)>0) break;
		}
		if(mygame.checkmate(1)==1) {}//output
		if(mygame.checkmate(1)==2) {}//output
		if(mygame.checkmate(1)==3) {}//stalemate
		mygame.display();
		
	}
	public static int resolve1(String a)
	{
		switch(a.charAt(0))
		{
		case 'a' : return 7; 
		case 'b' : return 6; 
		case 'c': return 5; 
		case 'd': return 4; 
		case 'e': return 3; 
		case 'f': return 2; 
		case 'g': return 1; 
		case 'h': return 0;
		default: JOptionPane.showMessageDialog(null, "Invalid Input macha");
		
		}
		return -1;
	}
	public static int resolve2(String a)
	{
		switch(a.charAt(1))
		{
		case '1' : return 0; 
		case '2' : return 1; 
		case '3': return 2; 
		case '4': return 3; 
		case '5': return 4; 
		case '6': return 5; 
		case '7': return 6; 
		case '8': return 7;
		default: JOptionPane.showMessageDialog(null, "Invalid Input macha");
		
		}
		return -1;
	}
	public static  int resolve3(String a)
	{
		switch(a.charAt(6))
		{
		case 'a' : return 7; 
		case 'b' : return 6; 
		case 'c': return 5; 
		case 'd': return 4; 
		case 'e': return 3; 
		case 'f': return 2; 
		case 'g': return 1; 
		case 'h': return 0;
		default: JOptionPane.showMessageDialog(null, "Invalid Input macha");
		
		}
		return -1;
	}
	public static int resolve4(String a)
	{
		switch(a.charAt(7))
		{
		case '1' : return 0; 
		case '2' : return 1; 
		case '3': return 2; 
		case '4': return 3; 
		case '5': return 4; 
		case '6': return 5; 
		case '7': return 6; 
		case '8': return 7;
		default: JOptionPane.showMessageDialog(null, "Invalid Input macha");
		
		}
		return -1;
	}
	
	public static int ischeck() throws Exception {
		int i,j;
		boolean testw=false;
		boolean testb=false;
		game config = new game();
		java.io.File file = new java.io.File(System.getProperty("user.dir")+"/StrikerChessGameData.txt");
		Scanner fin = new Scanner(file);
		//input section
		for(i=0;i<8;i++) for (j=0;j<8;j++) 
			{
				String whatEver = fin.next();
				config.chessboard[i][j] = whatEver.charAt(0);
			} 
		//input ends
		
		//for(i=0;i<8;i++) {for (j=0;j<8;j++) 
		//{
		//	System.out.print(config.chessboard[i][j]);
		//}System.out.println("");}
		//journey begins here
		for(i=0;i<8;i++) 
		{
			for (j=0;j<8;j++)
			{
				if(config.chessboard[i][j]=='k') {testb = config.blackfunda(i,j);}
				if(config.chessboard[i][j]=='K') {testw = config.whitefunda(i,j);}
				if(testw==true||testb==true) break;
			}
			if(testw==true && testb==true) System.out.println("Stop putting cock level input");
			if(testw==true||testb==true) break;
		}
		fin.close();
		//journey ends here
		if(testw==true) return 1;
		if(testb==true) return 2;
		if(testw==false && testb==false) return 0;
		return 0;
 
	}

}
