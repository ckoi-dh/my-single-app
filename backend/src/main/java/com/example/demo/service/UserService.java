package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.dto.req.UserCreateReq;
import com.example.demo.domain.dto.req.UserUpdateReq;
import com.example.demo.domain.vo.UserVO;

/** User service interface. 用户服务接口。 */
public interface UserService {

  /**
   * Create user. 创建用户。
   *
   * @param req User create request
   * @return UserVO
   */
  UserVO create(UserCreateReq req);

  /**
   * Update user. 更新用户。
   *
   * @param req User update request
   * @return UserVO
   */
  UserVO update(UserUpdateReq req);

  /**
   * Delete user. 删除用户。
   *
   * @param id User ID
   */
  void delete(Long id);

  /**
   * Get user by ID. 根据ID获取用户。
   *
   * @param id User ID
   * @return UserVO
   */
  UserVO getById(Long id);

  /**
   * Get user page. 获取用户分页列表。
   *
   * @param req Page request
   * @return Page of UserVO
   */
  Page<UserVO> getPage(PageReq req);

  /**
   * Get user by username. 根据用户名获取用户。
   *
   * @param username Username
   * @return UserVO
   */
  UserVO getByUsername(String username);

  /**
   * Check username exists. 检查用户名是否存在。
   *
   * @param username Username
   * @return true if exists
   */
  boolean existsByUsername(String username);

  /**
   * Check email exists. 检查邮箱是否存在。
   *
   * @param email Email
   * @return true if exists
   */
  boolean existsByEmail(String email);
}
