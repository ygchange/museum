package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.custom.ExhibitsTypeCustom;
import com.museum.custom.MemberInfoCustom;
import com.museum.mapper.ExhibitsInfoMapper;
import com.museum.mapper.ExhibitsTypeMapper;
import com.museum.mapper.MemberInfoMapper;
import com.museum.pojo.*;
import com.museum.service.ItemTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {
    @Autowired
    private ExhibitsTypeMapper exhibitsTypeMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private ExhibitsInfoMapper exhibitsInfoMapper;
    @Override
    //查询商品类型
    public PageHelperResult getItemTypeList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        ExhibitsTypeExample example=new ExhibitsTypeExample();
        List<ExhibitsType> list = exhibitsTypeMapper.selectByExample(example);
        List<ExhibitsTypeCustom> newList= new ArrayList<>();
        for (ExhibitsType exhibitsType:list) {

            ExhibitsTypeCustom custom=new ExhibitsTypeCustom();
            BeanUtils.copyProperties(exhibitsType,custom);
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(exhibitsType.getOperatorId());
            if(memberInfo!=null) {
                memberInfo.setPassword(null);
            }
                custom.setMemberInfo(memberInfo);
                newList.add(custom);

        }
        PageInfo<ExhibitsType> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setRows(newList);
        result.setTotal((int) pageInfo.getTotal());
        return result;

    }
    //判断商品类型是否存在
    @Override
    public ExhibitsType selectItemTypeByUserName(String typeName) {
        ExhibitsTypeExample example =new ExhibitsTypeExample();
        ExhibitsTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNameEqualTo(typeName);
        List<ExhibitsType> exhibitsTypes = exhibitsTypeMapper.selectByExample(example);
        if(exhibitsTypes.size()!=0){
            ExhibitsType exhibitsType=exhibitsTypes.get(0);
            return exhibitsType;
        }
        return null;
    }
    //添加商品类型
    @Override
    public void insertItemType(ExhibitsType exhibitsType) throws Exception {
        exhibitsType.setAddTime(new Date());
        exhibitsTypeMapper.insertSelective(exhibitsType);
    }
    //修改展品类型
    @Override
    public Integer updateItemTypeById(ExhibitsType exhibitsType) {
        int i = exhibitsTypeMapper.updateByPrimaryKeySelective(exhibitsType);
        return i;
    }
    //删除展品类型
    @Override
    public Integer deleteItemTypeById(Integer id) {
        ExhibitsInfoExample exhibitsInfoExample=new ExhibitsInfoExample();
        ExhibitsInfoExample.Criteria criteria = exhibitsInfoExample.createCriteria();
        criteria.andTypeIdEqualTo(id);
        List<ExhibitsInfo> exhibitsInfos = exhibitsInfoMapper.selectByExample(exhibitsInfoExample);
        if(exhibitsInfos.size()>0){
            return -1;
        }else {
            int i = exhibitsTypeMapper.deleteByPrimaryKey(id);
            return i;
        }

    }


}
