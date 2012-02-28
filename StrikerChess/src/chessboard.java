import java.awt.*;
import java.net.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.FileNotFoundException;
@SuppressWarnings("serial")
public class chessboard extends JFrame implements MouseListener
{    
      public static void main(String[] args) throws Exception
      {
            final chessboard StrikerChess = new chessboard();             
      }
      // the whole constructor is for setting up the UI of the form
      @SuppressWarnings("deprecation")
	public chessboard() throws Exception
      {
            c = getContentPane();
            setBounds(400, 100, 470, 525);
            setBackground(new Color(204, 204, 204));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Striker Chess");
            setResizable(true);          
            c.setLayout(null);             
            pnlMain.setBounds(3, 3, 460, 460);
            pnlMain.setBackground(new Color(255, 255, 255));           
            c.add(pnlMain);
            newgame ng= new newgame();
            exit ex = new exit();
            about ab = new about();
            
          //putting GUI
            JMenuBar menuBar;
            JMenu menu, submenu;
            JMenuItem menuItem;
            JRadioButtonMenuItem rbMenuItem;
            JCheckBoxMenuItem cbMenuItem;

            //Create the menu bar.
            menuBar = new JMenuBar();

            //Build the first menu.
            menu = new JMenu("File");
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription(
                    "The only menu in this program that has menu items");
            menuBar.add(menu);

            //a group of JMenuItems
            menuItem = new JMenuItem("New Game",
                                     KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItem.addActionListener(ng);
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menu.add(menuItem);

            menuItem = new JMenuItem("Exit",
                    KeyEvent.VK_T);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(
			   KeyEvent.VK_1, ActionEvent.ALT_MASK));
			menuItem.addActionListener(ex);
			menuItem.getAccessibleContext().setAccessibleDescription(
			   "This doesn't really do anything");
			menu.add(menuItem);

            //putting about in the menu bar.
            menu = new JMenu("About");
            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription(
                    "This menu does nothing");
            menuBar.add(menu);
            setJMenuBar(menuBar);
            menuItem = new JMenuItem("(C)Copyright 2011, Abhiram(Striker)",
                    KeyEvent.VK_T);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(
			   KeyEvent.VK_1, ActionEvent.ALT_MASK));
			menuItem.addActionListener(ab);
			menuItem.getAccessibleContext().setAccessibleDescription(
			   "This doesn't really do anything");
			menu.add(menuItem);
            
            this.drawChessBoard();
            this.arrangeChessPieces();
            show();
      }
    
      game mygame = new game();
      //boolean pq=false,rs=false,move=true,wrongmove=false;
      int p=0,q=0,r=0,s=0,click=0,moveno=1,bluah=0;char t='0',f='0';String g=" ",h=" ";
      char move='w';
      public void mouseClicked(MouseEvent e)
      {   	  	   
    	   click++;
           if(move=='w')
           {
        	   System.out.println("White's move");        	 
        	   Object source = e.getSource();
               JPanel pnlTemp = (JPanel)source;         
               int intX = (pnlTemp.getX()/57);
               System.out.println("intX="+intX);             
               int intY = (pnlTemp.getY()/57);
               System.out.println("intY="+intY);
               if(click%2==1) {p=intY;q=intX;pnlChessCells[p][q].setBackground(Color.cyan);  }
               if(click%2==0) {r=intY;s=intX;System.out.println("p="+p+" q="+q+"r="+r+" s="+s);if((p%2==1&&q%2==1)||(p%2==0&&q%2==0)) pnlChessCells[p][q].setBackground(Color.WHITE);else pnlChessCells[p][q].setBackground(Color.lightGray); } 
               if(click%2==0)
               {
            	   this.pntMoveFrom=new Point(q,p);
        		   this.pntMoveTo=new Point(s,r);
            	   try {
					if(mygame.ispossible(p, q, r, s, 1))
					   {
						   System.out.println("The white's move is possible");
						   t=mygame.chessboard[p][q];f=mygame.chessboard[r][s];
					 	   mygame.chessboard[r][s]=mygame.chessboard[p][q];
						   mygame.chessboard[p][q]='0';
							try {
								mygame.display();
							} catch (Exception e1) {
								
								e1.printStackTrace();
							}
							try {
								if(mygame.ischeckk()==1)
								{
									 mygame.chessboard[p][q]=t;mygame.chessboard[r][s]=f;
									 JOptionPane.showMessageDialog(null, "You have no choice but to protect your king!");
								}
								else
								{
									this.strChessBoard[r][s] = this.strChessBoard[p][q].toString();
					                this.strChessBoard[p][q] = "  ";
					                this.moveChessPiece();  
					             //   if(mygame.pending==1) {this.strChessBoard[r+1][s]=" "; mygame.pending=0;}
					             //   if(mygame.pending==2) {this.strChessBoard[r-1][s]=" "; mygame.pending=0;}
					               
					             //   arrangeChessPieces();
					                move='b';
								}
							} catch (Exception e1) {
								
								e1.printStackTrace();
							}
							if(mygame.promoti==1)
							{
								switch(mygame.chessboard[r][s])
								{
								case 'Q':this.strChessBoard[r][s]="QW";break;
								case 'R':this.strChessBoard[r][s]="RW";break;
								case 'B':this.strChessBoard[r][s]="BW";break;
								case 'N':this.strChessBoard[r][s]="NW";break;
								}			
								arrangeChessPieces();
								mygame.promoti=0;
							}
							if(mygame.castling1==2)
							{
								switch(s)
								{
								case 6 : this.strChessBoard[7][5] = this.strChessBoard[7][7].toString();
					            			this.strChessBoard[7][7] = "  ";System.out.println("we are in case 6");mygame.chessboard[7][6]='K';
					            			this.pntMoveFrom = new Point(7,7);this.pntMoveTo = new Point(5,7);
					            				this.moveChessPiece(); break;
								case 2 : this.strChessBoard[7][3] = this.strChessBoard[7][0].toString();
											this.strChessBoard[7][0] = "  ";System.out.println("we are in case 2");mygame.chessboard[7][2]='K';
											this.pntMoveFrom = new Point(0,7);this.pntMoveTo = new Point(3,7);
												this.moveChessPiece(); break;
								}
								mygame.castling1=0;
								mygame.display();
							}
							mygame.wem=-1;
							if(mygame.pending==1)
							{
								this.strChessBoard[r+1][s]="  ";
								arrangeChessPieces();
								mygame.pending=0;
							}
					   }
					   else
					   {
						  // JOptionPane.showMessageDialog(null, "Stop putting fart level moves.....try again");
						   this.makeChessPieceDifferent(false);
					   }
				} catch (HeadlessException e1) {
					
					e1.printStackTrace();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
               }
               
	   			//check for checkmate or stalemate
	   			try {
					if(mygame.checkmate(2)!=0)
					{
						switch(mygame.checkmate(2))
						{
						case 1: JOptionPane.showMessageDialog(null,"BLACK WINS! put treat da :D");break;
						case 2: JOptionPane.showMessageDialog(null,"WHITE WINS! put treat da :D");break;
						case 3: JOptionPane.showMessageDialog(null,"STALEMATE! both of you lose! :P");break;
						}
						mygame.ischeckmate=1;
						c.removeAll();
					}
				} catch (HeadlessException e1) {					
					e1.printStackTrace();
				} catch (Exception e1) {					
					e1.printStackTrace();
				}
				//check for opp check
	               try {
	   				if(mygame.ischeckk()==2&&mygame.ischeckmate==0) JOptionPane.showMessageDialog(null, "BLACK! CHECK!");
	   			} catch (HeadlessException e2) {
	   				
	   				e2.printStackTrace();
	   			} catch (Exception e2) {
	   				
	   				e2.printStackTrace();
	   			}
	           }
           else
           {
        	   System.out.println("Black's move");        	  
        	   Object source = e.getSource();
               JPanel pnlTemp = (JPanel)source;         
               int intX = (pnlTemp.getX()/57);
               System.out.println("intX="+intX);             
               int intY = (pnlTemp.getY()/57);
               System.out.println("intY="+intY);
               if(click%2==1) {p=intY;q=intX;pnlChessCells[p][q].setBackground(Color.cyan);  }
               if(click%2==0) {r=intY;s=intX;System.out.println("p="+p+" q="+q+"r="+r+" s="+s);if((p%2==1&&q%2==1)||(p%2==0&&q%2==0)) pnlChessCells[p][q].setBackground(Color.WHITE);else pnlChessCells[p][q].setBackground(Color.lightGray); }
               if(click%2==0)
               {
            	   this.pntMoveFrom=new Point(q,p);
        		   this.pntMoveTo=new Point(s,r);
            	   try {
					if(mygame.ispossible(p, q, r, s, 2))
					   {        
						   System.out.println("The black's move is possible");
						   t=mygame.chessboard[p][q];f=mygame.chessboard[r][s];
					 	   mygame.chessboard[r][s]=mygame.chessboard[p][q];
						   mygame.chessboard[p][q]='0';
							try {
								mygame.display();
							} catch (Exception e1) {
								
								e1.printStackTrace();
							}
							try {
								if(mygame.ischeckk()==2)
								{
									 mygame.chessboard[p][q]=t;mygame.chessboard[r][s]=f;
									 JOptionPane.showMessageDialog(null, "You have no choice but to protect your king!");
								}
								else
								{
									this.strChessBoard[r][s] = this.strChessBoard[p][q].toString();
					                this.strChessBoard[p][q] = "  ";
					                this.moveChessPiece();  
					            //    if(mygame.pending==1) {this.strChessBoard[r+1][s]=" "; mygame.pending=0;}
					            //    if(mygame.pending==2) {this.strChessBoard[r-1][s]=" "; mygame.pending=0;}					                
					            //    arrangeChessPieces();
					                move='w';
								}
							} catch (Exception e1) {
								
								e1.printStackTrace();
							}
							if(mygame.promoti==1)
							{
								switch(mygame.chessboard[r][s])
								{
								case 'q':this.strChessBoard[r][s]="QB";break;
								case 'r':this.strChessBoard[r][s]="RB";break;
								case 'b':this.strChessBoard[r][s]="BB";break;
								case 'n':this.strChessBoard[r][s]="NB";break;
								}		
								arrangeChessPieces();
								mygame.promoti=0;
							}
							if(mygame.castling2==2)
							{
								switch(s)
								{
								case 6 : this.strChessBoard[0][5] = this.strChessBoard[0][7].toString();
					            			this.strChessBoard[0][7] = "  ";System.out.println("we are in case 6");mygame.chessboard[0][6]='k';
					            			this.pntMoveFrom = new Point(7,0);this.pntMoveTo = new Point(5,0);
					            				this.moveChessPiece(); break;
								case 2 : this.strChessBoard[0][3] = this.strChessBoard[0][0].toString();
											this.strChessBoard[0][0] = "  ";System.out.println("we are in case 2");mygame.chessboard[0][2]='k';
											this.pntMoveFrom = new Point(0,0);this.pntMoveTo = new Point(3,0);
												this.moveChessPiece(); break;
								}
								mygame.castling2=0;
								mygame.display();
							}
							mygame.bem=-1;
							if(mygame.pending==1)
							{
								this.strChessBoard[r-1][s]="  ";
								arrangeChessPieces();
								mygame.pending=0;
							}
					   }
					   else
					   {
						   //JOptionPane.showMessageDialog(null, "stop putting pack level moves.....try again");
						   this.makeChessPieceDifferent(false);
					   }
				} catch (HeadlessException e1) {
					
					e1.printStackTrace();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
               }
              
      			try {
					if(mygame.checkmate(1)!=0)
					{
						switch(mygame.checkmate(1))
						{
						case 1: JOptionPane.showMessageDialog(null,"BLACK WINS! put treat da :D");break;
						case 2: JOptionPane.showMessageDialog(null,"WHITE WINS! put treat da :D");break;
						case 3: JOptionPane.showMessageDialog(null,"STALEMATE! both of you lose! :P");break;
						}
						mygame.ischeckmate=1;
						c.removeAll();
					}
				} catch (HeadlessException e1) {			
					e1.printStackTrace();
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				 //check for opponent check
	               try {
	      				if(mygame.ischeckk()==1&&mygame.ischeckmate==0) JOptionPane.showMessageDialog(null, "WHITE! CHECK!");
	      			} catch (HeadlessException e2) {
	      				
	      				e2.printStackTrace();
	      			} catch (Exception e2) {
	      				
	      				e2.printStackTrace();
	      			}
           }
      }    
      // This method checks if attempted move is valid or not
      private boolean isMoveValid()
      {
            boolean isMoveValid = true;
            return isMoveValid;
      }
      // This method makes the selected chess piece looks like selected
      private void makeChessPieceDifferent(boolean bSelected)
      {
      for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++)
            if(this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)
            {
                  JLabel lblTemp = (JLabel)this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z);
                  lblTemp.setEnabled(!bSelected);
            }          
      }
      // If class level variables Point-From and Point-To are set,
      // then this method actually moves a piece, if any exists, from
      // one cell to the other
      private void moveChessPiece()
      {
      for(int z = 0; z < this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponentCount(); z++)
            if(this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)
            {
                  this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].remove(z);
                  this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].repaint();
            }
      for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++)
            if(this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)
            {
                  this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].remove(z);
                  this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].repaint();
            }
          this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].add(this.getPieceObject(this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x]), BorderLayout.CENTER);
      this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].validate();
      }
      // Given the code of a piece as a string, this method instantiates
      // a label object with the right image inside it
      private JLabel getPieceObject(String strPieceName)
      {
            JLabel lblTemp;
      if(strPieceName.equals("RB"))
            lblTemp = new JLabel(this.rookBlack);
      else if(strPieceName.equals("BB"))
            lblTemp = new JLabel(this.bishopBlack);
      else if(strPieceName.equals("NB"))
            lblTemp = new JLabel(this.knightBlack);
      else if(strPieceName.equals("QB"))
            lblTemp = new JLabel(this.queenBlack);
      else if(strPieceName.equals("KB"))
            lblTemp = new JLabel(this.kingBlack);
      else if(strPieceName.equals("PB"))
            lblTemp = new JLabel(this.pawnBlack);
      else if(strPieceName.equals("RW"))
            lblTemp = new JLabel(this.rookWhite);
      else if(strPieceName.equals("BW"))
            lblTemp =  new JLabel(this.bishopWhite);
      else if(strPieceName.equals("NW"))
            lblTemp = new JLabel(this.knightWhite);
      else if(strPieceName.equals("QW"))
            lblTemp = new JLabel(this.queenWhite);
      else if(strPieceName.equals("KW"))
            lblTemp = new JLabel(this.kingWhite);
      else if(strPieceName.equals("PW"))
            lblTemp = new JLabel(this.pawnWhite);
      else
            lblTemp = new JLabel();
            return lblTemp;
      }
      // This method reads strChessBoard two-dimensional array of string
      // and places chess pieces at their right positions
      private void arrangeChessPieces() throws Exception
      {     
    	  
    		int i=0,j=0,k=0,l=0;
    		//java.io.File file = new java.io.File("/home/striker/Documents/programs/JAVA/input1.txt");
    		//java.io.PrintWriter fout = new java.io.PrintWriter(file);
    		//Initialize
    		for(i=0;i<8;i++) for(j=0;j<8;j++) mygame.chessboard[i][j]='0';
    		for(i=0;i<8;i++) for(j=0;j<8;j++) 
    		{
    			if(strChessBoard[i][j]=="  ") mygame.chessboard[i][j]='0';
    			if(strChessBoard[i][j]=="PB") mygame.chessboard[i][j]='p';
    			if(strChessBoard[i][j]=="PW") mygame.chessboard[i][j]='P';
    			if(strChessBoard[i][j]=="RB") mygame.chessboard[i][j]='r';
    			if(strChessBoard[i][j]=="NB") mygame.chessboard[i][j]='n';
    			if(strChessBoard[i][j]=="BB") mygame.chessboard[i][j]='b';
    			if(strChessBoard[i][j]=="QB") mygame.chessboard[i][j]='q';
    			if(strChessBoard[i][j]=="KB") mygame.chessboard[i][j]='k';
    			if(strChessBoard[i][j]=="RW") mygame.chessboard[i][j]='R';
    			if(strChessBoard[i][j]=="NW") mygame.chessboard[i][j]='N';
    			if(strChessBoard[i][j]=="BW") mygame.chessboard[i][j]='B';
    			if(strChessBoard[i][j]=="QW") mygame.chessboard[i][j]='Q';
    			if(strChessBoard[i][j]=="KW") mygame.chessboard[i][j]='K';    			
    		}
    		mygame.display();
            for(int y = 0; y < 8; y++)       
            for(int x = 0; x < 8; x++) 
            {                
            	  this.pnlChessCells[y][x].removeAll();
                  this.pnlChessCells[y][x].add(this.getPieceObject(strChessBoard[y][x]), BorderLayout.CENTER);
                  this.pnlChessCells[y][x].validate();
            }          
      }
      // This method draws chess board, i.e. black and white cells on the board
      private void drawChessBoard() throws FileNotFoundException
      {
    	  
            for (int y = 0; y < 8; y++)
                  for (int x = 0; x < 8; x++)
                  {
                        pnlChessCells[y][x] = new JPanel(new BorderLayout());
                        pnlChessCells[y][x].addMouseListener(this);
                        pnlMain.add(pnlChessCells[y][x]);
                        if (y % 2 == 0)
                              if (x % 2 != 0)
                               pnlChessCells[y][x].setBackground(Color.lightGray);                     			
                              else
                                    pnlChessCells[y][x].setBackground(Color.WHITE);
                        else
                              if (x % 2 == 0)
                                    pnlChessCells[y][x].setBackground(Color.lightGray);
                              else
                                    pnlChessCells[y][x].setBackground(Color.WHITE);
                  }
      }
      int xx,yy;
      public void mouseEntered(MouseEvent e)
      {
    	 
      } 
      public void mouseReleased(MouseEvent e)
      {    	  
          
      }
      public void mouseExited(MouseEvent e)
      {
    	 
      }  
      public void mousePressed(MouseEvent e){}
      private JPanel[][] pnlChessCells = new JPanel[8][8];
      private JPanel pnlMain = new JPanel(new GridLayout(8,8));
      public String[][] strChessBoard = new String[][] { {"RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB" }, {"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB"}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"PW", "PW", "PW", "PW", "PW", "PW", "PW", "PW"}, {"RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW"} };
      private ImageIcon rookBlack = new ImageIcon(System.getProperty("user.dir")+"/pieces/br.png");
      private ImageIcon rookWhite = new ImageIcon(System.getProperty("user.dir")+"/pieces/wr.png");
      private ImageIcon bishopBlack = new ImageIcon(System.getProperty("user.dir")+"/pieces/bb.png");
      private ImageIcon bishopWhite = new ImageIcon(System.getProperty("user.dir")+"/pieces/wb.png");
      private ImageIcon knightBlack = new ImageIcon(System.getProperty("user.dir")+"/pieces/bn.png");
      private ImageIcon knightWhite = new ImageIcon(System.getProperty("user.dir")+"/pieces/wn.png");
      private ImageIcon kingBlack = new ImageIcon(System.getProperty("user.dir")+"/pieces/bk.png");
      private ImageIcon kingWhite = new ImageIcon(System.getProperty("user.dir")+"/pieces/wk.png");
      private ImageIcon queenBlack = new ImageIcon(System.getProperty("user.dir")+"/pieces/bq.png");
      private ImageIcon queenWhite = new ImageIcon(System.getProperty("user.dir")+"/pieces/wq.png");
      private ImageIcon pawnBlack = new ImageIcon(System.getProperty("user.dir")+"/pieces/bp.png");
      private ImageIcon pawnWhite = new ImageIcon(System.getProperty("user.dir")+"/pieces/wp.png");
      private boolean boolMoveSelection = false, bWhite = true, bMyTurn = true;
      private Point pntMoveFrom, pntMoveTo;
      private Container c;
      class newgame implements ActionListener
      {

      	@SuppressWarnings("deprecation")
		@Override
      	public void actionPerformed(ActionEvent e) {
      		int i,j;
      		c = getContentPane();
            setBounds(400, 100, 470, 525);
            setBackground(new Color(204, 204, 204));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Striker Chess");
            setResizable(true);          
            c.setLayout(null);             
            pnlMain.setBounds(3, 3, 460, 460);
            pnlMain.setBackground(new Color(255, 255, 255));           
            c.add(pnlMain);
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
      		strChessBoard = new String[][] { {"RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB" }, {"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB"}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"PW", "PW", "PW", "PW", "PW", "PW", "PW", "PW"}, {"RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW"} };
      		try {
				arrangeChessPieces();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			click=0;move='w';
      	}
      	
      }
      class exit implements ActionListener
      {

		
		public void actionPerformed(ActionEvent e) {
			
			System.exit(0);
		}
    	  
      }
      class about extends JFrame  implements ActionListener
      {
    	 Container d= new Container();
    	public about()
    	{
    		d = getContentPane();
            setBounds(100, 100, 470, 300);
            setBackground(new Color(204, 204, 204));
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setTitle("About StrikerChess");
            setResizable(false);          
            d.setLayout(null);
            pnlMain2.setBounds(3, 3, 460, 460);
            pnlMain2.setBackground(new Color(255, 255, 255));  
            d.add(pnlMain2);
    	}
    	private JPanel pnlMain2 = new JPanel(new BorderLayout());
    	private ImageIcon credits = new ImageIcon(System.getProperty("user.dir")+"/pieces/StrikerChess.png");  	
    	private JLabel getPieceObj()
    	{
    		JLabel lblTemp = new JLabel(credits);  	      
    	      return lblTemp;
    	}
    	{
    		
    	pnlMain2.setBackground(Color.WHITE);
    	pnlMain2.removeAll();
        pnlMain2.add(getPieceObj(), BorderLayout.NORTH);
        pnlMain2.validate();
    	}
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			
			show();			
		}
    	  
      }
}    
      
      
      
      
      
      
      
    //BULLSHIT CODE THAT I WROTE - KEPT TO LEARN FROM MISTAKES
      
   // if(this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals(""))
      //       this.boolMoveSelection = !this.boolMoveSelection;
      // if((!this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals("")) &&
      //             this.bWhite && this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().charAt(1) == 'B')
      //       this.boolMoveSelection = !this.boolMoveSelection;
      // if((!this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals("")) &&
      //             !this.bWhite && this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().charAt(1) == 'W')
      //       this.boolMoveSelection = !this.boolMoveSelection;
      // if(this.boolMoveSelection)
      //       this.makeChessPieceDifferent(true);http://www.facebook.com/profile.php?id=1230967509
 
 //else
 //{
 //      this.pntMoveTo = new Point(intX, intY);
 //      if(!this.pntMoveFrom.equals(this.pntMoveTo))
  //     {
  //           if(      this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim() != "")
 //                  if(this.isMoveValid())
  //                 {                                        
//                         this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x] = this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString();
  //                       this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x] = "  ";
    //                     this.moveChessPiece();                                     
      //             }
        //           else
          //         {
            //             JOptionPane.showMessageDialog(this, "Invalid Move Request.", "Warning", JOptionPane.ERROR_MESSAGE);
              //           this.makeChessPieceDifferent(false);
                //   }
      // }
      // else
       //      this.makeChessPieceDifferent(false);
 //}  








//for(int i=0;i<8;i++)
//	for(int j=0;j<8;j++)
//	{
//		if(chessboard[i][j]=='k'||chessboard[i][j]=='K') 
//		{
//			for(int k=0;k<8;k++)
//				for(int l=0;l<8;l++)
//				{
//					int move=0;
//					if(chessboard[i][j]=='k') move=2;
//					if(chessboard[i][j]=='K') move=1;
//					if(ispossible(i,j,k,l,move)) 
//					{
//					x = chessboard[i][j];
//					y = chessboard[k][l];
//					chessboard[k][l]=chessboard[i][j];
//					chessboard[i][j]='0';
//					display();	
//					if(ischeckk()==0) return 0;
//					else
//					{
//						chessboard[i][j]=x;
//						chessboard[k][l]=y;
//						display();
//					}
//					}
//				}
//		}
//		
//	}
