package com.blog.common;

/**
 * 系统常量
 *
 * @author a1387
 * @date 2023/02/17
 */
public class SystemConstants {
    /**
     * 文章未发表
     */
    public static final Integer ARTICLE_NOT_PUBLISHED = 1;

    /**
     * 文章发布
     */
    public static final Integer ARTICLE_RELEASE = 0;

    /**
     * 状态正常
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 审核通过
     */
    public static final Integer APPROVED = 0;

    /**
     * 审核未通过
     */
    public static final Integer FAILED_TO_PASS_THE_REVIEW = 2;

    /**
     * 正在审核
     */
    public static final Integer AUDITING = 1;

    /**
     * 博客登录
     */
    public static final String BLOG_LOGIN = "blogLogin:";

    /**
     * 根评论
     */
    public static final String COMMENT_ROOT = "-1";

    /**
     * 该用户的创建人为自己本身
     */
    public static String REGISTER_YOURSELF = "createMe";

}
