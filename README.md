# Introduction

This connector is a thin wrapper around [JFairy](https://github.com/Codearte/jfairy) which is a great library for 
generating test data. It will generate data at the rate limit specified in the config.

# Configuration

## SimulatorSinkConnector

```properties
name=connector1
tasks.max=1
connector.class=com.github.jcustenborder.kafka.connect.simulator.SimulatorSinkConnector

# Set these required values
```

| Name        | Description                   | Type    | Default | Valid Values | Importance |
|-------------|-------------------------------|---------|---------|--------------|------------|
| log.entries | Flag to determine if          | boolean | false   |              | medium     |
| rate.limit  | Rate to write items to kafka. | double  | 100.0   |              | medium     |

## SimulatorSourceConnector

```properties
name=connector1
tasks.max=1
connector.class=com.github.jcustenborder.kafka.connect.simulator.SimulatorSourceConnector

# Set these required values
value.schema.fields=
key.schema.fields=
topic=
```

| Name                | Description                                | Type   | Default                                                    | Valid Values | Importance |
|---------------------|--------------------------------------------|--------|------------------------------------------------------------|--------------|------------|
| key.schema.fields   | Fields for the key schema.                 | list   |                                                            |              | high       |
| topic               | Kafka Topic to write to.                   | string |                                                            |              | high       |
| value.schema.fields | Fields for the value schema.               | list   |                                                            |              | high       |
| batch.size          | Number of records to be written per batch. | int    | 100                                                        | [1,...]      | medium     |
| key.schema.name     | Name for the key schema.                   | string | com.github.jcustenborder.kafka.connect.simulator.PersonKey |              | medium     |
| rate.limit          | Rate to write items to kafka.              | double | 100.0                                                      |              | medium     |
| value.schema.name   | Name for the value schema.                 | string | com.github.jcustenborder.kafka.connect.simulator.Person    |              | medium     |

### Fields

| Name                            | Description                                                  |
|---------------------------------|--------------------------------------------------------------|
| nationalIdentificationNumber    | The national identification number for the person.           |
| firstName                       | The first name for the person.                               |
| middleName                      | The middle name for the person.                              |
| lastName                        | The last name for the person.                                |
| email                           | The email for the person.                                    |
| username                        | The username for the person.                                 |
| password                        | The password for the person.                                 |
| fullName                        | The full name for the person.                                |
| isMale                          | Flag to specify if the person is male.                       |
| isFemale                        | Flag to specify if the person is female.                     |
| sex                             | Sex of the person. Male or Female.                           |
| telephoneNumber                 | The telephone number for the person.                         |
| dateOfBirth                     | The date of birth for the person.                            |
| age                             | The age in years of the person.                              |
| nationalIdentityCardNumber      | The national identity card number for the person.            |
| companyEmail                    | The company email for the person.                            |
| addressPostalCode               | The postal code for the person.                              |
| addressCity                     | The city for the person.                                     |
| addressStreet                   | The street for the person.                                   |
| addressStreetNumber             | The street number for the person.                            |
| addressApartmentNumber          | The apartment number for the person.                         |
| companyUrl                      | The company url for the person.                              |
| companyName                     | The company name for the person.                             |
| companyDomain                   | The company domain for the person.                           |
| companyVATIdentificationNumber  | The VAT identification number for the company of the person. |
| passportNumber                  | Passport number for the person                               |

# Running in development

```bash
./bin/debug.sh
```

To suspend until the debugger connects.

```bash
export SUSPEND='y'
./bin/debug.sh
```