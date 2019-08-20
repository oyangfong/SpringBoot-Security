

访问api接口
拿到token之后呢，我们就可以访问接口了。

》访问的方式有两种:

第一种：参数中传入access_token，

参数中传入
参数里面放入access_token访问api接口即可
;

第二种：在请求头Authorization中放入token的值，但是要注意格式。

我们最终获取到token的时候，我们同时得到了一个token_type: bearer是吧，

那我们就像通过http basic认证一样，在请求头Authorization中放入token_type access_token格式的字符串，

例如：bearer 07a06a24-d3c7-48bf-8a65-5dd315ab6eba，写好之后发送请求即可

到此呢我们就完成了token的获取和资源的访问。

=============================================================

curl service-hi:123456@localhost:5000/uaa/oauth/token -d grant_type=password -d username=fzp -d password=123456

curl localhost:8762/user/registry -d access_token=3be7b96c-6784-4696-8e1f-4673b8dfb92e -d username=miya3 -d password=123456


