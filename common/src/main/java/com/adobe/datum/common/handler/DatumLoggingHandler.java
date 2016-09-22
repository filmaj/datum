/*******************************************************************************
 * Copyright 2016 Adobe Systems Incorporated.
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * <p>
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.adobe.datum.common.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.String;

/**
 * @author Adobe Systems Inc.
 */
@ChannelHandler.Sharable
public class DatumLoggingHandler extends LoggingHandler {

  private static final Logger LOG = LoggerFactory.getLogger(DatumLoggingHandler.class);

  private static final String LOG_LEVEL_PROPERTY     = "datum.logLevel";
  private static final String DEFAULT_LOG_LEVEL_NAME = "DEBUG";

  public DatumLoggingHandler() {
    this(getLogLevel());
  }

  public DatumLoggingHandler(LogLevel logLevel) {
    super(logLevel);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext context) throws Exception {
    LOG.info("channel registered: {}", context.channel());
    context.fireChannelRegistered();
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext context) throws Exception {
    LOG.info("channel unregistered: {}", context.channel());
    context.fireChannelUnregistered();
  }

  private static LogLevel getLogLevel() {
    return LogLevel.valueOf(System.getProperty(LOG_LEVEL_PROPERTY, DEFAULT_LOG_LEVEL_NAME));
  }
}