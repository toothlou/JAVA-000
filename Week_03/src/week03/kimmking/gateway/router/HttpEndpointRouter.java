package com.jave.train.week03.kimmking.gateway.router;

import java.util.List;

public interface HttpEndpointRouter {
    
    String route(List<String> endpoints);
    
}
