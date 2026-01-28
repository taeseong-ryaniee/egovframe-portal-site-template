package egovframework.com.cmm.service.impl

import org.apache.ibatis.session.SqlSessionFactory
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import javax.annotation.Resource

/**
 * EgovComAbstractDAO.java 클래스
 *
 * @author 서준식
 * @since 2011. 9. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
abstract class EgovComAbstractDAO : EgovAbstractMapper() {
    @Resource(name = "egov.sqlSession")
    override fun setSqlSessionFactory(sqlSession: SqlSessionFactory?) {
        super.setSqlSessionFactory(sqlSession)
    }
}
