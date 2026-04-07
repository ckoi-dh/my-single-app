package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/** Article mapper. 文章Mapper。 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

  /**
   * Increase article view count. 增加文章浏览次数。
   *
   * @param id Article ID
   */
  @Update("UPDATE blog_article SET view_count = view_count + 1 WHERE id = #{id}")
  void increaseViewCount(Long id);

  /**
   * Increase article like count. 增加文章点赞次数。
   *
   * @param id Article ID
   */
  @Update("UPDATE blog_article SET like_count = like_count + 1 WHERE id = #{id}")
  void increaseLikeCount(Long id);

  /**
   * Increase article comment count. 增加文章评论次数。
   *
   * @param id Article ID
   */
  @Update("UPDATE blog_article SET comment_count = comment_count + 1 WHERE id = #{id}")
  void increaseCommentCount(Long id);

  /**
   * Increase article favorite count. 增加文章收藏次数。
   *
   * @param id Article ID
   */
  @Update("UPDATE blog_article SET favorite_count = favorite_count + 1 WHERE id = #{id}")
  void increaseFavoriteCount(Long id);

  /**
   * Increase article share count. 增加文章分享次数。
   *
   * @param id Article ID
   */
  @Update("UPDATE blog_article SET share_count = share_count + 1 WHERE id = #{id}")
  void increaseShareCount(Long id);
}
