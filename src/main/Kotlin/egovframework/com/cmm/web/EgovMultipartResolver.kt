package egovframework.com.cmm.web

import egovframework.com.cmm.service.EgovProperties.getProperty
import egovframework.let.utl.fcc.service.EgovFileUploadUtil.getFileExtension
import org.apache.commons.fileupload.FileItem
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import java.io.UnsupportedEncodingException
import java.util.*
import javax.servlet.ServletContext

/*
* Copyright 2001-2006 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the ";License&quot;);
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS"; BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/**
 * 실행환경의 파일업로드 처리를 위한 기능 클래스
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class EgovMultipartResolver : CommonsMultipartResolver {
    constructor()

    /**
     * 첨부파일 처리를 위한 multipart resolver를 생성한다.
     *
     * @param servletContext
     */
    constructor(servletContext: ServletContext) : super(servletContext)

    /**
     * multipart에 대한 parsing을 처리한다.
     */
    override fun parseFileItems(fileItems: MutableList<FileItem?>, encoding: String): MultipartParsingResult {
        // 스프링 3.0변경으로 수정한 부분

        val multipartFiles: MultiValueMap<String?, MultipartFile?> = LinkedMultiValueMap<String?, MultipartFile?>()
        val multipartParameters: MutableMap<String?, Array<String?>?> = HashMap<String?, Array<String?>?>()
        val whiteListFileUploadExtensions = getProperty("Globals.fileUpload.Extensions")
        val mpParamContentTypes: MutableMap<String?, String?> = HashMap<String?, String?>()

        // Extract multipart files and multipart parameters.
        val it: MutableIterator<FileItem> = fileItems.iterator()
        while (it.hasNext()) {
            val fileItem = it.next()

            if (fileItem.isFormField()) {
                var value: String? = null
                if (encoding != null) {
                    try {
                        value = fileItem.getString(encoding)
                    } catch (ex: UnsupportedEncodingException) {
                        LOGGER.warn(
                            "Could not decode multipart item '{}' with encoding '{}': using platform default",
                            fileItem.getFieldName(), encoding
                        )
                        value = fileItem.getString()
                    }
                } else {
                    value = fileItem.getString()
                }
                val curParam = multipartParameters.get(fileItem.getFieldName())
                if (curParam == null) {
                    // simple form field
                    multipartParameters.put(fileItem.getFieldName(), arrayOf<String?>(value))
                } else {
                    // array of simple form fields
                    val newParam = StringUtils.addStringToArray(curParam, value)
                    multipartParameters.put(fileItem.getFieldName(), newParam)
                }

                //contentType 입력
                mpParamContentTypes.put(fileItem.getFieldName(), fileItem.getContentType())
            } else {
                val file = createMultipartFile(fileItem)
                multipartFiles.add(file.getName(), file)

                LOGGER.debug(
                    ("Found multipart file [{" + file.getName() + "}] of size {" + file.getSize()
                            + "} bytes with original filename [{" + file.getOriginalFilename() + "}], stored {"
                            + file.getStorageDescription() + "}")
                )

                val fileName = file.getOriginalFilename()
                val fileExtension = getFileExtension(fileName)
                LOGGER.debug("Found File Extension = " + fileExtension)
                if (whiteListFileUploadExtensions == null || "" == whiteListFileUploadExtensions) {
                    LOGGER.debug("The file extension whitelist has not been set.")
                } else {
                    if (fileName == null || "" == fileName) {
                        LOGGER.debug("No file name.")
                    } else {
                        if ("" == fileExtension) { // 확장자 없는 경우 처리 불가
                            throw SecurityException("[No file extension] File extension not allowed.")
                        }
                        if ((whiteListFileUploadExtensions + ".").contains("." + fileExtension.lowercase(Locale.getDefault()) + ".")) {
                            LOGGER.debug("File extension allowed.")
                        } else {
                            throw SecurityException("[" + fileExtension + "] File extension not allowed.")
                        }
                    }
                }
            }
        }

        return MultipartParsingResult(
            multipartFiles,
            multipartParameters,
            mpParamContentTypes
        ) //2022.01. Method call passes null for non-null parameter 처리
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovMultipartResolver::class.java)
    }
}
