package Kchart;

import java.text.DecimalFormat;
import java.text.ParseException;

import javafx.util.StringConverter;

public class DecimalAxisFormatter extends StringConverter<Number>{

    protected DecimalFormat decimalFormat;
    
    public DecimalAxisFormatter( String format ) {
        decimalFormat = new DecimalFormat(format);
    }
    
    public DecimalAxisFormatter( DecimalFormat decimalFormat ) {
        this.decimalFormat = decimalFormat;
    }
    
    
    public String toString(Number object) {
        return decimalFormat.format(object.doubleValue());
    }

    public Number fromString(String string) {
        try {
            return decimalFormat.parse(string);
        } catch (ParseException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    
    
}
