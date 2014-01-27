package com.guilhermeesteves.gtexperiment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * 
 * Class to use only necessary from HtmlUnit.
 * Only WebClient, JavaScriptPage and Json 
 * classes are being used. 
 * 
 * @author Guilherme Esteves (@guilhermesteves)
 *
 */
public class HtmlUnitWrapper {
	
	public static String translateToPortuguese(String text) {
		return translateFromGoogleTranslate(prepareTextToUrl(text), "auto", "pt");
	}
	
	public static String translateToEnglish(String text) {
		return translateFromGoogleTranslate(prepareTextToUrl(text), "auto", "en");
	}
	
	private static String translateFromGoogleTranslate(String input, String inputLanguageCode, String outputLanguageCode)  {
	    JavaScriptPage page;
	    String translatedText = "";
	    
		try {
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
			
			String ajaxUrl = "http://translate.google.com/translate_a/t?"
					+ "client=t&sl=auto&tl=pt&hl=auto&sc=2&ie=UTF-8&oe=UTF-8&uptl=pt&oc=1&otf=2&ssel=0&tsel=0&q="
					+input;
			
			page = (JavaScriptPage) webClient.getPage(ajaxUrl);
			String pageAsText = page.getContent();
			
			translatedText = parseJsonPageResponse(pageAsText);
		
		} catch (FailingHttpStatusCodeException e) {
			System.err.println("ERROR (FailingHttpStatusCodeException): "+e.getMessage());
		} catch (IOException e) {
			System.err.println("ERROR (IOException in Page): "+e.getMessage());
		} 
		
	    return translatedText;
	}
	
	private static String parseJsonPageResponse(String pageResponse) {
		String result = "";
		
		
		try { 
			JsonFactory factory = new JsonFactory();
			JsonParser parser  = factory.createJsonParser(pageResponse);
			parser.nextToken();
			parser.nextToken();
			int openBrackets = 1;
			int closedBrackets = 0;
			List<List<String>> mainList = new ArrayList<List<String>>();
			List<String> innerList = null;
			
			while(openBrackets != closedBrackets){
			    JsonToken token = parser.nextToken();
			    if(token == JsonToken.START_ARRAY){
			        openBrackets++;
			        innerList = new ArrayList<String>();
			    }
			    else if(token == JsonToken.END_ARRAY){
			        closedBrackets ++;
			        if(innerList != null){
			            mainList.add(innerList);
			            innerList = null;
			        }
			    }
			    else{
			        String value = parser.getText();
			        if(! value.equals(",")) {
			            innerList.add(value);
			        }
			    }
			}
			
			StringBuffer buffer = new StringBuffer();
			for(List<String> list : mainList){
			    buffer.append(list.get(0));
			}
			
			result = buffer.toString();
		} catch (JsonParseException e) {
			System.err.println("ERROR (JsonParseException): "+e.getMessage());
		} catch (IOException e) {
			System.err.println("ERROR (IOException in Json): "+e.getMessage());
		} 
		
		return result;
	}
	
	private static String prepareTextToUrl(String text) {
		String url_term = "";
		
		try {
			url_term = URLEncoder.encode(text, "UTF-8");
		} catch(UnsupportedEncodingException uee) {
			System.err.println("ERROR (UnsupportedEncodingException in Url): "+uee.getMessage()
								+" | Text: "+text);
		}
		url_term = url_term.replaceAll("\\+", "%20");
		
		return url_term;
	}
	
}
