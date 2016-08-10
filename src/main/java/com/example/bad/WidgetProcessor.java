package com.example.bad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.util.Collections;

/**
 * Processor for widget tab separated files
 */
public class WidgetProcessor {
  
  public static int MISDATED = 1;
  public static int DISCOUNT = 2;
  public static int PREMIUM = 3;
  public static int OVERSIZED = 4;
  public static int MAX_SIZE = 20;
  

  /**
   * Reads a tab separated file of widgets and returns those matching the requested classification
   * 
   * @param widgetFile
   * @param classification
   * @return a vector of widgets
   */
  public Collection selectWidgets(String widgetFile, int classification) {
	BufferedReader br = null;
    Vector widgets = null;
    String line = "";
    String cvsSplitBy = ",";
    Widget w = new Widget();
    int myClassification = -1;
    
    SimpleDateFormat sdf = null;

    try 
	{
      widgets = new Vector();
      br = new BufferedReader(new FileReader(widgetFile));
      while ((line = br.readLine()) != null) {
        if (line != null) {
          sdf = new SimpleDateFormat("dd/MM/yyyy");
          String[] parts = line.split(cvsSplitBy);

          w = new Widget();
          w.setColour(parts[0]);
          w.setLength(Integer.parseInt(parts[1]));
          w.setSize(Integer.parseInt(parts[2]));
          w.setCreated(sdf.parse(parts[3]));

          if (w != null) {
            myClassification = PREMIUM;
            
            Date date = new Date();

		if ( w.getCreated().after(date) && w != null ) {
           		myClassification = MISDATED;
           	 } else if ( w.getLength() > 100 ) {
            		myClassification = OVERSIZED;
              // we discount PINK and BROWN widgets
            } else if ( w.getColour().equals("PINK") || w.getColour().equals("ORANGE") || w.getColour().equals("GREY") || w.getColour().equals("BROWN")) {
              myClassification = DISCOUNT;
            } else if ( w.getLength() > 100 & w.getSize() < 42 ) {
              myClassification = DISCOUNT;
            } else if ( w.getLength() > 50 & w.getSize() < 20 ) {
              myClassification = DISCOUNT;
            }
                        
            if ( myClassification == classification) {
              if (widgets != null) 
                widgets.add(w);
            }
          }
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    
    return widgets;
  }
  
  private void resize(Widget w, int a, int b, int c) {
    w.setSize(a * b);
    w.setLength(a);
  }

}
