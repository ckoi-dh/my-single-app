package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.FavoriteCreateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.entity.*;
import com.example.demo.domain.vo.FavoriteVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.*;
import com.example.demo.service.ArticleService;
import com.example.demo.service.FavoriteService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Favorite service implementation. 收藏服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

  private final FavoriteMapper favoriteMapper;
  private final ArticleMapper articleMapper;
  private final ArticleService articleService;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public FavoriteVO create(FavoriteCreateReq req) {
    // Check if already favorited
    if (exists(getCurrentUserId(), req.getTargetId(), req.getTargetType())) {
      throw new BusinessException("Already favorited");
    }

    // Check target exists
    ArticleEntity article = articleMapper.selectById(req.getTargetId());
    if (article == null) {
      throw new BusinessException("Article not found");
    }

    // Create favorite entity
    FavoriteEntity entity = new FavoriteEntity();
    entity.setUserId(getCurrentUserId());
    entity.setArticleId(req.getTargetId());

    favoriteMapper.insert(entity);
    log.info("Created favorite: user={}, article={}", entity.getUserId(), entity.getArticleId());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    FavoriteEntity entity = favoriteMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Favorite not found");
    }

    favoriteMapper.deleteById(id);
    log.info("Deleted favorite: id={}", id);
  }

  @Override
  public FavoriteVO getById(Long id) {
    FavoriteEntity entity = favoriteMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Favorite not found");
    }
    return convertToVO(entity);
  }

  @Override
  public Page<FavoriteVO> getPage(PageReq req) {
    Page<FavoriteEntity> page = new Page<>(req.getCurrent(), req.getSize());

    LambdaQueryWrapper<FavoriteEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByDesc(FavoriteEntity::getCreateTime);

    Page<FavoriteEntity> entityPage = favoriteMapper.selectPage(page, wrapper);
    return convertToVOPage(entityPage);
  }

  @Override
  public List<FavoriteVO> getByUserId(Long userId) {
    LambdaQueryWrapper<FavoriteEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(FavoriteEntity::getUserId, userId);
    wrapper.orderByDesc(FavoriteEntity::getCreateTime);

    List<FavoriteEntity> entities = favoriteMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public List<FavoriteVO> getByTarget(Long targetId, Integer targetType) {
    LambdaQueryWrapper<FavoriteEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(FavoriteEntity::getArticleId, targetId);
    wrapper.orderByDesc(FavoriteEntity::getCreateTime);

    List<FavoriteEntity> entities = favoriteMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public boolean exists(Long userId, Long targetId, Integer targetType) {
    LambdaQueryWrapper<FavoriteEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(FavoriteEntity::getUserId, userId);
    wrapper.eq(FavoriteEntity::getArticleId, targetId);

    return favoriteMapper.selectCount(wrapper) > 0;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public FavoriteVO toggle(FavoriteCreateReq req) {
    Long userId = getCurrentUserId();
    LambdaQueryWrapper<FavoriteEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(FavoriteEntity::getUserId, userId);
    wrapper.eq(FavoriteEntity::getArticleId, req.getTargetId());

    FavoriteEntity existing = favoriteMapper.selectOne(wrapper);
    if (existing != null) {
      delete(existing.getId());
      return null;
    } else {
      return create(req);
    }
  }

  /**
   * Convert entity to VO. 将实体转换为VO。
   *
   * @param entity Favorite entity
   * @return Favorite VO
   */
  private FavoriteVO convertToVO(FavoriteEntity entity) {
    FavoriteVO vo = new FavoriteVO();
    vo.setId(entity.getId());
    vo.setUserId(entity.getUserId());
    vo.setTargetId(entity.getArticleId());
    vo.setTargetType(1); // Article only
    vo.setCreateTime(entity.getCreateTime());

    ArticleEntity article = articleMapper.selectById(entity.getArticleId());
    if (article != null) {
      vo.setTargetTitle(article.getTitle());
      vo.setTargetContent(article.getSummary());
    }

    return vo;
  }

  /**
   * Convert entity page to VO page. 将实体分页转换为VO分页。
   *
   * @param entityPage Entity page
   * @return VO page
   */
  private Page<FavoriteVO> convertToVOPage(Page<FavoriteEntity> entityPage) {
    Page<FavoriteVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
    return voPage;
  }

  /**
   * Get current user ID. 获取当前用户ID。
   *
   * @return User ID
   */
  private Long getCurrentUserId() {
    // TODO: Get from security context
    return 1L;
  }
}
