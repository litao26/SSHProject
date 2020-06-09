package com.xuetao.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xuetao.entity.Article;
import com.xuetao.entity.Category;
import com.xuetao.entity.PageBean;
import com.xuetao.service.ArticleService;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {
    private Article article = new Article();
    @Override
    public Article getModel() {
        return article;
    }
    private ArticleService articleService;

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public String list(){
        //System.out.println("1234213");
        List<Article> allArticle = articleService.getAllArticle();
        //System.out.println(allArticle);
        //把数据存到值栈中，转发到jsp中
        ActionContext.getContext().getValueStack().set("allArticle", allArticle);
        return "list";
    }

    /*获取分页数据*/
    @Setter
    private Integer currPage = 1;
    //搜索关键字
    @Setter
    private String keyWord;
    public  String pageList(){
        //System.out.println(currPage);
        //System.out.println(keyWord);
        //设置查询条件
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        if(keyWord != null){
            //添加条件
            detachedCriteria.add(Restrictions.like("article_title","%"+keyWord+"%"));
        }
        //调用业务层
        PageBean pageBean = articleService.getPageData(detachedCriteria, currPage, 5);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }

    public String delete(){
        //System.out.println(article_id);
        Article article2 = new Article();
        article2.setArticle_id(article.getArticle_id());
        articleService.delete(article2);
        return "listRes";
    }
    @Setter
    private Integer parentid;
    public String getCategory() throws IOException {
        //System.out.println(parentid);
        //根据parentid查询分类
        List<Category> list = articleService.getCategory(parentid);
        //System.out.println(list);
        //以json格式响应给页面
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
        //System.out.println(jsonArray);
        //响应给页面
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());

        return null;
    }
    //添加文章
    /**
     * 文件上传提供的三个属性:
     */
    @Setter
    private String uploadFileName; // 文件名称
    @Setter
    private File upload; // 上传文件
    @Setter
    private String uploadContentType; // 文件类型
    public String add() throws IOException {
        if(upload != null){
            //上传文件
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            //随机生成文件名称
            //1.获取文件扩展名  ssh.jpg
            int index = uploadFileName.lastIndexOf(".");
            String etx = uploadFileName.substring(index);
            //System.out.println(etx);
            //2.随机生成文件名 拼接扩展名
            String uuid = UUID.randomUUID().toString();
            String uuidFileName = uuid.replace("-","")+etx;
            //确定上传路径
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            //拼接新文件路径
            File desFile = new File(path + "/" + uuidFileName);
            //文件上传
            FileUtils.copyFile(upload, desFile);
            article.setArticle_pic(uuidFileName);

        }
        //设置当前时间
        article.setArticle_time(new Date().getTime());
        //调用业务层保存到数据库
        articleService.save(article);
        //System.out.println(article);
        return "listRes";
    }

    public String edit(){
        //System.out.println(article.getArticle_id());
        Article resArticle = articleService.getOneArticle(article.getArticle_id());
        //System.out.println(resArticle);
        //把查询的数据放到值栈中
        ActionContext.getContext().getValueStack().push(resArticle);
        return "edit";
    }

    //修改
    public String update() throws IOException {

        //System.out.println(article);
        //判断有没有新上传的图片
        if(upload != null){
            //上传文件
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            //获取图片名称
            String picName = article.getArticle_pic();
            //删除原有的图片
            if(picName != null || "".equals(picName)){
                File file = new File(path + picName);
                file.delete();
            }

            //随机生成文件名称
            //1.获取文件扩展名  ssh.jpg
            int index = uploadFileName.lastIndexOf(".");
            String etx = uploadFileName.substring(index);
            //System.out.println(etx);
            //2.随机生成文件名 拼接扩展名
            String uuid = UUID.randomUUID().toString();
            String uuidFileName = uuid.replace("-","")+etx;
            //确定上传路径
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            //拼接新文件路径
            File desFile = new File(path + "/" + uuidFileName);
            //文件上传
            FileUtils.copyFile(upload, desFile);
            article.setArticle_pic(uuidFileName);

        }
        //设置时间
        article.setArticle_time(System.currentTimeMillis());
        //调用业务更新文章
        articleService.update(article);
        return "listRes";
    }

}
