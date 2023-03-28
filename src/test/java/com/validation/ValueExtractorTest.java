package com.validation;

import org.junit.jupiter.api.Test;

import com.validation.containers.Data;
import com.validation.containers.Entry;

public class ValueExtractorTest extends AbstracValidatorTest {
    
    @Test
    public void testValueExtractor() {
      SampleData sampleData = new SampleData();
      sampleData.setData(new Data<String>());
      sampleData.getData().setData("h");
      sampleData.getData().setData("                      ");
      validate(sampleData);
    }

    @Test
    public void testMultipleGenericType(){
        SampleEntry sampleEntry = new SampleEntry();
        sampleEntry.setEntryData(new Entry<String, String>());
        sampleEntry.getEntryData().setKey(" ");
        sampleEntry.getEntryData().setValue(" ");
        validate(sampleEntry);
    }
}
