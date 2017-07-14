package com.exmaple.devtoolindex.monitor.contributor;

import com.exmaple.devtoolindex.monitor.helper.Helper;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongkailiu on 2016-11-11.
 */
@Component
public class AppInfoContributor implements InfoContributor {
    @Autowired
    Helper helper;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> map = new HashMap();
        map.put("version", helper.getAppVersion());
        map.put("now", new DateTime().toString());
        map.put("ips", helper.getIps());
        builder.withDetail("app info", map);
    }
}
