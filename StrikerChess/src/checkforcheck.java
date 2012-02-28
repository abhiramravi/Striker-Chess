
import java.util.Scanner;

import javax.swing.JOptionPane;



public class checkforcheck {

	public static int main(String[] args) throws Exception {
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
		
		for(i=0;i<8;i++) {for (j=0;j<8;j++) 
		{
			System.out.print(config.chessboard[i][j]);
		}System.out.println("");}
		//journey begins here
		for(i=0;i<8;i++) 
		{
			for (j=0;j<8;j++)
			{
				if(config.chessboard[i][j]=='k') {testb = config.blackfunda(i,j);System.out.print("k encountered");}
				if(config.chessboard[i][j]=='K') {testw = config.whitefunda(i,j);System.out.print("K encountered");}
				if(testw==true||testb==true) break;
			}
			if(testw==true && testb==true) System.out.println("Stop putting cock level input");
			if(testw==true||testb==true) break;
		}
		//journey ends here
		if(testw==true) return 1;
		if(testb==true) return 2;
		if(testw==false && testb==false) return 0;
		return 0;
 
	}
	
}
class game
{
	public game() {}
	int ischeckmate=0,checking=0,pending=0,wem=-1,bem=-1;
	int castling1=1;
	int castling2=1;
	int promoti=0;
	//int castlecheck=0;
	char[][] chessboard=new char[8][8];
	public void display() throws Exception
	{
		int i,j;
		java.io.File file = new java.io.File(System.getProperty("user.dir")+"/StrikerChessGameData.txt");
		java.io.PrintWriter fout = new java.io.PrintWriter(file);
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
				if(chessboard[i][j]!='0') System.out.print(" "+chessboard[i][j]+" |");
				else System.out.print("   |");
			}
			System.out.print("\n   |___|___|___|___|___|___|___|___|\n");
		}
		System.out.println("     1   2   3   4   5   6   7   8");
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				if(chessboard[i][j]=='0') fout.print(". ");
				else fout.print(chessboard[i][j]+" ");
			}
			fout.println("");
		}
		fout.close();
	}
	public boolean iswhite(int i,int j)
	{
		char backup=chessboard[i][j];
		char lowercase = Character.toLowerCase(backup);
		if(lowercase==chessboard[i][j]) return false;
		else return true;
	}
	public boolean ispossible(int i,int j,int r,int s,int worb) throws Exception
	{
		int k=i,l=j;
		if(worb==1) if(chessboard[i][j]=='0'||(!iswhite(i,j))) return false; //checks if you are moving your piece only :)
		if(worb==2) if(chessboard[i][j]=='0'||iswhite(i,j)) return false;
		if(worb==1)
		{
			if(chessboard[r][s]!='0') if(iswhite(r,s)) return false;
			switch(chessboard[i][j])
			{
			case 'R' :
			{
				
				i++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'P':
			{
				if(i==6&&r==4&&j==s) if(chessboard[4][j]=='0'&&chessboard[5][j]=='0'){ if(r==0&&checking==0) promotion(i,j);if(j-1>=0) if(chessboard[4][j-1]=='p'&&checking==0) bem=j;if(j+1<8) if(chessboard[4][j+1]=='p'&&checking==0) bem=j;return true;}
				if(chessboard[r][s]=='0') if(j==s&&i-r==1){if(r==0&&checking==0) promotion(i,j); return true;}
				if(chessboard[r][s]!='0') 
				{
					if((r==i-1&&s==j+1)||(r==i-1&&s==j-1)){ if(r==0&&checking==0) promotion(i,j);return true;}
				}	
				if(chessboard[r][s]=='0'&&checking==0)
				{
					if(wem!=-1)
					{
						if(chessboard[r+1][s]=='p'&&s==wem&&i-r==1&&Math.abs(s-j)==1) {wem=-1;chessboard[r+1][s]='0';pending=1;return true;}
					}
				}
				return false;
				
			}
			case 'N' :
			{
				
				if((r==i+2&&s==j+1)||(r==i+2&&s==j-1)||(r==i-2&&s==j+1)||(r==i-2&&s==j-1)||(r==i+1&&s==j+2)||(r==i+1&&s==j-2)||(r==i-1&&s==j+2)||(r==i-1&&s==j-2))
						return true;	
				else return false;
			}
			case 'B' :
			{
				
				i++;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i++;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'Q':
			{
				
				i++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i++;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i++;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j++;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j--;
					}
					else 
					{
							if(i==r&&j==s) if(!iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'K':
			{
				
				if(Math.abs(i-r)<=1&&Math.abs(j-s)<=1) {castling1=0;return true;}
				if(castling1==1&&r==7&&s==6) if(ifdocastling(1)) {castling1=2;System.out.println("castling1 is 2");return true;}
				if(castling1==1&&r==7&&s==2) if(ifdocastling(1)) {castling1=2;System.out.println("castling1 is 2");return true;}
				System.out.println("Shit da, the king cant make simple mevement also now");
				return false;
			}
			default: JOptionPane.showMessageDialog(null, "The program is failing for some reason...please report this");
			}
			
		}
		if(worb==2)
		{
			if(chessboard[r][s]!='0') if(!iswhite(r,s)) return false;
			switch(chessboard[i][j])
			{
			case 'r' :
			{
				
				i++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'p':
			{
				if(i==1&&r==3&&j==s) if(chessboard[2][j]=='0'&&chessboard[3][j]=='0') {if(r==7&&checking==0) promotion(i,j); if(j-1>=0) if(chessboard[3][j-1]=='P'&&checking==0) wem=j;if(j+1<8) if(chessboard[3][j+1]=='P'&&checking==0) wem=j;return true;}
				if(chessboard[r][s]=='0') if(j==s&&r-i==1){if(r==7&&checking==0) promotion(i,j); return true;}
				if(chessboard[r][s]!='0') 
				{
					if((r==i+1&&s==j+1)||(r==i+1&&s==j-1)){if(r==7&&checking==0) promotion(i,j); return true;}
				}
				if(chessboard[r][s]=='0'&&checking==0)
				{
					if(bem!=-1)
					{
						if(chessboard[r-1][s]=='P'&&s==bem&&r-i==1&&Math.abs(s-j)==1) {bem=-1;chessboard[r-1][s]='0';pending=1;return true;}
					}
				}
				return false;
				
			}
		
			case 'n' :
			{
				
				if((r==i+2&&s==j+1)||(r==i+2&&s==j-1)||(r==i-2&&s==j+1)||(r==i-2&&s==j-1)||(r==i+1&&s==j+2)||(r==i+1&&s==j-2)||(r==i-1&&s==j+2)||(r==i-1&&s==j-2))
						return true;	
				else return false;
			}
			case 'b' :
			{
				
				i++;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i++;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'q':
			{
				
				i++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						j--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i++;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i++;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i++;j--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j++;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j++;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				i--;j--;
				while(0<=i&&0<=j&&j<8&&i<8)
				{
					if(chessboard[i][j]=='0') 
					{
						if(i==r&&j==s) return true;
						i--;j--;
					}
					else 
					{
							if(i==r&&j==s) if(iswhite(r,s)) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'k':
			{
				
				if(Math.abs(i-r)<=1&&Math.abs(j-s)<=1) {castling2=0;return true;}
				if(castling2==1&&r==0&&s==6) if(ifdocastling(2)) {castling2=2;return true;}
				if(castling2==1&&r==0&&s==2) if(ifdocastling(2)) {castling2=2;return true;}
				return false;
			}
			default: JOptionPane.showMessageDialog(null, "The program is failing for some reason...please report this right away");
			}
		}
		return false;
	}
	public boolean whitefunda(int i,int j)
	{
		int k=i,l=j;
		//pawn check
		if(i-1>=0 && j+1<8) if(chessboard[i-1][j+1]=='p') return true;
		if(i-1>=0 && j-1>=0) if (chessboard[i-1][j-1]=='p') return true;
		//pawn check ends
		//rook check queen involved
		i++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') i++;
			else 
				{
					if(chessboard[i][j]=='r'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		i=k;j=l;
		j++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') j++;
			else 
				{
					if(chessboard[i][j]=='r'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		i=k;j=l;
		j--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') j--;
			else 
				{
					if(chessboard[i][j]=='r'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		i=k;j=l;
		i--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') i--;
			else 
				{
					if(chessboard[i][j]=='r'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		//rookcheck ends
		//knight check
		i=k;j=l;
		i++;j+=2; if(0<=i&&0<=j&&j<8&&i<8)if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		i++;j-=2;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		i--;j+=2;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		i--;j-=2;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		i+=2;j--;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		i+=2;j++;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		i-=2;j++;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		i-=2;j--;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='n') return true;
		i=k;j=l;
		//knightcheck ends
		//bishopcheck+ queen involved
		i++;j++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i++;j++;}
			else 
				{
					if(chessboard[i][j]=='b'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		i=k;j=l;
		i--;j++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i--;j++;}
			else 
				{
					if(chessboard[i][j]=='b'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		i=k;j=l;
		i++;j--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i++;j--;}
			else 
				{
					if(chessboard[i][j]=='b'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		i=k;j=l;
		i--;j--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i--;;j--;}
			else 
				{
					if(chessboard[i][j]=='b'||chessboard[i][j]=='q') return true;
					else break;
				}
		}
		//bishop check ends
		return false;
	}
	public boolean blackfunda(int i,int j)
	{
		int k=i,l=j;
		//pawn check
		if(i+1<8 && j+1<8) if(chessboard[i+1][j+1]=='P') return true;
		if(i+1<8 && j-1>=0) if (chessboard[i+1][j-1]=='P') return true;
		//pawn check ends
		//rook check queen involved
		i++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') i++;
			else 
				{
					if(chessboard[i][j]=='R'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		i=k;j=l;
		j++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') j++;
			else 
				{
					if(chessboard[i][j]=='R'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		i=k;j=l;
		j--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') j--;
			else 
				{
					if(chessboard[i][j]=='R'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		i=k;j=l;
		i--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') i--;
			else 
				{
					if(chessboard[i][j]=='R'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		//rookcheck ends
		//knight check
		i=k;j=l;
		i++;j+=2; if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		i++;j-=2;if(0<=i&&0<=j&&j<8&&i<8) if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		i--;j+=2; if(0<=i&&0<=j&&j<8&&i<8)if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		i--;j-=2; if(0<=i&&0<=j&&j<8&&i<8)if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		i+=2;j++; if(0<=i&&0<=j&&j<8&&i<8)if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		i+=2;j--; if(0<=i&&0<=j&&j<8&&i<8)if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		i-=2;j++; if(0<=i&&0<=j&&j<8&&i<8)if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		i-=2;j--; if(0<=i&&0<=j&&j<8&&i<8)if(chessboard[i][j]=='N') return true;
		i=k;j=l;
		//knightcheck ends
		//bishopcheck+ queen involved
		i++;j++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i++;j++;}
			else 
				{
					if(chessboard[i][j]=='B'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		i=k;j=l;
		i--;j++;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i--;j++;}
			else 
				{
					if(chessboard[i][j]=='B'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		i=k;j=l;
		i++;j--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i++;j--;}
			else 
				{
					if(chessboard[i][j]=='B'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		i=k;j=l;
		i--;j--;
		while(0<=i&&0<=j&&j<8&&i<8)
		{
			if(chessboard[i][j]=='.') {i--;;j--;}
			else 
				{
					if(chessboard[i][j]=='B'||chessboard[i][j]=='Q') return true;
					else break;
				}
		}
		//bishop check ends
		return false;
	}
	public void promotion(int i,int j)
	{
		if(i==1)
		{  
			String promo;
			char promot;
			do
			{
			 promo = JOptionPane.showInputDialog("Enter promotion index - Q,R,B,N");	
			 promot=promo.charAt(0);
			}
			while(promot!='Q'&&promot!='R'&&promot!='B'&&promot!='N');
			 
			switch(promot)
			{
			case 'Q':chessboard[i][j]='Q';break;
			case 'R':chessboard[i][j]='R';break;
			case 'N':chessboard[i][j]='N';break;
			case 'B':chessboard[i][j]='B';break;
			default:JOptionPane.showMessageDialog(null, "Stop putting cock level input");
			}
			promoti=1;
		}
		if(i==6)
		{
			String promo;
			char promot;
			do
			{
			 promo = JOptionPane.showInputDialog("Enter promotion index - q,r,b,n");	
			 promot=promo.charAt(0);
			}
			while(promot!='q'&&promot!='r'&&promot!='b'&&promot!='n');
			
			switch(promot)
			{
			case 'q':chessboard[i][j]='q';break;
			case 'r':chessboard[i][j]='r';break;
			case 'n':chessboard[i][j]='n';break;
			case 'b':chessboard[i][j]='b';break;
			default:JOptionPane.showMessageDialog(null, "Stop putting cock level input");
			}
			promoti=1;
		}	
		
	}
	public boolean ifdocastling(int i) throws Exception
	{
		//System.out.println("ifdocastling is on");
		if(i==1)
		{
			if(castling1==0) return false;
			if(castling1==1) 
			{
				//System.out.println("castling1 is 1");
				if(ischeckk()==0)
				{
					//System.out.println("ischeckk is 0");
					if(chessboard[7][4]=='K'&&chessboard[7][0]=='R'&&chessboard[7][1]=='0'&chessboard[7][2]=='0'&&chessboard[7][3]=='0')
					{
						chessboard[7][3]='K';display();
							if(ischeckk()==1) {chessboard[7][3]='0';return false;}
						chessboard[7][0]='0';
						chessboard[7][4]='0';
						chessboard[7][2]='K';
						chessboard[7][3]='R';display();
						if(ischeckk()==1) 
						{
							chessboard[7][0]='R';
							chessboard[7][4]='K';
							chessboard[7][2]='0';
							chessboard[7][3]='0';
							display();
							return false;
						}
						return true;
					}
					if(chessboard[7][4]=='K'&&chessboard[7][7]=='R'&&chessboard[7][5]=='0'&&chessboard[7][6]=='0')
					{
						chessboard[7][5]='K';display();
						if(ischeckk()==1){chessboard[7][5]='0';return false;}
						chessboard[7][4]='0';
						chessboard[7][5]='R';
						chessboard[7][6]='K';
						chessboard[7][7]='0';display();
						if(ischeckk()==1) 
						{
							chessboard[7][4]='K';
							chessboard[7][5]='0';
							chessboard[7][6]='0';
							chessboard[7][7]='R';
							display();
							return false;
						}
						return true;
					}
					return false;
				}
				else
				{
					return false;
				}
			}
		}
		if(i==2)
		{
			if(castling2==0) return false;
			if(castling2==1) 
			{
				if(ischeckk()==0)
				{
					if(chessboard[0][4]=='k'&&chessboard[0][0]=='r'&&chessboard[0][1]=='0'&chessboard[0][2]=='0'&&chessboard[0][3]=='0')
					{
						chessboard[0][3]='k';display();
						if(ischeckk()==2){chessboard[0][3]='0';return false;}
						chessboard[0][0]='0';
						chessboard[0][4]='0';
						chessboard[0][2]='k';
						chessboard[0][3]='r';display();
						if(ischeckk()==2) 
						{
							chessboard[0][0]='r';
							chessboard[0][4]='k';
							chessboard[0][2]='0';
							chessboard[0][3]='0';
							display();
							return false;
						}
						return true;
					}
					if(chessboard[0][4]=='k'&&chessboard[0][7]=='r'&&chessboard[0][5]=='0'&&chessboard[0][6]=='0')
					{
						chessboard[0][5]='k';display();
						if(ischeckk()==2){chessboard[0][5]='0';return false;}
						chessboard[0][4]='0';
						chessboard[0][5]='r';
						chessboard[0][6]='k';
						chessboard[0][7]='0';display();
						if(ischeckk()==2) 
						{
							chessboard[0][4]='k';
							chessboard[0][5]='0';
							chessboard[0][6]='0';
							chessboard[0][7]='r';
							display();
							return false;
						}
						return true;
					}
				}
				else
				{
					return false;
				}
			}
		}
		return false;
	}
	public int ischeckk() throws Exception {
		int i,j;
		boolean testw=false;
		boolean testb=false;
		game config = new game();
		java.io.File file = new java.io.File(System.getProperty("user.dir")+"/StrikerChessGameData.txt");
		Scanner fin2 = new Scanner(file);
		//input section
		for(i=0;i<8;i++) for (j=0;j<8;j++) 
			{
				String whatEver = fin2.next();
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
		//journey ends here
		fin2.close();
		if(testw==true) return 1;
		if(testb==true) return 2;
		if(testw==false && testb==false) return 0;
		return 0;

	}
    public int checkmate(int move) throws Exception
    {
    	char x,y;
    	checking=1;
    	//if(ischeckk()==0) return 0;
    	//else
    	{
    	// CHECK IF THE KING CAN MOVE IF ITS IN CHECK
    	
    	//END OF CHECKING IF THE KING CAN MOVE WHEN IN CHECK
    	//if it cannot, then check if any other piece can make a legal move and remove check.
    	for(int i=0;i<8;i++)
    		for(int j=0;j<8;j++)
    		{
    			for(int k=0;k<8;k++)
    				for(int l=0;l<8;l++)
    				{   					
						if(ispossible(i,j,k,l,move)) 
						{
						x = chessboard[i][j];
						y = chessboard[k][l];
						chessboard[k][l]=chessboard[i][j];
						chessboard[i][j]='0';
						display();	
						if (move==1) if(ischeckk()==0||ischeckk()==2)
							{chessboard[i][j]=x;
	    					chessboard[k][l]=y;display();checking=0;return 0;}
						if(move==2) if(ischeckk()==0||ischeckk()==1) {chessboard[i][j]=x;
    					chessboard[k][l]=y;display();checking=0;return 0;}					
						
						chessboard[i][j]=x;
    					chessboard[k][l]=y;
    					display();
						
						}
    				}
    		}
    	//if this part of the code has been reached, then the king is checkmated /stalemated
    	if(ischeckk()==0) return 3; //stalemate
    	if(ischeckk()==1) return 1; //white is checkmated
    	if(ischeckk()==2) return 2;// black is checkmated
    	return 0;
    	}
    }
}

