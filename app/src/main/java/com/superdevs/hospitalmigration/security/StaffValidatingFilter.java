package com.superdevs.hospitalmigration.security;

import com.superdevs.hospitalmigration.App;
import com.superdevs.hospitalmigration.staff.StaffFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StaffValidatingFilter extends OncePerRequestFilter {
    private final StaffFacade staffFacade;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var xStaffUuid = request.getHeader("x-staff-uuid");

        if (isStaffUuidAuthorized(xStaffUuid, request)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(401);
        }
    }

    private boolean isStaffUuidAuthorized(String xStaffUuid, HttpServletRequest request) {
        return xStaffUuid != null &&
                (staffFacade.isStaffUuidValid(UUID.fromString(xStaffUuid))
                        || isAddingStaffAllowed(xStaffUuid, request));
    }

    private boolean isAddingStaffAllowed(String xStaffUuid, HttpServletRequest request) {
        return !staffFacade.isAnyStaffAvailable()
                && "/staff-members".equals(request.getServletPath())
                && "POST".equals(request.getMethod())
                && xStaffUuid.equals(App.INITIAL_SECURITY_KEY);
    }

}
