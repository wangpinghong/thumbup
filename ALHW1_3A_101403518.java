 import java.awt.*; 
import java.awt.event.*;

import javax.swing.JOptionPane;
 public class ALHW1_3A_101403518 extends Canvas { 
  public static void main(String[] args) {
   //�]�w�ج[
   Frame frame = new Frame("MyCanvasDemo_1");
   frame.add(new ALHW1_3A_101403518());
   
   //�������k�W����X�ϥܳQ���U����A�����|����
   frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
     System.exit(0);
    }
       });
 
   frame.pack();
   frame.setVisible(true);
  }
  
  public ALHW1_3A_101403518() {
	   //�]�wCanvas����j�p�A�w��600*600  50���I�ݰ_�ӷ|����M��
	   setSize(600,600);
	  }
	   
  public void paint(Graphics g ){
	  //�]�w��J���I��
	  int total =Integer.parseInt(JOptionPane.showInputDialog("��J3~50���Ʀr?"));
	  //�ŧi�I�M�s���I���}�C
	  int x,y,now=0,count=0;
	  int a=0 ,b=0 ,c=0 ;
	  int xa[]= new int [52];
	  int ya[]=new int[52];
	  int line[]=new int[3];
	  boolean check=true;
	  //���Ͷü��I
	  for(int i=1;i<=total;i++){
		  //+50���I���|�����ɤW
		  x=(int)(Math.random()*500+50);
		  y=(int)(Math.random()*500+50);
		  g.drawString("�E", x, y);
		  //�N�I�y���x�s��}�C��
		  xa[i]=x;
		  ya[i]=y;
	  }
	  //�T�{�i��convexhull 
	  JOptionPane.showMessageDialog(null, "�}�l");
	  //�]�߰�ǽu�q�A���Ia���y��
	  for(int i=1;i<total;i++){
		  //�]�߰�ǽu�q�A���Ib���y��
		  for(int j=i+1;j<=total;j++){
			  now=0;
			  //�]�ߤ�����I
			  for(int p=1;p<=lastnum(i,j,total);p++){
				 //�P�_�O�_���T�Ӥ��P���I
				  if(isdif(i,j,p)){
					  //���ĴX�Ӥ���I
					  now++;
					//�p�G���Ĥ@����I�A�N��]����ǡA�p�G���D�Ĥ@����I�A�h�ΨөM�Ĥ@����I���
					if(now==1){
						check=isleft(setline(xa,ya,i,j,p));
					}else{
					if(isleft(setline(xa,ya,i,j,p))!=check){
						break;
					}else if(check==isleft(setline(xa,ya,i,j,p)) && p==lastnum(i,j,total)){
						JOptionPane.showMessageDialog(null, "ø�s�u�q");
						g.drawLine(xa[i], ya[i], xa[j], ya[j]);
						}
					}
				 }
			 }
		  }
	  }
	  JOptionPane.showMessageDialog(null, "����");
	  	  
  }
  //�N���I�a�J���u��{�����A�o��@�ӥ��t��
  public int setline(int[] x, int[] y,int a ,int b ,int c){
		int ans= (y[b]-y[a])*x[c]+(x[a]-x[b])*y[c]-(x[a]*y[b]-y[a]*x[b]);
		return ans;
	  }
  //�N���t�ȩ�J��k���A�i�o���O�_���u�q����A�p�G�O�h�^��true�A�Ϥ�false�A�[�W=���u�q�W�]����
  public boolean isleft(int a){
	  if(a<=0){
		  return true;
	  }else{
		  return false;
	  }
  }
  //�P�_�O�_���T�Ӥ��@�˪����I
  public boolean isdif(int a , int b ,int c){
	  if(a!= b && b!=c && a!=c){
		  return true;
	  }else{
		  return false;
	  }
  }
  //�M�w�̫�@�Ӥ�����I���ĴX���I
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