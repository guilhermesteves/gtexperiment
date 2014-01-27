package com.guilhermeesteves.gtexperiment;

import com.guilhermeesteves.gtexperiment.utils.JOptionPaneWrapper;

/**
 * Simple interface to make tests.
 * 
 * Make sure the person will
 * receive the message about Google's 
 * paid API in the end. ;)
 * 
 * @author Guilherme Esteves (@guilhermesteves)
 *
 */
public class Start {
	
	public static void main(String[] args) {
		
		JOptionPaneWrapper window = new JOptionPaneWrapper();
		boolean english;
		
		int languageOption = window.languageQuestion();
		
		if (languageOption != 0 && languageOption != 1) {
			end(window);
			
		} else {
		
			if (languageOption == 0) {
				english = true;
			} else {
				english = false;
			}
			window.setEnglish(english);
			
			String textToTranslate = window.getTextToTranslate();
			String translated = translate(english, textToTranslate);
			
			
			if (!("").equals(translated)) {
				window.messageInfo(textToTranslate);
			} else {
				window.messageError();
			}
			
			
			int continueTranslating = window.anythingElse();
			
			if (continueTranslating == 0) {
				
				do {
					
					textToTranslate = window.getTextToTranslate();
					translated = translate(english, textToTranslate);
					
					if (!("").equals(translated)) {
						window.messageInfo(textToTranslate);
					} else {
						window.messageError();
					}
					
					continueTranslating = -1;
					continueTranslating = window.anythingElse();
					
				} while (continueTranslating == 0);
				
				end(window);
			
			} else {
				end(window);
			}
		}
	}
	
	private static String translate(boolean english, String text) {
		return (english) ? HtmlUnitWrapper.translateToEnglish(text) : HtmlUnitWrapper.translateToPortuguese(text);
	}
	
	private static void end(JOptionPaneWrapper jopw) {
    	
		jopw.messageWarn();
		System.exit(0);
    }
}
