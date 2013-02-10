package com.binout.soccer5.front;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.io.InputStream;
import java.util.Properties;

@Model
public class VersionBean {

    private String version = "unknown";

    @PostConstruct
    public void loadVersion() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream("version.properties");
        if (in == null)
            return;

        Properties props = new Properties();
        props.load(in);

        version = props.getProperty("build.version");
    }

    public String getVersion() {
        return version;
    }
}
