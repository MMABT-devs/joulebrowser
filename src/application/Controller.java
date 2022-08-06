package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class Controller implements Initializable {

	@FXML
	private WebView webView;
	@FXML
	private TextField textField;
	
	private WebEngine engine;
	private WebHistory webHist;
	
	private String homePage;
	
	private double webZoom;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		engine = webView.getEngine();
		homePage = "www.google.com";
		textField.setText(homePage);
		webZoom = 1;
		loadPage();
	}
	
	public void loadPage() {
		//engine.load("http://google.com/");
		engine.load("http://" + textField.getText());
	}
	public void refreshPage() {
		engine.reload();
	}
	public void zoomIn() {
		webZoom += 0.25;
		webView.setZoom(webZoom);
	}
	public void zoomOut() {
		webZoom -= 0.25;
		webView.setZoom(webZoom);
	}
	public void displayHistory() {
		webHist = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = webHist.getEntries();
		
		for(WebHistory.Entry entry : entries) {
			//System.out.println(entry);
			System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
		}
	}
	public void back() {
		webHist = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = webHist.getEntries();
		webHist.go(-1);
		textField.setText(entries.get(webHist.getCurrentIndex()).getUrl());
	}
	public void forward() {
		webHist = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = webHist.getEntries();
		webHist.go(1);
		textField.setText(entries.get(webHist.getCurrentIndex()).getUrl());
	}

}
