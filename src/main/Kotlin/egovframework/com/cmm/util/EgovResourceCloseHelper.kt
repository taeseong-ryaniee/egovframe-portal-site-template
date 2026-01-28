package egovframework.com.cmm.util

import java.io.Closeable
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.sql.*

/**
 * Utility class  to support to close resources
 * @author Vincent Han
 * @since 2014.09.18
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
object EgovResourceCloseHelper {
    /**
     * Resource close 처리.
     * @param resources
     */
    @JvmStatic
    fun close(vararg resources: Closeable?) {
        for (resource in resources) {
            if (resource != null) {
                try {
                    resource.close()
                } catch (ignore: IOException) { //KISA 보안약점 조치 (2018-10-29, 윤창원)
                    ignore("Occurred IOException to close resource is ingored!!")
                } catch (ignore: Exception) {
                    ignore("Occurred Exception to close resource is ingored!!")
                }
            }
        }
    }

    /**
     * JDBC 관련 resource 객체 close 처리
     * @param objects
     */
    fun closeDBObjects(vararg objects: Wrapper?) {
        for (`object` in objects) {
            if (`object` != null) {
                if (`object` is ResultSet) {
                    try {
                        `object`.close()
                    } catch (ignore: SQLException) { //KISA 보안약점 조치 (2018-10-29, 윤창원)
                        ignore("Occurred SQLException to close resource is ingored!!")
                    } catch (ignore: Exception) {
                        ignore("Occurred Exception to close resource is ingored!!")
                    }
                } else if (`object` is Statement) {
                    try {
                        `object`.close()
                    } catch (ignore: SQLException) { //KISA 보안약점 조치 (2018-10-29, 윤창원)
                        ignore("Occurred SQLException to close resource is ingored!!")
                    } catch (ignore: Exception) {
                        ignore("Occurred Exception to close resource is ingored!!")
                    }
                } else if (`object` is Connection) {
                    try {
                        `object`.close()
                    } catch (ignore: SQLException) {
                        ignore("Occurred SQLException to close resource is ingored!!")
                    } catch (ignore: Exception) {
                        ignore("Occurred Exception to close resource is ingored!!")
                    }
                } else {
                    throw IllegalArgumentException("Wrapper type is not found : " + `object`.toString())
                }
            }
        }
    }

    /**
     * Socket 관련 resource 객체 close 처리
     * @param objects
     */
    fun closeSocketObjects(socket: Socket?, server: ServerSocket?) {
        if (socket != null) {
            try {
                socket.shutdownOutput()
            } catch (ignore: IOException) {
                ignore("Occurred IOException to close resource is ingored!!")
            } catch (ignore: Exception) {
                ignore("Occurred Exception to shutdown ouput is ignored!!")
            }

            try {
                socket.close()
            } catch (ignore: IOException) {
                ignore("Occurred IOException to close resource is ingored!!")
            } catch (ignore: Exception) {
                ignore("Occurred Exception to close resource is ignored!!")
            }
        }

        if (server != null) {
            try {
                server.close()
            } catch (ignore: IOException) {
                ignore("Occurred IOException to close resource is ingored!!")
            } catch (ignore: Exception) {
                ignore("Occurred Exception to close resource is ignored!!")
            }
        }
    }

    /**
     * Socket 관련 resource 객체 close 처리
     *
     * @param sockets
     */
    fun closeSockets(vararg sockets: Socket?) {
        for (socket in sockets) {
            if (socket != null) {
                try {
                    socket.shutdownOutput()
                } catch (ignore: IOException) {
                    ignore("Occurred IOException to close resource is ingored!!")
                } catch (ignore: Exception) {
                    ignore("Occurred Exception to shutdown ouput is ignored!!")
                }

                try {
                    socket.close()
                } catch (ignore: IOException) {
                    ignore("Occurred IOException to close resource is ingored!!")
                } catch (ignore: Exception) {
                    ignore("Occurred Exception to close resource is ignored!!")
                }
            }
        }
    }
}