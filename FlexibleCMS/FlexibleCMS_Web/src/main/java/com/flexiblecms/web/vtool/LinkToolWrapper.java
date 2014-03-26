package com.flexiblecms.web.vtool;

import org.apache.velocity.tools.view.tools.LinkTool;

/**
 * Created by wanglu on 14-3-26.
 * 连接包装
 */
public class LinkToolWrapper extends LinkTool {


    /**
     * 基本地址获取
     * @return
     */
    public String getBasePath(){


        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + getContextPath();

      return basePath;

    }

}
