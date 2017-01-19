/**
 * Copyright © 2016 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

import java.util.List;
import java.util.Map;


public class SimulatorSourceConnectorConfig extends AbstractConfig {

  public static final String TOPIC_CONFIG = "topic";
  private static final String TOPIC_DOC = "Kafka Topic to write to.";

  public static final String RATE_LIMIT_CONFIG = "rate.limit";
  private static final String RATE_LIMIT_DOC = "Rate to write items to kafka.";

  public static final String BATCH_SIZE_CONFIG = "batch.size";
  private static final String BATCH_SIZE_DOC = "Number of records to be written per batch.";

  public static final String KEY_SCHEMA_NAME_CONFIG = "key.schema.name";
  static final String KEY_SCHEMA_NAME_DOC = "Name for the key schema.";

  public static final String KEY_SCHEMA_FIELDS_CONFIG = "key.schema.fields";
  static final String KEY_SCHEMA_FIELDS_DOC = "Fields for the key schema.";

  public static final String VALUE_SCHEMA_NAME_CONFIG = "value.schema.name";
  static final String VALUE_SCHEMA_NAME_DOC = "Name for the value schema.";

  public static final String VALUE_SCHEMA_FIELDS_CONFIG = "value.schema.fields";
  static final String VALUE_SCHEMA_FIELDS_DOC = "Fields for the value schema.";

  public final String topic;
  public final double rateLimit;
  public final int batchSize;
  public final String keySchemaName;
  public final List<String> keyFields;
  public final String valueSchemaName;
  public final List<String> valueFields;

  public SimulatorSourceConnectorConfig(Map<String, String> parsedConfig) {
    super(conf(), parsedConfig);
    this.topic = getString(TOPIC_CONFIG);
    this.rateLimit = getDouble(RATE_LIMIT_CONFIG);
    this.batchSize = getInt(BATCH_SIZE_CONFIG);
    this.keySchemaName = getString(KEY_SCHEMA_NAME_CONFIG);
    this.keyFields = getList(KEY_SCHEMA_FIELDS_CONFIG);
    this.valueSchemaName = getString(VALUE_SCHEMA_NAME_CONFIG);
    this.valueFields = getList(VALUE_SCHEMA_FIELDS_CONFIG);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_DOC)
        .define(RATE_LIMIT_CONFIG, Type.DOUBLE, 100D, Importance.MEDIUM, RATE_LIMIT_DOC)
        .define(BATCH_SIZE_CONFIG, Type.INT, 100, ConfigDef.Range.atLeast(1), Importance.MEDIUM, BATCH_SIZE_DOC)
        .define(KEY_SCHEMA_NAME_CONFIG, Type.STRING, "io.confluent.example.simulator.PersonKey", Importance.MEDIUM, KEY_SCHEMA_NAME_DOC)
        .define(KEY_SCHEMA_FIELDS_CONFIG, Type.LIST, Importance.HIGH, KEY_SCHEMA_FIELDS_DOC)
        .define(VALUE_SCHEMA_NAME_CONFIG, Type.STRING, "io.confluent.example.simulator.Person", Importance.MEDIUM, VALUE_SCHEMA_NAME_DOC)
        .define(VALUE_SCHEMA_FIELDS_CONFIG, Type.LIST, Importance.HIGH, VALUE_SCHEMA_FIELDS_DOC);
  }
}
