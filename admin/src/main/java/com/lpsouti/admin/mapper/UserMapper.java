package com.lpsouti.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lpsouti.common.entity.User;
import org.apache.ibatis.annotations.Select;

/**
* @author zhanglp0129
* @description 针对表【users(用户表)】的数据库操作Mapper
* @createDate 2024-11-05 17:18:44
* @Entity com.lpsouti.common.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    // 查询数据库中是否存在记录
    @Select("select exists(select 1 from users where is_deleted=0)")
    boolean exists();

    // 用户的条件分页查询

}




