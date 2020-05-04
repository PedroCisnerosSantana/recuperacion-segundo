package recuperacionSegundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ex04pcs1 {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		HashMap<String, String> translate = new HashMap<String, String>();
		HashMap<String, String> repasa = new HashMap<String, String>();
		
		translate.put("house", "casa");
		translate.put("raspberry", "frambuesa");
		translate.put("apple", "manzana");
		translate.put("green", "verde");
		translate.put("water", "agua");
		translate.put("hello", "hola");
		translate.put("car", "coche");
		translate.put("laptop", "portatil");
		
		List<String> values = new ArrayList<String>(translate.keySet());
		List<String> keys = new ArrayList<String>(translate.values());
		
		int acertadas = 0;
		int flag = 0;
		
		while (flag < 5) {
			int randomIndex = new Random().nextInt(values.size());
			String randomEng = values.get(randomIndex);
			
			int randomKey = new Random().nextInt(values.size());
			String randomEs = keys.get(randomKey);
			
			System.out.print("¿" + randomEng + " es " + randomEs+ "? Conteste (s/n): ");
			char resp = s.next().toLowerCase().charAt(0);
			
			if (translate.get(randomEng).equals(randomEs)) {
				if (resp == 's') {
					acertadas++;	
				} else {
					repasa.put(randomEng, randomEs);	
				}
			} else {
				if (resp == 'n') {
					acertadas++;	
				} else {
					repasa.put(randomEng, translate.get(randomEng));	
				}
			}
			flag++;
		}
		
		System.out.print("Puntos: " + acertadas + "\nDebe repasar las siguientes palabras:\n");
		for (String i : repasa.keySet()) {
			System.out.println(i + " : " +repasa.get(i));
		}
		s.close();
	}

}