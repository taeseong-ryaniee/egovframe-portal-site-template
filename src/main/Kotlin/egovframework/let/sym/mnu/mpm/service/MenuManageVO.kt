package egovframework.let.sym.mnu.mpm.service

import org.apache.commons.lang3.builder.ToStringBuilder

/**
 * 메뉴목록관리 처리를 위한 VO 클래스르를 정의한다
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class MenuManageVO {
    /** 메뉴정보  */
    /**
     * menuNo attribute를 리턴한다.
     * @return int
     */
    /**
     * menuNo attribute 값을 설정한다.
     * @param menuNo int
     */
    /** 메뉴번호  */
    var menuNo: Int = 0
    /**
     * menuOrdr attribute를 리턴한다.
     * @return int
     */
    /**
     * menuOrdr attribute 값을 설정한다.
     * @param menuOrdr int
     */
    /** 메뉴순서  */
    var menuOrdr: Int = 0
    /**
     * menuNm attribute를 리턴한다.
     * @return String
     */
    /**
     * menuNm attribute 값을 설정한다.
     * @param menuNm String
     */
    /** 메뉴명  */
    var menuNm: String? = null
    /**
     * upperMenuId attribute를 리턴한다.
     * @return int
     */
    /**
     * upperMenuId attribute 값을 설정한다.
     * @param upperMenuId int
     */
    /** 상위메뉴번호  */
    var upperMenuId: Int = 0
    /**
     * menuDc attribute를 리턴한다.
     * @return String
     */
    /**
     * menuDc attribute 값을 설정한다.
     * @param menuDc String
     */
    /** 메뉴설명  */
    var menuDc: String? = null
    /**
     * relateImagePath attribute를 리턴한다.
     * @return String
     */
    /**
     * relateImagePath attribute 값을 설정한다.
     * @param relateImagePath String
     */
    /** 관련이미지경로  */
    var relateImagePath: String? = null
    /**
     * relateImageNm attribute를 리턴한다.
     * @return String
     */
    /**
     * relateImageNm attribute 값을 설정한다.
     * @param relateImageNm String
     */
    /** 관련이미지명  */
    var relateImageNm: String? = null
    /**
     * progrmFileNm attribute를 리턴한다.
     * @return String
     */
    /**
     * progrmFileNm attribute 값을 설정한다.
     * @param progrmFileNm String
     */
    /** 프로그램파일명  */
    var progrmFileNm: String? = null

    /** 사이트맵  */
    /**
     * creatPersonId attribute를 리턴한다.
     * @return String
     */
    /**
     * creatPersonId attribute 값을 설정한다.
     * @param creatPersonId String
     */
    /** 생성자ID  */
    var creatPersonId: String? = null

    /** 권한정보설정  */
    /**
     * authorCode attribute를 리턴한다.
     * @return String
     */
    /**
     * authorCode attribute 값을 설정한다.
     * @param authorCode String
     */
    /** 권한코드  */
    var authorCode: String? = null

    /**
     * tempValue attribute를 리턴한다.
     * @return String
     */
    /**
     * tempValue attribute 값을 설정한다.
     * @param tempValue String
     */
    /** 기타VO변수  */
    var tempValue: String? = null
    /**
     * tempInt attribute를 리턴한다.
     * @return int
     */
    /**
     * tempInt attribute 값을 설정한다.
     * @param tempInt int
     */
    var tempInt: Int = 0


    /** Login 메뉴관련 VO변수  */
    /**
     * tmp_Id attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_Id attribute 값을 설정한다.
     * @param tmp_Id String
     */
    /** tmp_Id  */
    var tmp_Id: String? = null
    /**
     * tmp_Password attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_Password attribute 값을 설정한다.
     * @param tmp_Password String
     */
    /** tmp_Password  */
    var tmp_Password: String? = null
    /**
     * tmp_Name attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_Name attribute 값을 설정한다.
     * @param tmp_Name String
     */
    /** tmp_Name  */
    var tmp_Name: String? = null
    /**
     * tmp_UserSe attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_UserSe attribute 값을 설정한다.
     * @param tmp_UserSe String
     */
    /** tmp_UserSe  */
    var tmp_UserSe: String? = null
    /**
     * tmp_Email attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_Email attribute 값을 설정한다.
     * @param tmp_Email String
     */
    /** tmp_Email  */
    var tmp_Email: String? = null
    /**
     * tmp_OrgnztId attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_OrgnztId attribute 값을 설정한다.
     * @param tmp_OrgnztId String
     */
    /** tmp_OrgnztId  */
    var tmp_OrgnztId: String? = null
    /**
     * tmp_UniqId attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_UniqId attribute 값을 설정한다.
     * @param tmp_UniqId String
     */
    /** tmp_UniqId  */
    var tmp_UniqId: String? = null
    /**
     * tmp_Cmd attribute를 리턴한다.
     * @return String
     */
    /**
     * tmp_Cmd attribute 값을 설정한다.
     * @param tmp_Cmd String
     */
    /** tmp_Cmd  */
    var tmp_Cmd: String? = null

    /**
     * toString 메소드를 대치한다.
     */
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}