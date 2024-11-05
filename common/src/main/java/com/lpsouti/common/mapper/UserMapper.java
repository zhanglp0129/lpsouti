package com.lpsouti.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lpsouti.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhanglp0129
* @description 针对表【users(用户表)】的数据库操作Mapper
* @createDate 2024-11-05 17:18:44
* @Entity com.lpsouti.common.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

}




