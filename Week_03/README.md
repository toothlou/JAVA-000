
# 自己服务的返回
http://127.0.0.1:8088/hello?name=aa

Hello aa headers: host:[127.0.0.1:8088] connection:[keep-alive] cache-control:[max-age=0] upgrade-insecure-requests:[1] user-agent:[Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36] accept:[text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9] sec-fetch-site:[none] sec-fetch-mode:[navigate] sec-fetch-user:[?1] sec-fetch-dest:[document] accept-encoding:[gzip, deflate, br] accept-language:[zh-CN,zh;q=0.9] cookie:[csrftoken=dhqY3rCvo0mLzW2iFvPxmrMRMgLnObjrJ29Z0EwIUMWSUQVKfeX3z7ZvB6biEsr1; sessionid=91wy77hxef4btfhh67ub64v2rt75jqdd]

# 通过网关调用的返回
http://127.0.0.1:8888/hello?name=aa


Hello aa
headers:
connection:[Keep-Alive]
host:[localhost:8088]
user-agent:[Apache-HttpAsyncClient/4.1.4 (Java/1.8.0_221)]

自己加的过滤器没起作用，应该是地方没加对。而且把之前的header信息也全清了。
