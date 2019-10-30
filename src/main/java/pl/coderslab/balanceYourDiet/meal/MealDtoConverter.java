package pl.coderslab.balanceYourDiet.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.balanceYourDiet.exception.MealNotFoundException;

public class MealDtoConverter implements Converter<String, MealDto> {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MealService mealService;

    @Override
    public MealDto convert(String s) {
        long mealId = Long.parseLong(s);
        MealEntity mealEntity = mealRepository.findById(mealId).orElseThrow(MealNotFoundException::new);
        return mealService.mapEntityToDto(mealEntity);
    }
}
