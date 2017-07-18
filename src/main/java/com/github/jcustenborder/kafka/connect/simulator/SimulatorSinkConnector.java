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

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.github.jcustenborder.kafka.connect.utils.config.Description;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Description("The SimulatorSinkConnector is used to read data from a topic at the configured rate.")
public class SimulatorSinkConnector extends SinkConnector {
  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }

  SimulatorSinkConnectorConfig config;
  Map<String, String> settings;

  @Override
  public void start(Map<String, String> settings) {
    this.settings = settings;
    this.config = new SimulatorSinkConnectorConfig(settings);
  }

  @Override
  public Class<? extends Task> taskClass() {
    return SimulatorSinkTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int taskCount) {
    List<Map<String, String>> configs = new ArrayList<>();

    for (int i = 0; i < taskCount; i++) {
      configs.add(this.settings);
    }

    return configs;
  }

  @Override
  public void stop() {

  }

  @Override
  public ConfigDef config() {
    return SimulatorSinkConnectorConfig.conf();
  }
}
