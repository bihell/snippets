#!/bin/sh
#使用方法
# 1：执行运行 ./test.task.sh 输入 1、2、3
# 2：执行运行 ./test.task.sh start|stop|restart


BIZ_NAME="test_demo_task"                        # 业务名称（英文、数字、下划线）
JAR_NAME="demo-0.0.1-SNAPSHOT.jar"                        # exec jar 文件路径
JAR_ARGS="export"                        # jar包启动参数
PID_PATH="/var/srv/test/"                        # PID 路径


start(){


    if [ ! -d $PID_PATH ]
    then
    mkdir -p $PID_PATH
    chmod 777 -R $PID_PATH
    fi

    JAVA_OPTS="-Xmx512M -Xms64M -Xmn32M -Xss256K "
    echo "ˇstart service ..."
    nohup java -jar "$(cd `dirname $0`; pwd)/$JAR_NAME" $JAR_ARGS  $JAVA_OPTS &
    echo $!>"${PID_PATH}_${BIZ_NAME}.pid"
    echo "ˇstart service success ..."
}

stop(){
    echo "stop service ..."
    kill `cat "${PID_PATH}_${BIZ_NAME}.pid"`
    rm -rf "${PID_PATH}_${BIZ_NAME}.pid"
    echo "stop service end ..."
}

restart(){
    stop
    sleep 3
    start
}

if [ -n "$1" ]
then

case "$1" in
        "start")
        start
        ;;
        "stop")
        stop
        ;;
        "restart")
        restart
        ;;
        *)
        echo 'You do not input a run param'
        ;;
    esac

else

    if read -n 1 -t 10 -p "User Command: {start:1|stop:2|restart:3}" input
    then

    case $input in
        1)
        start
        ;;
        2)
        stop
        ;;
        3)
        restart
        stop
        ;;
        *)
        echo 'You do not select a number between 1 to 3'
        ;;
    esac

    else

    echo "timeout ,please retry ..."

    fi

fi

exit 0