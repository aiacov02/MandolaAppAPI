package com.mandola.app;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;


public class DbBitmapUtility {


    public static byte[] decodeBase64(String input)
    {
        byte[] decodedByte = Base64.decode(input);
        return decodedByte;
    }

    public static BufferedImage createRGBImage(byte[] bytes, int width, int height) {
        DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
        ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8}, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, Raster.createInterleavedRaster(buffer, width, height, width * 3, 3, new int[]{0, 1, 2}, null), false, null);
    }
}