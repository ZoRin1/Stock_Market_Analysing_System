package uiControl;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class tableData {
	private SimpleStringProperty a;
	private SimpleDoubleProperty b;
	
	public SimpleDoubleProperty exchangeDouble(double temp){
		b = new SimpleDoubleProperty(temp);
		return b;
	}
	public SimpleStringProperty exchangeString(String temp){
		a = new SimpleStringProperty(temp);
		return a;
	}
}
