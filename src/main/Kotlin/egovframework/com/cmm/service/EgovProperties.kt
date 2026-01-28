package egovframework.com.cmm.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.*
import java.util.*

//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Properties;
/**
 * Class Name : EgovProperties.java
 * Description : properties값들을 파일로부터 읽어와   Globals클래스의 정적변수로 로드시켜주는 클래스로
 * 문자열 정보 기준으로 사용할 전역변수를 시스템 재시작으로 반영할 수 있도록 한다.
 * Modification Information
 *
 * 수정일         수정자                   수정내용
 * -------    --------    ---------------------------
 * 2009.01.19    박지욱          최초 생성
 * 2011.07.20    서준식 	      Globals파일의 상대경로를 읽은 메서드 추가
 * 2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009. 01. 19
 * @version 1.0
 * @see
 */
object EgovProperties {
    private val LOGGER: Logger = LoggerFactory.getLogger(EgovProperties::class.java)

    //프로퍼티값 로드시 에러발생하면 반환되는 에러문자열
    const val ERR_CODE: String = " EXCEPTION OCCURRED"
    const val ERR_CODE_FNFE: String = " EXCEPTION(FNFE) OCCURRED"
    const val ERR_CODE_IOE: String = " EXCEPTION(IOE) OCCURRED"

    //파일구분자
    val FILE_SEPARATOR: String = System.getProperty("file.separator")

    //프로퍼티 파일의 물리적 위치
    val RELATIVE_PATH_PREFIX: String = (EgovProperties::class.java.getResource("").getPath()
            + FILE_SEPARATOR + ".." + FILE_SEPARATOR + ".." + FILE_SEPARATOR + ".." + FILE_SEPARATOR)

    val GLOBALS_PROPERTIES_FILE
            : String = RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + "globals.properties"

    /**
     * 인자로 주어진 문자열을 Key값으로 하는 상대경로 프로퍼티 값을 절대경로로 반환한다(Globals.java 전용)
     * @param keyName String
     * @return String
     *
     * public static String getPathProperty(String keyName) {
     * String value = ERR_CODE;
     * value="99";
     * debug(GLOBALS_PROPERTIES_FILE + " : " + keyName);
     * FileInputStream fis = null;
     * try {
     * Properties props = new Properties();
     * fis  = new FileInputStream(GLOBALS_PROPERTIES_FILE);
     * props.load(new java.io.BufferedInputStream(fis));
     * value = props.getProperty(keyName).trim();
     * value = RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + value;
     * } catch(FileNotFoundException fne) {
     * debug(fne);
     * } catch(IOException ioe) {
     * debug(ioe);
     * } catch(Exception e) {
     * debug(e);
     * } finally {
     * try {
     * if (fis != null) fis.close();
     * } catch (Exception ex) {
     * ex.printStackTrace();
     * }
     *
     * }
     * return value;
     * }
     */
    /**
     * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
     * @param keyName String
     * @return String
     */
    @JvmStatic
    fun getProperty(keyName: String?): String {
        var value = ERR_CODE
        value = "99"
        debug(GLOBALS_PROPERTIES_FILE + " : " + keyName)
        var fis: FileInputStream? = null
        try {
            val props = Properties()
            fis = FileInputStream(GLOBALS_PROPERTIES_FILE)
            props.load(BufferedInputStream(fis))
            value = props.getProperty(keyName).trim { it <= ' ' }
        } catch (fne: FileNotFoundException) {
            debug(fne)
        } catch (ioe: IOException) {
            debug(ioe)
        } finally {
            try {
                if (fis != null) {
                    fis.close()
                }
            } catch (ioe: IOException) {
                debug(ioe)
            }
        }
        return value
    }

    /**
     * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 상대 경로값을 절대 경로값으로 반환한다
     * @param fileName String
     * @param key String
     * @return String
     *
     * public static String getPathProperty(String fileName, String key) {
     * FileInputStream fis = null;
     * try {
     * java.util.Properties props = new java.util.Properties();
     * fis  = new FileInputStream(fileName);
     * props.load(new java.io.BufferedInputStream(fis));
     * fis.close();
     *
     * String value = props.getProperty(key);
     * value = RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + value;
     * return value;
     * } catch(java.io.FileNotFoundException fne) {
     * return ERR_CODE_FNFE;
     * } catch(java.io.IOException ioe) {
     * return ERR_CODE_IOE;
     * } finally {
     * try {
     * if (fis != null) fis.close();
     * } catch (Exception ex) {
     * ex.printStackTrace();
     * }
     * }
     * }
     */
    /**
     * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
     * @param fileName String
     * @param key String
     * @return String
     *
     * public static String getProperty(String fileName, String key) {
     * FileInputStream fis = null;
     * try {
     * java.util.Properties props = new java.util.Properties();
     * fis  = new FileInputStream(fileName);
     * props.load(new java.io.BufferedInputStream(fis));
     * fis.close();
     *
     * String value = props.getProperty(key);
     * return value;
     * } catch(java.io.FileNotFoundException fne) {
     * return ERR_CODE_FNFE;
     * } catch(java.io.IOException ioe) {
     * return ERR_CODE_IOE;
     * } finally {
     * try {
     * if (fis != null) fis.close();
     * } catch (Exception ex) {
     * ex.printStackTrace();
     * }
     * }
     * }
     */
    /**
     * 주어진 프로파일의 내용을 파싱하여 (key-value) 형태의 구조체 배열을 반환한다.
     * @param property String
     * @return ArrayList
     */
    @Suppress("unused")
    fun loadPropertyFile(property: String): ArrayList<MutableMap<String?, String?>?> {
        // key - value 형태로 된 배열 결과

        val keyList = ArrayList<MutableMap<String?, String?>?>()

        val src = property.replace("\\", FILE_SEPARATOR).replace("/", FILE_SEPARATOR)
        var fis: FileInputStream? = null
        try {
            val srcFile = File(src)
            if (srcFile.exists()) {
                val props = Properties()
                fis = FileInputStream(src)
                props.load(BufferedInputStream(fis))
                fis.close()

                val i = 0
                val plist = props.propertyNames()
                if (plist != null) {
                    while (plist.hasMoreElements()) {
                        val map: MutableMap<String?, String?> = HashMap<String?, String?>()
                        val key = plist.nextElement() as String?
                        map.put(key, props.getProperty(key))
                        keyList.add(map)
                    }
                }
            }
        } catch (ex: IOException) {
            debug("EX:" + ex)
        } finally {
            try {
                if (fis != null) fis.close()
            } catch (ex: IOException) {
                debug("EX:" + ex) //ex.printStackTrace();
            }
        }

        return keyList
    }

    /**
     * 시스템 로그를 출력한다.
     * @param obj Object
     */
    private fun debug(obj: Any?) {
        if (obj is Exception) {
            //((Exception)obj).printStackTrace();
            //System.out.println("DEBUG: " + obj);	// 2011.10.10 보안점검 후속조치
            LOGGER.debug("IGNORED: {}", obj.message)
        }
    }
}

