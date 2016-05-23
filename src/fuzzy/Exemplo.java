package fuzzy;

import java.util.Scanner;
public class Exemplo {
	
	public static void main(String args[]){		
		System.out.println("\fInforme a quantidade de pessoas: ");
		double pessoas = getPercentual();
		System.out.println("Informe a temperatura: ");
		double temperatura = getPercentual();
		
		ExercicioDois exemplo = new ExercicioDois();
		exemplo.fuzzyfication(pessoas, temperatura);
		exemplo.inferencia();
	    double refr = exemplo.defuzzyfication();
		System.out.println(exemplo);
		System.out.println("Refrigeração = " + refr);
	}
	
	private static double getPercentual(){
		Scanner in = new Scanner (System.in);
		double valor = 0;
		do{
			System.out.print("Informe um valor entre 0 e 60 para pessoas, entre 0 e 42 para temperatura: ");
			valor = in.nextDouble();
		}while(valor<0 || valor>100);
		return valor;
	}

}
