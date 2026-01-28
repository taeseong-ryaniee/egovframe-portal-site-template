package egovframework.let.main.service

import java.io.Serializable

/**
 * 템플릿 메인화면 작업 List 항목 VO(Sample 소스)
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class EgovMainContentsVO : Serializable {
    /**
     * getWorkItemName To-Do List 항목 명 getter
     * @return To-Do List 항목 명
     */
    /**
     * 작업항목 이름
     */
    var workItemName: String? = null
        /**
         * setWorkItemName To-Do List 항목 명 Setter
         *
         * @param workItemName    To-Do List 항목 명
         */
        set(workItemName) {
        }
    /**
     * getWorkItemURL 업무화면 URL getter
     * @return 업무화면 URL
     */
    /**
     * To-Do List 항목 별 업무화면 URL
     */
    var workItemURL: String? = null
        /**
         * setWorkItemURL 업무화면 URL setter
         *
         * @param workItemURL    업무화면 URL
         */
        set(workItemURL) {
        }

    /**
     *
     * @exception Throwable
     */
    @Throws(Throwable::class)
    fun finalize() {
    }

    var itemCount: Int
        /**
         * getItemCount 항목 개수 getter
         * @return
         */
        get() = 0
        /**
         * setItemCount 항목 개수 setter
         *
         * @param itemCount    itemCount
         */
        set(itemCount) {
        }

    companion object {
        /**
         * serialVersionUID
         */
        private val serialVersionUID = -2202175699511921484L
    }
}