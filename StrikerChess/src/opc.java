import java.util.Scanner;

import javax.swing.JOptionPane;


public class opc 
{
	static String[] inp = new String[8];
	static char[][] chessboard = new char[8][8];
	public static void main(String[] args) throws Exception
	{
		Scanner jin = new Scanner(System.in);
		
		
		
		for(int i = 0 ; i < 8; i++ )
		{
			inp[i] = jin.next();
			for(int j = 0; j < 8; j++ )
			{
				if(inp[i].charAt(j)=='.') chessboard[i][j] = '0';
				else chessboard[i][j] = inp[i].charAt(j);
			}
		}
		
		/* Now checking the queries */
		
		int N;
		N = jin.nextInt();
		int x,y,a,b;
		while(N-->0)
		{
			x = jin.nextInt();
			y = jin.nextInt();
			a = jin.nextInt();
			b = jin.nextInt();
			if(isFree(x,y)) 
			{
				System.out.println("Y");
				continue;
			}
			if(isWhite(x,y))
			{
				if(isAttack(x,y,a,b,1)) System.out.println("Y");
				else System.out.println("N");
			}
			else
			{
				if(isAttack(x,y,a,b,2))  System.out.println("Y");
				else System.out.println("N");
			}			
		}
		
		
	}
	private static boolean isAttack(int x, int y, int a, int b, int i) throws Exception 
	{
		boolean poss = ispossible(x,y,a,b);
		if(poss && isFree(a,b)) return false;
		if(i==1)
		{
			if(poss) if(!isWhite(a,b)) return true;
		}
		if(i==2)
		{
			if(poss) if(isWhite(a,b)) return true;
		}
		return false;
	}
	public static boolean ispossible(int i,int j,int r,int s) throws Exception
	{
		int k=i,l=j;
		{
			switch(chessboard[i][j])
			{
			case 'R' : case 'r':
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'P':
			{
				if(Math.abs(s-j)==1 && r-i == 1) return true;
				return false;
				
			}
			case 'p':
			{
				if(Math.abs(s-j)==1 && r-i == -1) return true;
				return false;
			}
			case 'N' : case 'n':
			{
				
				if((r==i+2&&s==j+1)||(r==i+2&&s==j-1)||(r==i-2&&s==j+1)||(r==i-2&&s==j-1)||(r==i+1&&s==j+2)||(r==i+1&&s==j-2)||(r==i-1&&s==j+2)||(r==i-1&&s==j-2))
						return true;	
				else return false;
			}
			case 'B' : case 'b':
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'Q': case 'q':
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
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
							if(i==r&&j==s) return true;
							break;
					}
				}
				i=k;j=l;
				return false;
			}
			case 'K': case 'k':
			{
				
				if(Math.abs(i-r)<=1&&Math.abs(j-s)<=1) {return true;}
				return false;
			}
			default: JOptionPane.showMessageDialog(null, "The program is failing for some reason...please report this");
			}
			
		}
		
		return false;
	}
	public static boolean isWhite(int x, int y)
	{
		if(chessboard[x][y] != '0' && Character.isUpperCase(chessboard[x][y])) return true;
		else return false;
	}
	public static boolean isFree(int x, int y)
	{
		if(chessboard[x][y] == '0') return true;
		else return false;
	}

}
