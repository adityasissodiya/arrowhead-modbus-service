# **Arrowhead Modbus Service**

A Spring Boot application that integrates Modbus TCP communication with the Eclipse Arrowhead SOA framework. The application allows reading discrete inputs and writing coils to a Modbus TCP Slave, while registering services with the Arrowhead Core Systems for dynamic orchestration and secure communication.

Also checkout: https://github.com/vanDeventer/mbaigodemo/tree/master/modboss
---

## **Table of Contents**

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Usage](#usage)
  - [Reading Discrete Inputs](#reading-discrete-inputs)
  - [Writing a Coil](#writing-a-coil)
- [Testing](#testing)
- [Logging](#logging)
- [Security](#security)
- [Integration with Arrowhead](#integration-with-arrowhead)
- [Troubleshooting](#troubleshooting)
- [License](#license)
- [Contact](#contact)

---

## **Features**

- **Modbus TCP Communication**: Read discrete inputs and write coils to a Modbus TCP Slave using the `j2mod` library.
- **Spring Boot Framework**: Leveraging Spring Boot for rapid application development.
- **Arrowhead SOA Integration**: Registers services with the Arrowhead Service Registry and interacts with the Orchestrator for dynamic service discovery.
- **Secure Communication**: Implements SSL/TLS encryption for secure data transfer.
- **RESTful APIs**: Exposes endpoints for Modbus operations via HTTP requests.
- **Configurable**: Application settings can be easily modified through property files.
- **Logging**: Detailed logging for monitoring and debugging.

---

## **Prerequisites**

- **Java Development Kit (JDK) 11 or higher**: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Apache Maven**: [Download Maven](https://maven.apache.org/download.cgi)
- **Arrowhead Core Systems**: Access to the Arrowhead Service Registry, Orchestrator, and Authorization services.
- **Modbus TCP Slave Device**: A device or simulator that supports Modbus TCP communication.
- **Git**: For cloning the repository.

---

## **Project Structure**

```
arrowhead-modbus-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.arrowheadmodbus/
│   │   │       ├── ArrowheadModbusApplication.java
│   │   │       ├── controller/
│   │   │       │   ├── ModbusReadController.java
│   │   │       │   └── ModbusWriteController.java
│   │   │       ├── service/
│   │   │       │   ├── ModbusService.java
│   │   │       │   ├── ArrowheadRegistrationService.java
│   │   │       │   └── OrchestratorService.java
│   │   │       ├── config/
│   │   │       │   └── AppConfig.java
│   │   │       ├── dto/
│   │   │       │   ├── ServiceRegistryRequestDTO.java
│   │   │       │   ├── SystemRequestDTO.java
│   │   │       │   └── ModbusRequestDTO.java
│   │   │       └── utils/
│   │   │           └── ArrowheadHttpClient.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── logback-spring.xml
│   │   │   └── arrowhead/
│   │   │       ├── certificates/
│   │   │       │   ├── keystore.p12
│   │   │       │   └── truststore.p12
│   │   │       └── security.properties
│   ├── test/
│   │   └── java/
│   │       └── com.example.arrowheadmodbus/
│   │           └── ModbusServiceTests.java
├── pom.xml
└── README.md
```

---

## **Installation**

1. **Clone the Repository**

   ```bash
   git clone https://github.com/adityasissodiya/arrowhead-modbus-service.git
   cd arrowhead-modbus-service
   ```

2. **Build the Project**

   ```bash
   mvn clean install
   ```

---

## **Configuration**

### **Modbus Settings**

Modify `src/main/resources/application.properties` to configure Modbus settings:

```properties
# Modbus settings
modbus.slave.ip=192.168.1.2
modbus.slave.port=503
modbus.unit.id=1
```

- **modbus.slave.ip**: IP address of the Modbus TCP Slave.
- **modbus.slave.port**: Port number (default is 502).
- **modbus.unit.id**: Unit ID of the Modbus device.

### **Arrowhead Core System URLs**

Configure the URLs for the Arrowhead Core Systems:

```properties
# Arrowhead Core System URLs
arrowhead.service.registry.url=https://arrowhead-service-registry:8443/serviceregistry
arrowhead.orchestrator.url=https://arrowhead-orchestrator:8443/orchestrator
arrowhead.authorization.url=https://arrowhead-authorization:8443/authorization
```

### **SSL Configuration**

Set up SSL for secure communication:

```properties
# SSL Configuration
server.ssl.key-store=classpath:arrowhead/certificates/keystore.p12
server.ssl.key-store-password=your_keystore_password
server.ssl.key-store-type=PKCS12
server.ssl.trust-store=classpath:arrowhead/certificates/truststore.p12
server.ssl.trust-store-password=your_truststore_password
server.ssl.trust-store-type=PKCS12
server.ssl.enabled=true
```

Place your `keystore.p12` and `truststore.p12` files in `src/main/resources/arrowhead/certificates/`.

---

## **Running the Application**

Start the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```

Or run the JAR file after building:

```bash
java -jar target/arrowhead-modbus-service-1.0.0.jar
```

---

## **Usage**

### **Reading Discrete Inputs**

- **Endpoint**: `/modbus/read-inputs`
- **Method**: `GET`
- **Parameters**:
  - `startAddress` (optional, default `0`): Starting address of the discrete inputs.
  - `count` (optional, default `2`): Number of inputs to read.

**Example Request**:

SSL Enabled:
```bash
curl -X GET "https://localhost:8080/modbus/read-inputs?startAddress=0&count=2" -k
```
SSL Disabled:
```bash
curl -X GET "http://localhost:8080/modbus/read-inputs?startAddress=0&count=2"
```
**Example Response**:

```json
[true, false]
```

### **Writing a Coil**

- **Endpoint**: `/modbus/write-coil`
- **Method**: `POST`
- **Request Body** (`application/json`):

  ```json
  {
    "coilAddress": 0,
    "coilValue": true
  }
  ```

**Example Request**:

SSL Enabled:
```bash
curl -X POST "https://localhost:8080/modbus/write-coil" \
     -H "Content-Type: application/json" \
     -d '{"coilAddress": 0, "coilValue": true}' -k
``` 
SSL Disabled:
```bash
curl -X POST "http://localhost:8080/modbus/write-coil" \
-H "Content-Type: application/json" \
-d '{"coilAddress": 8, "coilValue": true}'
```
**Example Response**:

```
Coil written successfully.
```

---

## **Testing**

Run unit tests using Maven:

```bash
mvn test
```

---

## **Logging**

Logging is configured using `logback-spring.xml` located in `src/main/resources/`. Logs are written to `modbus-app.log` in the project root.

---

## **Security**

- **SSL/TLS**: Secure communication is enforced using SSL/TLS.
- **Certificates**: SSL certificates are located in `src/main/resources/arrowhead/certificates/`.
- **Arrowhead Authorization**: Integrates with Arrowhead Authorization service for secure service provisioning.

---

## **Integration with Arrowhead**

The application registers its services with the Arrowhead Service Registry upon startup.

- **Service Registration**: Handled by `ArrowheadRegistrationService.java`.
- **Service Definitions**:
  - `ModbusReadService`: Exposes `/modbus/read-inputs`.
  - `ModbusWriteService`: Exposes `/modbus/write-coil`.
- **Orchestration**: Interaction with the Arrowhead Orchestrator for dynamic service discovery.

---

## **Troubleshooting**

- **Modbus Connection Issues**:
  - Ensure the Modbus TCP Slave is reachable and the IP and port are correct.
  - Check firewall settings that may block the connection.
- **SSL Handshake Failures**:
  - Verify that the keystore and truststore passwords are correct.
  - Ensure the certificates are valid and not expired.
- **Arrowhead Registration Failures**:
  - Confirm the Arrowhead Core Systems are running and accessible.
  - Check the URLs and ports configured in `application.properties`.

---

## **License**

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## **Contact**

- **Author**: Aditya Sissodiya
- **Email**: aditya.sissodiya@ltu.se
- **GitHub**: [adityasissodiya](https://github.com/adityasissodiya)

For any questions or support, feel free to reach out.

---

**Enjoy using the Arrowhead Modbus Service!**