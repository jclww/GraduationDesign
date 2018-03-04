package com.lww.design.graduation.utils.bean.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.math.BigDecimal;

/**
 * orika类型转换器
 */
public class NumericConverters {

    public static class BigDecimalToIntegerConverter extends BidirectionalConverter<BigDecimal, Integer> {

        @Override
        public Integer convertTo(BigDecimal bigDecimal, Type<Integer> type, MappingContext mappingContext) {
            return bigDecimal.intValue();
        }

        @Override
        public BigDecimal convertFrom(Integer integer, Type<BigDecimal> type, MappingContext mappingContext) {
            return new BigDecimal(integer);
        }
    }

    public static class BigDecimalToLongConverter extends BidirectionalConverter<BigDecimal, Long> {

        @Override
        public Long convertTo(BigDecimal source, Type<Long> destinationType, MappingContext mappingContext) {
            return source.longValue();
        }

        @Override
        public BigDecimal convertFrom(Long source, Type<BigDecimal> destinationType, MappingContext mappingContext) {
            return new BigDecimal(source);
        }
    }
}