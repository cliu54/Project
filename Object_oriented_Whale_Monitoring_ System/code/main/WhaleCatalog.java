package ca.uvic.seng330.ex5;

import java.util.*;
import java.lang.Iterable;

public class WhaleCatalog implements Iterable<Whale>{

	private ArrayList<Whale> whales;
	
	public WhaleCatalog(){

		this.whales= new ArrayList<Whale>();
	}

	public WhaleCatalog(WhaleCatalog whaleCatalog){

		this.whales= whaleCatalog.getWhaleCatalog();
	}

	private ArrayList<Whale> getWhaleCatalog(){
		return this.whales;
	}

		
	/**
	This method is used to add a whale to the whales list
	 @return Whale This returns a new whale object
     * @param whaleGender  Whale's gender as an enum
     * @param whaleBreed  Whales's breed as an enum
     * @param isAlive  Boolean for is whale alive
     * @param weight  The weight of the whale in kg
     * @param length  The lenght of the whale in metres
     */
	public Whale addWhale(Whale.Gender whaleGender, Whale.Breed whaleBreed, Boolean isAlive, int weight, int length){
		
		Whale newWhale = createWhale(getId(), whaleGender, whaleBreed, isAlive,weight, length);
		this.whales.add(newWhale);

		return newWhale;
	}

	/**
	This method is used to get a whale with its id if needed 
	@return int Assign whale with a new id 
	*/
	private int getId(){

		return this.whales.size();
	}

	/**
	This method is used to create a whale object
	@param id Whale's id 
	@param whaleGender  Whale's gender as an enum
	@param whaleBreed  Whales breed as an enum
	@param isAlive  Boolean for is whale alive
	@param weight  The weight of the whale in kg	  
	@param length  The length of the whale in metres
	@return Whale This returns a new whale object
	*/
	private Whale createWhale(int id, Whale.Gender whaleGender, Whale.Breed whaleBreed,Boolean isAlive,int weight,int length){
		return new Whale(id, whaleGender, whaleBreed,isAlive,weight,length);
	}

	/**
	This method is used to get a whale with its id if needed
	@param id Requested whale's id 
	@return Whale The requested whale
	 */
	public Whale getWhale(int id){
		
		return this.whales.get(id);
	}

	public void sortByLength(){
		if(this.whales.size() > 1)
		Collections.sort(this.whales, new CompareByLength());
	}

	public void sortByWeight(){
		if(this.whales.size() > 1)
			Collections.sort(this.whales, new CompareByWeight());
	}
	
	public Iterator<Whale> iterator(){
		return this.whales.iterator();
	}

	public int size(){

		return this.whales.size();
	}
}


	
