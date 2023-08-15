package tn.taxi.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ADMIN,AGENT,TAXI;
	
	@Override
	public String getAuthority() {

		return "ROLE_" + name();

		}

}
