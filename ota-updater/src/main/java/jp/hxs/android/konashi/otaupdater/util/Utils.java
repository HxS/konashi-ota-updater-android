package jp.hxs.android.konashi.otaupdater.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.zip.CRC32;

import rx.Observable;

/**
 * Created by izumin on 5/4/2016 AD.
 */
public final class Utils {
    private Utils() {
        throw new AssertionError("constructor of the utility class should not be called");
    }

    public static int bytes2int(byte[] bytes) {
        int value = 0;
        for (byte b : bytes) {
            value = (value << 8) | (b & 0xff);
        }
        return value;
    }

    public static byte[] int2bytes(int a) {
        int arraySize = Integer.SIZE / Byte.SIZE;
        ByteBuffer buffer = ByteBuffer.allocate(arraySize);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.putInt(a).array();
    }

    public static byte[] list2array(List<Byte> byteList) {
        final byte[] bytes = new byte[byteList.size()];
        Observable.range(0, byteList.size()).subscribe(i -> bytes[i] = byteList.get(i));
        return bytes;
    }

    public static byte[] crc32(byte[] data) {
        final CRC32 crc32 = new CRC32();
        crc32.update(data);
        return int2bytes((int) crc32.getValue());
    }
}