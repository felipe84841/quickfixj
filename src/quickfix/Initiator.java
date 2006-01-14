/****************************************************************************
 ** Copyright (c) 2001-2005 quickfixengine.org  All rights reserved.
 **
 ** This file is part of the QuickFIX FIX Engine
 **
 ** This file may be distributed under the terms of the quickfixengine.org
 ** license as defined by quickfixengine.org and appearing in the file
 ** LICENSE included in the packaging of this file.
 **
 ** This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
 ** WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 **
 ** See http://www.quickfixengine.org/LICENSE for licensing information.
 **
 ** Contact ask@quickfixengine.org if any conditions of this licensing are
 ** not clear to you.
 **
 ****************************************************************************/

package quickfix;

import java.util.ArrayList;

/**
 * Establishes sessions with FIX servers and manages the associated sessions.
 */
public interface Initiator {
    /**
     * Initiator setting for reconnect interval in seconds. Only valid when
     * session connection type is "initiator".
     *
     * @see quickfix.SessionFactory#SETTING_CONNECTION_TYPE
     */
    public static final String SETTING_RECONNECT_INTERVAL = "ReconnectInterval";
    /**
     * Initiator setting for connection host. Only valid when session connection
     * type is "initiator".
     *
     * @see quickfix.SessionFactory#SETTING_CONNECTION_TYPE
     */
    public static final String SETTING_SOCKET_CONNECT_HOST = "SocketConnectHost";

    /**
     * Initiator setting for connection port. Only valid when session connection
     * type is "initiator".
     *
     * @see quickfix.SessionFactory#SETTING_CONNECTION_TYPE
     */
    public static final String SETTING_SOCKET_CONNECT_PORT = "SocketConnectPort";

    /**
     * Establish connections. This method blocks until stop is called from
     * another thread.
     *
     * @throws ConfigError  Problem with configuration.
     * @throws RuntimeError Other unspecified error
     */
    void block() throws ConfigError, RuntimeError;

    /**
     * Returns the sessions managed by this initiator.
     *
     * @return the sessions associated with this initiator
     */
    ArrayList getSessions();

    /**
     * Checks the logged on status of the initiator's sessions.
     *
     * @return true is any session is logged on, false otherwise.
     */
    boolean isLoggedOn();

    /**
     * Checks the logged on status of the specified session.
     *
     * @return true is the session is logged on, false otherwise.
     */
    boolean isLoggedOn(SessionID session);

    /**
     * Processes a single message.
     *
     * @return false if stopped, true if still active.
     * @throws ConfigError  Problem with configuration.
     * @throws RuntimeError Other unspecified error
     */
    boolean poll() throws ConfigError, RuntimeError;

    /**
     * Establish sessions. Returns immediately. See implementations of this
     * interface potential threading issues.
     *
     * @throws ConfigError  Problem with configuration.
     * @throws RuntimeError Other unspecified error
     */
    void start() throws ConfigError, RuntimeError;

    /**
     * Logout existing sessions and close their connections.
     */
    void stop();

    /**
     * Stops all sessions, optionally waiting for logout completion.
     *
     * @param force don't wait for logout before disconnect.
     */
    public void stop(boolean force);
}