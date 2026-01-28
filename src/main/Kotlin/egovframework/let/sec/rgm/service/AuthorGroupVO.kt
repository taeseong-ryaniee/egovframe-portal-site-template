package egovframework.let.sec.rgm.service

/**
 * 권한그룹에 대한 Vo 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class AuthorGroupVO : AuthorGroup() {
    /**
     * authorGroupList attribute 를 리턴한다.
     * @return List<AuthorGroupVO>
    </AuthorGroupVO> */
    /**
     * authorGroupList attribute 값을 설정한다.
     * @param authorGroupList List<AuthorGroupVO>
    </AuthorGroupVO> */
    @JvmField
    var authorGroupList: MutableList<AuthorGroupVO?>? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}