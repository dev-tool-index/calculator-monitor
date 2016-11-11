package com.exmaple.devtoolindex.monitor.contributor;

import com.exmaple.devtoolindex.monitor.helper.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by hongkailiu on 2016-11-11.
 */
@Component
public class AppInfoContributor implements InfoContributor {
    @Autowired
    Helper helper;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app info",
                Collections.singletonMap("version", helper.getAppVersion()));
    }
}
