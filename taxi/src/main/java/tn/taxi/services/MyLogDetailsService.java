package tn.taxi.services;

import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.taxi.entities.Log;


@Service
public class MyLogDetailsService implements UserDetailsService {
	
	@Autowired
	private LogService logService;

	@Override
	 @Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Log log = logService.findLogByLogUsername(username);
		if (log == null) {
            throw new UsernameNotFoundException(username +"Not Found");
        }
		List<GrantedAuthority> authorities = getUserAuthority(log);
		return new User(log.getUsername(), log.getPassword(), log.getActive(), true, true, true, authorities);
				
	}
	private List<GrantedAuthority> getUserAuthority(Log log){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(log.getRole().getAuthority()));
        return authorities;
        /*Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(log.getRole().getAuthority()));      
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities; */ 
	}

}
