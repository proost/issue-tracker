#! /bin/bash
# wait for mysql and redis

set -e

echo "db host: $DB_HOST_NAME"
echo "db port: $DB_PORT"
echo "redis host: $REDIS_HOST_NAME"
echo "redis port: $REDIS_PORT"

dockerize -wait tcp://"$DB_HOST_NAME":"$DB_PORT" -wait tcp://"$REDIS_HOST_NAME":"$REDIS_PORT" -timeout 30s # Check mysql

PONG=$(redis-cli -h "$REDIS_HOST_NAME" -p "$REDIS_PORT" ping | grep PONG)
echo "redis connection: $PONG"
while [ -z "$PONG" ]; do
    sleep 1
    PONG=$(redis-cli -h "$REDIS_HOST_NAME" -p "$REDIS_PORT" ping | grep PONG)
    echo "redis connection: $PONG"
done

java -Dspring.profiles.active="$PROFILE" -jar /app/issue-tracker.jar
