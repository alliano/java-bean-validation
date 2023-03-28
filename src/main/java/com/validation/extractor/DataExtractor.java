package com.validation.extractor;

import com.validation.containers.Data;

import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.ValueExtractor;

public class DataExtractor implements ValueExtractor<Data<@ExtractedValue ?>> {

    @Override
    public void extractValues(Data<@ExtractedValue ?> originalValue, ValueReceiver recive) {
        Object data = originalValue.getData();
        /**
         * cara untuk meng ekstrak datanya, dengan menggunakan ValueReciver dengan menthod value
         * dan paameter pertaman adalah nama node nya dan parameter ke 2 adalah data nya
         * disini unutk node name nya kita isi null karena tipe data yang kita buat bukan nested
         * object melainkan data biasa
         */
        recive.value(null, data);
    }
}
