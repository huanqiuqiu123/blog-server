package com.huanqiu.blog.util;

import java.util.Random;

public class SnowFlakeUtil {

    // 起始时间戳
    private final static long START_STAMP = 1480166465631L;

    // 每部分的位数
    private final static long SEQUENCE_BIT = 12; // 序列号占用位数
    private final static long MACHINE_BIT = 5; // 机器id占用位数
    private final static long DATACENTER_BIT = 5; // 机房id占用位数

    // 每部分最大值
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    // 每部分向左的位移
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private static long datacenterId; // 机房id
    private static long machineId; // 机器id
    private static long sequence = 0L; // 序列号
    private static long lastStamp = -1L; // 上次的时间戳

    private SnowFlakeUtil() {
        datacenterId = getDataCenterId();
        machineId = getWorkId();
    }

    // 产生下一个ID
    public static synchronized long getNextId() {
        long currentStamp = getNewStamp();
        if (currentStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.Refusing to generate id");
        }
        if (currentStamp == lastStamp) {
            // 若在相同毫秒内 序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已达到最大
            if (sequence == 0L) {
                currentStamp = getNextMill();
            }
        } else {
            // 若在不同毫秒内 则序列号置为0
            sequence = 0L;
        }
        lastStamp = currentStamp;

        return (currentStamp - START_STAMP) << TIMESTAMP_LEFT // 时间戳部分
                | datacenterId << DATACENTER_LEFT // 机房id部分
                | machineId << MACHINE_LEFT // 机器id部分
                | sequence; // 序列号部分
    }

    private static Long getWorkId() {
        return new Random().nextLong();
    }

    private static Long getDataCenterId() {
        return new Random().nextLong();
    }

    // 获取新的毫秒数
    private static long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    // 获取当前的毫秒数
    private static long getNewStamp() {
        return System.currentTimeMillis();
    }
}
