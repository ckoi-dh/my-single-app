package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.ArticleCreateReq;
import com.example.demo.domain.dto.req.ArticleQueryReq;
import com.example.demo.domain.dto.req.ArticleUpdateReq;
import com.example.demo.domain.entity.ArticleEntity;
import com.example.demo.domain.vo.ArticleVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Article service implementation. 文章服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final ArticleMapper articleMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ArticleVO create(ArticleCreateReq req) {
    ArticleEntity entity = new ArticleEntity();
    entity.setTitle(req.getTitle());
    entity.setSummary(req.getSummary());
    entity.setContent(req.getContent());
    entity.setCoverImage(req.getCoverImage());
    entity.setStatus(req.getStatus() != null ? req.getStatus() : 0);
    entity.setSeoTitle(req.getSeoTitle());
    entity.setSeoKeywords(req.getSeoKeywords());
    entity.setSeoDescription(req.getSeoDescription());
    entity.setAuthorId(1L);
    entity.setPublishTime(LocalDateTime.now());
    entity.setViewCount(0L);
    entity.setLikeCount(0L);
    entity.setCommentCount(0L);
    entity.setFavoriteCount(0L);
    entity.setShareCount(0L);
    entity.setIsSticky(0);
    entity.setIsFeatured(0);

    articleMapper.insert(entity);
    log.info("Created article: {}", entity.getTitle());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ArticleVO update(ArticleUpdateReq req) {
    ArticleEntity entity = articleMapper.selectById(req.getId());
    if (entity == null) {
      throw new BusinessException("Article not found");
    }

    if (StringUtils.isNotBlank(req.getTitle())) {
      entity.setTitle(req.getTitle());
    }
    if (StringUtils.isNotBlank(req.getSummary())) {
      entity.setSummary(req.getSummary());
    }
    if (StringUtils.isNotBlank(req.getContent())) {
      entity.setContent(req.getContent());
    }
    if (StringUtils.isNotBlank(req.getCoverImage())) {
      entity.setCoverImage(req.getCoverImage());
    }
    if (req.getStatus() != null) {
      entity.setStatus(req.getStatus());
    }
    if (StringUtils.isNotBlank(req.getSeoTitle())) {
      entity.setSeoTitle(req.getSeoTitle());
    }
    if (StringUtils.isNotBlank(req.getSeoKeywords())) {
      entity.setSeoKeywords(req.getSeoKeywords());
    }
    if (StringUtils.isNotBlank(req.getSeoDescription())) {
      entity.setSeoDescription(req.getSeoDescription());
    }

    articleMapper.updateById(entity);
    log.info("Updated article: {}", entity.getTitle());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }

    articleMapper.deleteById(id);
    log.info("Deleted article: {}", id);
  }

  @Override
  public ArticleVO getById(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }
    return convertToVO(entity);
  }

  @Override
  public Page<ArticleVO> getPage(ArticleQueryReq req) {
    Page<ArticleEntity> page = new Page<>(req.getCurrent(), req.getSize());

    LambdaQueryWrapper<ArticleEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByDesc(ArticleEntity::getCreateTime);

    Page<ArticleEntity> entityPage = articleMapper.selectPage(page, wrapper);
    return convertToVOPage(entityPage);
  }

  @Override
  public Page<ArticleVO> search(ArticleQueryReq req) {
    return getPage(req);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ArticleVO publish(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }

    entity.setStatus(1);
    entity.setPublishTime(LocalDateTime.now());
    articleMapper.updateById(entity);

    log.info("Published article: {}", entity.getTitle());
    return convertToVO(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ArticleVO setFeatured(Long id, Boolean isFeatured) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }

    entity.setIsFeatured(isFeatured ? 1 : 0);
    articleMapper.updateById(entity);

    log.info("Set article featured: {} - {}", entity.getTitle(), isFeatured);
    return convertToVO(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ArticleVO setSticky(Long id, Boolean isSticky) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }

    entity.setIsSticky(isSticky ? 1 : 0);
    articleMapper.updateById(entity);

    log.info("Set article sticky: {} - {}", entity.getTitle(), isSticky);
    return convertToVO(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void increaseViewCount(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }
    entity.setViewCount(entity.getViewCount() + 1);
    articleMapper.updateById(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void increaseLikeCount(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }
    entity.setLikeCount(entity.getLikeCount() + 1);
    articleMapper.updateById(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void increaseCommentCount(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }
    entity.setCommentCount(entity.getCommentCount() + 1);
    articleMapper.updateById(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void increaseFavoriteCount(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }
    entity.setFavoriteCount(entity.getFavoriteCount() + 1);
    articleMapper.updateById(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void increaseShareCount(Long id) {
    ArticleEntity entity = articleMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Article not found");
    }
    entity.setShareCount(entity.getShareCount() + 1);
    articleMapper.updateById(entity);
  }

  /**
   * Convert entity to VO. 将实体转换为VO。
   *
   * @param entity Article entity
   * @return Article VO
   */
  private ArticleVO convertToVO(ArticleEntity entity) {
    ArticleVO vo = new ArticleVO();
    vo.setId(entity.getId());
    vo.setTitle(entity.getTitle());
    vo.setSummary(entity.getSummary());
    vo.setContent(entity.getContent());
    vo.setCoverImage(entity.getCoverImage());
    vo.setAuthorId(entity.getAuthorId());
    vo.setStatus(entity.getStatus());
    vo.setPublishTime(entity.getPublishTime());
    vo.setViewCount(entity.getViewCount());
    vo.setLikeCount(entity.getLikeCount());
    vo.setCommentCount(entity.getCommentCount());
    vo.setFavoriteCount(entity.getFavoriteCount());
    vo.setShareCount(entity.getShareCount());
    vo.setSeoTitle(entity.getSeoTitle());
    vo.setSeoKeywords(entity.getSeoKeywords());
    vo.setSeoDescription(entity.getSeoDescription());
    vo.setIsSticky(entity.getIsSticky());
    vo.setIsFeatured(entity.getIsFeatured());
    vo.setCreateTime(entity.getCreateTime());
    vo.setUpdateTime(entity.getUpdateTime());
    vo.setCategories(new ArrayList<>());
    vo.setTags(new ArrayList<>());
    return vo;
  }

  /**
   * Convert entity page to VO page. 将实体分页转换为VO分页。
   *
   * @param entityPage Entity page
   * @return VO page
   */
  private Page<ArticleVO> convertToVOPage(Page<ArticleEntity> entityPage) {
    Page<ArticleVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
    return voPage;
  }
}
