package egovframework.let.uat.uia.service.impl

import egovframework.com.cmm.LoginVO
import org.egovframe.rte.fdl.security.userdetails.EgovUserDetails
import org.egovframe.rte.fdl.security.userdetails.jdbc.EgovUsersByUsernameMapping
import java.sql.ResultSet
import java.sql.SQLException
import javax.sql.DataSource

/**
 * mapRow 결과를 사용자 EgovUserDetails Object 에 정의한다.
 *
 * @author ByungHun Woo
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class EgovSessionMapping
/**
 * 사용자정보를 테이블에서 조회하여 EgovUsersByUsernameMapping 에 매핑한다.
 * @param ds DataSource
 * @param usersByUsernameQuery String
 */
    (ds: DataSource?, usersByUsernameQuery: String?) : EgovUsersByUsernameMapping(ds, usersByUsernameQuery) {
    /**
     * mapRow Override
     * @param rs ResultSet 결과
     * @param rownum row num
     * @return Object EgovUserDetails
     * @exception SQLException
     */
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rownum: Int): EgovUserDetails? {
        logger.debug("## EgovUsersByUsernameMapping mapRow ##")

        val strUserId = rs.getString("user_id")
        val strPassWord = rs.getString("password")
        val strEnabled = rs.getBoolean("enabled")

        val strUserNm = rs.getString("user_nm")
        val strUserSe = rs.getString("user_se")
        val strUserEmail = rs.getString("user_email")
        val strOrgnztId = rs.getString("orgnzt_id")
        val strUniqId = rs.getString("esntl_id")

        /**2010.06.30 *이용   *조직명 추가   */
        val strOrgnztNm = rs.getString("orgnzt_nm")


        // 세션 항목 설정
        val loginVO = LoginVO()
        loginVO.id = strUserId
        loginVO.password = strPassWord
        loginVO.name = strUserNm
        loginVO.userSe = strUserSe
        loginVO.email = strUserEmail
        loginVO.orgnztId = strOrgnztId
        loginVO.uniqId = strUniqId
        /**2010.06.30 *이용   *조직명 추가   */
        loginVO.orgnztNm = strOrgnztNm

        return EgovUserDetails(strUserId, strPassWord, strEnabled, loginVO)
    }
}
