package com.example.demo.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Demo DTO - General data transfer object. Demo 通用数据传输对象。 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoDTO {

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
