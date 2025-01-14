package com.lpsouti.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.user.UserPageDTO;
import com.lpsouti.admin.vo.user.UserVO;
import com.lpsouti.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhanglp0129
 * @description 针对表【users(用户表)】的数据库操作Mapper
 * @createDate 2024-11-05 17:18:44
 * @Entity com.lpsouti.common.entity.User
 */
public interface UserMapper extends BaseMapper<User> {

    // 查询数据库中是否存在记录
    @Select("select exists(select 1 from users)")
    boolean exists();

    // 用户的条件分页查询
    IPage<UserVO> pageQuery(@Param("page") IPage<UserVO> page, @Param("dto") UserPageDTO dto);

    // 根据id查找用户
    UserVO queryById(Long id);

    List<UserVO> queryBatch(List<Long> ids);
}




