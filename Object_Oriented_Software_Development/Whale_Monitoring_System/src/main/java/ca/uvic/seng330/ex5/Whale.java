package ca.uvic.seng330.ex5;


public class Whale {

	public enum Gender { MALE, FEMALE, UNKNOWN};
	public enum Breed {BLUE,KILLER,SPERM,BELUGA,HUMPBACK,NARWHAL,BOWHEAD,GRAY,FIN,SEI};

	private final int id;
	private Gender gender;
    private final Breed breed;
	private Boolean alive;
	private int weight;
	private int length;

	public Whale(int id,Gender gender,Breed breed,Boolean alive,int weight,int length){
		if(gender == null || breed == null || alive == null) throw new NullPointerException();
		if(weight < 0 || length < 0) throw new NumberFormatException();
		this.id=id;
		this.gender=gender;
		this.breed=breed;
		this.alive=alive;
		this.weight=weight;
		this.length=length;
	}

	public Whale(Whale whale){
		this.id=whale.getId();
		this.gender=whale.getGender();
		this.breed=whale.getBreed();
		this.alive=whale.getAlive();
		this.weight=whale.getWeight();
		this.length=whale.getLength();
	}

	public int getId(){
		return this.id;
	}

	public Gender getGender(){
		return this.gender;
	}

	public void setGender(Gender gender){
		if(gender == null) throw new NullPointerException();
		this.gender=gender;
	}

	public Breed getBreed(){
		return this.breed;
	}
   
	public void setAlive(Boolean life){
		if(life == null) throw new NullPointerException();
		this.alive=life;
	}

	public Boolean getAlive(){
		return this.alive;
	}

	public void setWeight(int weight){
		this.weight=weight;
	}

	public int getWeight(){
		return this.weight;
	}

	public void setLength(int length){
		this.length=length;
	}

	public int getLength(){
		return this.length;
	}

}
