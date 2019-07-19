package cn.db.separate.service.impl;

import cn.db.separate.dao.TUserMapper;
import cn.db.separate.pojo.TUser;
import cn.db.separate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cheng
 * @create 2019-07-10 11:31
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public TUser selectUserById(int id) {
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertUser(TUser user) {
        return tUserMapper.insertSelective(user);
    }

    @Override
    public int updateUser(TUser user) {
        return tUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUserById(int id) {
        return tUserMapper.deleteByPrimaryKey(id);
    }
}
