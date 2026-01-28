package egovframework.let.sym.mnu.mpm.service

import org.apache.commons.lang3.builder.ToStringBuilder

/**
 * 메뉴관리, 메뉴 생성을 위한 모델 클래스를 정의한다.
 * @author 공통서비스 개발팀 이 용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class MenuManage {
    /**
     * 메뉴설명
     */
    var menuDc: String? = null

    /**
     * 메뉴명
     */
    var menuNm: String? = null

    /**
     * 메뉴번호
     */
    var menuNo: Int = 0

    /**
     * 메뉴순서
     */
    var menuOrdr: Int = 0

    /**
     * 프로그램파일명
     */
    var progrmFileNm: String? = null

    /**
     * 관련이미지명
     */
    var relateImageNm: String? = null

    /**
     * 관련이미지경로
     */
    var relateImagePath: String? = null

    /**
     * 상위메뉴번호
     */
    var upperMenuId: Int = 0

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}