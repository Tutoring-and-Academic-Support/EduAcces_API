package com.upao.eduaccess.service;


import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.dto.PlanRequest;
import com.upao.eduaccess.dto.PlanResponse;

import java.util.List;

public interface PlanService {

    public void delete(Integer idPlan);
    public PlanResponse create(PlanRequest planRequest);
    public PlanResponse update(PlanRequest planRequest, Integer idPlan);
    public List<PlanResponse> getAllPlan();
    public PlanResponse getPlanById(Integer idPlan);
    // Nuevo método para obtener la entidad Plan
    Plan getPlanEntityById(Integer idPlan);

}
