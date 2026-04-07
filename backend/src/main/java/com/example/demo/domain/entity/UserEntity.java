package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** System user entity. 系统用户实体。 */
@Data
@TableName("sys_user")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** User ID. 用户ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Username. 用户名。 */
  @TableField("username")
  private String username;

  /** Password (BCrypt encrypted). 密码（BCrypt加密）。 */
  @TableField("password")
  private String password;

  /** Nickname. 昵称。 */
  @TableField("nickname")
  private String nickname;

  /** Email. 邮箱。 */
  @TableField("email")
  private String email;

  /** Avatar URL. 头像URL。 */
  @TableField("avatar")
  private String avatar;

  /** Biography. 个人简介。 */
  @TableField("bio")
  private String bio;

  /** User status (0-inactive, 1-active, 2-blocked). 用户状态。 */
  @TableField("status")
  private Integer status;

  /** Role type (1-SUPER_ADMIN, 2-ADMIN, 3-AUTHOR, 4-VISITOR). 角色类型。 */
  @TableField("role_type")
  private Integer roleType;

  /** Last login time. 最后登录时间。 */
  @TableField("last_login_time")
  private LocalDateTime lastLoginTime;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  /** Deleted flag (0-not deleted, 1-deleted). 逻辑删除标记。 */
  @TableField("deleted")
  @TableLogic
  private Integer deleted;
}
