package com.example.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 周泽
 * @date Create in 11:15 2019/6/4
 * @Description ByteBufTests
 */
@Slf4j
public class ByteBufTests {

    public static void main(String[] args) {

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("ByteBufAllocator.DEFAULT.buffer(9, 100)", buffer);

        // 写入数据 write 方法会改变写指针的位置,写完之后,写指针未到capacity的时候,ByteBuf仍然可写
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print("buffer.writeBytes(new byte[]{1, 2, 3, 4})", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写, 写完 int 类型之后，写指针增加4
        buffer.writeInt(12);
        print("writeInt(12)", buffer);

        // write 方法改变写指针, 写完之后写指针等于 capacity 的时候，buffer 不可写
        buffer.writeBytes(new byte[]{5});
        print("writeBytes(5)", buffer);

        // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变
        buffer.writeBytes(new byte[]{6});
        print("writeBytes(6)", buffer);

        // get 方法不改变读写指针
        log.info("getByte(3) return:{}", buffer.getByte(3));
        log.info("getShort(3) return:{}", buffer.getShort(3));
        log.info("getInt(3) return:{}", buffer.getInt(3));
        print("getByte()", buffer);

        // set 方法不改变读写指针
        buffer.setByte(buffer.readableBytes() + 1, 0);
        print("setByte()", buffer);

        // read 方法改变读指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        print("readBytes(" + dst.length + ")", buffer);
    }

    public static void print(String action, ByteBuf buffer) {
        log.info("after =========={}==========", action);
        log.info("当前容量-->capacity():{}", buffer.capacity());
        log.info("最大容量-->maxCapacity():{}", buffer.maxCapacity());
        log.info("读指针位置-->readerIndex():{}", buffer.readerIndex());
        log.info("可读字节-->readableBytes():{}", buffer.readableBytes());
        log.info("是否可读-->isReadable():{}", buffer.isReadable());
        log.info("写指针位置-->writerIndex():{}", buffer.writerIndex());
        log.info("可写字节-->writableBytes():{}", buffer.writableBytes());
        log.info("是否可写-->isWritable():{}", buffer.isWritable());
        log.info("最大可写字节-->maxWritableBytes():{}", buffer.maxWritableBytes());
    }
}
