/* A3Algorithms.java
   CSC 225 - Summer 2019


   Chenghao Liu V00841275 
   (Add your name/studentID/date here)

*/ 

import java.awt.Color;
import java.util.*;

public class A3Algorithms{

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a 
	   pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
		/* Your code here */
		Stack<PixelVertex> stack = new Stack<PixelVertex>();
		stack.push(v);
		LinkedList<PixelVertex> result = new LinkedList<PixelVertex>();
		while(!stack.isEmpty()){
			PixelVertex temp = stack.pop();
			result.add(temp);
			temp.flag = true;

			if(temp.up!=null && temp.vertex_color.getRGB() == temp.up.vertex_color.getRGB() ){
				if(temp.up.flag==false)
					stack.push(temp.up);
			}

			if(temp.down!=null && temp.vertex_color.getRGB() == temp.down.vertex_color.getRGB() ){
				if(temp.down.flag==false)
				stack.push(temp.down);
			}

			if(temp.left!=null && temp.vertex_color.getRGB() == temp.left.vertex_color.getRGB() && temp.left.flag==false){
				stack.push(temp.left);
			}

			if(temp.right!=null && temp.vertex_color.getRGB() == temp.right.vertex_color.getRGB() && temp.right.flag==false){
				stack.push(temp.right);
			}

		}

			for(int i=0;i<result.size();i++){
				PixelVertex curr = result.get(i);
				writer.setPixel( curr.getX(), curr.getY(), fillColour);
				curr.flag = false;
			}

	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour)
	{
		/* Your code here */
		Queue<PixelVertex>  queue = new  LinkedList<PixelVertex>();
		queue.add(v);
		v.flag = true;
		LinkedList<PixelVertex>  resultQueue = new  LinkedList<PixelVertex>();
		// System.out.println("asc:");

		while(!queue.isEmpty())
		{
			// System.out.println("asc:");
			PixelVertex temp = queue.poll();
			temp.flag = true;
			resultQueue.add(temp);
			
			if(temp.up!=null && temp.vertex_color.getRGB() == temp.up.vertex_color.getRGB() && temp.up.flag==false ){
			
					queue.add(temp.up);
					temp.up.flag=true;

			}

			if(temp.down!=null && temp.vertex_color.getRGB() == temp.down.vertex_color.getRGB()&& temp.down.flag==false ){
				
				queue.add(temp.down);
				temp.down.flag=true;
			}

			if(temp.left!=null && temp.vertex_color.getRGB() == temp.left.vertex_color.getRGB() && temp.left.flag==false){
				queue.add(temp.left);
				temp.left.flag = true;
			}

			if(temp.right!=null && temp.vertex_color.getRGB() == temp.right.vertex_color.getRGB() && temp.right.flag==false){
				queue.add(temp.right);
				temp.right.flag = true;
			}

		}
			for(int i=0;i<resultQueue.size();i++){
				PixelVertex curr = resultQueue.get(i);
				writer.setPixel( curr.getX(), curr.getY(), fillColour);
				curr.flag = false;
			}
	}
	
	/* OutlineRegionDFS(v, writer, outlineColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void OutlineRegionDFS(PixelVertex v, PixelWriter writer, Color outlineColour){
		/* Your code here */

		/* Your code here */
		Stack<PixelVertex> stack = new Stack<PixelVertex>();
		stack.push(v);
		LinkedList<PixelVertex> result = new LinkedList<PixelVertex>();
		while(!stack.isEmpty()){
			PixelVertex temp = stack.pop();
			result.add(temp);
			temp.flag = true;

			if(temp.up!=null && temp.vertex_color.getRGB() == temp.up.vertex_color.getRGB() ){
				if(temp.up.flag==false)
					stack.push(temp.up);
			}

			if(temp.down!=null && temp.vertex_color.getRGB() == temp.down.vertex_color.getRGB() ){
				if(temp.down.flag==false)
				stack.push(temp.down);
			}

			if(temp.left!=null && temp.vertex_color.getRGB() == temp.left.vertex_color.getRGB() && temp.left.flag==false){
				stack.push(temp.left);
			}

			if(temp.right!=null && temp.vertex_color.getRGB() == temp.right.vertex_color.getRGB() && temp.right.flag==false){
				stack.push(temp.right);
			}

		}

			for(int i=0;i<result.size();i++){
				PixelVertex curr = result.get(i);
				if(curr.getDegree()<4)
				{
				writer.setPixel( curr.getX(), curr.getY(), outlineColour);
				}
				curr.flag = false;
			}

		

	}
	
	/* OutlineRegionBFS(v, writer, outlineColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void OutlineRegionBFS(PixelVertex v, PixelWriter writer, Color outlineColour)
	{
		/* Your code here */
		Queue<PixelVertex>  queue = new  LinkedList<PixelVertex>();
		queue.add(v);
		v.flag = true;
		LinkedList<PixelVertex>  resultQueue = new  LinkedList<PixelVertex>();
	
		while(!queue.isEmpty())
		{
			PixelVertex temp = queue.poll();
			temp.flag = true;
			resultQueue.add(temp);
			
			if(temp.up!=null && temp.vertex_color.getRGB() == temp.up.vertex_color.getRGB() && temp.up.flag==false ){
			
					queue.add(temp.up);
					temp.up.flag=true;

			}

			if(temp.down!=null && temp.vertex_color.getRGB() == temp.down.vertex_color.getRGB()&& temp.down.flag==false ){
				
				queue.add(temp.down);
				temp.down.flag=true;
			}

			if(temp.left!=null && temp.vertex_color.getRGB() == temp.left.vertex_color.getRGB() && temp.left.flag==false){
				queue.add(temp.left);
				temp.left.flag = true;
			}

			if(temp.right!=null && temp.vertex_color.getRGB() == temp.right.vertex_color.getRGB() && temp.right.flag==false){
				queue.add(temp.right);
				temp.right.flag = true;
			}

		}
			for(int i=0;i<resultQueue.size();i++){
				PixelVertex curr = resultQueue.get(i);
				if(curr.getDegree()<4)
					writer.setPixel( curr.getX(), curr.getY(), outlineColour);
				curr.flag = false;
			}
	}


public static void DFS(PixelVertex v){
	v.flag = true;
	PixelVertex[] neighs = v.getNeighbours();
	if(neighs !=null){
		for(int i=0;i<neighs.length;i++){
			if(neighs[i].flag ==false && neighs[i]!=null ){
				DFS(neighs[i]);
			}
		}
	}
}

	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	*/
	public static int CountComponents(PixelGraph G){
		/* Your code here */
		for(int i=0;i<G.getHeight();i++){
			for(int j=0;j<G.getWidth();j++){
				G.arr[i][j].flag = false;
			}
		}


		int count =0;
		for(int i=0;i<G.getHeight();i++){
			for(int j=0;j<G.getWidth();j++){
				PixelVertex t = G.getPixelVertex(i,j);
				if(t.flag == false && t!=null){
					DFS(t);
					count++;
				}
			}
		}


		return count;
	}
}