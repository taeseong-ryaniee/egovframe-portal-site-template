package egovframework.com.cmm

import org.springframework.context.MessageSource
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import java.util.*

/**
 * 메시지 리소스 사용을 위한 MessageSource 인터페이스 및 ReloadableResourceBundleMessageSource 클래스의 구현체
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class EgovMessageSource : ReloadableResourceBundleMessageSource(), MessageSource {
    /**
     * getReloadableResourceBundleMessageSource()
     * @return ReloadableResourceBundleMessageSource
     */
    /**
     * getReloadableResourceBundleMessageSource()
     * @param reloadableResourceBundleMessageSource - resource MessageSource
     * @return ReloadableResourceBundleMessageSource
     */
    var reloadableResourceBundleMessageSource: ReloadableResourceBundleMessageSource? = null

    /**
     * 정의된 메세지 조회
     * @param code - 메세지 코드
     * @return String
     */
    fun getMessage(code: String): String {
        return this.reloadableResourceBundleMessageSource!!.getMessage(code, null, Locale.getDefault())
    }
}
