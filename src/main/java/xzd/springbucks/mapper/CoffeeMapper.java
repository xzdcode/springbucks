package xzd.springbucks.mapper;

import xzd.springbucks.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoffeeMapper {
    void insertCoffee(Coffee coffee);

    void updateCoffeeById(Coffee coffee);

    void deleteCoffeeById(@Param(value="id")long id);

    Coffee findCoffeeById(@Param(value="id")long id);

    List<Coffee> findAll();
}
