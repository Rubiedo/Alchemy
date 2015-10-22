//Stephen Wu CMSC 401 - HW 4

import java.util.*;

public class cmsc401{

	public static int dijkstrasAlgorithm(ArrayList<Node> node, Hashtable<Integer, Integer> Hashtable, int begin, int stop){
		
		int minW;
		
		int minWLoc = 1000;
		
		int place;
		
		int x = Integer.MAX_VALUE;
		
		int[] length = new int[node.size()];	
		
		for(int v = 0; v < node.size(); v++) {
			
			length[v] = x;
			
		}
		
		length[Hashtable.get(begin)] = 0;
		
		Node y;
		
		ArrayList<Node> adjacent;
		
		ArrayList<Integer> metalX;
		
		for(int i = 0; i < node.size(); i++){
			
			y = node.get(Hashtable.get(begin));
			
			y.setChecked();
			
			adjacent = y.getadjacent();
			
			metalX = y.getMetalY();
			
			minW = x;
			
			for(int w = 0; w < metalX.size(); w++){
				
				place = Hashtable.get(adjacent.get(w).getElement());
				
				if(length[place] == x){
					
					length[place] = length[Hashtable.get(begin)] + metalX.get(w);
					
				}else if(length[place] > length[Hashtable.get(begin)] + metalX.get(w)){
					
					length[place] = length[Hashtable.get(begin)] + metalX.get(w);
					
				}	
				
			}
			
			
			for(int k = 0; k < length.length; k++){
				
				if((length[k] < minW)&&(!node.get(k).checked)){
					
					minW = length[k];
					
					minWLoc = k;
					
				}
				
			}
			
			begin = node.get(minWLoc).getElement();
			
			if(begin == stop){
				
				return length[Hashtable.get(stop)];
			}
			
		}
		
		return length[Hashtable.get(stop)];
		
	}
	
public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
		
		int size = scan.nextInt();
		
		int begin; 
		
		int stop;
		
		int costXY;

		int place = 0;
		
		ArrayList<Node> node = new ArrayList<Node>();
		
		Hashtable<Integer, Integer> Hashtable = new Hashtable<Integer, Integer>();
		
		for(int z = 0; z < size; z++){
			
			begin = scan.nextInt();
			
			stop = scan.nextInt();
			
			costXY = scan.nextInt();
			
			if(Hashtable.get(stop) == null){
				
				node.add(new Node(stop));
				
				Hashtable.put(stop, place);
				
				place++;
			}
			
			if(Hashtable.get(begin) == null){
				
				node.add(new Node(begin, stop, costXY));
				
				Hashtable.put(begin, place);
				
				node.get(place).addNeighbor(node.get(Hashtable.get(stop)), costXY);
				
				place++;
				
			}else{
				
				node.get(Hashtable.get(begin)).addPointer(stop, costXY);
				
				node.get(Hashtable.get(begin)).addNeighbor(node.get(Hashtable.get(stop)), costXY);
				
			}	
			
		}	
		
		System.out.println(dijkstrasAlgorithm(node, Hashtable, 47, 79));
	}
	
	
}

class Node{
	
	boolean checked = false;
	
	private int element;	
	
	private ArrayList<Node> adjacent = new ArrayList<Node>();
	
	private ArrayList<Integer> metalX = new ArrayList<Integer>();
	
	private ArrayList<Integer> metalY = new ArrayList<Integer>();
		
	private ArrayList<Integer> intPointers = new ArrayList<Integer>();
	
	public Node(int element, int pointer, int costXY){
		
		this.element = element;
		
		intPointers.add(pointer);
		
		metalX.add(costXY);
		
	}
	
	public int getElement(){
		
		return element;
		
	}
	
	public void setElement(int element){
		
		this.element = element; 
		
	}
	
	public ArrayList<Integer> getMetalX(){
		
		return metalX;
		
	}
	
	public ArrayList<Integer> getMetalY(){
		
		return metalX;
		
	}
	
	public void setChecked(){
		
		checked = true;
		
	}
	
	public Node(int element){
		
		this.element = element;
		
	}
	
	public void addNeighbor(Node node, int costXY){
		
		adjacent.add(node);
		
		metalY.add(costXY);
	}
	
	public ArrayList<Node> getadjacent(){
		
		return adjacent;
	}
	
	public void addPointer(int node, int costXY){
		
		intPointers.add(node);
		
		metalX.add(costXY);
		
	}

}