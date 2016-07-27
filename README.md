# Mood Predictor

##Introduction
It's a platform built to analyze the mood of users based on their conversation with other people.

##Technologies
1. JMS  
2. Kafka
3. Spark
4. Hbase

##How does it work?
An application is built with JMS to facilitate chatting service. These messages are sent to kafka, streamed to Spark, which analyzes the messages and categorise into various moods. The analyzed information is then ingested into Hbase. Now users can query Hbase to know the mood of other users. 

##Note
The applciation is under development.
