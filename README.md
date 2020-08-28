# Spring cloud Security OAuth2.0


### 客户端信息：



![image-20200828135520358](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828135520358.png)

### 令牌访问端点

![image-20200828135637101](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828135637101.png)







### 令牌访问安全





![image-20200828135650530](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828135650530.png)







## 三、四种模式

### 授权码模式

首先先通过spring security的web安全验证，自定义的userDetailService用户名密码。

1、客户端先向UAA申请授权码(GET请求即可),同意授权

http://localhost:8083/oauth/authorize?client_id=client1&response_type=code&scope=all&redirect=http://laughing-blog.cn/

![image-20200828140457729](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828140457729.png)

Approval后，拿到授权码

![image-20200828140542457](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828140542457.png)





2、POST请求申请令牌

http://localhost:8083/oauth/token?client_id=client1&client_secret=123456&grant_type=authorization_code&code=vy9xpC&redirect_uri=http://laughing-blog.cn/

![image-20200828142256557](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828142256557.png)









![image-20200828151209059](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828151209059.png)

### 简化模式 response_type=token

没有服务端的情况

1、用户访问客户端进行授权

http://localhost:8083/oauth/authorize?client_id=client1&response_type=token&scope=all&redirect=http://laughing-blog.cn/

![image-20200828143246563](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828143246563.png)

2、返回access_token

![image-20200828143359177](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828143359177.png)



http://laughing-blog.cn/#access_token=bf80805d-383c-4974-a1e1-23aac95f9c93&token_type=bearer&expires_in=6589



### 密码模式

会把密码泄露给客户端

http://localhost:8083/oauth/token?client_id=client1&client_secret=123456&grant_type=authorization_code&code=vy9xpC&redirect_uri=http://laughing-blog.cn/

![image-20200828143814464](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828143814464.png)



### 客户端模式

服务端完全信任客户端

![image-20200828144027406](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828144027406.png)







### 用token校验

![image-20200828153320059](https://github.com/laughingfuzihao/oauth2.0/blob/master/z_pic/image-20200828153320059.png)





