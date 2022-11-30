#!/bin/sh

for i in $(seq $1); do
    curl -s "http://localhost:8080/generate?num=1" > /dev/null
done
