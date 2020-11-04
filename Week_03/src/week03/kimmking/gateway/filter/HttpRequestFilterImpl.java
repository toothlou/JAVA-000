package com.jave.train.week03.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class HttpRequestFilterImpl implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders headers = new DefaultHttpHeaders();
        headers.set("nio", "toothlou");
        //fullRequest.headers().add(headers);
    }
}
