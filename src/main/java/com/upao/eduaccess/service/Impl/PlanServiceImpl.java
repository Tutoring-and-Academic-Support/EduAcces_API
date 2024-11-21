package com.upao.eduaccess.service.Impl;

import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.dto.PlanRequest;
import com.upao.eduaccess.dto.PlanResponse;
import com.upao.eduaccess.mapper.PlanMapper;
import com.upao.eduaccess.repository.PlanRepository;
import com.upao.eduaccess.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {


    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Override
    public void delete(Integer idPlan) {
        planRepository.deleteById(idPlan);
    }

    @Override
    public PlanResponse create(PlanRequest planRequest) {
        Plan plan = planMapper.converttoentity(planRequest);
        return planMapper.converttoresponse(planRepository.save(plan));

    }

    @Override
    public PlanResponse update(PlanRequest planRequest, Integer idPlan) {
        Plan plan = planRepository.findById(idPlan).orElse(null);
        if (plan == null) {
            throw new RuntimeException("Plan not found");
        }

        if(planRequest.getPrecio() != 0)plan.setPrecio(planRequest.getPrecio());
        if(planRequest.getNombre() != null)plan.setNombre(planRequest.getNombre());

        return planMapper.converttoresponse(planRepository.save(plan));

    }

    @Override
    public List<PlanResponse> getAllPlan() {
        return planMapper.converttoresponse(planRepository.findAll());
    }

    @Override
    public PlanResponse getPlanById(Integer idPlan) {
        return planMapper.converttoresponse(planRepository.findById(idPlan).orElse(null));
    }
}
