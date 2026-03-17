package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.DemoCreateReq;
import com.example.demo.domain.dto.req.DemoUpdateReq;
import com.example.demo.domain.vo.DemoVO;

/** Demo service interface. Demo 服务接口。 */
public interface DemoService {

  /** Create a new demo. 创建新的 Demo。 */
  DemoVO create(DemoCreateReq req);

  /** Update demo. 更新 Demo。 */
  DemoVO update(DemoUpdateReq req);

  /** Delete demo by ID. 根据 ID 删除 Demo。 */
  void delete(Long id);

  /** Get demo by ID. 根据 ID 获取 Demo。 */
  DemoVO getById(Long id);

  /** Get demo page. 获取 Demo 分页列表。 */
  Page<DemoVO> getPage(Integer pageNum, Integer pageSize);
}
