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

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulatorSourceConnector extends SourceConnector {
  private static Logger log = LoggerFactory.getLogger(SimulatorSourceConnector.class);
  private SimulatorSourceConnectorConfig config;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  Map<String, String> settings;

  @Override
  public void start(Map<String, String> map) {
    this.settings = map;
    config = new SimulatorSourceConnectorConfig(map);
  }

  @Override
  public Class<? extends Task> taskClass() {
    return SimulatorSourceTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int count) {
    List<Map<String, String>> configs = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      configs.add(this.settings);
    }
    return configs;
  }

  @Override
  public void stop() {
  }

  @Override
  public ConfigDef config() {
    return SimulatorSourceConnectorConfig.conf();
  }
}
