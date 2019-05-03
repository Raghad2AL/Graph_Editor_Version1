import java.awt.Point;

class Node {

	private Point point;
	private int data ;
	public int adjacent[];
	int count=0;
	public Node(){
		
		point =new Point ((int)(400*Math.random()),(int)(400*Math.random()));
		data =-1;
		adjacent=new int[20];
		for(int i=0;i<20;i++)
			adjacent[i]=-1;
	}
	public void setPoint(int a,int b){
		point.x=a;
		point.y=b;
	
	}
	public Point getPoint(){
		return point;	
	}
	public void setData (int a){
		data=a;
	}
	public int getData(){
		return data;	
	}

}//node