package cn.db.separate.service;

import cn.db.separate.pojo.TUser;

/**
 * T_User接口
 */
public interface UserService {
    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    TUser selectUserById(int id);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(TUser user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUser(TUser user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUserById(int id);
}
