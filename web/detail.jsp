<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>
<!-- 内容区 -->
<section class="layout main-wrap  content">
    <section class='col-main'>
        <p class="tips">温馨提示：为了您的体验更佳，请在PC端使用</p>
        <!--博客社区-->
        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content" >
                    <div role="tabpanel" style="margin-left: 100px;" class="tab-pane fade in active" id="home">

                        <div id="lk_blog_list" style="padding-left: 20px; width: 1000px" class="container">
                            <div class="row">
                                <ul class="blog-list">
                                    <li>
                                        <div class="blog-list-left">
                                            <div class="main-title" id="title">标题</div>
                                            <div style="color: #c2c2c2" id="time">2019-1-1</div>
                                            <hr>
                                            <div id="content">

                                            </div>

                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </article>
    </section>

</section>
<footer id="lk_footer">
    <div class="container">

        <div class="footer-bottom col-sm-offset-2 hidden-sm hidden-xs">
            <ul>
                <li><a href="">联络我们</a></li>
                <li><a href="">支持我们</a></li>
                <li><a href="">沪ICP备 8888888号-1</a></li>
            </ul>
            <p>Copyright © 2018-2020 Angel Care All Rights Reserved. 极寒雪涛 版权所有</p>
        </div>
    </div>
</footer>
<script>
    //获取当前参数
    function getParams(key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }
    var id = getParams("id");
    // alert(id)
    $.post("${pageContext.request.contextPath}/web_getDetail.action",{id:id},function (data) {
        // console.log(data);
        $("#title").html(data.article_title);
        $("#content").html(data.article_content);
        $("#time").html(new Date(data.article_time.toLocaleDateString()));
    })
    // 请求数据
</script>
</body>
</html>