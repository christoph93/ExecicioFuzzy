package fuzzy;


public class ExercicioDois {
	private double [] ativacao;
	private double [] valoresQuantidadePessoas;
	private double [] valoresTemperatura;
	
//R1: SE a quantidade de pessoas no ambiente é pequena OU
//      a temperatura do ambiente é baixa
//     ENTÃO a refrigeração do ambiente é mínima
//R2: SE a quantidade de pessoas no ambiente é média E
//      a temperatura do ambiente é agradável
//      ENTÃO a refrigeração do ambiente é média
//R3: SE a quantidade de pessoas no ambiente é grande E
//      a temperatura do ambiente é alta
//      ENTÃO a refrigeração do ambiente é alta
	public ExercicioDois(){
		ativacao = new double[3]; //uma para cada regra
		valoresQuantidadePessoas = new double[3]; 
		valoresTemperatura = new double[3];
	}
	
	/**
	 * Etapa 1 - Fuzzyfication
	 * Aplicada apenas as premissas das regras
	 * @param quantidadePessoas
	 * @param temperatura
	 */
	public void fuzzyfication(double pessoas, double temperatura){
		//quantidade de pessoas pequena ?
		valoresQuantidadePessoas[0] = OperacoesFuzzy.funcaoL(pessoas, 0, 20);
		//quantidade de pessoas média?
		valoresQuantidadePessoas[1] = OperacoesFuzzy.funcaoTriangular(pessoas, 15, 30, 45);
		//quantidade de pessoas pequena grande ?
		valoresQuantidadePessoas[2] = OperacoesFuzzy.funcaoGama(pessoas, 40, 60);
		
		//temperatura é baixa?
		valoresTemperatura[0] = OperacoesFuzzy.funcaoL(temperatura, 0, 15);
		//temperatura é agradável?
		valoresTemperatura[1] = OperacoesFuzzy.funcaoTriangular(temperatura, 12, 23, 30);
                //temperatura é alta?
                valoresTemperatura[2] = OperacoesFuzzy.funcaoGama(temperatura, 28, 42);
	}
	
	/**
	 * Etaoa 2 - Inferência
	 * Calcula a força de ativacao das regras
	 * Apenas regras com força maior que 0 são ativadas
	 */
	public void inferencia(){
		//Calculando ativacao das regras
		
		//R1 - SE a quantidade de pessoas no ambiente é pequena OU a temperatura do ambiente é baixa
		ativacao[0] = OperacoesFuzzy.opOR(valoresQuantidadePessoas[0], valoresTemperatura[0]);
		
		//R2 - SE a quantidade de pessoas no ambiente é média E a temperatura do ambiente é agradável
		ativacao[1] = OperacoesFuzzy.opAND(valoresQuantidadePessoas[1] , valoresTemperatura[1]);
		
		//R3:  SE a quantidade de pessoas no ambiente é grande E a temperatura do ambiente é alta 
		ativacao[2] = OperacoesFuzzy.opAND(valoresQuantidadePessoas[2] , valoresTemperatura[2]);	
	}
	
	/**
	 * Etapa 3 - Defuzzyfication
	 * Calcula a refrigeração 
	 * Atua sobre o consequente da regra (parte então)
	 * Método do centróide
	 */
	public double defuzzyfication(){
		int quantidade = 100;
		int []z = new int[quantidade];
		double [] graus = new double[quantidade];
		double valor;
		
		//Valores do conjunto Z
		for(int i=0; i<quantidade; i++) z[i] = i+1;
		
		// R1: .. ENTÃO refrigeração baixa - 
		if(ativacao[0]>0){
			for(int i=0;i<quantidade; i++){
				graus[i] = OperacoesFuzzy.funcaoL(z[i],0,30, ativacao[0]);
			}			
		}
		// R2: ...ENTÃO refrigeração - trapezio(u,25;45;55;75)
		if(ativacao[1]>0){
			for(int i=0;i<quantidade; i++){
				valor = OperacoesFuzzy.funcaoTrapezio(z[i], 20, 45, 55, 75, ativacao[1]);
				if(valor>graus[i]) graus[i] = valor;
			}			
		}
		// R3: ...ENTÃO refrigeração alta -gama (u;60;75)
		if(ativacao[2]>0){
			for(int i=0;i<quantidade; i++){
				valor = OperacoesFuzzy.funcaoGama(z[i], 70 , 100,ativacao[2]);
				if(valor>graus[i]) graus[i] = valor;
			}			
		}
		
	//	for(int i=0;i<quantidade; i++)
	//		System.out.println("z= " + z[i] + " Grauu: " + graus[i]);
		
		double refrigeracao = 0;
		double somaGraus = 0;
		for(int i=0;i<quantidade; i++){
			refrigeracao = refrigeracao + z[i] * graus[i];
			somaGraus = somaGraus + graus[i];
		}		
		return refrigeracao/somaGraus;
	}

	public String toString(){
		String msg = "";
		msg = "Quantidade de pessoas - baixa: " + valoresQuantidadePessoas[0] + "\n";
		msg = msg + "Quantidade de pessoas - média: " + valoresQuantidadePessoas[1] + "\n";
		msg = msg + "Quantidade de pessoas - alta: " + valoresQuantidadePessoas[2] + "\n";
		msg = msg + "Temperatura - baixa: " + valoresTemperatura[0] + "\n";
		msg = msg + "Temperatura - agradável: " + valoresTemperatura[1] + "\n";
                msg = msg + "Temperatura - alta: " + valoresTemperatura[2] + "\n";
		
		if(ativacao[0] > 0) msg = msg + "Ativou a regra R1 com força: " + ativacao[0] + "\n";
		if(ativacao[1] > 0) msg = msg + "Ativou a regra R2 com força: " + ativacao[1] + "\n";
		if(ativacao[2] > 0) msg = msg + "Ativou a regra R3 com força: " + ativacao[2] + "\n";
		return msg;
	}
}
