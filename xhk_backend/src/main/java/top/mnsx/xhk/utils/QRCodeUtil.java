package top.mnsx.xhk.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

/**
 * 二维码生成
 */
public class QRCodeUtil {
    //编码格式,采用utf-8
    private static final String UNICODE = "utf-8";
    //图片格式
    private static final String FORMAT = "JPG";
    //二维码宽度,单位：像素pixels
    private static final int QRCODE_WIDTH = 100;
    //二维码高度,单位：像素pixels
    private static final int QRCODE_HEIGHT = 100;

    /**
     * 只需要提供文本信息，尺寸需要在函数内修改
     * @param content
     * @return
     * @throws WriterException
     */
    public static BufferedImage getQRCode(String content) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, UNICODE);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }
}
