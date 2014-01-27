package com.guilhermeesteves.gtexperiment.utils;

/**
 * Constants used in the simple interface
 * with JOptionPaneWrapper
 * 
 * @author Guilherme Esteves (@guilhermesteves)
 *
 */
public final class CONSTANTS {

	public static final String LANGUAGE_QUESTION = "To which language do you want to translate? \n"
													+ "Para qual l�ngua voc� deseja traduzir?";
	
	public static final String AUTHOR = "Guilherme Esteves (@guilhermesteves)";
	public static final String DEFAULT_TITLE = "Google Translate Experiment v1";
	
	public static final class PT {		
		public static final String PORTUGUES = "Portugu�s";		
		public static final String O_QUE = "O que voc� quer traduzir? (Em qualquer l�ngua)";
		public static final String MAIS_ALGUMA_COISA = "Deseja traduzir alguma outra coisa?";
		public static final String ERRO_AO_TRADUZIR = "Desculpe, ocorreu um erro ao traduzir. \n";
													
		public static final String SIM = "Sim";
		public static final String NAO = "N�o";
		public static final String DIGITE_AQUI = "Digite aqui";
		
		public static final String AVISO_FINAL = "Este � s� um experimento. \n"
												+ "Por favor, pague para usar a API do Google. \n"
												+ "Eles investiram muito dinheiro e tempo no Google Tradutor.\n\n"
												+ "Obrigado,\n"
												+ AUTHOR;
	}
	
	public static final class EN {		
		public static final String ENGLISH = "English";		
		public static final String WHAT = "What do you want to translate? (In any language)";
		public static final String ANYTHING_ELSE = "Do you want to translate anything else?";		
		public static final String ERROR_TRANSLATING = "Sorry, an error ocurred while translating. \n"
													+ "Please, try again.";
		public static final String YES = "Yes";
		public static final String NO = "No";
		public static final String TYPE_HERE = "Type here";
		
		public static final String FINAL_WARNING = "This is just an experiment. \n"
				+ "Please, pay to use Google's API.  \n"
				+ "They invested a lot of money and time in Google Translator.\n\n"
				+ "Thank you,\n"
				+ AUTHOR;
	}
}
