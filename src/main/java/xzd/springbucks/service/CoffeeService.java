package xzd.springbucks.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import xzd.springbucks.mapper.CoffeeMapper;
import xzd.springbucks.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public void insertCoffee(Coffee coffee){
        coffeeMapper.insertCoffee(coffee);
    }
    public Coffee findCoffeeById(long id){
        return coffeeMapper.findCoffeeById(id);
    }
    public void updateCoffeeById(Coffee coffee){
        coffeeMapper.updateCoffeeById(coffee);
    }

    public void deleteCoffeeById(long id){
        coffeeMapper.deleteCoffeeById(id);
    }

    public List<Coffee> findAll(){
        return coffeeMapper.findAll();
    }

    public PageInfo findAllWithParam(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Coffee> list = coffeeMapper.findAll();
        return new PageInfo<>(list);
    }


}
