/**
 * Copyright © 2016 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.kafka.connect.simulator;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;


public class SimulatorSinkConnectorConfig extends AbstractConfig {
  public static final String LOG_ENTRIES_CONFIG = "log.entries";
  public static final String LOG_ENTRIES_DOC = "Flag to determine if ";

  public static final String RATE_LIMIT_CONFIG = "rate.limit";
  private static final String RATE_LIMIT_DOC = "Rate to write items to kafka.";

  public boolean logEntries;
  public final double rateLimit;

  public SimulatorSinkConnectorConfig(Map<String, String> parsedConfig) {
    super(conf(), parsedConfig);
    this.rateLimit = getDouble(RATE_LIMIT_CONFIG);
    this.logEntries = this.getBoolean(LOG_ENTRIES_CONFIG);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(RATE_LIMIT_CONFIG, Type.DOUBLE, 100D, Importance.MEDIUM, RATE_LIMIT_DOC)
        .define(LOG_ENTRIES_CONFIG, Type.BOOLEAN, false, Importance.MEDIUM, LOG_ENTRIES_DOC);
  }
}
