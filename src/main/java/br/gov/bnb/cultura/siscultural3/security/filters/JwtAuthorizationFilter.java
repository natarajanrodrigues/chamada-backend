//package br.gov.bnb.cultura.siscultural3.security.filters;
//
//import io.jsonwebtoken.*;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import br.gov.bnb.cultura.siscultural3.security.JwtConfig;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//
////	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
//
//	private final JwtConfig jwtConfig;
//
//	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
//		super(authenticationManager);
//		this.jwtConfig = jwtConfig;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//									FilterChain filterChain) throws IOException, ServletException {
//		Authentication authentication = getAuthentication(request);
//		if (authentication == null) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		filterChain.doFilter(request, response);
//	}
//
//	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
////		var token = request.getHeader(SecurityConstants.TOKEN_HEADER);
////		if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
////			try {
////				var signingKey = SecurityConstants.JWT_SECRET.getBytes();
////
////				var parsedToken = Jwts.parser()
////						.setSigningKey(signingKey)
////						.parseClaimsJws(token.replace("Bearer ", ""));
////
////				var username = parsedToken
////						.getBody()
////						.getSubject();
////
////				var authorities = ((List<?>) parsedToken.getBody()
////						.get("rol")).stream()
////						.map(authority -> new SimpleGrantedAuthority((String) authority))
////						.collect(Collectors.toList());
////
////				if (StringUtils.isNotEmpty(username)) {
////					return new UsernamePasswordAuthenticationToken(username, null, authorities);
////				}
////			} catch (ExpiredJwtException exception) {
//////				log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
////			} catch (UnsupportedJwtException exception) {
//////				log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
////			} catch (MalformedJwtException exception) {
//////				log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
////			} catch (SignatureException exception) {
//////				log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
////			} catch (IllegalArgumentException exception) {
//////				log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
////			}
////		}
//
//
//		String header = request.getHeader(jwtConfig.getHeader());
//
//
//		//  Get the token
//		String token = header.replace(jwtConfig.getPrefix(), "");
//
//		try {	// exceptions might be thrown in creating the claims if for example the token is expired
//
//			// 4. Validate the token
//			Claims claims = Jwts.parser()
//					.setSigningKey(jwtConfig.getSecret().getBytes())
//					.parseClaimsJws(token)
//					.getBody();
//
//			String username = claims.getSubject();
//			if(username != null) {
//				@SuppressWarnings("unchecked")
//				List<String> authorities = (List<String>) claims.get("authorities");
//
//				// 5. Create auth object
//				// UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
//				// It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
//				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//						username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//
//				// 6. Authenticate the user
//				// Now, user is authenticated
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//		} catch (ExpiredJwtException e) {
//
//			// In case of expired token. Make sure it's clear; so guarantee user won't be authenticated
//			response.setHeader("message", "AHAHAHAHAH");
////            SecurityContextHolder.clearContext();
//			response.addHeader("error", "Sessão expirada");
//
//		} catch (Exception e) {
//			// In case of failure. Make sure it's clear; so guarantee user won't be authenticated
//			SecurityContextHolder.clearContext();
//		}
//
//		return null;
//	}
//}