package ca.uvic.seng330.ex4;

import java.util.*;

public class CompareByLength implements Comparator<Whale>{

	public int compare(Whale whale1, Whale whale2){
		return whale1.getLength()-whale2.getLength();
	}
}