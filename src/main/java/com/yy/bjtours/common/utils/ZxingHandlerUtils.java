package com.yy.bjtours.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ZxingHandlerUtils
 *
 * @author lufl
 * @date 2017/2/14
 */
public final class ZxingHandlerUtils {
    /**
     * 生成二维码BufferedImage图片
     *
     * @param contents 二维码图片内容
     * @param desc     图片描述信息
     * @param width    二维码宽度
     * @param height   二维码高度
     * @return
     * @throws WriterException
     */
    public static BufferedImage createCodeImage(final String contents, final String desc, int width, int height)
            throws WriterException {
        //生成二维码图片
        BufferedImage image = createCodeImage(contents, width - 190, height - 190);
        if (StringUtils.isNotEmpty(desc)) {
            //新的图片，把二维码下面加上文字
            BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = outImage.createGraphics();
            graphics.setBackground(Color.WHITE);
            graphics.clearRect(0, 0, width, height);
            //画二维码到新的面板
            graphics.drawImage(image, 95, 155, image.getWidth(), image.getHeight(), null);
            //设置颜色、字体、字形、字号
            graphics.setColor(new Color(128, 128, 128));
            graphics.setFont(new Font("Arial", Font.PLAIN, 68));
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //获取备注信息长度
            int strWidth = graphics.getFontMetrics().stringWidth(desc);
            //TODO 暂时未考虑备注信息超长问题
            graphics.drawString(desc, width / 2 - strWidth / 2, 102); //画文字
            graphics.dispose();
            outImage.flush();
            image = outImage;
        }
        return image;
    }

    /**
     * 生成二维码BufferedImage图片
     *
     * @param contents 二维码内容
     * @param width    二维码宽度
     * @param height   二维码高度
     * @return
     * @throws WriterException
     */
    private static BufferedImage createCodeImage(final String contents, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hints = getDecodeHintType();
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix matrix = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    /**
     * 设置二维码参数格式
     *
     * @return
     */
    private static Map<EncodeHintType, Object> getDecodeHintType() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        //设置QR二维码容错级别(H为最高30%)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //设置边框宽度
        hints.put(EncodeHintType.MARGIN, 0);
        return hints;
    }

    /**
     *  设置图像分辨率（dpi）
     * @param image
     * @param dpi
     * @param outputStream
     * @throws IOException
     */
    public static void process(BufferedImage image, int dpi, OutputStream outputStream) throws IOException {
        for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName("png"); iw.hasNext(); ) {
            ImageWriter writer = iw.next();
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
            IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
            if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
                continue;
            }
            ImageOutputStream stream = null;
            try {
                setDPI(metadata, dpi);
                stream = ImageIO.createImageOutputStream(outputStream);
                writer.setOutput(stream);
                writer.write(metadata, new IIOImage(image, null, metadata), writeParam);
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        }
    }

    private static void setDPI(IIOMetadata metadata, int dpi) throws IIOInvalidTreeException {
        // for PMG, it's dots per millimeter
        double dotsPerMilli = 1.0 * dpi / 10 / 2.54d;
        IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
        horiz.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
        vert.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode dim = new IIOMetadataNode("Dimension");
        dim.appendChild(horiz);
        dim.appendChild(vert);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
        root.appendChild(dim);

        metadata.mergeTree("javax_imageio_1.0", root);
    }

    public static void main(String[] args) {
        try {
            BufferedImage image = createCodeImage("http://172.28.66.33:8080", "006742", 272, 272);
//            BufferedImage image = createQRCodeImage("http://www.baidu.com", 300, 300);
            image.flush();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, "png", baos);

            //二维码生成的路径，但是实际项目中，我们是把这生成的二维码显示到界面上的，因此下面的折行代码可以注释掉
            //可以看到这个方法最终返回的是这个二维码的imageBase64字符串
            //前端用 <img src="data:image/png;base64,${imageBase64QRCode}"/> 其中${imageBase64QRCode}对应二维码的imageBase64字符串
            ImageIO.write(image, "png", new File("C:/Users/lufl/Desktop/TDC-" + new Date().getTime() + "test.png")); //TODO
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
