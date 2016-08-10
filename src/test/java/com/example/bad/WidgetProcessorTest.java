package com.example.bad;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import junit.framework.TestCase;

public class WidgetProcessorTest extends TestCase {

  public void testCases() {
    Collection selected; 
    String file = "/home/henry/code/bad_code/src/test/java/testwidgets.csv";
    
    WidgetProcessor processor = new WidgetProcessor();
    selected  = processor.selectWidgets(file, 2);
    
    assertEquals(1, selected.size());
    
    selected  = processor.selectWidgets(file, 1);
    
    assertEquals(0, selected.size());
    
    selected  = processor.selectWidgets(file, 3);
    
    assertEquals(1, selected.size());
    
    selected  = processor.selectWidgets(file, 4);
    
    assertEquals(1, selected.size());    
    
  }

}
