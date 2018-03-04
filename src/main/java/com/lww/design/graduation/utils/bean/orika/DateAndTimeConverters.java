package com.lww.design.graduation.utils.bean.orika;

import com.lww.design.graduation.utils.DateUtil;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeConverters extends ma.glasnost.orika.converter.builtin.DateAndTimeConverters {

    public static class LongToDateConverter extends BidirectionalConverter<Long, Date> {

        @Override
        public Date convertTo(Long source, Type<Date> destinationType, MappingContext context) {
            return DateUtil.parseFromSecond(source);
        }

        @Override
        public Long convertFrom(Date source, Type<Long> destinationType, MappingContext context) {
            return source.getTime() / 1000;
        }
    }

    public static class StringToDateConverter extends BidirectionalConverter<String,Date>{

        @Override
        public Date convertTo(String source, Type<Date> destinationType, MappingContext mappingContext) {
            FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
            try {
                return (Date) fdf.parseObject(source);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String convertFrom(Date source, Type<String> destinationType, MappingContext mappingContext) {
            if (source == null) {
                return "";
            }
            FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
            return fdf.format(source);
        }

    }

    public static class StringToLongConverter extends BidirectionalConverter<String, Long> {

        @Override
        public Long convertTo(String source, Type<Long> destinationType, MappingContext mappingContext) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = df.parse(source);
                return date.getTime() / 1000;
            } catch (ParseException e) {
                e.printStackTrace();
                return 0L;
            }
        }

        @Override
        public String convertFrom(Long source, Type<String> destinationType, MappingContext mappingContext) {
            Date date = new Date(source * 1000);
            FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
            return fdf.format(date);
        }
    }

}
