package com.rOushAn.cabcore.controllers.rider;

import com.rOushAn.cabcore.dtos.RideDto;
import com.rOushAn.cabcore.dtos.RiderDto;
import com.rOushAn.cabcore.service.RiderService;
import io.swagger.v3.oas.annotations.Operation; // Import
import io.swagger.v3.oas.annotations.tags.Tag; // Import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rider")
@Secured("ROLE_RIDER")
@Tag(name = "Rider - Profile & Rides", description = "Endpoints for riders to manage their profile and view ride history.")
// Tag for the controller
public class RiderGetMapping {

    private final RiderService riderService;
    private final int PAGE_SIZE = 4;

    public RiderGetMapping(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/getMyProfile")
    @Operation(summary = "Get rider's own profile",
            description = "Retrieves the detailed profile information for the authenticated rider.")
    public ResponseEntity<RiderDto> getRiderProfile() {
        return ResponseEntity.ok(riderService.getRiderProfile());
    }

    @GetMapping("/getMyRides")
    @Operation(summary = "Get rider's ride history",
            description = "Retrieves a paginated list of all rides taken by the authenticated rider.")
    public ResponseEntity<List<RideDto>> getAllMyRides(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "1") Integer pageNumber) {
        Pageable pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy).ascending());
        Page<RideDto> rides = riderService.getAllMyRides(pageRequest);
        return ResponseEntity.ok(rides.getContent());
    }
}