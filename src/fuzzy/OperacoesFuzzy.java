package fuzzy;



//REVER LIMITE !!!!!!
public class OperacoesFuzzy {
	
	/**
	 * Função L : Decrescente - usada em inicio de domínio
	 * @param entrada 
	 * @param alfa
	 * @param beta
	 * @param limite
	 * @return
	 */
	public static double funcaoL(double entrada, double alfa, double beta, double limite){
		double resultado = funcaoL(entrada,alfa,beta);
		if(resultado>limite) resultado=limite;
		return resultado;
	}
	
	public static double funcaoL(double entrada, double alfa, double beta){
		if(entrada<=alfa) return 1;
		if(entrada<beta) return (beta-entrada)/(beta-alfa);
		return 0;
	}
	
	/**
	 * Função Triangular - intermediária
	 * @param entrada
	 * @param alfa
	 * @param beta
	 * @param gama
	 * @param limite
	 * @return
	 */
	public static double funcaoTriangular(double entrada, double alfa, double beta, double gama,double limite){
		double resultado = funcaoTriangular(entrada,alfa,beta,gama);
		if(resultado>limite) resultado = limite;
		return resultado;
	}
	public static double funcaoTriangular(double entrada, double alfa, double beta, double gama){
		if(entrada<=alfa) return 0;
		if(entrada<beta)  return (entrada-alfa)/(beta-alfa);
		if(entrada==beta) return 1;
		if(entrada<gama)  return (gama-entrada)/(gama-beta);
		return 0;
	}
	
	/**
	 * Funcão Gama - crescente, usada no final do domínio
	 * @param entrada
	 * @param alfa
	 * @param beta
	 * @param limite
	 * @return
	 */
	public static double funcaoGama(double entrada, double alfa, double beta, double limite){
		double resultado = funcaoGama(entrada,alfa,beta);
		if(resultado>limite) resultado = limite;
		return resultado;
	}
	public static double funcaoGama(double entrada, double alfa, double beta){
		if(entrada<=alfa) return 0;
		if(entrada<beta) return (entrada-alfa)/(beta-alfa);
		return 1;
	}
	
	/**
	 * Função Trapézio - intermediaria
	 * 
	 */
	public static double funcaoTrapezio(double entrada, double alfa, double beta, 
			                            double gama, double delta, double limite){
		double resultado = funcaoTrapezio(entrada,alfa,beta,gama,delta);
		if(resultado>limite) resultado=limite;
		return resultado;
	}
	public static double funcaoTrapezio(double entrada, double alfa, double beta, 
            double gama, double delta){
		if(entrada<=alfa) return 0;
		if(entrada<beta) return (entrada-alfa)/(beta-alfa);
		if(entrada<=gama) return 1;
		if(entrada<delta) return (delta-entrada)/(delta-gama);
		return 0;
	}
	
	public static double opAND(double valorDifuso1, double valorDifuso2){
		double menor = valorDifuso1;
		if(valorDifuso2<menor) menor = valorDifuso2;
		return menor;
	}
	
	public static double opOR(double valorDifuso1, double valorDifuso2){
		double maior = valorDifuso1;
		if(valorDifuso2>maior) maior = valorDifuso2;
		return maior;
	}
	public static double opNOT(double valorDifuso){
		return 1 - valorDifuso;
	}
}
