package pl.coderslab.balanceYourDiet.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.balanceYourDiet.exception.MealNotFoundException;

public class MealDtoConverter implements Converter<String, MealDto> {

    @Autowired
    private MealService mealService;

    @Override
    public MealDto convert(String s) {
        MealEntity mealEntity = mealService.findById(Long.parseLong(s)).orElseThrow(MealNotFoundException::new);
        return mealService.mapEntityToDto(mealEntity);
    }
}
