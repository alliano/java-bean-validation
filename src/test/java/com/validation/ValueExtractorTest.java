package com.validation;

import org.junit.jupiter.api.Test;

import com.validation.containers.Data;

public class ValueExtractorTest extends AbstracValidatorTest {
    
    @Test
    public void testValueExtractor() {
      SampleData sampleData = new SampleData();
      sampleData.setData(new Data<String>());
      sampleData.getData().setData("h");
      sampleData.getData().setData("                      ");
      validate(sampleData);
    }
}
