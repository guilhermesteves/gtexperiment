package com.guilhermeesteves.gtexperiment.utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * Simple wrapper to JOptionPane to 
 * make easier display messages in
 * the application.
 * 
 * @author Guilherme Esteves (@guilhermesteves)
 *
 */
public class JOptionPaneWrapper extends JOptionPane {
	
	private static final long serialVersionUID = 3384713831523762603L;
	
	private final String DEFAULT_TITLE = CONSTANTS.DEFAULT_TITLE;
	private boolean isEnglish;
	private final JFrame window;
	
	public JOptionPaneWrapper() {
		this.window = new JFrame();
	}
	
	public int languageQuestion() {
		return twoOptionsQuestion(
				CONSTANTS.LANGUAGE_QUESTION, 
				CONSTANTS.EN.ENGLISH, 
				CONSTANTS.PT.PORTUGUES);
	}
	
	public int anythingElse() {
		
		String ANYTHING_ELSE = (this.isEnglish) ? CONSTANTS.EN.ANYTHING_ELSE : CONSTANTS.PT.MAIS_ALGUMA_COISA;
		String OPT_1 = (this.isEnglish) ? CONSTANTS.EN.YES : CONSTANTS.PT.SIM;
		String OPT_2 = (this.isEnglish) ? CONSTANTS.EN.NO : CONSTANTS.PT.NAO;
		
		return twoOptionsQuestion(ANYTHING_ELSE, OPT_1, OPT_2);
	}
	
	private int twoOptionsQuestion(Object message, String OPT_1, String OPT_2) {	
		
		Object[] options = {OPT_1, OPT_2};
		int choice = JOptionPane.showOptionDialog(  window,
													message,
													DEFAULT_TITLE,
													JOptionPane.YES_NO_CANCEL_OPTION,
													JOptionPane.QUESTION_MESSAGE,
													null,
													options,
													options[1]);
		return choice;
	}
	
	public String getTextToTranslate() {
		
		String TYPE_HERE = (this.isEnglish) ? CONSTANTS.EN.TYPE_HERE : CONSTANTS.PT.DIGITE_AQUI;
		String MESSAGE = (this.isEnglish) ? CONSTANTS.EN.WHAT : CONSTANTS.PT.O_QUE;
		String text;
		
		do {
			text = null;
			text = (String)JOptionPane.showInputDialog( window,
														MESSAGE,
														DEFAULT_TITLE,
														JOptionPane.PLAIN_MESSAGE,
														icon,
														null,
														TYPE_HERE);
			
		} while (!text.equals("") && (text == null) && (text.length() > 0));
		
		return text.trim();
	}
	
	public void messageInfo(String message) {
		JOptionPane.showMessageDialog(  window,
										message, 
										DEFAULT_TITLE,
										JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void messageError() {
		
		String ERROR_TRANSLATING = (this.isEnglish) ? CONSTANTS.EN.ERROR_TRANSLATING : CONSTANTS.PT.ERRO_AO_TRADUZIR;
		JOptionPane.showMessageDialog(  window,
										ERROR_TRANSLATING,
										DEFAULT_TITLE,
										JOptionPane.ERROR_MESSAGE);
	}
	
	public void messageWarn() {
		
		String WARNING = (this.isEnglish) ? CONSTANTS.EN.FINAL_WARNING : CONSTANTS.PT.AVISO_FINAL;
		JOptionPane.showMessageDialog(  window,
										WARNING,
										DEFAULT_TITLE,
										JOptionPane.WARNING_MESSAGE);
	}

	public void setEnglish(boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
	
}
