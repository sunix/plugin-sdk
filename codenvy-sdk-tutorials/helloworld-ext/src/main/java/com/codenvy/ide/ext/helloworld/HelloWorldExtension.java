/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.ext.helloworld;

/**
 * Codenvy API imports. In this extension we'll need
 * to talk to Parts and Action API. Gin and Singleton
 * imports are obligatory as well for any extension
 */

import com.codenvy.ide.api.action.ActionManager;
import com.codenvy.ide.api.constraints.Constraints;
import com.codenvy.ide.api.action.DefaultActionGroup;
import com.codenvy.ide.api.action.IdeActions;
import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.notification.Notification;
import com.codenvy.ide.api.notification.Notification.Type;
import com.codenvy.ide.api.notification.NotificationManager;
import com.codenvy.ide.ext.helloworld.action.HelloWorldAction;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @Singleton is required in case the instance is triggered several times this extension will be initialized several times as well.
 * @Extension lets us know this is an extension and code injected in it will be executed when launched
 */
@Singleton
@Extension(title = "Hello world", version = "1.0.0")
public class HelloWorldExtension

/**
 * All menu items are actions. Here we register a new action in actionManager - HelloWorldID. Then, we get it in the DefaultActionGroup
 * which is general class for all items on the toolbar, context menus etc.
 */
{
    @Inject
    public HelloWorldExtension(ActionManager actionManager, HelloWorldAction action, NotificationManager notificationManager) {
        actionManager.registerAction("HelloWorldID", action);

        DefaultActionGroup contextMenu = (DefaultActionGroup)actionManager.getAction(IdeActions.GROUP_MAIN_CONTEXT_MENU);

        /**
         * Finally, this action is added to a menu
         */
        contextMenu.add(action, Constraints.LAST);


        Notification notification = new Notification("Hello World", Type.INFO);
        notificationManager.showNotification(notification);

    }
}
