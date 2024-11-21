package com.upao.eduaccess.mapper;

import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.dto.PlanRequest;
import com.upao.eduaccess.dto.PlanResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class PlanMapper {

    private final ModelMapper modelMapper;

    public Plan converttoentity(PlanRequest planRequest) {
        return modelMapper.map(planRequest, Plan.class);
    }

    public PlanResponse converttoresponse(Plan plan) {
        return modelMapper.map(plan, PlanResponse.class);
    }

    public List<PlanResponse> converttoresponse(List<Plan> planList) {
        return planList.stream().map(this::converttoresponse).toList();
    }
}
