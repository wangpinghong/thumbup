 import java.awt.*; 
import java.awt.event.*;

import javax.swing.JOptionPane;
 public class ALHW1_3A_101403518 extends Canvas { 
  public static void main(String[] args) {
   //設定框架
   Frame frame = new Frame("MyCanvasDemo_1");
   frame.add(new ALHW1_3A_101403518());
   
   //讓視窗右上角的X圖示被按下之後，視窗會關閉
   frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
     System.exit(0);
    }
       });
 
   frame.pack();
   frame.setVisible(true);
  }
  
  public ALHW1_3A_101403518() {
	   //設定Canvas元件大小，定為600*600  50個點看起來會比較清楚
	   setSize(600,600);
	  }
	   
  public void paint(Graphics g ){
	  //設定輸入的點數
	  int total =Integer.parseInt(JOptionPane.showInputDialog("輸入3~50的數字?"));
	  //宣告點和存放點的陣列
	  int x,y,now=0,count=0;
	  int a=0 ,b=0 ,c=0 ;
	  int xa[]= new int [52];
	  int ya[]=new int[52];
	  int line[]=new int[3];
	  boolean check=true;
	  //產生亂數點
	  for(int i=1;i<=total;i++){
		  //+50讓點不會位於邊界上
		  x=(int)(Math.random()*500+50);
		  y=(int)(Math.random()*500+50);
		  g.drawString("‧", x, y);
		  //將點座標儲存於陣列中
		  xa[i]=x;
		  ya[i]=y;
	  }
	  //確認進行convexhull 
	  JOptionPane.showMessageDialog(null, "開始");
	  //設立基準線段，端點a的座標
	  for(int i=1;i<total;i++){
		  //設立基準線段，端點b的座標
		  for(int j=i+1;j<=total;j++){
			  now=0;
			  //設立比較端點
			  for(int p=1;p<=lastnum(i,j,total);p++){
				 //判斷是否為三個不同端點
				  if(isdif(i,j,p)){
					  //為第幾個比較點
					  now++;
					//如果為第一比較點，將其設為基準，如果為非第一比較點，則用來和第一比較點比較
					if(now==1){
						check=isleft(setline(xa,ya,i,j,p));
					}else{
					if(isleft(setline(xa,ya,i,j,p))!=check){
						break;
					}else if(check==isleft(setline(xa,ya,i,j,p)) && p==lastnum(i,j,total)){
						JOptionPane.showMessageDialog(null, "繪製線段");
						g.drawLine(xa[i], ya[i], xa[j], ya[j]);
						}
					}
				 }
			 }
		  }
	  }
	  JOptionPane.showMessageDialog(null, "結束");
	  	  
  }
  //將端點帶入直線方程式中，得到一個正負值
  public int setline(int[] x, int[] y,int a ,int b ,int c){
		int ans= (y[b]-y[a])*x[c]+(x[a]-x[b])*y[c]-(x[a]*y[b]-y[a]*x[b]);
		return ans;
	  }
  //將正負值放入方法中，可得知是否為線段左邊，如果是則回傳true，反之false，加上=讓線段上也成立
  public boolean isleft(int a){
	  if(a<=0){
		  return true;
	  }else{
		  return false;
	  }
  }
  //判斷是否為三個不一樣的端點
  public boolean isdif(int a , int b ,int c){
	  if(a!= b && b!=c && a!=c){
		  return true;
	  }else{
		  return false;
	  }
  }
  //決定最後一個比較的點為第幾端點
  public int lastnum(int a ,int b ,int t){
		int x=0;
	  if(a==t && b==t){
			x=t-1;
			return x ; 
		}else if(a==t-1 && b==t){
			x=t-2;
			return x;
		}else if(b==t-1 && a==t){
			x=t-2;
			return x;
		}else if(a!=t-1 && b==t){
			x=t-1;
			return x;
		}else if(b!=t-1 &&a==t){
			x=t-1;
			return x;
		}else{
			return t;
		}
	
	  }
 }