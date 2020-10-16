from kafka import KafkaClient,SimpleProducer,SimpleConsumer
kafka = KafkaClient("localhost:9092")
producer = SimpleProducer(kafka)
producer.send_messages("first",b"Hello Python!")
producer.send_messages("first",b"Hello Python!",b"Bihell")
producer = SimpleProducer(kafka,async=True)
producer.send_messages("first",b"Hello Python!",b"Bihell")
producer = SimpleProducer(kafka,req_acks=SimpleProducer.ACK_AFTER_LOCAL_WRITE)
producer.send_messages("first",b"Hello Python!",b"Bihell")
consumer = SimpleConsumer(kafka,"test","v1")
for msg in consumer:
    print(msg)
kafka.close()