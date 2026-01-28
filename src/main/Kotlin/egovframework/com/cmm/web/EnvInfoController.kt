package egovframework.com.cmm.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*
import java.util.stream.Collectors
import javax.annotation.Resource

@Controller
@Profile("security-dev")
class EnvInfoController {
    @Resource
    private val environment: Environment? = null

    @RequestMapping(value = ["/cmm/env/info.do"])
    @ResponseBody
    fun info(): String {
        val active = environment!!.getActiveProfiles()
        val activeProfiles = if (active == null || active.size == 0)
            "(none)"
        else
            Arrays.stream<String?>(active).collect(Collectors.joining(","))

        val javaOpts = System.getenv("JAVA_OPTS")
        val javaToolOpts = System.getenv("JAVA_TOOL_OPTIONS")

        val sb = StringBuilder()
        sb.append("active.profiles=").append(activeProfiles).append('\n')
        sb.append("env.JAVA_OPTS=").append(if (javaOpts == null) "(null)" else javaOpts).append('\n')
        sb.append("env.JAVA_TOOL_OPTIONS=").append(if (javaToolOpts == null) "(null)" else javaToolOpts).append('\n')

        LOGGER.info("[ENV-INFO] profiles={} JAVA_OPTS={} JAVA_TOOL_OPTIONS={}", activeProfiles, javaOpts, javaToolOpts)

        return sb.toString()
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EnvInfoController::class.java)
    }
}

