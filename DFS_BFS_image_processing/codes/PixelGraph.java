/* PixelGraph.java
   CSC 225 - Summer 2019

   Chenghao Liu V00841275 
   (Add your name/studentID/date here)
*/ 

import java.awt.Color;

public class PixelGraph
{
	PixelVertex[][] arr ;
	/* PixelGraph constructor
	   Given a 2d array of colour values (where element [x][y] is the colour 
	   of the pixel at position (x,y) in the image), initialize the data
	   structure to contain the pixel graph of the image. 
	*/
	public PixelGraph(Color[][] imagePixels)
	{
		arr = new PixelVertex[imagePixels.length][imagePixels[0].length];

		for(int i =0; i<imagePixels.length; i ++)
		{
			for(int j = 0; j < imagePixels[i].length;j++)
			{
				arr[i][j] = new PixelVertex(i,j,imagePixels[i][j]);
				
			}
		}

		int width = imagePixels[0].length;
		int height = imagePixels.length;
		for(int x =0; x<imagePixels.length; x++)
		{
			for(int y = 0; y < imagePixels[x].length;y++)
			{
				// four edges
			

				if(x==0 && y== 0)
				{
					arr[x][y].right = arr[x][y+1];
					arr[x][y].down = arr[x+1][y];
				}
				else if(x == 0 && y == width - 1)
				{
					arr[x][y].left = arr[x][y-1];
					arr[x][y].down = arr[x+1][y];
				}

				else if(x == height -1 && y == 0)
				{
					arr[x][y].up = arr[x-1][y];
					arr[x][y].right = arr[x][y+1];
				}
				
				else if(x == height -1 && y == width -1)
				{
					arr[x][y].up = arr[x-1][y];
					arr[x][y].left = arr[x][y-1];
				}
				
				// between the edges
				else if(x>0 && x < height - 1 && y == 0)
				{
					arr[x][y].up = arr[x-1][y];
					arr[x][y].down =arr[x+1][y];
					arr[x][y].right=arr[x][y+1];
				}

				else if (x >0&& x < height - 1 && y == width-1)
				{
					arr[x][y].up = arr[x-1][y];
					arr[x][y].down =arr[x+1][y];
					arr[x][y].left=arr[x][y-1];
				}

				else if (x == 0 && y > 0 && x < width -1)
				{
					arr[x][y].left = arr[x][y-1];
					arr[x][y].right =arr[x][y+1];
					arr[x][y].down=arr[x+1][y];
				}

				else if (x == height-1 && y>0  &&  y< width-1)
				{
					arr[x][y].left = arr[x][y-1];
					arr[x][y].right =arr[x][y+1];
					arr[x][y].up=arr[x-1][y];
				}

				//inside middle
				else
				{
					arr[x][y].left = arr[x][y-1];
					arr[x][y].right =arr[x][y+1];
					arr[x][y].up=arr[x-1][y];
					arr[x][y].down=arr[x+1][y];
				}
			}
		}




		
	}
	
	/* getPixelVertex(x,y)
	   Given an (x,y) coordinate pair, return the PixelVertex object 
	   corresponding to the pixel at the provided coordinates.
	   This method is not required to perform any error checking (and you may
	   assume that the provided (x,y) pair is always a valid point in the 
	   image).
	*/
	public PixelVertex getPixelVertex(int x, int y){
		return arr[x][y];
	}
	
	/* getWidth()
	   Return the width of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getWidth(){
		return arr[0].length;
	}
	
	/* getHeight()
	   Return the height of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getHeight(){
		return arr.length;
	}
	
}