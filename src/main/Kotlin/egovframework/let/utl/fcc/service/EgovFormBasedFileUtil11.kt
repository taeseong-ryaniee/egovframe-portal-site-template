package egovframework.let.utl.fcc.service

import egovframework.com.cmm.EgovWebUtil.filePathBlackList
import egovframework.com.cmm.EgovWebUtil.removeCRLF
import egovframework.com.cmm.util.EgovResourceCloseHelper.close
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse

/**
 * @Class Name  : EgovFormBasedFileUtil.java
 * @Description : Form-based File Upload 유틸리티
 * @Modification Information
 *
 * 수정일                수정자              수정내용
 * ----------   --------     ---------------------------
 * 2009.08.26   한성곤               최초 생성
 * 2017.03.03     조성원 	            시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 * 2019.12.09   신용호               KISA 보안약점 조치 (위험한 형식 파일 업로드) : uploadFiles 삭제  => EgovFileUploadUtil.uploadFilesExt(확장자 기록) 대체
 *
 * @author 공통컴포넌트 개발팀 한성곤
 * @since 2009.08.26
 * @version 1.0
 * @see
 */
open class EgovFormBasedFileUtil {
    companion object {
        /** Buffer size  */
        const val BUFFER_SIZE: Int = 8192

        val SEPERATOR: String = File.separator

        private val LOGGER: Logger = LoggerFactory.getLogger(EgovFormBasedFileUtil::class.java)

        val todayString: String
            /**
             * 오늘 날짜 문자열 취득.
             * ex) 20090101
             * @return
             */
            get() {
                val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())

                return format.format(Date())
            }

        val physicalFileName: String
            /**
             * 물리적 파일명 생성.
             * @return
             */
            get() = EgovFormBasedUUID.Companion.randomUUID().toString().replace("-".toRegex(), "")
                .uppercase(Locale.getDefault())

        /**
         * 파일명 변환.
         * @param filename String
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        protected fun convert(filename: String?): String? {
            //return java.net.URLEncoder.encode(filename, "utf-8");
            return filename
        }

        /**
         * Stream으로부터 파일을 저장함.
         * @param is InputStream
         * @param file File
         * @throws IOException
         */
        @Throws(IOException::class)
        fun saveFile(`is`: InputStream, file: File): Long {
            //KISA 보안약점 조치 (2018-10-29, 윤창원)
            if (file.getParentFile() == null) {
                LOGGER.debug("file.getParentFile() is null")
                throw RuntimeException("file.getParentFile() is null")
            }


            // 디렉토리 생성
            if (!file.getParentFile().exists()) {
                //2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
                if (file.getParentFile().mkdirs()) {
                    LOGGER.debug("[file.mkdirs] file : Directory Creation Success")
                } else {
                    LOGGER.error("[file.mkdirs] file : Directory Creation Fail")
                }
            }

            var os: OutputStream? = null
            var size = 0L

            try {
                os = FileOutputStream(file)

                var bytesRead = 0
                val buffer = ByteArray(BUFFER_SIZE)

                while ((`is`.read(buffer, 0, BUFFER_SIZE).also { bytesRead = it }) != -1) {
                    size += bytesRead.toLong()
                    os.write(buffer, 0, bytesRead)
                }
            } finally {
                close(os)
            }

            return size
        }

        /**
         * 파일을 Upload 처리한다. (삭제)
         * EgovFileUploadUtil.uploadFilesExt(확장자 확인) 대체
         *
         * @param request
         * @param where
         * @param maxFileSize
         * @return
         * @throws Exception
         */
        /*
	public static List<EgovFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize) throws Exception {
		List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload();
			upload.setFileSizeMax(maxFileSize); // SizeLimitExceededException

			// Parse the request
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					LOGGER.info("Form field '{}' with value '{}' detected.", name, Streams.asString(stream));
				} else {
					LOGGER.info("File field '{}' with file name '{}' detected.", name, item.getName());

					if ("".equals(item.getName())) {
						continue;
					}

					// Process the input stream
					EgovFormBasedFileVo vo = new EgovFormBasedFileVo();

					String tmp = item.getName();

					if (tmp.lastIndexOf("\\") >= 0) {
						tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
					}

					vo.setFileName(tmp);
					vo.setContentType(item.getContentType());
					vo.setServerSubPath(getTodayString());
					vo.setPhysicalName(getPhysicalFileName());

					if (tmp.lastIndexOf(".") >= 0) {
						vo.setPhysicalName(vo.getPhysicalName() + tmp.substring(tmp.lastIndexOf(".")));
					}

					long size = saveFile(stream, new File(EgovWebUtil.filePathBlackList(where) + SEPERATOR + vo.getServerSubPath() + SEPERATOR + vo.getPhysicalName()));

					vo.setSize(size);

					list.add(vo);
				}
			}
		} else {
			throw new IOException("form's 'enctype' attribute have to be 'multipart/form-data'");
		}

		return list;
	}*/
        /**
         * 파일을 Download 처리한다.
         *
         * @param response
         * @param where
         * @param serverSubPath
         * @param physicalName
         * @param original
         * @throws Exception
         */
        @Throws(Exception::class)
        fun downloadFile(
            response: HttpServletResponse,
            where: String?,
            serverSubPath: String?,
            physicalName: String?,
            original: String
        ) {
            var original = original
            val downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName

            val file = File(filePathBlackList(downFileName))

            if (!file.exists()) {
                throw FileNotFoundException(downFileName)
            }

            if (!file.isFile()) {
                throw FileNotFoundException(downFileName)
            }

            val b = ByteArray(BUFFER_SIZE)

            original = original.replace("\r".toRegex(), "").replace("\n".toRegex(), "")
            response.setContentType("application/octet-stream")
            response.setHeader("Content-Disposition", "attachment; filename=\"" + convert(original) + "\";")
            response.setHeader("Content-Transfer-Encoding", "binary")
            response.setHeader("Pragma", "no-cache")
            response.setHeader("Expires", "0")

            var fin: BufferedInputStream? = null
            var outs: BufferedOutputStream? = null

            try {
                fin = BufferedInputStream(FileInputStream(file))
                outs = BufferedOutputStream(response.getOutputStream())

                var read = 0

                while ((fin.read(b).also { read = it }) != -1) {
                    outs.write(b, 0, read)
                }
            } finally {
                close(outs, fin)
            }
        }

        /**
         * 이미지에 대한 미리보기 기능을 제공한다.
         *
         * mimeType의 경우는 JSP 상에서 다음과 같이 얻을 수 있다.
         * getServletConfig().getServletContext().getMimeType(name);
         *
         * @param response
         * @param where
         * @param serverSubPath
         * @param physicalName
         * @param mimeType
         * @throws Exception
         */
        @Throws(Exception::class)
        fun viewFile(
            response: HttpServletResponse,
            where: String?,
            serverSubPath: String?,
            physicalName: String?,
            mimeTypeParam: String?
        ) {
            var mimeType = mimeTypeParam
            val downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName

            val file = File(filePathBlackList(downFileName))

            if (!file.exists()) {
                throw FileNotFoundException(downFileName)
            }

            if (!file.isFile()) {
                throw FileNotFoundException(downFileName)
            }

            val b = ByteArray(BUFFER_SIZE)

            if (mimeType == null) {
                mimeType = "application/octet-stream;"
            }

            response.setContentType(removeCRLF(mimeType))
            response.setHeader("Content-Disposition", "filename=image;")

            var fin: BufferedInputStream? = null
            var outs: BufferedOutputStream? = null

            try {
                fin = BufferedInputStream(FileInputStream(file))
                outs = BufferedOutputStream(response.getOutputStream())

                var read = 0

                while ((fin.read(b).also { read = it }) != -1) {
                    outs.write(b, 0, read)
                }
            } finally {
                close(outs, fin)
            }
        }
    }
}
