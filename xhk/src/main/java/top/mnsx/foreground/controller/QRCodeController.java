package top.mnsx.foreground.controller;

import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.foreground.utils.QRCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 二维码的展示功能Controller
 */
@CrossOrigin
@RestController
public class QRCodeController {
    /**
     * 被发送请求后，响应一个二维码图片，关于删除对应优惠券，扣除金额的功能
     * @param resp
     * @param userId
     * @param discountId
     * @throws IOException
     * @throws WriterException
     */
    @RequestMapping("/qrcode/{userId}/{discountId}")
    public void test(HttpServletResponse resp, @PathVariable("userId")Integer userId, @PathVariable("discountId")Integer discountId) throws IOException, WriterException {
//        BufferedImage code = QRCodeUtil.getQRCode("http://localhost/xhk/user_discount/d/" + userId + "/" + discountId);
        BufferedImage code = QRCodeUtil.getQRCode("http://mnsx.top/xhk/user_discount/d/" + userId + "/" + discountId);
        ImageIO.write(code, "JPEG", resp.getOutputStream());
    }
}
