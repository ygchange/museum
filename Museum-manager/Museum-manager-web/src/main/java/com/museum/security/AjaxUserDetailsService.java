package com.museum.security;

import com.museum.pojo.MemberInfo;
import com.museum.custom.MemberInfoCustom;
import com.museum.service.MemberInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class AjaxUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberInfoService memberInfoService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MemberInfo memberInfo = memberInfoService.selectMemberInfoByUserName(s);
        MemberInfoCustom memberInfoCustom = null;
        if(memberInfo!=null) {
            memberInfoCustom=new MemberInfoCustom();
            BeanUtils.copyProperties(memberInfo, memberInfoCustom);
            String role = memberInfo.getMemberAccountTypeId().toString();
            List<GrantedAuthority> authorities=new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
            memberInfoCustom.setAuthorities(authorities);

            return memberInfoCustom;
        }
        memberInfoCustom=new MemberInfoCustom();
        return memberInfoCustom;
    }
}
