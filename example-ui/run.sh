#!/bin/sh
JAR_NAME=example-ui-1.0.0.100.jar
start() {
    echo "start process...";
    rm -rf ./logs
    nohup java -Xmx128m -Xms128m \
    -jar ${JAR_NAME} \
    --spring.profiles.active=test \
    --eureka.instance.ip-address=192.168.223.129 \
    >/dev/null 2>&1 &
}


stop() {    
    while true
    do
        process=`ps aux | grep ${JAR_NAME} | grep -v grep`;
        if [ "$process" = "" ]; then
            echo "no process";
            break;
        else
            echo "kill process...";
            ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}' | xargs kill -15
            sleep 3
        fi
    done    

}

restart() {
    stop;
    start;
}

status() {
    ps -ef | grep ${JAR_NAME}
}

case "$1" in
    'start')
        start
        ;;
    'stop')
        stop
        ;;
    'status')
        status
        ;;
    'restart')
        restart
        ;;
    *)
    echo "usage: $0 {start|stop|restart|status}"
    exit 1
        ;;
    esac
