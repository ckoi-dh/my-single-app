package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.CommentCreateReq;
import com.example.demo.domain.dto.req.CommentQueryReq;
import com.example.demo.domain.dto.req.CommentUpdateReq;
import com.example.demo.domain.entity.CommentEntity;
import com.example.demo.domain.vo.CommentVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Comment service implementation. 评论服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentMapper commentMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CommentVO create(CommentCreateReq req) {
    CommentEntity entity = new CommentEntity();
    entity.setArticleId(req.getArticleId());
    entity.setParentId(req.getParentId());
    entity.setContent(req.getContent());
    entity.setStatus(1);
    entity.setLikeCount(0L);
    entity.setReplyCount(0L);

    commentMapper.insert(entity);
    log.info("Created comment for article: {}", req.getArticleId());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CommentVO update(CommentUpdateReq req) {
    CommentEntity entity = commentMapper.selectById(req.getId());
    if (entity == null) {
      throw new BusinessException("Comment not found");
    }

    if (req.getContent() != null) {
      entity.setContent(req.getContent());
    }
    if (req.getStatus() != null) {
      entity.setStatus(req.getStatus());
    }

    commentMapper.updateById(entity);
    log.info("Updated comment: {}", entity.getId());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    CommentEntity entity = commentMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Comment not found");
    }

    commentMapper.deleteById(id);
    log.info("Deleted comment: {}", id);
  }

  @Override
  public CommentVO getById(Long id) {
    CommentEntity entity = commentMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Comment not found");
    }
    return convertToVO(entity);
  }

  @Override
  public Page<CommentVO> getPage(CommentQueryReq req) {
    Page<CommentEntity> page = new Page<>(req.getCurrent(), req.getSize());

    LambdaQueryWrapper<CommentEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByDesc(CommentEntity::getCreateTime);

    Page<CommentEntity> entityPage = commentMapper.selectPage(page, wrapper);
    return convertToVOPage(entityPage);
  }

  @Override
  public List<CommentVO> getByArticleId(Long articleId) {
    LambdaQueryWrapper<CommentEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(CommentEntity::getArticleId, articleId);
    wrapper.orderByDesc(CommentEntity::getCreateTime);

    List<CommentEntity> entities = commentMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public List<CommentVO> getTreeByArticleId(Long articleId) {
    return getByArticleId(articleId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CommentVO approve(Long id) {
    CommentEntity entity = commentMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Comment not found");
    }

    entity.setStatus(1);
    commentMapper.updateById(entity);

    return getById(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CommentVO reject(Long id) {
    CommentEntity entity = commentMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Comment not found");
    }

    entity.setStatus(2);
    commentMapper.updateById(entity);

    return getById(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void increaseLikeCount(Long id) {
    CommentEntity entity = commentMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Comment not found");
    }

    entity.setLikeCount(entity.getLikeCount() + 1);
    commentMapper.updateById(entity);
  }

  private CommentVO convertToVO(CommentEntity entity) {
    CommentVO vo = new CommentVO();
    vo.setId(entity.getId());
    vo.setArticleId(entity.getArticleId());
    vo.setParentId(entity.getParentId());
    vo.setContent(entity.getContent());
    vo.setCommentatorId(1L); // 默认为管理员
    vo.setStatus(entity.getStatus());
    vo.setLikeCount(entity.getLikeCount());
    vo.setReplyCount(entity.getReplyCount());
    vo.setCreateTime(entity.getCreateTime());
    vo.setUpdateTime(entity.getUpdateTime());
    vo.setChildren(new java.util.ArrayList<>());
    return vo;
  }

  private Page<CommentVO> convertToVOPage(Page<CommentEntity> entityPage) {
    Page<CommentVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
    return voPage;
  }
}
