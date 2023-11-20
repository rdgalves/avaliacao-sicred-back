#!/bin/bash

echo "Esperando o Kafka Manager iniciar..."
while ! nc -z localhost 9000; do
  sleep 1
done
echo "Kafka Manager iniciado."

# Adiciona o cluster Kafka ao Kafka Manager
echo "Adicionando cluster ao Kafka Manager."
curl -X POST "http://localhost:9000/clusters" -d '{
  "name": "KAFKA-SICRED",
  "zkHosts": "zookeeper:2181",
  "kafkaVersion": "2.4.0",
  "jmxEnabled": "false",
  "jmxUser": "",
  "jmxPass": "",
  "pollConsumers": "false",
  "tuning": {
    "brokerViewUpdatePeriodSeconds": "30",
    "clusterManagerThreadPoolSize": "2",
    "clusterManagerThreadPoolQueueSize": "100",
    "kafkaCommandThreadPoolSize": "2",
    "kafkaCommandThreadPoolQueueSize": "100",
    "logkafkaCommandThreadPoolSize": "2",
    "logkafkaCommandThreadPoolQueueSize": "100",
    "logkafkaUpdatePeriodSeconds": "30",
    "deleteTopicEnable": "true",
    "disableCache": "false",
    "displaySizeEnabled": "true",
    "offsetCacheTimeoutMs": "5000"
  },
  "securityProtocol": "PLAINTEXT",
  "saslMechanism": "",
  "jaasConfig": ""
}' -H "Content-Type: application/json"

echo "Cluster adicionado ao Kafka Manager."
