package egovframework.let.sec.ram.service

/**
 * 권한관리에 대한 Vo 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class AuthorManageVO : AuthorManage() {
    /**
     * authorManageList attribute 를 리턴한다.
     * @return List<AuthorManageVO>
    </AuthorManageVO> */
    /**
     * authorManageList attribute 값을 설정한다.
     * @param authorManageList List<AuthorManageVO>
    </AuthorManageVO> */
    @JvmField
    var authorManageList: MutableList<AuthorManageVO?>? = null


    companion object {
        private const val serialVersionUID = 1L
    }
}