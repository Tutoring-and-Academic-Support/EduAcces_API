package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.PlanRequest;
import com.upao.eduaccess.dto.PlanResponse;
import com.upao.eduaccess.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plan")
public class PlanController {


    private final PlanService planService;

    @PostMapping("/crear-plan") public ResponseEntity<PlanResponse> createPlan(@RequestBody PlanRequest planRequest) {
        return new ResponseEntity<>(planService.create(planRequest), HttpStatus.CREATED);
    }

    @GetMapping("/obtener-plan/{idPlan}") public ResponseEntity<PlanResponse> obtenerPlan(@PathVariable Integer idPlan) {
        return new ResponseEntity<>(planService.getPlanById(idPlan), HttpStatus.OK);
    }
}
