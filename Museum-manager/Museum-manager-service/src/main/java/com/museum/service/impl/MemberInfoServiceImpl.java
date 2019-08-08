package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.MemberInfoResult;
import com.museum.common.pojo.PageHelperResult;
import com.museum.mapper.MemberInfoMapper;
import com.museum.pojo.MemberInfo;
import com.museum.custom.MemberInfoCustom;
import com.museum.pojo.MemberInfoExample;
import com.museum.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
public class MemberInfoServiceImpl implements MemberInfoService {
    @Autowired
    private MemberInfoMapper memberInfoMapper;

    /**
     * 分页查询管理员用户
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageHelperResult getMemberInfoList(Integer page, Integer rows)  {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        MemberInfoExample example=new MemberInfoExample();
        example.setOrderByClause("id DESC");
        List<MemberInfo> memberInfos = memberInfoMapper.selectByExample(example);
        int num=0;
        Iterator<MemberInfo> iterator = memberInfos.iterator();
        while (iterator.hasNext()){
            MemberInfo next = iterator.next();
            next.setPassword(null);
            if(next.getMemberAccountTypeId()==1){
                iterator.remove();
                num+=1;
            }
        }
        //取查询结果
        PageInfo<MemberInfo> pageInfo=new PageInfo<>(memberInfos);

        PageHelperResult result=new PageHelperResult();

        result.setRows(memberInfos);
        result.setPages(pageInfo.getPages());
        result.setTotal((int) pageInfo.getTotal()-num);
        return result;
    }

    /**
     * 返回用户信息
     * @param custom
     * @return
     */
    @Override
    public MemberInfoResult resultCustomUser(MemberInfoCustom custom) {
        MemberInfo memberInfo=new MemberInfo();
        memberInfo.setId(custom.getId());
        memberInfo.setLastLoginDate(new Date());
        memberInfo.setLastIp(custom.getLastIp());
        memberInfo.setToken(custom.getToken());
        updateMemberInfoById(memberInfo);

       MemberInfoResult result=new MemberInfoResult();
        result.setRemark(custom.getRemark());
        result.setNickname(custom.getNickname());
        result.setMemberAccountTypeId(custom.getMemberAccountTypeId());
        result.setId(custom.getId());
        result.setTelephone(custom.getTelephone());
        result.setUserName(custom.getUserName());
        return result;
    }

    @Override
    public MemberInfo selectMemberInfoByUserName(String userName) {
        MemberInfoExample example=new MemberInfoExample();
        MemberInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<MemberInfo> memberInfos = memberInfoMapper.selectByExample(example);
        if(memberInfos.size()!=0){
            MemberInfo memberInfo=memberInfos.get(0);
            return memberInfo;
        }

        return null;
    }
    //修改用户信息
    @Override
    public Integer updateMemberInfoById(MemberInfo memberInfo) {
        if(memberInfo.getPassword()!=null){
            memberInfo.setPassword(new BCryptPasswordEncoder().encode(memberInfo.getPassword()));
        }

        int i = memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
        return i;


    }
    //删除管理员用户信息
    @Override
    public Integer deleteMemberInfoById(Integer id) {
        int i = memberInfoMapper.deleteByPrimaryKey(id);
        return i;
    }
    //添加管理员
    @Override
    public void insertMember(MemberInfo memberInfo) throws Exception {
        //密码加密
        memberInfo.setPassword(new BCryptPasswordEncoder().encode(memberInfo.getPassword()));
        memberInfo.setOpenDate(new Date());
        memberInfo.setStatus("on");
        memberInfo.setNickname(memberInfo.getUserName());
         memberInfoMapper.insertSelective(memberInfo);

    }
    //根据用户id查询用户密码
    @Override
    public MemberInfo getPasswordById(MemberInfo memberInfo){
        MemberInfo result = memberInfoMapper.selectByPrimaryKey(memberInfo.getId());
        return  result;
    }
    //修改管理员状态
    @Override
    public Integer updateStatusById(MemberInfo memberInfo) {
        int i = memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
        return i;
    }


}
