package egovframework.com.cmm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@Profile("security-dev")
public class EnvInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvInfoController.class);

    @Resource
    private Environment environment;

    @RequestMapping(value = "/cmm/env/info.do")
    @ResponseBody
    public String info() {
        String[] active = environment.getActiveProfiles();
        String activeProfiles = (active == null || active.length == 0)
                ? "(none)"
                : Arrays.stream(active).collect(Collectors.joining(","));

        String javaOpts = System.getenv("JAVA_OPTS");
        String javaToolOpts = System.getenv("JAVA_TOOL_OPTIONS");

        StringBuilder sb = new StringBuilder();
        sb.append("active.profiles=").append(activeProfiles).append('\n');
        sb.append("env.JAVA_OPTS=").append(javaOpts == null ? "(null)" : javaOpts).append('\n');
        sb.append("env.JAVA_TOOL_OPTIONS=").append(javaToolOpts == null ? "(null)" : javaToolOpts).append('\n');

        LOGGER.info("[ENV-INFO] profiles={} JAVA_OPTS={} JAVA_TOOL_OPTIONS={}", activeProfiles, javaOpts, javaToolOpts);

        return sb.toString();
    }
}

