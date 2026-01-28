package egovframework.let.uss.umt.service

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

/**
 * 가입약관VO클래스로서가입약관확인시 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class StplatVO : Serializable {
    /**
     * useStplatId attribute 값을  리턴한다.
     * @return String
     */
    /**
     * useStplatId attribute 값을 설정한다.
     * @param useStplatId String
     */
    /** 약관아이디 */
    var useStplatId: String? = null

    /**
     * useStplatCn attribute 값을  리턴한다.
     * @return String
     */
    /**
     * useStplatCn attribute 값을 설정한다.
     * @param useStplatCn String
     */
    /** 사용약관안내 */
    var useStplatCn: String? = null

    /**
     * infoProvdAgeCn attribute 값을  리턴한다.
     * @return String
     */
    /**
     * infoProvdAgeCn attribute 값을 설정한다.
     * @param infoProvdAgeCn String
     */
    /** 정보동의안내 */
    var infoProvdAgeCn: String? = null

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}
