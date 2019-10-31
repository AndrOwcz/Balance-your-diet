package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DailyPlanService {

    private final DailyPlanRepository dailyPlanRepository;
    private final DailyPlanMapper dailyPlanMapper;

    public DailyPlanService(DailyPlanRepository dailyPlanRepository, DailyPlanMapper dailyPlanMapper) {
        this.dailyPlanRepository = dailyPlanRepository;
        this.dailyPlanMapper = dailyPlanMapper;
    }

    public DailyPlanEntity mapDtoToEntity(DailyPlanDto dailyPlanDto) {
        return dailyPlanMapper.mapDailyPlanDtoToEntity(dailyPlanDto);
    }

    public DailyPlanDto mapEntityToDto(DailyPlanEntity dailyPlanEntity) {
        return dailyPlanMapper.mapDailyPlanEntityToDto(dailyPlanEntity);
    }

    public List<DailyPlanEntity> findAllById(Long id) {
        return dailyPlanRepository.findAllById(id);
    }

    public List<DailyPlanEntity> findAll() {
        return dailyPlanRepository.findAllDailyPlans();
    }

    public List<DailyPlanEntity> findAllByUserId(Long id) {
        return dailyPlanRepository.findAllByUserId(id);
    }

    public DailyPlanEntity save(DailyPlanEntity dailyPlanEntity) {
        return dailyPlanRepository.save(dailyPlanEntity);
    }

    public Optional<DailyPlanEntity> findById(Long id) {
        return dailyPlanRepository.findById(id);
    }

    public List<Long> findAllMealEntitiesIdByDailyPlanId(Long dailyPlanId) {
        return dailyPlanRepository.findAllMealEntitiesIdByDailyPlanId(dailyPlanId);
    }

    public void deleteById(Long id) {
        dailyPlanRepository.deleteById(id);
    }

}
