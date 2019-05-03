import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import javax.management.RuntimeErrorException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ThePanel extends JFrame{
	private JPanel south;
	private DrawingPanel center;	
	private JButton add,connect,Delete; //edit
	private Vector v;
	private Node node;
	boolean flag=false;int f;
	 boolean dragging=false;
	 class MyException extends Exception{
		 public MyException() { super();
		}
		 
	}
	 class  MyException2 extends Exception{
		 public MyException2() { super();
		}}	
		 class MyException3 extends Exception{
			 public MyException3() { super();
			}
	}
			class DrawingPanel extends JPanel {
				public DrawingPanel(){}
				public void paintComponent(Graphics g){
					super.paintComponent(g);
					
					Node temp=new Node();Point p,p2;Node tempLink=new Node();
					
					 for(int i=0;i<v.size();i++){
						 temp=(Node)v.get(i);
						 p=new Point(temp.getPoint());
						 g.setColor(Color.GREEN);
				        	g.fillRect(p.x, p.y, 40, 40);
							g.setColor(Color.BLACK);
				            g.drawString(""+temp.getData(), p.x+15, p.y+25);
				            g.setColor(Color.MAGENTA);
				            g.drawRect(p.x, p.y, 40, 40);	 
					 }//for
					 for(int i=0;i<v.size()-1;i++){
						 temp=(Node)v.get(i);
						 p=new Point(temp.getPoint());
						 for(int j=i+1;j<v.size();j++){
						 tempLink=(Node)v.get(j);
						 p2=new Point(tempLink.getPoint());
						 for(int k=0;k<20;k++)
						 if(tempLink.getData()==temp.adjacent[k]&&temp.adjacent[k]!=-1){
						 g.setColor(Color.RED);
						 g.drawLine(p.x,p.y,p2.x,p2.y );}}
					 }
						
					if(dragging){
						g.setColor(Color.GREEN);
			        	g.fillRect(node.getPoint().x, node.getPoint().y, 40, 40);
						g.setColor(Color.BLACK);
			            g.drawString(""+node.getData(), node.getPoint().x+15, node.getPoint().y+25);
			            g.setColor(Color.magenta);
			            g.drawRect(node.getPoint().x, node.getPoint().y, 40, 40);
						
					}	
						 
						 
				}
				
			}
	public ThePanel(){
		super("Graph Edittor");
		 pack();
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   int height = screenSize.height;
		   int width = screenSize.width;
		   setSize(width/2, height/2);
		   setLocationRelativeTo(null);
		Color C = new Color(196,167,209);
		south=new JPanel();
		center=new DrawingPanel();	
		v=new Vector();
		
		add=new JButton("Add New Node"); add.setBackground(C);
		connect=new JButton("Add New Link");connect.setBackground(C);
		Delete=new JButton("Delete Node");Delete.setBackground(C);
		south.setLayout(new FlowLayout());
		south.add(add);
		south.add(connect);
		south.add(Delete);
		//center.setBackground(new Color(239,229,131));
		center.setBackground(Color.WHITE);
		//south.setBackground(new Color(239,229,131));
		add(south,BorderLayout.SOUTH);
		add(center,BorderLayout.CENTER);


		
		add.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						String s;	
						node=new Node();
						
						try{
						s=JOptionPane.showInputDialog(ThePanel.this,"Enter a number for the node you want to add");
						Node temp=new Node();
						 for(int i=0;i<v.size();i++){
							 temp=(Node)v.get(i);
							 if(temp.getData()==Integer.parseInt(s))
								 throw new MyException(); }
						node.setData(Integer.parseInt(s));
						
						v.add(node);
						repaint();	
					
						}
						catch ( NumberFormatException e){
							JOptionPane.showMessageDialog(ThePanel.this,"you can ONLY enter \n integers please try again"
									,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
						}
						catch ( MyException e){
							JOptionPane.showMessageDialog(ThePanel.this,"the number you entered already exists \n please  try again with another value"
									,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
						}
						
					}	
				}
				);
		connect.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
					try{
				      String d1,d2;
						d1=	JOptionPane.showInputDialog(ThePanel.this,"Enter the first node");
					boolean found =false;boolean found2 =false;boolean found3 =false;
						Node temp=new Node();int i;Node temp2=new Node();
						for( i=0;i<v.size();i++){
							temp=(Node)v.get(i);
							 if(temp.getData()==Integer.parseInt(d1))found=true;
							 }
						if(!found)	 throw new MyException2();
						d2=JOptionPane.showInputDialog(ThePanel.this,"Enter the second node");
						for( i=0;i<v.size();i++){
							temp=(Node)v.get(i);
							 if(temp.getData()==Integer.parseInt(d2))found2=true;
							 }	if(!found2)	 throw new MyException2();
								
							 for( i=0;i<v.size();i++){
									temp=(Node)v.get(i);
							if(temp.getData()==Integer.parseInt(d1))
								for(int k=0;k<20;k++){
									if(temp.adjacent[k]==Integer.parseInt(d2))found3=true;
								}	
								}
									if(found3)	throw new MyException3();
									
								
	
					
						if(Integer.parseInt(d1 )==Integer.parseInt(d2 ))throw new  MyException();
						
						Node link=new Node();
						 for( i=0;i<v.size();i++){
							 link=(Node)v.get(i);
							  if(Integer.parseInt(d2)==link.getData())
								 link.adjacent[link.count++]=Integer.parseInt(d1 ); 
							 if(Integer.parseInt(d1)==link.getData())
								 link.adjacent[link.count++]=Integer.parseInt(d2); 
							 
							 }
						
					 repaint ();	}
					catch(  MyException e ){
						JOptionPane.showMessageDialog(ThePanel.this,"you can NOT make a link from the same node to itself"
							,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
					}
					catch( MyException2 e){
					JOptionPane.showMessageDialog(ThePanel.this,"there is no such node "
								,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);	
						
						
					}
					catch( MyException3 e){
						JOptionPane.showMessageDialog(ThePanel.this,"this link is already added "
									,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);	
							
							
						}
					catch ( NumberFormatException e){
						JOptionPane.showMessageDialog(ThePanel.this,"you can ONLY enter \n integers please try again"
								,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
					}catch(ArrayIndexOutOfBoundsException e){
						JOptionPane.showMessageDialog(ThePanel.this,"you can ONLY make a link with 20 nodes please delete a link then try again"
								,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
						
						
						
					}
					
				}
				}
				);
		Delete.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
					try{
					String S;
					S=	JOptionPane.showInputDialog(ThePanel.this,"Enter the node's number you wish to delete");
					boolean found =false;
					Node temp=new Node();int i;
					for( i=0;i<v.size();i++){
						temp=(Node)v.get(i);
						 if(temp.getData()==Integer.parseInt(S))found=true;
						 }
					if(!found)	 throw new MyException2();
					Node delete=new Node();Node delete2=new Node();
					 for( i=0;i<v.size();i++){
						 delete=(Node)v.get(i);
						  if(Integer.parseInt(S)==delete.getData()){
							  for(int k=0;k<0;k++)
								  delete.adjacent[k]=-1;
								for(int j=0;j<v.size();j++){
									 delete2=(Node)v.get(j);
									 for(int k=0;k<20;k++)
									if(delete.adjacent[k]==delete2.getData()){
										 for(int l=0;l<20;l++)
											 if(delete2.adjacent[l]==Integer.parseInt(S))delete2.adjacent[l]=-1;
									}
								}
							v.remove(i); 
						  }
					 }
					 repaint ();	    	
				}catch(MyException2 e){
					JOptionPane.showMessageDialog(ThePanel.this,"there is no such node "
							,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
					
				}catch ( NumberFormatException e){
					JOptionPane.showMessageDialog(ThePanel.this,"you can ONLY enter \n integers please try again"
							,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
				}
				}}
				);	
		center.addMouseListener(
				new MouseAdapter(){
					
			 public void mousePressed(MouseEvent event) {
				 Node m=new Node();String S; Node m2=new Node();
				 for(int i=0;i<v.size();i++){
					 m=(Node)v.get(i);
				if(((event.getX()<m.getPoint().x+40)&&(event.getX()>m.getPoint().x))
				 &&((event.getY()<m.getPoint().y+40)&&(event.getY()>m.getPoint().y))){
				f=m.getData();
				/*if(event.isMetaDown()){
					try{
					S=	JOptionPane.showInputDialog(ThePanel.this,"Enter the new number ");
				
					Node temp=new Node();
					 for(int k=0;k<v.size();k++){
						 temp=(Node)v.get(k);
						 if(temp.getData()==Integer.parseInt(S))
							 throw new MyException(); }
						m.setData(Integer.parseInt(S));
					for(int j=0;j<v.size();j++){
						 m2=(Node)v.get(j);
						 for(int k=0;k<20;k++)
						if(m.adjacent[k]==m2.getData()){
							 for(int l=0;l<20;l++)
								 if(m2.adjacent[l]==f)m2.adjacent[l]=Integer.parseInt(S);	
						}
					}	}
					catch(MyException e){
						JOptionPane.showMessageDialog(ThePanel.this,"the number you entered already exists \n please try again with another different one"
								,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
		}//catch
					catch ( NumberFormatException e){
						JOptionPane.showMessageDialog(ThePanel.this,"you can ONLY enter \n integers please try again"
								,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
	}//catch
				}//if*/
				}//big if
				
				 dragging=false;	
				 repaint();	 
			 }//for
				 }//end
				
				
			 public void mouseReleased(MouseEvent event) {
			
				 Node m=new Node();String S; Node m2=new Node();
				 for(int i=0;i<v.size();i++){
					 m=(Node)v.get(i);
				if(((event.getX()<m.getPoint().x+40)&&(event.getX()>m.getPoint().x))
				 &&((event.getY()<m.getPoint().y+40)&&(event.getY()>m.getPoint().y))){
				f=m.getData();
				if(event.isMetaDown()){
					try{
					S=	JOptionPane.showInputDialog(ThePanel.this,"Enter the new number ");
				
					Node temp=new Node();
					 for(int k=0;k<v.size();k++){
						 temp=(Node)v.get(k);
						 if(temp.getData()==Integer.parseInt(S))
							 throw new MyException(); }
						m.setData(Integer.parseInt(S));
					for(int j=0;j<v.size();j++){
						 m2=(Node)v.get(j);
						 for(int k=0;k<20;k++)
						if(m.adjacent[k]==m2.getData()){
							 for(int l=0;l<20;l++)
								 if(m2.adjacent[l]==f)m2.adjacent[l]=Integer.parseInt(S);	
						}
					}	}
					catch(MyException e){
						JOptionPane.showMessageDialog(ThePanel.this,"the number you entered already exists \n please try again with another different one"
								,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);

				
				}//catch
					catch ( NumberFormatException e){
						JOptionPane.showMessageDialog(ThePanel.this,"you can ONLY enter \n integers please try again"
								,"Graph Editor Exception",JOptionPane.INFORMATION_MESSAGE);
					}
				}//if meta
			 }//if
		}//for
				 Node m1=new Node();
				 for(int i=0;i<v.size();i++){
					 m1=(Node)v.get(i);
					 if(f==m1.getData())
					m1.setPoint(event.getX(), event.getY());
					dragging=false;
					//v.addElement(node);}
				 }//for
						 repaint();
			 	 
				 dragging=false;	
				 repaint();			
					
	  }//mouse
				
				 
				 /*Node m=new Node();
				 for(int i=0;i<v.size();i++){
					 m=(Node)v.get(i);
					 if(f==m.getData())
					m.setPoint(event.getX(), event.getY());
					dragging=false;
					//v.addElement(node);}
				 }//for
						 repaint();
			 }*/	 
			 
		});
		
		center.addMouseMotionListener(
				new MouseMotionAdapter(){
					 public void mouseDragged(MouseEvent evt) { 
						 Node m=new Node();
						 for(int i=0;i<v.size();i++){
							 m=(Node)v.get(i);
							 if(f==m.getData())
						 m.setPoint(evt.getX(), evt.getY());
						 dragging=true;
						// v.addElement(node);
						 }
						 repaint(); 
					 }	
					
					
					})	;	
			
		}//frame
		
	
		
		
	public static void main(String[] args) {
	ThePanel panel=new ThePanel();
	  panel.pack();
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   int height = screenSize.height;
	   int width = screenSize.width;
	   panel.setSize(width/2, height/2);
	   panel .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   panel.setSize(700,500);
	   panel.setVisible(true);
		
	

	  }//main
	}//class