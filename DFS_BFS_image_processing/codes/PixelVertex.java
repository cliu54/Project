/* PixelVertex.java
   CSC 225 - Summer 2019


   B. Bird - 04/28/2019
   (Add your name/studentID/date here)
*/

import java.awt.Color;

public class PixelVertex{
	int x;
	int y;
	PixelVertex up;
	PixelVertex down;
	PixelVertex left;
	PixelVertex right;
	Color vertex_color;
	boolean flag;

	/* Add a constructor here (if necessary) */
	public PixelVertex(int passed_x, int passed_y,Color passed_color)
	{
		this.x = passed_x;
		this.y = passed_y;
		this.vertex_color = passed_color;
		this.flag = false;
		this.up = null;
		this.left = null;
		this.right = null;
		this.down = null;
	}
	
	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){
		return this.x;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		return this.y;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	   The size of the array must be equal to the degree of this vertex (and
	   the array may therefore contain no duplicates).
	*/
	public PixelVertex[] getNeighbours()
	{
		int length = this.getDegree();
		if(length == 0)
		{
			return null;
		}
		else
		{
			PixelVertex[] arr = new PixelVertex[length];
			int counter = 0;

			if(this.up != null && this.vertex_color.getRGB() == this.up.vertex_color.getRGB())
			{
				arr[counter] = this.up;
				counter++;
			}

			if(this.down != null && this.vertex_color.getRGB() == this.down.vertex_color.getRGB())
			{
				arr[counter] = this.down;
				counter++;
			}
			if(this.left != null && this.vertex_color.getRGB() == this.left.vertex_color.getRGB())
			{
				arr[counter] = this.left;
				counter++;
			}
			if(this.right != null && this.vertex_color.getRGB() == this.right.vertex_color.getRGB())
			{
				arr[counter] = this.right;
				counter++;
			}
			return arr;
		}
		
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
	public void addNeighbour(PixelVertex newNeighbour)
	{	
		//up
		if(newNeighbour.x - this.x == 0 && newNeighbour.y-this.y == 1)	
			this.right =newNeighbour;

		if(newNeighbour.x - this.x == 0 &&  newNeighbour.y-this.y == -1)
			this.left = newNeighbour;
			//left
		if(newNeighbour.x - this.x == 1 && newNeighbour.y-this.y ==0 )
			this.down = newNeighbour;
		if(newNeighbour.x - this.x == -1 && newNeighbour.y-this.y == 0)
			this.up = newNeighbour;
	}
	
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	public void removeNeighbour(PixelVertex neighbour)
	{
		if(neighbour.x - this.x == 0 && neighbour.y-this.y == 1)	
			this.right =null;

		if(neighbour.x - this.x == 0 &&  neighbour.y-this.y == -1)
			this.left = null;
			//left
		if(neighbour.x - this.x == 1 && neighbour.y-this.y ==0 )
			this.down = null;
		if(neighbour.x - this.x == -1 && neighbour.y-this.y == 0)
			this.up = null;	
	}
	
	/* getDegree()
	   Return the degree of this vertex. Since the graph is simple, 
	   the degree is equal to the number of distinct neighbours of this vertex.
	*/
	public int getDegree()
	{
		int counter = 0;
		if(this.up != null && this.vertex_color.getRGB() == this.up.vertex_color.getRGB())
			counter++;
		if(this.down != null && this.vertex_color.getRGB() == this.down.vertex_color.getRGB())
			counter++;

		if(this.left != null && this.vertex_color.getRGB() == this.left.vertex_color.getRGB())
			counter++;

		if(this.right != null && this.vertex_color.getRGB() == this.right.vertex_color.getRGB())
			counter++;

		return counter;
	}
	
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex)
	{	
		
		if(otherVertex.x - this.x == 0 && otherVertex.y-this.y == 1 && otherVertex.vertex_color.getRGB() == this.right.vertex_color.getRGB())	
			return true;

		if(otherVertex.x - this.x == 0 &&  otherVertex.y-this.y == -1 && otherVertex.vertex_color.getRGB() == this.left.vertex_color.getRGB())
			return true;
			//left
		if(otherVertex.x - this.x == 1 && otherVertex.y-this.y ==0 && otherVertex.vertex_color.getRGB() == this.down.vertex_color.getRGB())
			return true;
		if(otherVertex.x - this.x == -1 && otherVertex.y-this.y == 0 && otherVertex.vertex_color.getRGB() == this.up.vertex_color.getRGB())
			return true;	
		
		return false;
	}
	
}