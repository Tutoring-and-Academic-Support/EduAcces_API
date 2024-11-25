package com.upao.eduaccess.mapper;

import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.dto.PlanRequest;
import com.upao.eduaccess.dto.PlanResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanMapper {

        private final ModelMapper modelMapper;

        public PlanMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;

            // Configuración explícita del mapeo si los nombres de los campos difieren
            modelMapper.typeMap(Plan.class, PlanResponse.class).addMappings(mapper -> {
                mapper.map(Plan::getCantidadEstudiantes, PlanResponse::setCantidadEstudiantes);
            });
        }

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