package com.example.demo.domain.vo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Demo VO. Demo 响应 VO。 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoVO {

  /** Demo ID */
  private Long id;

  /** Demo name */
  private String name;

  /** Demo description */
  private String description;

  /** Demo status (0-inactive, 1-active) */
  private Integer status;

  /** Create time */
  private LocalDateTime createTime;

  /** Update time */
  private LocalDateTime updateTime;
}
