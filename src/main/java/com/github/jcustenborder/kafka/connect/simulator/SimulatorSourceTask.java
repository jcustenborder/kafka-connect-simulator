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
import com.google.common.util.concurrent.RateLimiter;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.apache.kafka.connect.data.Date;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class SimulatorSourceTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(SimulatorSourceTask.class);
  static final TimeZone UTC = TimeZone.getTimeZone("UTC");
  SimulatorSourceConnectorConfig config;

  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }

  Fairy fairy;
  RateLimiter rateLimiter;

  Schema keySchema;
  List<StructSetter> keySetters;
  Schema valueSchema;
  List<StructSetter> valueSetters;


  void configureField(final String field, SchemaBuilder builder, List<StructSetter> setters) {
    SchemaBuilder fieldSchemaBuilder;

    switch (field) {
      case "nationalIdentificationNumber":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The national identification number for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.nationalIdentificationNumber());
          }
        });
        break;
      case "firstName":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The first name for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.firstName());
          }
        });
        break;
      case "middleName":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The middle name for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.middleName());
          }
        });
        break;
      case "lastName":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The last name for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.lastName());
          }
        });
        break;
      case "email":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The email for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.email());
          }
        });
        break;
      case "username":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The username for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.username());
          }
        });
        break;
      case "password":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The password for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.password());
          }
        });
        break;
      case "fullName":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The full name for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.fullName());
          }
        });
        break;
      case "isMale":
        fieldSchemaBuilder = SchemaBuilder.bool();
        fieldSchemaBuilder.doc("Flag to specify if the person is male.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.isMale());
          }
        });
        break;
      case "isFemale":
        fieldSchemaBuilder = SchemaBuilder.bool();
        fieldSchemaBuilder.doc("Flag to specify if the person is female.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.isFemale());
          }
        });
        break;
      case "sex":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("Sex of the person. Male or Female.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.sex().toString());
          }
        });
        break;
      case "telephoneNumber":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The telephone number for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.telephoneNumber());
          }
        });
        break;
      case "dateOfBirth":
        fieldSchemaBuilder = Date.builder();
        fieldSchemaBuilder.doc("The date of birth for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {

            Calendar calendar = Calendar.getInstance(UTC);
            java.util.Date value = person.dateOfBirth().toDate();
            calendar.setTime(value);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            struct.put(field, calendar.getTime());
          }
        });
        break;
      case "age":
        fieldSchemaBuilder = SchemaBuilder.int32();
        fieldSchemaBuilder.doc("The age in years of the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.age());
          }
        });
        break;
      case "nationalIdentityCardNumber":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The national identity card number for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.nationalIdentityCardNumber());
          }
        });
        break;
      case "companyEmail":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The company email for the person.");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.companyEmail());
          }
        });
        break;
      case "addressPostalCode":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The postal code for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getAddress().getPostalCode());
          }
        });
        break;
      case "addressCity":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The city for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getAddress().getCity());
          }
        });
        break;
      case "addressStreet":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The street for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getAddress().street());
          }
        });
        break;
      case "addressStreetNumber":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The street number for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getAddress().streetNumber());
          }
        });
        break;
      case "addressApartmentNumber":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The apartment number for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getAddress().apartmentNumber());
          }
        });
        break;
      case "companyUrl":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The company url for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getCompany().url());
          }
        });
        break;
      case "companyName":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The company name for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getCompany().name());
          }
        });
        break;
      case "companyDomain":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The company domain for the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getCompany().domain());
          }
        });
        break;
      case "companyVATIdentificationNumber":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("The VAT identification number for the company of the person.");

        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.getCompany().vatIdentificationNumber());
          }
        });
        break;
      case "passportNumber":
        fieldSchemaBuilder = SchemaBuilder.string();
        fieldSchemaBuilder.doc("Passport number for the person");
        setters.add(new StructSetter() {
          @Override
          public void set(Struct struct, Person person) {
            struct.put(field, person.passportNumber());
          }
        });
        break;
      default:
        throw new UnsupportedOperationException(
            String.format("\"%s\" is not a supported field", field)
        );
    }

    Schema fieldSchema = fieldSchemaBuilder.optional().build();
    builder.field(field, fieldSchema);
  }


  @Override
  public void start(Map<String, String> map) {
    this.config = new SimulatorSourceConnectorConfig(map);
    this.rateLimiter = RateLimiter.create(this.config.rateLimit);
    this.fairy = Fairy.create();

    this.keySetters = new ArrayList<>();
    SchemaBuilder keySchemaBuilder = SchemaBuilder.struct();
    keySchemaBuilder.name(this.config.keySchemaName);
    for (String field : this.config.keyFields) {
      configureField(field, keySchemaBuilder, keySetters);
    }
    this.keySchema = keySchemaBuilder.build();

    this.valueSetters = new ArrayList<>();
    SchemaBuilder valueSchemaBuilder = SchemaBuilder.struct();
    valueSchemaBuilder.name(this.config.valueSchemaName);
    for (String field : this.config.valueFields) {
      configureField(field, valueSchemaBuilder, valueSetters);
    }
    this.valueSchema = valueSchemaBuilder.build();

  }

  Map<String, ?> sourcePartition = new HashMap<>();
  Map<String, ?> sourceOffset = new HashMap<>();

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
    List<SourceRecord> records = new ArrayList<>(this.config.batchSize);

    for (int i = 0; i < this.config.batchSize; i++) {
      Person person = this.fairy.person();
      Struct keyStruct = new Struct(this.keySchema);
      Struct valueStruct = new Struct(this.valueSchema);

      for (StructSetter setter : this.keySetters) {
        setter.set(keyStruct, person);
      }
      for (StructSetter setter : this.valueSetters) {
        setter.set(valueStruct, person);
      }

      SourceRecord record = new SourceRecord(sourcePartition, sourceOffset, this.config.topic, this.keySchema, keyStruct, this.valueSchema, valueStruct);
      records.add(record);
      this.rateLimiter.acquire();
    }

    return records;
  }


  @Override
  public void stop() {

  }
}