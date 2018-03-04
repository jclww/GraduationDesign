package com.lww.design.graduation.utils;

import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.utils.bean.orika.DateAndTimeConverters;
import com.lww.design.graduation.utils.bean.orika.NumericConverters;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class OrikaBeanUtil {

    private MapperFacade mapperFacade = null;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();


    public <V, P> P convert(V base, Class<P> target) {
        return base == null ? null : mapperFacade.map(base, target);
    }

    public <V, P> List<P> convertList(List<V> baseList, Class<P> target) {
        return baseList == null ? null : mapperFacade.mapAsList(baseList, target);
    }


    @PostConstruct
    private void init() {
        // 不指定名称,则设为默认的转换器。若指定名称,则调用时需要显示声明。
        mapperFactory.getConverterFactory().registerConverter(new NumericConverters.BigDecimalToIntegerConverter());
        mapperFactory.getConverterFactory().registerConverter(new NumericConverters.BigDecimalToLongConverter());
        mapperFactory.getConverterFactory().registerConverter(new DateAndTimeConverters.LongToDateConverter());
        mapperFactory.getConverterFactory().registerConverter(new DateAndTimeConverters.StringToDateConverter());
        mapperFactory.getConverterFactory().registerConverter("stringToLongConverter", new DateAndTimeConverters.StringToLongConverter());

        mapperFactory.classMap(User.class, ShiroUserVO.class)
                .field("account", "userName")
                .byDefault().register();

        mapperFacade = mapperFactory.getMapperFacade();
    }
}
