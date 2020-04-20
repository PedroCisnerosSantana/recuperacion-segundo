package recuperacionSegundo;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex04pcs2 {
	
	static ArrayList<Integer> mezclaListas(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		if (lista1.size() > lista2.size()) {
			for (int i = 0; i < lista2.size(); i++) {
				numbers.add(lista1.get(i));
				numbers.add(lista2.get(i));
			}
			if (lista2.size() != 0) {
				for (int i = (lista1.size()-lista2.size()); i < lista1.size(); i++) {
					numbers.add(lista1.get(i));
				}	
			} else {
				for (int i = 0; i < lista1.size(); i++) {
					numbers.add(lista1.get(i));
				}	
			}
		} else {
			for (int i = 0; i < lista1.size(); i++) {
				numbers.add(lista1.get(i));
				numbers.add(lista2.get(i));
			}
			if (lista1.size() != 0) {
				for (int i = (lista2.size() - lista1.size()); i < lista2.size(); i++) {
					numbers.add(lista2.get(i));
				}	
			} else {
				for (int i = 0; i < lista2.size(); i++) {
					numbers.add(lista2.get(i));
				}	
			}
		}
		
		return(numbers);
		
	}

	public static void main(String[] args) {
		ArrayList<Integer> lista1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		ArrayList<Integer> lista2 = new ArrayList<>(Arrays.asList(77, 88, 99));
		ArrayList<Integer> lista3 = new ArrayList<>();
		System.out.println(mezclaListas(lista1, lista2));
		System.out.println(mezclaListas(lista2, lista1));
		System.out.println(mezclaListas(lista1, lista3));
		System.out.println(mezclaListas(lista2, lista3));

	}

}
