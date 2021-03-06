package com.git.sys.mapper.weipinhui;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.git.sys.entity.weipinhui.Area;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author authorZhao
 * @since 2019-08-22
 */
public interface AreaMapper extends BaseMapper<Area> {

	public List<Map> queryBySql(String sql);

}
