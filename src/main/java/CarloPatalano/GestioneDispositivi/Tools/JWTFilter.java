package CarloPatalano.GestioneDispositivi.Tools;

import CarloPatalano.GestioneDispositivi.DTO.UnauthorizedException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import CarloPatalano.GestioneDispositivi.Services.DipendenteService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {



 @Value("${jwt_secret}")
    private String secret;


    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header mancante o formato non valido");
        }
        String token = authHeader.substring(7);
        jwtTools.verifyToken(token);



        Long dipendenteId = jwtTools.extractIdFromToken(token);


        Dipendente dipendente = dipendenteService.getDipendenteById(dipendenteId);


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                dipendente, null, List.of(new SimpleGrantedAuthority(dipendente.getRole().toString())));


        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}

